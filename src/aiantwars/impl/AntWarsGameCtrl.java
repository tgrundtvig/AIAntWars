/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.impl;

import aiantwars.IAntAI;
import aiantwars.EAntType;
import aiantwars.graphicsinterface.IGraphicsAnt;
import aiantwars.ITeamInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import aiantwars.graphicsinterface.IGraphicsAntWarsGUI;

/**
 *
 * @author Tobias Grundtvig
 */
public class AntWarsGameCtrl implements Runnable
{
    private final PriorityQueue<LogicAnt> queue;
    private int turn;
    private boolean[] teamAlive;
    private final IGraphicsAntWarsGUI graphicsAntWarsGUI;
    private final Board board;
    private final List<EAntType> eggTypes;
    private int lastID;

    public AntWarsGameCtrl(IGraphicsAntWarsGUI graphicsAntWarsGUI, Board board)
    {
        this.graphicsAntWarsGUI = graphicsAntWarsGUI;
        this.board = board;
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
    }
    
    public void executeTurn()
    {
        turn++;
        graphicsAntWarsGUI.onStartTurn(turn);
        while(!queue.isEmpty() && queue.peek().getActionPoints() > 0)
        {
            LogicAnt ant = queue.poll();
            ant.takeAction();
            if(!ant.isDead())
            {
                queue.add(ant);
            }
        }
        
        // We make a temporary list of all ants
        // to avoid concurrent modifications of the queue
        List<LogicAnt> tmp = new ArrayList<>(queue);
        queue.clear();
        for(LogicAnt ant : tmp)
        {
            ant.endOfTurn(turn);
            if(!ant.isDead()) queue.add(ant);
        }
    }

    @Override
    public void run()
    {
        while(queue.size() > 0)
        {
            executeTurn();
        }
    }
}
