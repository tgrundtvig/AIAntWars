/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.impl;

/**
 *
 * @author Tobias Grundtvig
 */
public class Score
{
    private final int major;
    private final int minor;

    public Score(int major, int minor)
    {
        this.major = major;
        this.minor = minor;
    }

    public int getMajor()
    {
        return major;
    }

    public int getMinor()
    {
        return minor;
    }
    
    @Override
    public String toString()
    {
        return major + ":" + minor;
    }
}
