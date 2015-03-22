/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.texas;

/**
 *
 * @author kim
 */
public class Bet 
{
    private int callAmount;
    public Bet()
    {
        callAmount = 0;
    }
    public void setBet(int bet)
    {
        callAmount = bet;
    }
    public int getBet()
    {
        return callAmount;
    }
}
