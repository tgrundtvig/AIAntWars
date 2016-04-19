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
public interface IAntWarsPlayer
{
    public IAntAI getQueenAIInstance();
    public String getTeamName();
}
