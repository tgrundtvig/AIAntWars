/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.impl;

import aiantwars.IAntAI;
import aiantwars.EAntType;
import aiantwars.IOnGameFinished;
import aiantwars.graphicsinterface.IGraphicsAnt;
import aiantwars.ITeamInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import aiantwars.graphicsinterface.IGraphicsAntWarsGUI;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tobias Grundtvig
 */
public class AntWarsGameCtrl implements Runnable
{

    private final IOnGameFinished onFinished;
    private final PriorityQueue<LogicAnt> queue;
    private int turn;
    private int minor;
    private boolean endOfTurn;
    private final IGraphicsAntWarsGUI graphicsAntWarsGUI;
    private final Board board;
    private final List<EAntType> eggTypes;
    private final Map<ITeamInfo, Score> scores;
    private int lastID;

    public AntWarsGameCtrl(IGraphicsAntWarsGUI graphicsAntWarsGUI, Board board, IOnGameFinished onFinished)
    {
        this.scores = new HashMap<>();
        this.graphicsAntWarsGUI = graphicsAntWarsGUI;
        this.board = board;
        this.onFinished = onFinished;
        this.lastID = 0;
        queue = new PriorityQueue<>();
        turn = 0;
        ArrayList<EAntType> tmp = new ArrayList<>();
        tmp.add(EAntType.CARRIER);
        tmp.add(EAntType.SCOUT);
        tmp.add(EAntType.WARRIOR);
        tmp.trimToSize();
        eggTypes = Collections.unmodifiableList(tmp);
    }

    public void createAnt(ITeamInfo team, EAntType type, IAntAI ai, Location location, int dir)
    {
        int id = ++lastID;
        LogicAnt newAnt = new LogicAnt(this, board, location, dir, id, team, type, ai);
        IGraphicsAnt graphicsAnt = graphicsAntWarsGUI.createNewGraphicsAnt(newAnt);
        newAnt.setGraphicsAnt(graphicsAnt);

        location.setAnt(newAnt);
        queue.add(newAnt);
    }

    public List<EAntType> getEggTypes()
    {
        return eggTypes;
    }

    public void onDeath(LogicAnt deadAnt)
    {
        queue.remove(deadAnt);
        if(deadAnt.getAntType() == EAntType.QUEEN)
        {
            if(!endOfTurn) ++minor;
            ITeamInfo deadTeam = deadAnt.getTeamInfo();
            Score score = new Score(turn, minor);
            scores.put(deadTeam, score);
            System.out.println("Team: " + deadTeam.getTeamName() + " Score: " + score);
        }
    }

    public void executeTurn()
    {
        minor = 0;
        endOfTurn = false;
        turn++;
        graphicsAntWarsGUI.onStartTurn(turn);
        while(!queue.isEmpty() && queue.peek().getActionPoints() > 0)
        {
            LogicAnt ant = queue.poll();
            if(scores.get(ant.getTeamInfo()) != null)
            {
                ant.kill();
            }
            else
            {
                ant.takeAction();
                queue.add(ant);
            }
        }
        endOfTurn = true;
        ++minor;
        // We make a temporary list of all ants
        // to avoid concurrent modifications of the queue
        List<LogicAnt> tmp = new ArrayList<>(queue);
        queue.clear();
        for(LogicAnt ant : tmp)
        {
            ant.endOfTurn(turn);
            if(!ant.isDead())
            {
                if(scores.get(ant.getTeamInfo()) != null)
                {
                    ant.kill();
                }
                else
                {
                    queue.add(ant);
                }
            }
        }
    }

    @Override
    public void run()
    {
        while(queue.size() > 0)
        {
            executeTurn();
        }
        if(onFinished != null)
        {
            onFinished.onGameFinished(scores);
        }
    }

    public Map<ITeamInfo, Score> getScores()
    {
        return scores;
    }
}
