/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars.graphicsinterface;

import aiantwars.EAntType;

/**
 *
 * @author Tobias Grundtvig
 */
public interface IGraphicsAnt
{
    public void onTakeAction();
    public void playTurnLeftAnimation();
    public void playTurnRightAnimation();
    public void playMoveForwardAnimation();
    public void playMoveBackwardAnimation();
    public void playAttackAnimation(int damage);
    public void playOnDamage(int damage);
    public void playPickUpFoodAnimation();
    public void playDropFoodAnimation();
    public void playEatFoodAnimation(int hitPointsGained);
    public void playDigOutAnimation();
    public void playDropSoilAnimation();
    public void playLayEggAnimation(EAntType eggType);
    public void playPassAnimation();
    public void playHatchAnimation();
    public void playDeathAnimation();
}
