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
import javax.swing.SwingUtilities;
import model.texas.Bet;
import util.Card;
import util.Round;
import util.Texas;
import util.TexasBot;
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
    public TexasFrame tf;
    BufferedImage image;
    TexasCards tc;
    Bet bets;
    Font Italic = new Font("Serif", Font.ITALIC, 12);
    Font Bold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    int nrCards = 0;
    int dealer;
    boolean folded;
    TexasPlayer player;
	int blind;
	boolean playersdeal;
    public TexasLogic(TexasGui gui, TexasFrame tf, TexasCards tc, int blind)
    {
        this.gui = gui;
        this.tf = tf;
        this.tc = tc;
        this.bets = new Bet();
        this.blind = blind;
        bets.callAmount = blind;
        dealer = 0;
	playersdeal = false;
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
        public void newDeck(ArrayList<Texas> players)
    {
//        gui.newDeck(players);
    }
        public void housedeal(int nr)
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
            new Round(this, tf, dealer, bets, blind).round(bets);
            dealer++;
        }
	public void firstRound()
	{
		tf.hideButton();
		new Round(this, tf, dealer, bets,blind).round(bets);
		dealer++;
	}
        
       
        public void whatsNext(Bet betz)
        {
			this.bets = betz;
			if(nrCards == 5)
				{
					results();
                                        return;
				}
            if(nrCards == 0 && !playersdeal)
				{
					playersDeal();
					return;
				}
			if(nrCards == 0)
				{
					housedeal(3);
					return;
				}
            if(nrCards >= 3 &&  playersdeal)
				{
					housedeal(1);
				}
        }
        public void playersDeal()
        {
            for(Texas t : tf.players)
            {
                if(!t.folded())
                {
                    t.newCards();
                    if(t instanceof TexasBot)
                    {
                        t.hideCards();
                    }
                    pack();
                }
            }
			playersdeal = true;
			new Round(this, tf, dealer, bets, blind).round(bets);
            dealer++;
        }
        public void pack()
        {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    tf.pack();
                }
            });
        }
	public void updateTotal(Bet bets)
	{
		tf.updateTotal(bets);
	}
	public void results()
	{
            for(Texas t : tf.players)
            {
                if(t instanceof TexasBot)
                {
                    t.showCards();
                }
            }
		gui.whoWins(tf.players, tf.table.getCards());
                tf.showButton();
                pack();
	}
        public void newRound()
        {
            this.bets = new Bet();
            this.blind = blind;
            bets.callAmount = blind;
            dealer = 0;
            playersdeal = false;
            for(Texas t : tf.players)
            {
                if(!t.folded())
                    t.newRound();
            }
            pack();
            firstRound();
            
        }
        
}
