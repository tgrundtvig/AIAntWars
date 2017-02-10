/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars;

import aiantwars.impl.Score;
import java.util.Map;

/**
 *
 * @author Tobias Grundtvig
 */
public interface IOnGameFinished
{
    public void onGameFinished(Map<ITeamInfo, Score> scores);
}
