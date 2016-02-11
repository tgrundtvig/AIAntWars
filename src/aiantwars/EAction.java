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
public enum EAction
{
    TurnLeft("Turn Left <-"),
    TurnRight("Turn Right ->"),
    MoveForward("Move forward"),
    MoveBackward("Move backward"),
    Attack("Attack"),
    PickUpFood("Pick up food"),
    DropFood("Drop food"),
    EatFood("Eat food"),
    DigOut("Dig out"),
    DropSoil("Drop soil"),
    LayEgg("Lay egg"),
    Pass("Pass");
    
    private final String name;

    private EAction(String name)
    {
        this.name = name;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
}
