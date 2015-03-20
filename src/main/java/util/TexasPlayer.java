/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.blackjack.User;
import model.texas.Player;
import net.miginfocom.swing.MigLayout;
import view.texas.TexasLogic;

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
    private JLabel deal;
    private ArrayList<JLabel> cards;
    Font Italic = new Font("Serif", Font.ITALIC, 12);
    Font Bold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    private JButton raise;
    private JButton call;
    private JButton fold;
    JPanel buttons;
    private int playersLeft;
    private TexasLogic tl;
    public TexasPlayer(Player player, ArrayList<JLabel> cards)
    {
        this.player = player;
        this.cards = cards;
        init();
        updateCards();
        this.call = new JButton("Call");
        call.setFont(Bold);
        this.fold = new JButton("Fold");
        fold.setFont(Bold);
        this.raise = new JButton("Raise");
        raise.setFont(Bold);
        buttons = new JPanel(new MigLayout("wrap 3"));
        buttons.add(call, "span 1, align center");
        buttons.add(fold, "span 1, align center");
        buttons.add(raise, "span 1, align center");
        
            raise.addActionListener(new ActionListener() 
           {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    panel.remove(buttons);
                    panel.remove(deal);
                    tl.pack();
                    tl.bet(1, getBet(), playersLeft);
	        }
           });
            fold.addActionListener(new ActionListener() 
           {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    bet.setText("Folded");
                    tl.bet(1, getBet(), playersLeft);
	        }
           });
           call.addActionListener(new ActionListener() 
           {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    bet.setText("Call");
                    tl.bet(1, getBet(), playersLeft);
	        }
           });
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
            bet = new JLabel("0");
            bet.setFont(Bold);
            panel.add(bet, "span 1, align center");
    }
    public JPanel getPanel()
    {
        return panel;
    }
    public int getBet()
    {
        return player.getBet();
    }
    public Player getPlayer()
    {
        return player;
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
        player.init();
    }
    public int bet(JLabel deal, int bet, int playersLeft)
    {
        System.out.println("Name: " + player.getName());
        this.deal = deal;
        this.playersLeft = playersLeft;
        int res = player.bet();
        if(res == 1)
        {   
            panel.add(buttons, "span, align center");
            panel.add(deal, "span, align center");
            return 0;
        }
        panel.add(deal, "span, align center");
        return bet + 0;
    }
    public void setTexasLogic(TexasLogic tl)
    {
        this.tl = tl;
    }

}
