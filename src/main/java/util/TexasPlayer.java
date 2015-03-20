/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.texas.Player;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author kim
 */
public class TexasPlayer 
{
    private Player player;
    private JPanel panel;
    private JLabel cash;
    private JLabel bet;
    private ArrayList<JLabel> cards;
    Font Italic = new Font("Serif", Font.ITALIC, 12);
    Font Bold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    
    public TexasPlayer(Player player, ArrayList<JLabel> cards)
    {
        this.player = player;
        this.cards = cards;
        init();
        updateCards();
    }
    public void init()
    {
            panel = new JPanel(new MigLayout("wrap 2"));
            JLabel text = new JLabel(player.getName());
            text.setFont(Bold);
            panel.add(text, "span, align center");
            text = new JLabel("Cash");
            text.setFont(Bold);
            panel.add(text, "span 1, align center");
            JLabel cash = new JLabel("500");
            cash.setFont(Bold);
            panel.add(cash, "span 1, align center");
            text = new JLabel("Bet");
            text.setFont(Bold);
            panel.add(text, "span 1, align center");
            JLabel bet = new JLabel("0");
            bet.setFont(Bold);
            panel.add(bet, "span 1, align center");
    }
    public Player getPlayer()
    {
        return player;
    }
    public JPanel getPanel()
    {
        return panel;
    }
    public JLabel getCash()
    {
        return cash;
    }
    public JLabel getBet()
    {
        return bet;
    }
    public void setCards(ArrayList<JLabel> cards)
    {
        for(JLabel c : this.cards)
        {
            panel.remove(c);
        }
        this.cards = cards;
        updateCards();
        
    }
    public void updateCards()
    {
        for(JLabel c : cards)
        {
            panel.add(c);
        }
    }
    public ArrayList<Card> getCards()
    {
        return player.getHand();
    }
    public void newDeal()
    {
        //init();
        player.init();
    }

}
