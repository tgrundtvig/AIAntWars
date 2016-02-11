/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.graphicsinterface;

import aiantwars.IAntInfo;

/**
 *
 * @author Tobias Grundtvig
 */
public interface IGraphicsAntWarsGUI
{
    public void onStartTurn(int turn);
    public IGraphicsAnt createNewGraphicsAnt(IAntInfo antInfo);
}
