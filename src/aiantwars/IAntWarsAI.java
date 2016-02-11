/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars;

/**
 *
 * @author Tobias Grundtvig
 */
public interface IAntWarsAI
{
    public int getTeamID();
    public String getTeamName();
    
    //Indicates that a new game is started.
    public void startNewGame(ITeamInfo[] teams, int rounds);
    
    //Returns the AI for the first queen.
    public IAntAI startRound(ITeamInfo[] teams);
    public void endRound(ITeamInfo[] teams);
    
    public void endGame();
}
