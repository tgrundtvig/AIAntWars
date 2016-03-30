/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.impl;

import aiantwars.EAntType;
import aiantwars.IOnGameFinished;
import aiantwars.ITeamInfo;
import java.util.Map;

/**
 *
 * @author Tobias Grundtvig
 */
public class FirstTest implements IOnGameFinished
{
    public static void main(String[] args)
    {
        Board board = new Board(200, 200);
        for(int y = 0; y < 200; ++y)
        {
            for(int x = 0; x < 200; ++x)
            {
                Location loc = board.getLocation(x, y);
                loc.setFoodCount(20);
            }
        }
        AntWarsGameCtrl ctrl = new AntWarsGameCtrl(new DummyGraphicsAntWarsGUI(false), board, new FirstTest());
        TeamInfo team1 = new TeamInfo(1, "Team 1");
        TeamInfo team2 = new TeamInfo(2, "Team 2");
        TeamInfo team3 = new TeamInfo(3, "Team 3");
        TeamInfo team4 = new TeamInfo(4, "Team 4");
        ctrl.createAnt(team1, EAntType.QUEEN, new SimpleAI(), board.getLocation(0, 0), 1);
        ctrl.createAnt(team2, EAntType.QUEEN, new SimpleAI(), board.getLocation(0, 199), 1);
        ctrl.createAnt(team3, EAntType.QUEEN, new SimpleAI(), board.getLocation(199, 0), 1);
        ctrl.createAnt(team4, EAntType.QUEEN, new SimpleAI(), board.getLocation(199, 199), 1);
        ctrl.run();
        System.out.println("Goodbye!");
    }
    
    @Override
    public void onGameFinished(Map<ITeamInfo, Score> scores)
    {
        for(Map.Entry<ITeamInfo, Score> entry : scores.entrySet())
        {
            ITeamInfo team = entry.getKey();
            Score score = entry.getValue();
            System.out.println("Team: " + team.getTeamName() + " Score: " + score);
        }
    }
}
