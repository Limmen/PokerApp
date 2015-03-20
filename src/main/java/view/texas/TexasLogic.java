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
    Font Italic = new Font("Serif", Font.ITALIC, 12);
    Font Bold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    public TexasLogic(TexasGui gui, TexasFrame tf, TexasCards tc)
    {
        this.gui = gui;
        this.tf = tf;
        this.tc = tc;
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
            bots.add(gui.newPlayer(i,getPlaceholder(2)));
        }
        return bots;
    }
       public TexasPlayer generateUser()
    {
        return gui.newPlayer(-1, getPlaceholder(2));
        
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
   
}
