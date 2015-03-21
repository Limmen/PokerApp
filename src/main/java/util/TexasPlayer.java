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
    private TexasPlayer me;
    private Player player;
    private JPanel panel;
    private JLabel cash;
    private JLabel bet;
    private JLabel deal;
    private JPanel dealpanel;
    private ArrayList<JLabel> cards;
    Font Italic = new Font("Serif", Font.ITALIC, 12);
    Font Bold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    private JButton raise;
    private JButton call;
    private JButton fold;
    JPanel buttons;
    private int playersLeft;
    private TexasLogic tl;
    private int callAmount = 0;
    public TexasPlayer(Player player, ArrayList<JLabel> cards, TexasLogic tl)
    {
        this.player = player;
        this.cards = cards;
        this.me = this;
        this.tl = tl;
        this.deal = tl.getDeal();
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
        init();
        
            raise.addActionListener(new ActionListener() 
           {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    RaiseFrame rf = new RaiseFrame(me);
	        }
           });
            fold.addActionListener(new ActionListener() 
           {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    bet.setText("folded");
                    cleanUp();
	        }
           });
           call.addActionListener(new ActionListener() 
           {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    addBet(callAmount);
                    cleanUp();
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
            cash = new JLabel("500");
            this.cash.setFont(Bold);
            panel.add(cash, "span 1, align center");
            text = new JLabel("Bet");
            text.setFont(Bold);
            panel.add(text, "span 1, align center");
            bet = new JLabel("0");
            this.bet.setFont(Bold);
            panel.add(bet, "span 1, align center");
            updateCards();
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
            dealpanel = new JPanel(new MigLayout());
            dealpanel.add(deal, "span, align center");
            panel.add(dealpanel, "wrap, span, align center");
            panel.add(buttons, "wrap, span, align center");
            hide();
    }
    public ArrayList<Card> getCards()
    {
        return player.getHand();
    }
    public void newDeal()
    {
        player.init();
    }
    public int bet(int bet, int playersLeft)
    {
        this.playersLeft = playersLeft;
        this.callAmount = bet;
        if(isUser())
        {   
            panel.add(buttons, "span, align center");
            show();
            return 0;
        }
        else
        {
            show();
            return bet + 0;
        }
    }
    public void addBet(int val)
    {
        player.addBet(val);
        cash.setText(Integer.toString(player.getCash()));
        bet.setText(Integer.toString(player.getBet()));
        tl.pack();
    }
    public void cleanUp()
    {
        panel.remove(buttons);
        hide();
        tl.pack();
        tl.bet(1, getBet(), playersLeft);
    }
    public boolean isUser()
    {
        return player.isUser();
    }
    public void hide()
    {
        deal.setVisible(false);
        buttons.setVisible(false);
    }
    public void show()
    {
        deal.setVisible(true);
        if(isUser())
            buttons.setVisible(true);
    }
}
