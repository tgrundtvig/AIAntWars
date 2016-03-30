/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.impl;

import aiantwars.EAction;
import aiantwars.IAntAI;
import aiantwars.IAntInfo;
import aiantwars.EAntType;
import aiantwars.graphicsinterface.IGraphicsAnt;
import aiantwars.ILocationInfo;
import aiantwars.ITeamInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Tobias Grundtvig
 */
public class LogicAnt implements IAntInfo, Comparable<LogicAnt>
{
    private final Random rnd;
    private IGraphicsAnt graphicsAnt;
    private final int id;
    private final ITeamInfo teamInfo;
    private final EAntType type;
    private final IAntAI ai;
    private final Board board;
    private final AntWarsGameCtrl factory;
    private Location location;
    private int dir;
    private boolean carriesSoil;
    private int age;
    private int hitPoints;
    private int foodLoad;
    private int actionPoints;
    private boolean isDead;

    public LogicAnt(AntWarsGameCtrl factory, Board board, Location location, int dir, int id, ITeamInfo teamInfo, EAntType type, IAntAI ai)
    {
        this.rnd = new Random();
        this.graphicsAnt = null;
        this.factory = factory;
        this.board = board;
        this.location = location;
        this.dir = dir;
        this.id = id;
        this.teamInfo = teamInfo;
        this.type = type;
        this.ai = ai;
        this.age = -type.getIncubationPeriod();
        this.hitPoints = 1;
        this.actionPoints = 0;
        this.carriesSoil = false;
        this.foodLoad = 0;
        this.isDead = false;
    }
    
    public void setGraphicsAnt(IGraphicsAnt graphicsAnt)
    {
        this.graphicsAnt = graphicsAnt;
    }

    @Override
    public int antID()
    {
        return id;
    }

    @Override
    public ITeamInfo getTeamInfo()
    {
        return teamInfo;
    }

    @Override
    public EAntType getAntType()
    {
        return type;
    }

    @Override
    public int getDirection()
    {
        return dir;
    }

    @Override
    public boolean carriesSoil()
    {
        return carriesSoil;
    }

    @Override
    public int getAge()
    {
        return age;
    }

    @Override
    public int getHitPoints()
    {
        return hitPoints;
    }

    @Override
    public int getFoodLoad()
    {
        return foodLoad;
    }

    @Override
    public int getActionPoints()
    {
        return actionPoints;
    }

    @Override
    public ILocationInfo getLocation()
    {
        return location;
    }

    public void takeAction()
    {
        if(graphicsAnt != null)
        {
            graphicsAnt.onTakeAction();
        }
        List<EAction> possibleActions = getPossibleActions();
        List<ILocationInfo> visibleLocations = getVisibleLocations();
        EAction action = ai.chooseAction(new ImmutableAntInfo(this), new ImmutableLocation(location), visibleLocations, possibleActions);
        //Test for cheating by returning an action that is not possible
        if (!canPerformAction(action))
        {
            action = EAction.Pass;
        }
        //Perform action
        int cost = type.getActionCost(action);
        actionPoints -= cost;
        switch (action)
        {
            case Pass:
                if(graphicsAnt != null)
                {
                    graphicsAnt.playPassAnimation();
                }
                break;
            case TurnLeft:
                turnLeft();
                break;
            case TurnRight:
                turnRight();
                break;
            case MoveForward:
                moveForward();
                break;
            case MoveBackward:
                moveBackward();
                break;
            case Attack:
                attack();
                break;
            case PickUpFood:
                pickUpFood();
                break;
            case DropFood:
                dropFood();
                break;
            case EatFood:
                eatFood();
                break;
            case DigOut:
                digOut();
                break;
            case DropSoil:
                dropSoil();
                break;
            case LayEgg:
                layEgg();
                break;
        }
    }

    private List<ILocationInfo> getVisibleLocations()
    {
        int viewDist = type.getViewDist();
        List<ILocationInfo> res = new ArrayList<>(viewDist);
        Location cur = location;
        for (int i = 0; i < viewDist; ++i)
        {
            cur = board.getNeighbour(cur, dir);
            if (cur == null)
            {
                break;
            }
            res.add(new ImmutableLocation(cur));
            if (cur.isFilled())
            {
                break;
            }
        }
        return res;
    }

