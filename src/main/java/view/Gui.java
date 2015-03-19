/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import util.Card;

/**
 *
 * @author kim
 */
public class Gui 
{
    private View view;
    private MainFrame main;
    private BlackJackFrame bjf;
    public Gui(View view)
    {
        this.view = view;
        this.main = new MainFrame(this);
        //this.bjf = new BlackJackFrame(this);
    }
    
    public void printCards()
    {
        view.printCards();
    }
    public int getRandomCard(String who)
    {
        return view.getRandomCard(who);
    }
    public void newDeck()
    {
        view.newDeck();
    }
    public void newGame()
    {
        view.newGame();
    }
    public String getScore(String who)
    {
        return view.getScore(who);
    }
    public int getValue(String who)
    {
        return view.getValue(who);
    }
    public void updateScore(String who)
    {
        view.updateScore(who);
    }
    public String getResult()
    {
        return view.getResult();
    }
    public ArrayList<Card> getCards(String who)
    {
        return view.getCards(who);
    }
    public int getVisible()
    {
        return view.getVisible();
    }
}
