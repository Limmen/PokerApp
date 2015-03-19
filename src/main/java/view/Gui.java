/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import view.blackjack.BlackJackGui;

/**
 *
 * @author kim
 */
public class Gui 
{
    private View view;
    private MainFrame main;
    private Controller contr;
    private BlackJackGui bgui;
    
    public Gui(View view, Controller contr)
    {
        this.view = view;
        this.main = new MainFrame(this);
        this.contr = contr;
        //this.bjf = new BlackJackFrame(this);
    }
    public void blackjack()
    {
        view.blackjack();
    }
    public void texas()
    {
        view.texas();
    }
}
