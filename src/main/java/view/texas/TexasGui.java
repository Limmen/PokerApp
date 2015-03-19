/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.texas;

import controller.Controller;
import java.util.ArrayList;
import util.Card;
import view.MainFrame;

/**
 *
 * @author kim
 */
public class TexasGui 
{
    private Controller contr;
    private MainFrame main;
    private TexasFrame tf;
    public TexasGui(Controller contr)
    {
        this.contr = contr;
        this.tf = new TexasFrame(this);
    }
    
    
}

