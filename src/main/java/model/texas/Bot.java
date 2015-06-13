/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.texas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import util.Card;

/**
 *
 * @author kim
 */
public class Bot implements Player
{
    private int cash;
    private int currentbet;
    private ArrayList<Card> hand;
    private String name;
    
    public Bot(int id, int cash)
    {
        this.cash = cash;
        this.currentbet = 0;
        this.hand = new ArrayList<Card>();
        this.name = "Bot" + id;
    }
    @Override
    public String getName() 
    {
        return name;
    }
     @Override
    public int getCash() 
    {
        return cash;
    }

    @Override
    public int getBet() 
    {
         return currentbet;
    }

    @Override
    public ArrayList<Card> getHand() 
    {
       return hand;
    }
    @Override
    public void newCards(ArrayList<Card> newcards)
    {
        hand = new ArrayList();
        for(Card c : newcards)
        {
            hand.add(c);
        }
    }
    @Override
    public void init()
    {
        currentbet = 0;
        hand = new ArrayList();
    }
    @Override
    public boolean isUser()
    {
        return false;
    }
    @Override
    public void raise(int raise)
    {
        if(raise > cash)
            raise = cash;
        System.out.println("Bot raising with " + raise + " old bet was " + currentbet);
        currentbet = currentbet + raise;
        cash = cash - raise;
        System.out.println("Bots new bet is: " + currentbet);
        
    }
    @Override
    public void call(int val)
    {
        if(val > currentbet)
        {
            if((val- currentbet) <= cash)
            {
                cash = cash - (val-currentbet);
                currentbet = val;
            }
            else
            {
                val = cash + currentbet;
                cash = cash - (val-currentbet);
                currentbet = val;
            }

        }
    }
    @Override
    public void winCash(int val)
    {
        cash = cash + val;
    }
    @Override
    public void newRound()
    {
        this.currentbet = 0;
        this.hand = new ArrayList<Card>();
    }

    
}
