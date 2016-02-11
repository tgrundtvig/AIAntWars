/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.impl;

import aiantwars.IAntInfo;
import aiantwars.graphicsinterface.IGraphicsAnt;
import aiantwars.graphicsinterface.IGraphicsAntWarsGUI;

/**
 *
 * @author Tobias Grundtvig
 */
public class DummyGraphicsAntWarsGUI implements IGraphicsAntWarsGUI
{

    @Override
    public IGraphicsAnt createNewGraphicsAnt(IAntInfo antInfo)
    {
        return new DummyGraphicsAnt(antInfo.antID());
    }

    @Override
    public void onStartTurn(int turn)
    {
        System.out.println("\n\nTurn " + turn + "\n*********");
    }
    
}
