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
import javax.swing.Timer;
import model.texas.Bet;
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
    private Font Title = new Font("Serif", Font.PLAIN, 20);
    private Font Italic = new Font("Serif", Font.ITALIC, 12);
    private Font Plain = new Font("Serif", Font.PLAIN, 12);
    private Font IBold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    private Font PBold = Plain.deriveFont(Plain.getStyle() | Font.BOLD);
    private Font TBold = Title.deriveFont(Title.getStyle() | Font.BOLD);
    private JButton raise;
    private JButton call;
    private JButton fold;
    JPanel buttons;
    private int playersLeft;
    private TexasLogic tl;
    private int callAmount = 0;
    private int dealer;
    private Bet bets;
    public TexasPlayer(Player player, ArrayList<JLabel> cards, TexasLogic tl, Bet bets)
    {
        this.player = player;
        this.cards = cards;
        this.me = this;
        this.tl = tl;
        this.deal = tl.getDeal();
        this.bets = bets;
        this.call = new JButton("Call");
        call.setFont(PBold);
        this.fold = new JButton("Fold");
        fold.setFont(PBold);
        this.raise = new JButton("Raise");
        raise.setFont(PBold);
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
                    fold();
                    cleanUp();
	        }
           });
           call.addActionListener(new ActionListener() 
           {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    userCall(callAmount);
                    cleanUp();
	        }
           });
    }
    public void init()
    {
            panel = new JPanel(new MigLayout("wrap 2"));
            JLabel text = new JLabel(player.getName());
            text.setFont(PBold);
            panel.add(text, "span, align center");
            text = new JLabel("Cash");
            text.setFont(PBold);
            panel.add(text, "span 1, align center");
            cash = new JLabel("500");
            this.cash.setFont(PBold);
            panel.add(cash, "span 1, align center");
            text = new JLabel("Bet");
            text.setFont(PBold);
            panel.add(text, "span 1, align center");
            bet = new JLabel("0");
            this.bet.setFont(PBold);
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
    public void bet(int bet, int playersLeft, int dealer)
    {
        this.playersLeft = playersLeft;
        this.callAmount = bets.getBet();
        this.dealer = dealer;
        if(isUser())
        {   
            panel.add(buttons, "span, align center");
            show();
        }
        else
        {
            show();
            update(tl.botBet(this, callAmount));
            delay();
        }
    }
    public void userCall(int val)
    {
        player.call(val);
        cash.setText(Integer.toString(player.getCash()));
        bet.setText(Integer.toString(player.getBet()));
        bets.setBet(val);
        tl.pack();
    }
    public void userRaise(int val)
    {
        player.raise(val);
        cash.setText(Integer.toString(player.getCash()));
        bet.setText(Integer.toString(player.getBet()));
        bets.setBet(val);
        tl.pack();
    }
    public void botBet()
    {
        cash.setText(Integer.toString(player.getCash()));
        bet.setText(Integer.toString(player.getBet()));
        tl.pack();
    }
    public void cleanUp()
    {
        hide();
        tl.pack();
        tl.bet(dealer, getBet(), playersLeft);
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
    public void update(String res)
    {
        if(res.equalsIgnoreCase("fold"))
        {
            bet.setText("folded");
            fold();
        }
        if(res.equalsIgnoreCase("call"))
        {
            botBet();
        }
        if(res.equalsIgnoreCase("raise"))
        {
            botBet();
            bets.setBet(player.getBet());
        }
    }
    public void delay()
    {
        int delay = 2000;
        Timer timer = new Timer( delay, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ){
                System.out.println("timer went off");
                cleanUp();
            }
        });
        timer.setRepeats( false );
        timer.start();
    }
    public void fold()
    {
        tl.fold(me);
    }
}
