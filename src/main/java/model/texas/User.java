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
    public User()
    {
        this.cash = 500;
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
         System.out.println("Returning current bet: " + currentbet);
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
    public void addBet(int val)
    {
        currentbet = currentbet + val;
        cash = cash - val;
    }
    @Override
    public boolean isUser()
    {
        return true;
    }
    
}
