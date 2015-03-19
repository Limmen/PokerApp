/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.texas.TexasGui;
import controller.Controller;
import view.blackjack.BlackJackGui;
/**
 *
 * @author kim
 */
public class View 
{
    private Controller contr;
    private Gui gui;
    private BlackJackGui bgui;
    private TexasGui tgui;
    public View(Controller contr)
    {
        this.contr = contr;
        this.gui = new Gui(this, contr);
    }
    
    public void blackjack()
    {
        bgui = new BlackJackGui(contr);
    }
    public void texas()
    {
        tgui = new TexasGui(contr);
    }
}
