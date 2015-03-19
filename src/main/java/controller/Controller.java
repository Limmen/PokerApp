/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.DeckManager;
import model.GameManager;
import util.Card;

/**
 *
 * @author kim
 */
public class Controller 
{
    private DeckManager dm;
    private GameManager gm;
    public Controller()
    {
        dm = new DeckManager();
        gm = new GameManager();
    }
    
    public void printCards()
    {
        dm.printCards();
    }
    public int getRandomCard(String who)
    {
            if(gm.checkChoice(who))
            {
                Card c =  dm.getRandomCard();
                int id = c.getId();
                if(id == 12 || id == 25 || id == 38 || id == 51)
                {
                    gm.addAce(c,who);
                }
                else
                    gm.updateValue(c, who);
                return c.getId();
            }
            else
                return -1;
    }
    public void newDeck()
    {
        dm.newDeck();
        gm.newDeck();
    }
    public void newGame()
    {
        dm.newDeck();
        gm.newGame();
    }
    public String getScore(String who)
    {
        return gm.getScore(who);
    }
    public int getValue(String who)
    {
        return gm.getValue(who);
    }
    public void updateScore(String who)
    {
        gm.updateScore(who);
    }
    public String getResult()
    {
        return gm.getResult();
    }
    public ArrayList<Card> getCards(String who)
    {
        return gm.getCards(who);
    }
        public int getVisible()
    {
        return gm.getVisible();
    }
}
