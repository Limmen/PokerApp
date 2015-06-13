/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.texas;

import controller.Controller;
import java.util.ArrayList;
import javax.swing.JLabel;
import model.texas.Bet;
import model.texas.Player;
import util.Card;
import util.Texas;
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
    public TexasFrame tf;
    private TexasOptions to;
    public TexasGui(Controller contr)
    {
        this.contr = contr;
        this.to = new TexasOptions(this);
    }
    /*
    public TexasPlayer newPlayer(int id, ArrayList<JLabel> cards,TexasLogic tl, Bet bets, int cash)
    {
        return contr.newPlayer(id,cards,tl,bets,this, cash);
    } */
    public TexasTableCard newTableCard(int id, JLabel card)
    {
        return contr.newTableCard(id, card, this);
    }
    public TexasTable newTable(ArrayList<TexasTableCard> cards)
    {
        return contr.newTable(cards, this);
    }
    public void texasplayerDeal(Player p)
    {
        contr.texasplayerDeal(p);
    }
    public void newDeck(ArrayList<Texas> players)
    {
        contr.newDeck(players, this);
    }
    public String botBet(Player p, ArrayList<TexasTableCard> table, Bet bet)
    {
        return contr.botBet(p, table, bet);
    }
    public ArrayList<Card> tableDeal(int nr)
    {
        return contr.tableDeal(nr, this);
    }
    public int getTotal(ArrayList<Texas> players)
    {
        return contr.getTotal(players);
    }
    public void whoWins(ArrayList<Texas> players, ArrayList<TexasTableCard> table)
    {
        contr.whoWins(players, table);
    } 
    
}

