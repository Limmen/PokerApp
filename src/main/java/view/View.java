/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.ArrayList;
import util.Card;
/**
 *
 * @author kim
 */
public class View 
{
    private Controller contr;
    private Gui gui;
    public View(Controller contr)
    {
        this.contr = contr;
        this.gui = new Gui(this);
    }
    
    public void printCards()
    {
        contr.printCards();
    }
    public int getRandomCard(String who)
    {
        return contr.getRandomCard(who);
    }
    public void newDeck()
    {
        contr.newDeck();
    }
    public void newGame()
    {
        contr.newGame();
    }
    public String getScore(String who)
    {
        return contr.getScore(who);
    }
    public int getValue(String who)
    {
        return contr.getValue(who);
    }
    public void updateScore(String who)
    {
        contr.updateScore(who);
    }
    public String getResult()
    {
        return contr.getResult();
    }
        public ArrayList<Card> getCards(String who)
    {
        return contr.getCards(who);
    }
    public int getVisible()
    {
        return contr.getVisible();
    }
}
