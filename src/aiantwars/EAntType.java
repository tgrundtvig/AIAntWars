/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars;

/*
    TurnLeft,
    TurnRight,
    MoveForward,
    MoveBackward,
    Attack,
    PickUpFood,
    DropFood,
    EatFood,
    DigOut,
    DropSoil,
    LayEgg,
    Pass
*/
/**
 *
 * @author Tobias Grundtvig
 */
public enum EAntType
{
    QUEEN ("Queen", 2, 1, 4, 10, 50, 5, 5, 5, new int[] {2,2,3,4,5,2,2,5,-1,-1,5,1}),
    CARRIER ("Carrier", 2, 1, 8, 30, 100, 5, 4, -1, new int[] {2,2,3,4,5,2,2,5,5,5,-1,1}),
    SCOUT ("Scout", 4, 2, 8, 4, 75, 10, 4, -1, new int[] {2,2,3,4,5,2,2,5,-1,-1,-1,1}),
    WARRIOR ("Warrier", 2, 8, 16, 4, 200, 5, 8, -1, new int[] {2,2,3,4,5,2,2,5,-1,-1,-1,1});
    
    private final String name;
    private final int viewDist;
    private final int minAttack;
    private final int maxAttack;
    private final int maxFoodLoad;
    private final int maxHitPoints;
    private final int maxActionPoints;
    private final int incubationPeriod;
    private final int layEggCost;
    private final int[] actionCosts;
    
    private EAntType(   String name,
                        int viewDist,
                        int minAttack,
                        int maxAttack,
                        int maxFoodLoad,
                        int maxHitPoints,
                        int maxActionPoints,
                        int incubationPeriod,
                        int layEggCost,
                        int[] actionCosts)
    {
        this.name = name;
        this.viewDist = viewDist;
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
        this.maxFoodLoad = maxFoodLoad;
        this.maxHitPoints = maxHitPoints;
        this.maxActionPoints = maxActionPoints;
        this.incubationPeriod = incubationPeriod;
        this.layEggCost = layEggCost;
        this.actionCosts = actionCosts;
        if(actionCosts.length != EAction.values().length)
        {
            throw new RuntimeException("Wrong number of action costst. Number is " + actionCosts.length + " it should be " + EAction.values().length);
        }
    }
   
    public String getTypeName()
    {
        return name;
    }

    public int getViewDist()
    {
        return viewDist;
    }
   
    public int getMinAttack()
    {
        return minAttack;
    }
    
    public int getMaxAttack()
    {
        return maxAttack;
    }
    
    public int getMaxFoodLoad()
    {
        return maxFoodLoad;
    }
    
    public int getMaxHitpoints()
    {
        return maxHitPoints;
    }
    
    public int getMaxActionPoints()
    {
        return maxActionPoints;
    }
    
    public int getIncubationPeriod()
    {
        return incubationPeriod;
    }
    
    public int getLayEggCost()
    {
        return layEggCost;
    }
   
    public int getActionCost(EAction action) //a value < 0 means action not available to this type
    {
        return actionCosts[action.ordinal()];
    }
    
    @Override
    public String toString()
    {
        return name;
    }
}
