/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
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
    public void newGame()
    {
        contr.newGame();
    }
    public String getScore(String who)
    {
        return contr.getScore(who);
    }
}
