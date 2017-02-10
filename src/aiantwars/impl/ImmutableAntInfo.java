/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.impl;

import aiantwars.EAntType;
import aiantwars.IAntInfo;
import aiantwars.ILocationInfo;
import aiantwars.ITeamInfo;

/**
 *
 * @author Tobias Grundtvig
 */
public class ImmutableAntInfo implements IAntInfo
{
    private final int antID;
    private final ITeamInfo teamInfo;
    private final EAntType antType;
    private final ImmutableLocation loc;
    private final int dir;
    private final boolean carriesSoil;
    private final int age;
    private final int hitPoints;
    private final int foodLoad;
    private final int actionPoints;

    public ImmutableAntInfo(IAntInfo antInfo)
    {
        this.antID = antInfo.antID();
        this.teamInfo = antInfo.getTeamInfo();
        this.antType = antInfo.getAntType();
        this.dir = antInfo.getDirection();
        this.carriesSoil = antInfo.carriesSoil();
        this.age = antInfo.getAge();
        this.hitPoints = antInfo.getHitPoints();
        this.foodLoad = antInfo.getFoodLoad();
        this.actionPoints = antInfo.getActionPoints();
        ILocationInfo locInfo = antInfo.getLocation();
        if(locInfo instanceof ImmutableLocation)
        {
            this.loc = (ImmutableLocation) locInfo;
        }
        else
        {
            this.loc = new ImmutableLocation(locInfo, this);
        }
    }
    
    public ImmutableAntInfo(IAntInfo antInfo, ImmutableLocation loc)
    {
        this.antID = antInfo.antID();
        this.teamInfo = antInfo.getTeamInfo();
        this.antType = antInfo.getAntType();
        this.dir = antInfo.getDirection();
        this.carriesSoil = antInfo.carriesSoil();
        this.age = antInfo.getAge();
        this.hitPoints = antInfo.getHitPoints();
        this.foodLoad = antInfo.getFoodLoad();
        this.actionPoints = antInfo.getActionPoints();
        this.loc = loc;
    }
    
    
    @Override
    public int antID()
    {
        return antID;
    }

    @Override
    public ITeamInfo getTeamInfo()
    {
        return teamInfo; 
    }

    @Override
    public EAntType getAntType()
    {
        return antType;
    }

    @Override
    public ILocationInfo getLocation()
    {
        return loc;
    }

    @Override
    public int getDirection()
    {
        return dir;
    }

    @Override
    public boolean carriesSoil()
    {
        return carriesSoil;
    }

    @Override
    public int getAge()
    {
        return age;
    }

    @Override
    public int getHitPoints()
    {
        return hitPoints;
    }

    @Override
    public int getFoodLoad()
    {
        return foodLoad;
    }

    @Override
    public int getActionPoints()
    {
        return actionPoints;
    }    

    @Override
    public boolean isDead()
    {
        return false;
    }
}
