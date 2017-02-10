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
public class Board
{
    private final int sizeX;
    private final int sizeY;
    private final int[] dx;
    private final int[] dy;
    private final Location[][] map;

    public Board(int sizeX, int sizeY)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        map = new Location[sizeX][sizeY];
        for(int x = 0; x < sizeX; ++x)
        {
            for(int y = 0; y < sizeY; ++y)
            {
                map[x][y] = new Location(x, y);
            }
        }
        this.dx = new int[4];
        this.dy = new int[4];
        dx[0] = 0;
        dx[1] = 1;
        dx[2] = 0;
        dx[3] = -1;
        dy[0] = 1;
        dy[1] = 0;
        dy[2] = -1;
        dy[3] = 0;
    }
    
    public int getSizeX()
    {
        return sizeX;
    }
    
    public int getSizeY()
    {
        return sizeY;
    }
    
    public Location getLocation(int x, int y)
    {
        if(x < 0 || y < 0 || x >= sizeX || y >= sizeY)
        {
            return null;
        }
        return map[x][y];
    }
    
    public Location getNeighbour(ILocationInfo loc, int dir)
    {
        int x = loc.getX() + dx[dir];
        int y = loc.getY() + dy[dir];
        return getLocation(x, y);
    }   
}
