/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.impl;

import aiantwars.EAntType;
import aiantwars.graphicsinterface.IGraphicsAnt;

/**
 *
 * @author Tobias Grundtvig
 */
public class DummyGraphicsAnt implements IGraphicsAnt
{
    private final int id;
    private final boolean verbose;

    public DummyGraphicsAnt(int id, boolean verbose)
    {
        this.id = id;
        this.verbose = verbose;
    }
    
    @Override
    public void playTurnLeftAnimation()
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " turns left.");
        }
    }

    @Override
    public void playTurnRightAnimation()
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " turns right.");
        }
    }

    @Override
    public void playMoveForwardAnimation()
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " moves forward.");
        }
    }

    @Override
    public void playMoveBackwardAnimation()
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " moves backward.");
        }
    }

    @Override
    public void playAttackAnimation(int damage)
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " attacks.");
        }
    }

    @Override
    public void playPickUpFoodAnimation()
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " picks up a piece of food.");
        }
    }

    @Override
    public void playDropFoodAnimation()
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " drops a piece of food.");
        }
    }

    @Override
    public void playEatFoodAnimation(int hitPointsGained)
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " eats a piece of food.");
        }
    }

    @Override
    public void playDigOutAnimation()
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " digs out a location.");
        }
    }

    @Override
    public void playDropSoilAnimation()
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " drops soil.");
        }
    }

    @Override
    public void playLayEggAnimation(EAntType eggType)
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " lays an egg. Type: " + eggType);
        }
    }

    @Override
    public void playPassAnimation()
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " passes.");
        }
    }

    @Override
    public void playHatchAnimation()
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " hatches.");
        }
    }

    @Override
    public void playDeathAnimation()
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " dies.");
        }
    }

    @Override
    public void playOnDamage(int damage)
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " takes " + damage + " damage.");
        }
    }

    @Override
    public void onTakeAction()
    {
        if(verbose)
        {
            System.out.println("ID: " + id + " on take action.");
        }
    }
    
}
