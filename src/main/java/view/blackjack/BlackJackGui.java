/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.blackjack;

import controller.Controller;
import java.util.ArrayList;
import util.Card;
import view.MainFrame;

/**
 *
 * @author kim
 */
public class BlackJackGui 
{
    private Controller contr;
    private MainFrame main;
    private BlackJackFrame bjf;
    public BlackJackGui(Controller contr)
    {
        this.contr = contr;
        this.bjf = new BlackJackFrame(this);
    }
    
    public void printCards()
    {
        contr.printCards();
    }
    public int getRandomCard(String who)
    {
        return contr.getRandomCard(who, this);
    }
    public void newDeck()
    {
        contr.newDeck(this);
    }
    public void newGame()
    {
        contr.newGame(this);
    }
    public String getScore(String who)
    {
        return contr.getScore(who, this);
    }
    public int getValue(String who)
    {
        return contr.getValue(who, this);
    }
    public void updateScore(String who)
    {
        contr.updateScore(who, this);
    }
    public String getResult()
    {
        return contr.getResult(this);
    }
        public ArrayList<Card> getCards(String who)
    {
        return contr.getCards(who, this);
    }
    public int getVisible()
    {
        return contr.getVisible(this);
    }
}
