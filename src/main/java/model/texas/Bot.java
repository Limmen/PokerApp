/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.texas;

import java.util.ArrayList;
import javax.swing.JButton;
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
    
    public Bot(int id)
    {
        this.cash = 500;
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
    public void addBet(int val)
    {
        //getChoice();
        currentbet = currentbet + val;
        cash = cash - val;
    }
    
}
