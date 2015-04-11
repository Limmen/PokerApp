/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.texas;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.texas.Bet;
import util.Card;
import util.TexasPlayer;
import util.TexasTable;
import util.TexasTableCard;

/**
 *
 * @author kim
 */
public class TexasLogic 
{
    TexasGui gui;
    TexasFrame tf;
    BufferedImage image;
    TexasCards tc;
    Bet bets;
    Font Italic = new Font("Serif", Font.ITALIC, 12);
    Font Bold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    int nrCards = 0;
    public TexasLogic(TexasGui gui, TexasFrame tf, TexasCards tc)
    {
        this.gui = gui;
        this.tf = tf;
        this.tc = tc;
        this.bets = new Bet();
    }
    public TexasTable generateTable()
    {
        ArrayList<TexasTableCard> tableCards = new ArrayList();
        for(int i = 0; i<5; i++)
        {
            image = tc.getPlaceholder();
            JLabel Card = new JLabel(new ImageIcon(image));
            TexasTableCard tableCard = gui.newTableCard(i, Card);
            tableCards.add(tableCard);
        }
        
        return gui.newTable(tableCards);
    }
    public ArrayList<TexasPlayer> generateBots(int number)
    {
        ArrayList<TexasPlayer> bots = new ArrayList();
        for(int i = 0; i<number; i++)
        {
            bots.add(gui.newPlayer(i,getPlaceholder(2), this, bets));
        }
        return bots;
    }
       public TexasPlayer generateUser()
    {
        TexasPlayer user = gui.newPlayer(-1, getPlaceholder(2), this, bets);
        return user;
        
    }
    public ArrayList<JLabel>  getPlaceholder(int n)
    {
        ArrayList<JLabel> cards = new ArrayList();
        for(int i = 0; i<2; i++)
        {
            image = tc.getPlaceholder();
            JLabel Card = new JLabel(new ImageIcon(image));
            cards.add(Card);
        }
        return cards;
    }
    public void playersDeal(ArrayList<TexasPlayer> players)
    {
        gui.playersDeal(players);
        for(TexasPlayer p : players)
        {
            ArrayList<Card> cards = p.getCards();
            ArrayList<JLabel> newCards = new ArrayList();
            for(Card c : cards)
            {
                image = tc.readImage(c.getId());
                JLabel Card = new JLabel(new ImageIcon(image));
                newCards.add(Card);
            }
            p.setCards(newCards);
        }
    }
    public void newDeck(ArrayList<TexasPlayer> players)
    {
        gui.newDeck(players);
    }
    public void bet(int dealer, int bet, int playersLeft)
    {
        Boolean newRound = false;
        ArrayList<TexasPlayer> players = tf.getPlayers();
        if(playersLeft > 0)
        {
            if(dealer > players.size()-1)
                dealer = 0;
            players.get(dealer).bet(bet, playersLeft-1,dealer+1);
        }
        else
        {
            for(TexasPlayer p : players)
            {
                if (p.getPlayer().getBet() < bets.getBet() && p.getPlayer().getCash() > 0)
                {
                    newRound = true;
                }
            }
            if(newRound)
            {
                bet(0, bets.getBet(), players.size());
            }
            else
            {
                if(nrCards > 0)
                {
                    deal(1);
                }
                if(nrCards == 0)
                {
                    deal(3);
                }
            }
            
        }
            
    }
    public void pack()
    {
        tf.pack();
    }
    public void fold(TexasPlayer p)
    {
        tf.players.remove(p);
        tf.folded.add(p);
    }
    public JLabel getDeal()
    {
        image = tc.getDealer();
        return new JLabel(new ImageIcon(image));
    }
    public String botBet(TexasPlayer p, int callAmount)
    {
        return gui.botBet(p.getPlayer(), tf.table.getCards(), callAmount);
    }
    public void deal(int nr)
    {
        TexasTable table = tf.table;
        ArrayList<Card> cards = gui.tableDeal(nr);
        ArrayList<JLabel> Vcards = new ArrayList();
        for(Card c : cards)
            {
                image = tc.readImage(c.getId());
                JLabel Card = new JLabel(new ImageIcon(image));
                Vcards.add(Card);
            }
        for(int i = 0; i < cards.size(); i++)
        {
            table.Deal(Vcards,cards, nrCards +1, i);
            nrCards++;
        }
        table.addCards();
        pack();
        
    }
   
}
