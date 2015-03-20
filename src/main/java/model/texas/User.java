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
public class User implements Player
{
    private int cash;
    private int currentbet;
    private ArrayList<Card> hand;
    private String name;
    private JButton call;
    private JButton fold;
    private JButton raise;
    public User()
    {
        this.cash = 500;
        this.currentbet = 0;
        this.hand = new ArrayList<Card>();
        this.name = "You";
        this.call = new JButton("Call");
        this.fold = new JButton("Fold");
        this.raise = new JButton("Raise");
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
    public JButton getCall()
    {
        return call;
    }
        public JButton getRaise()
    {
        return raise;
    }
    public JButton getFold()
    {
        return fold;
    }

}