    private boolean canPerformAction(EAction action)
    {
        int cost = type.getActionCost(action);
        if (cost < 0 || cost > actionPoints)
        {
            return false;
        }
        switch (action)
        {
            case Pass:
            case TurnLeft:
            case TurnRight:
                return true;
            case MoveForward:
                return canMoveForward();
            case MoveBackward:
                return canMoveBackward();
            case Attack:
                return canAttack();
            case PickUpFood:
                return location.getFoodCount() > 0 && foodLoad < type.getMaxFoodLoad();
            case DropFood:
            case EatFood:
                return foodLoad > 0;
            case DigOut:
                return canDigout();
            case DropSoil:
                return canDropSoil();
            case LayEgg:
                return canLayEgg();
        }
        throw new RuntimeException("LogicAnt.canPerformAction() -> Unhandled action: " + action);
    }

    private List<EAction> getPossibleActions()
    {
        ArrayList<EAction> res = new ArrayList<>(EAction.values().length);
        for (EAction action : EAction.values())
        {
            if (canPerformAction(action))
            {
                res.add(action);
            }
        }
        return res;
    }
    
    @Override
    public boolean isDead()
    {
        return isDead;
    }
    
    private void moveForward()
    {
        move(dir);
        if (graphicsAnt != null)
        {
            graphicsAnt.playMoveForwardAnimation();
        }
    }

    private void moveBackward()
    {
        move((dir + 2) % 4);
        if (graphicsAnt != null)
        {
            graphicsAnt.playMoveBackwardAnimation();
        }
    }

    private void move(int direction)
    {
        Location newLocation = board.getNeighbour(location, direction);
        if (!validMoveLocation(newLocation))
        {
            throw new RuntimeException("Invalid move!");
        }
        location.setAnt(null);
        location = newLocation;
        location.setAnt(this);
    }

    private boolean canMoveForward()
    {
        return canMove(dir);
    }

    private boolean canMoveBackward()
    {
        return canMove((dir + 2) % 4);
    }

    private boolean canMove(int direction)
    {
        Location newLocation = board.getNeighbour(location, direction);
        return validMoveLocation(newLocation);
    }

    private boolean validMoveLocation(Location loc)
    {
        return (loc != null && !loc.isFilled() && loc.getAnt() == null);
    }

    private boolean canAttack()
    {
        Location inFront = board.getNeighbour(location, dir);
        return (inFront != null && inFront.getAnt() != null);
    }

    private boolean canDigout()
    {
        Location inFront = board.getNeighbour(location, dir);
        return (inFront != null && inFront.isFilled() && !inFront.isRock() && !carriesSoil);
    }

    private boolean canDropSoil()
    {
        Location inFront = board.getNeighbour(location, dir);
        return (inFront != null && !inFront.isFilled() && inFront.getAnt() == null && carriesSoil);
    }

    private boolean canLayEgg()
    {
        Location inFront = board.getNeighbour(location, dir);
        return (foodLoad >= type.getLayEggCost()
                && inFront != null
                && !inFront.isFilled()
                && inFront.getAnt() == null);
    }
    
    public void kill()
    {
        onDeath();
    }

    private void onDeath()
    {
        ai.onDeath(new ImmutableAntInfo(this));
        isDead = true;
        //Play death-animation
        if (graphicsAnt != null)
        {
            graphicsAnt.playDeathAnimation();
        }
        //Drop food...
        int food = location.getFoodCount();
        food += foodLoad;
        location.setFoodCount(food);
        //Drop soil
        if (carriesSoil)
        {
            location.setFilled(true);
        }
        location.setAnt(null);
        factory.onDeath(this);
    }

    public void turnLeft()
    {
        dir = (dir + 3) % 4;
        if (graphicsAnt != null)
        {
            graphicsAnt.playTurnLeftAnimation();
        }
    }

