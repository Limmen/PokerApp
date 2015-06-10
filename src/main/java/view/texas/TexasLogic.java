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
    int dealer;
    boolean folded;
    TexasPlayer player;
	int callAmount;
    public TexasLogic(TexasGui gui, TexasFrame tf, TexasCards tc, int blind)
    {
        this.gui = gui;
        this.tf = tf;
        this.tc = tc;
        this.bets = new Bet();
        this.callAmount = blind;
        bets.raise(blind);
        dealer = 0;
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
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                pack();
            }
        });
			Bet betz = bets;
            new Round(this, tf, dealer, betz).round(betz);
            dealer++;
        }
	public void firstRound()
	{
		Bet betz = bets;
		new Round(this, tf, dealer, betz).round(betz);
		dealer++;
	}
        
       
        public void whatsNext()
        {
			System.out.println("What's next?!");
			if(nrCards == 0)
				{
					housedeal(3);
				}
            if(nrCards == 3)
            {
                playersDeal();
            }
        }
        public void playersDeal()
        {
            for(Texas t : tf.players)
            {
                if(!t.folded())
                {
                    t.newCards();
                    pack();
                }
            }
        }
        public void pack()
        {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    tf.pack();
                }
            });
        }
        
}
