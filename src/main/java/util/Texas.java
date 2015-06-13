/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JPanel;
import model.texas.Bet;
import model.texas.Player;

/**
 *
 * @author kim
 */
public interface Texas {
    public JPanel getPanel();
    public int getBet();
    public int getCash();
    public Player getPlayer();
    public boolean folded();
    public void turn(Bet bet, Round r);
    public void newCards();
    public void youDeal();
    public void dealDone();
    public void youWin(int amount);
    public void youLoose();
    public void showCards();
    public void hideCards();
    public void newRound();
}
