/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.impl;

import aiantwars.IAntInfo;
import aiantwars.ILocationInfo;

/**
 *
 * @author Tobias Grundtvig
 */
public class ImmutableLocation implements ILocationInfo
{
    private final int x;
    private final int y;
    private final boolean isFilled;
    private final boolean isRock;
    private final int foodCount;
    private final ImmutableAntInfo ant;

    public ImmutableLocation(ILocationInfo locInfo)
    {
        this.x = locInfo.getX();
        this.y = locInfo.getY();
        this.isFilled = locInfo.isFilled();
        this.isRock = locInfo.isRock();
        this.foodCount = locInfo.getFoodCount();
        
        IAntInfo antInfo = locInfo.getAnt();
        if(locInfo.getAnt() != null)
        {
            this.ant = new ImmutableAntInfo(antInfo, this);
        }
        else
        {
            this.ant = null;
        }
    }
    
    public ImmutableLocation(ILocationInfo locInfo, ImmutableAntInfo antInfo)
    {
        this.x = locInfo.getX();
        this.y = locInfo.getY();
        this.isFilled = locInfo.isFilled();
        this.isRock = locInfo.isRock();
        this.foodCount = locInfo.getFoodCount();
        this.ant = antInfo;
    }
    
    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public IAntInfo getAnt()
    {
        return ant;
    }

    @Override
    public boolean isFilled()
    {
        return isFilled;
    }

    @Override
    public boolean isRock()
    {
        return isRock;
    }

    @Override
    public int getFoodCount()
    {
        return foodCount;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + this.x;
        hash = 41 * hash + this.y;
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
        if(!(obj instanceof ILocationInfo))
        {
            return false;
        }
        final ILocationInfo other = (Location) obj;
        if(this.x != other.getX())
        {
            return false;
        }
        if(this.y != other.getY())
        {
            return false;
        }
        return true;
    }
    
}
