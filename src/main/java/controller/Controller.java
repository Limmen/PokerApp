/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
        Card c =  dm.getRandomCard();
        gm.updateScore(c.getValue(), who);
        return c.getId();
    }
    public void newGame()
    {
        dm.newGame();
        gm.newGame();
    }
    public String getScore(String who)
    {
        return gm.getScore(who);
    }
            
}
