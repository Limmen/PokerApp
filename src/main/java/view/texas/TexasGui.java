/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.texas;

import controller.Controller;
import java.util.ArrayList;
import javax.swing.JLabel;
import model.texas.Player;
import util.Card;
import util.TexasPlayer;
import util.TexasTable;
import util.TexasTableCard;
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
    public TexasPlayer newPlayer(int id, ArrayList<JLabel> cards, TexasLogic tl)
    {
        return contr.newPlayer(id,cards,tl,this);
    }
    public TexasTableCard newTableCard(int id, JLabel card)
    {
        return contr.newTableCard(id, card, this);
    }
    public TexasTable newTable(ArrayList<TexasTableCard> cards)
    {
        return contr.newTable(cards, this);
    }
    public void playersDeal(ArrayList<TexasPlayer> players)
    {
        contr.playersDeal(players,this);
    }
    public void newDeck(ArrayList<TexasPlayer> players)
    {
        contr.newDeck(players, this);
    }
    public String botBet(Player p, ArrayList<TexasTableCard> table, int callAmount)
    {
        return contr.botBet(p, table,callAmount, this);
    }
    public ArrayList<Card> tableDeal(int nr)
    {
        return contr.tableDeal(nr, this);
    }
    
}

