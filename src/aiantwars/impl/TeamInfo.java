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
}
