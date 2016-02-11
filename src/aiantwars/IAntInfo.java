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
public interface IAntInfo
{
    public int antID();
    public ITeamInfo getTeamInfo();
    public EAntType getAntType();
    public ILocationInfo getLocation();
    public int getDirection();
    public boolean carriesSoil();
    public int getAge();
    public int getHitPoints();
    public int getFoodLoad();
    public int getActionPoints();
    public boolean isDead();
    
    public default float getHealth()
    {
        return ((float) getHitPoints()) / ((float) getAntType().getMaxHitpoints());
    }
    
    public default boolean hasHatched()
    {
        return getAge() >= 0;
    }
    
    public default String asString()
    {
        return  "Team: " + getTeamInfo().getTeamName() +
                ", Type: " + getAntType() +
                ", ID: " + antID() +
                ", Age: " + getAge() +
                "\nPos: (" + getLocation().getX() + ", " + getLocation().getY() + ")" +
                ", Dir: " + getDirection() +
                "\nFood: " + getFoodLoad() +
                ", HitPoints: " + getHitPoints() + "\n";
    }
}
