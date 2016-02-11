/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwars;

import java.util.List;

/**
 *
 * @author Tobias Grundtvig
 */
public interface IAntAI
{
    public void onHatch(IAntInfo thisAnt, ILocationInfo thisLocation, int worldSizeX, int worldSizeY);
    public void onStartTurn(IAntInfo thisAnt, int turn);
    public EAction chooseAction(IAntInfo thisAnt,
                                ILocationInfo thisLocation,
                                List<ILocationInfo> visibleLocations,
                                List<EAction> possibleActions);
    //Only used for Queen AI's
    public void onLayEgg(IAntInfo thisAnt, List<EAntType> types, IEgg egg);
    
    public void onAttacked(IAntInfo thisAnt, int dir, IAntInfo attacker, int damage);
    public void onDeath(IAntInfo thisAnt);
}