    public void turnRight()
    {
        dir = (dir + 1) % 4;
        if (graphicsAnt != null)
        {
            graphicsAnt.playTurnRightAnimation();
        }
    }

    public void eatFood()
    {
        if (foodLoad <= 0)
        {
            throw new RuntimeException("No food to eat!");
        }
        --foodLoad;
        int hitPointsGained = type.getMaxHitpoints() - hitPoints;
        hitPoints = type.getMaxHitpoints();
        if (graphicsAnt != null)
        {
            graphicsAnt.playEatFoodAnimation(hitPointsGained);
        }
    }
    
    public void endOfTurn(int turn)
    {
        ++age;
        if (age > 0)
        {
            --hitPoints;
            if (graphicsAnt != null)
            {
                graphicsAnt.playOnDamage(1);
            }
            if(hitPoints <= 0)
            {
                onDeath();
            }
            else
            {
                actionPoints = type.getMaxActionPoints();
                ai.onStartTurn(new ImmutableAntInfo(this), turn + 1);
            }
        }
        else if (age == 0)
        {
            //Hatch...
            hitPoints = type.getMaxHitpoints();
            if (graphicsAnt != null)
            {
                graphicsAnt.playHatchAnimation();
            }
            actionPoints = type.getMaxActionPoints();
            ai.onHatch(new ImmutableAntInfo(this), new ImmutableLocation(location), board.getSizeX(), board.getSizeY());
            ai.onStartTurn(new ImmutableAntInfo(this), turn + 1);
        }
    }

    @Override
    public int compareTo(LogicAnt other)
    {
        return other.actionPoints - actionPoints;
    }

    private void attack()
    {
        Location locationInFront = board.getNeighbour(location, dir);
        LogicAnt antInFront = locationInFront.getAnt();
        int min = type.getMinAttack();
        int max = type.getMaxAttack();
        int damage = min + rnd.nextInt(max - min + 1);
        if (graphicsAnt != null)
        {
            graphicsAnt.playAttackAnimation(damage);
        }
        antInFront.onAttacked(damage, this, dir);
    }
    
    private void onAttacked(int damage, LogicAnt attacker, int attackDir)
    {
        if (graphicsAnt != null)
        {
            graphicsAnt.playOnDamage(damage);
        }
        ai.onAttacked(new ImmutableAntInfo(this), attackDir, new ImmutableAntInfo(attacker), damage);
        hitPoints -= damage;
        if(hitPoints <= 0)
        {
            onDeath();
        }
    }

    private void pickUpFood()
    {
        location.setFoodCount(location.getFoodCount() - 1);
        ++foodLoad;
        if (graphicsAnt != null)
        {
            graphicsAnt.playPickUpFoodAnimation();
        }
    }

    private void dropFood()
    {
        location.setFoodCount(location.getFoodCount() + 1);
        --foodLoad;
        if (graphicsAnt != null)
        {
            graphicsAnt.playDropFoodAnimation();
        }
    }

    private void digOut()
    {
        Location inFront = board.getNeighbour(location, dir);
        inFront.setFilled(false);
        carriesSoil = true;
        if (graphicsAnt != null)
        {
            graphicsAnt.playDigOutAnimation();
        }
    }

    private void dropSoil()
    {
        Location inFront = board.getNeighbour(location, dir);
        inFront.setFilled(true);
        carriesSoil = false;
        if (graphicsAnt != null)
        {
            graphicsAnt.playDropSoilAnimation();
        }
    }

    private void layEgg()
    {
        foodLoad -= type.getLayEggCost();
        AntEgg egg = new AntEgg();
        ai.onLayEgg(new ImmutableAntInfo(this), factory.getEggTypes(), egg);

        EAntType newAntType = egg.getType();
        IAntAI newAntAI = egg.getAi();
        
        if(newAntType == null || !factory.getEggTypes().contains(newAntType))
        {
            newAntType = EAntType.WARRIOR;
        }
        
        if(newAntAI == null)
        {
            newAntAI = new SimpleAI();
        }
        Location inFront = board.getNeighbour(location, dir);
        
        factory.createAnt(teamInfo, newAntType, newAntAI, inFront, dir);
    }
}
