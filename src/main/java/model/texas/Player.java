/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.texas;

import java.util.ArrayList;
import javax.swing.JButton;
import util.Card;

/**
 *
 * @author kim
 */
public interface Player 
{
    public int getCash();
    public int getBet();
    public ArrayList<Card> getHand();
    public String getName();
    public void newCards(ArrayList<Card> c);
    public void init();
    public void call(int val);
    public void raise(int val);
    public boolean isUser();
    public void winCash(int val);
    public void newRound();
}
