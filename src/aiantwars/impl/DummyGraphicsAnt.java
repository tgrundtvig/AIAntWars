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

    public DummyGraphicsAnt(int id)
    {
        this.id = id;
    }
    
    @Override
    public void playTurnLeftAnimation()
    {
        System.out.println("ID: " + id + " turns left.");
    }

    @Override
    public void playTurnRightAnimation()
    {
        System.out.println("ID: " + id + " turns right.");
    }

    @Override
    public void playMoveForwardAnimation()
    {
        System.out.println("ID: " + id + " moves forward.");
    }

    @Override
    public void playMoveBackwardAnimation()
    {
        System.out.println("ID: " + id + " moves backward.");
    }

    @Override
    public void playAttackAnimation(int damage)
    {
        System.out.println("ID: " + id + " attacks.");
    }

    @Override
    public void playPickUpFoodAnimation()
    {
        System.out.println("ID: " + id + " picks up a piece of food.");
    }

    @Override
    public void playDropFoodAnimation()
    {
        System.out.println("ID: " + id + " drops a piece of food.");
    }

    @Override
    public void playEatFoodAnimation(int hitPointsGained)
    {
        System.out.println("ID: " + id + " eats a piece of food.");
    }

    @Override
    public void playDigOutAnimation()
    {
        System.out.println("ID: " + id + " digs out a location.");
    }

    @Override
    public void playDropSoilAnimation()
    {
        System.out.println("ID: " + id + " drops soil.");
    }

    @Override
    public void playLayEggAnimation(EAntType eggType)
    {
        System.out.println("ID: " + id + " lays an egg. Type: " + eggType);
    }

    @Override
    public void playPassAnimation()
    {
        System.out.println("ID: " + id + " passes.");
    }

    @Override
    public void playHatchAnimation()
    {
        System.out.println("ID: " + id + " hatches.");
    }

    @Override
    public void playDeathAnimation()
    {
        System.out.println("ID: " + id + " dies.");
    }

    @Override
    public void playOnDamage(int damage)
    {
        System.out.println("ID: " + id + " takes " + damage + " damage.");
    }

    @Override
    public void onTakeAction()
    {
        System.out.println("ID: " + id + " on take action.");
    }
    
}
