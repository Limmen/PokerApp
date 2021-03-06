/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.blackjack;

import java.util.ArrayList;
import util.Card;

/**
 *
 * @author kim
 */
public class BlackJackManager 
{
    private User user;
    private House house;
    
    public BlackJackManager()
    {
        this.user = new User();
        this.house = new House();
    }
    
    public void updateValue(Card c, String who)
    {
        if(who.equalsIgnoreCase("user"))
        {
            user.updateValue(c);
        }
        else
            house.updateValue(c);
    }
    public int getValue(String who)
    {
         if(who.equalsIgnoreCase("user"))
        {
            return user.getValue();
        }
        else
            return house.getValue();
    }
    
        public void updateScore(String who)
    {
        if(who.equalsIgnoreCase("user"))
        {
            user.updateScore();
        }
        else
            house.updateScore();
    }
    public String getScore(String who)
    {
         if(who.equalsIgnoreCase("user"))
        {
            return user.getScore();
        }
        else
            return house.getScore();
    }
 
    public void newGame()
    {
        user = new User();
        house = new House();
    }
    public void newDeck()
    {
        user.setValue(0);
        house.setValue(0);
        user.setAce(0);
        house.setAce(0);
        user.resetHand();
        house.resetHand();
    }
    public boolean checkChoice(String who)
    {
        if(who.equalsIgnoreCase("user"))
            return user.check();
        else
            return house.check();
    }
    public String getResult()
    {
        if(user.getValue() > house.getValue())
        {
            return "user";
        }
        if(user.getValue() < house.getValue())
        {
            return "house";
        }
        else
            return "tie";
    }
    public void addAce(Card c, String who)
    {
        if(who.equalsIgnoreCase("user"))
            user.addAce(c);
        else
            house.addAce(c);
    }
        public ArrayList<Card> getCards(String who)
    {
        if(who.equalsIgnoreCase("user"))
        {
            return user.getCards();
        }
        else
            return house.getCards();
    }
    public int getVisible()
    {
        return house.getVisible();
    }
}
