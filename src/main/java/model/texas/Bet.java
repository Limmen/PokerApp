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
    public int callAmount;
    public int totalBet;
    public Bet()
    {
        callAmount = 0;
        totalBet = 0;
    }
    public void addBet(int bet)
    {
        totalBet = totalBet + bet;
    }
    public void raise(int bet)
    {
        totalBet = totalBet - callAmount;
        callAmount = bet;
        totalBet = totalBet + callAmount;
    }
    public int getTotalBet()
    {
        return totalBet;
    }
    public int getCallAmount()
    {
        return callAmount;
    }
}
