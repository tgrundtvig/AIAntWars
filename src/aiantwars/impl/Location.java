/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.impl;

import aiantwars.ILocationInfo;

/**
 *
 * @author Tobias Grundtvig
 */
public class Location implements ILocationInfo
{
    private final int x;
    private final int y;
    private boolean rock;
    private boolean filled;
    private int foodCount;
    private LogicAnt ant;

    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
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
    public boolean isFilled()
    {
        return filled;
    }
    
    @Override
    public boolean isRock()
    {
        return rock;
    }

    @Override
    public int getFoodCount()
    {
        return foodCount;
    }
    
    public void setFilled(boolean filled)
    {
        this.filled = filled;
    }
    
    public void setRock(boolean rock)
    {
        this.rock = rock;
        setFilled(rock);
    }

    public void setFoodCount(int foodCount)
    {
        this.foodCount = foodCount;
    }
    
    public boolean subtractFood()
    {
        if(foodCount <= 0)
        {
            return false;
        }
        --foodCount;
        return true;
    }
    
    @Override
    public LogicAnt getAnt()
    {
        return ant;
    }
    
    public void setAnt(LogicAnt ant)
    {
        this.ant = ant;
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
        if(getClass() != obj.getClass())
        {
            return false;
        }
        final Location other = (Location) obj;
        if(this.x != other.x)
        {
            return false;
        }
        if(this.y != other.y)
        {
            return false;
        }
        return true;
    }
    
    
}
