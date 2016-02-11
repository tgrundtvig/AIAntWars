/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.impl;

import aiantwars.EAntType;
import aiantwars.IAntAI;
import aiantwars.IEgg;

/**
 *
 * @author Tobias Grundtvig
 */
public class AntEgg implements IEgg
{
    private EAntType type;
    private IAntAI ai;

    public AntEgg()
    {
        type = null;
        ai = null;
    }
    
    @Override
    public void set(EAntType type, IAntAI ai)
    {
        this.type = type;
        this.ai = ai;
    }

    public EAntType getType()
    {
        return type;
    }

    public IAntAI getAi()
    {
        return ai;
    }
}
