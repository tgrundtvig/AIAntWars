/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.impl;

import aiantwars.ITeamInfo;

/**
 *
 * @author Tobias Grundtvig
 */
public class TeamInfo implements ITeamInfo
{
    private final int id;
    private final String name;

    public TeamInfo(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public int getTeamID()
    {
        return id;
    }

    @Override
    public String getTeamName()
    {
        return name;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if(obj == null)
        {
            return false;
        }
        if(!(obj instanceof ITeamInfo))
        {
            return false;
        }
        final ITeamInfo other = (ITeamInfo) obj;
        if(this.id != other.getTeamID())
        {
            return false;
        }
        return true;
    }
    
    
}
