/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.texas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import util.Card;

/**
 *
 * @author kim
 */
public class User implements Player
{
    private int cash;
    private int currentbet;
    private ArrayList<Card> hand;
    private String name;
    public User(int cash)
    {
        this.cash = cash;
        this.currentbet = 0;
        this.hand = new ArrayList<Card>();
        this.name = "You";
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
    public void raise(int val)
    {
        cash = cash - val;
        currentbet = currentbet + val;
    }
    @Override
    public void call(int val)
    {
        if(val > currentbet)
        {
            cash = cash - (val-currentbet);
            currentbet = val;
        }
    }
    @Override
    public boolean isUser()
    {
        return true;
    }
    @Override
    public void winCash(int val)
    {
        cash = cash + val;
    }
    public void newRound()
    {
        this.currentbet = 0;
        this.hand = new ArrayList<Card>();
    }
    
}
