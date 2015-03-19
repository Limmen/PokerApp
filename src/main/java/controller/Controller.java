/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.blackjack.BlackJackManager;
import model.blackjack.DeckManager;
import model.texas.TexasManager;
import util.Card;
import view.blackjack.BlackJackGui;

/**
 *
 * @author kim
 */
public class Controller 
{
    private DeckManager dm;
    private BlackJackManager bm;
    private TexasManager tm;
    public Controller()
    {
        dm = new DeckManager();
        bm = new BlackJackManager();
        tm = new TexasManager();
    }
    
    public void printCards()
    {
        dm.printCards();
    }
    public int getRandomCard(String who, BlackJackGui g)
    {
            if(bm.checkChoice(who))
            {
                Card c =  dm.getRandomCard();
                int id = c.getId();
                if(id == 12 || id == 25 || id == 38 || id == 51)
                {
                    bm.addAce(c,who);
                }
                else
                    bm.updateValue(c, who);
                return c.getId();
            }
            else
                return -1;
    }
    public void newDeck(BlackJackGui g)
    {
        dm.newDeck();
        bm.newDeck();
    }
    public void newGame(BlackJackGui g)
    {
        dm.newDeck();
        bm.newGame();
    }
    public String getScore(String who, BlackJackGui g)
    {
        return bm.getScore(who);
    }
    public int getValue(String who, BlackJackGui g)
    {
        return bm.getValue(who);
    }
    public void updateScore(String who,BlackJackGui g)
    {
        bm.updateScore(who);
    }
    public String getResult(BlackJackGui g)
    {
        return bm.getResult();
    }
    public ArrayList<Card> getCards(String who, BlackJackGui g)
    {
        return bm.getCards(who);
    }
        public int getVisible(BlackJackGui g)
    {
        return bm.getVisible();
    }
}
