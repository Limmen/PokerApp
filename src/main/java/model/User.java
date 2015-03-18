/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import util.Card;

/**
 *
 * @author kim
 */
public class User 
{
    private int value;
    private int score;
    private int aces;
    private ArrayList<Card> cards;
    public User()
    {
        this.value = 0;
        this.score = 0;
        this.aces = 0;
        this.cards = new ArrayList<Card> ();
    }
    public String getValue()
    {
        for(int i = 0; i<aces; i++)
        {
            ace();
        }
        if(value > 21)
            return "Busted!";
        else
            return Integer.toString(value);
    }
    public String getScore()
    {
        return Integer.toString(score);
    }
    public void updateValue(Card c)
    {
        cards.add(c);
        if(value < 22)
        {
            value = value + c.getValue();
        }
    }
    public void updateScore()
    {
        score = score +1;
    }
    public void setValue(int n)
    {
        this.value = n;
    }
    public void setScore(int n)
    {
        this.score = n;
    }
    public boolean check()
    {
        if(value > 21)
        {
            return false;
        }
        else
            return true;
    }
    public void addAce(Card c)
    {
        cards.add(c);
        this.aces++;
    }
    public void ace()
    {
        if((value + 11) < 22)
        {
            value = value+11;
        }
        else value = value +1;
    }
}
