/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.texas.Bet;
import model.texas.Player;
import model.texas.User;
import net.miginfocom.swing.MigLayout;
import view.texas.TexasCards;
import view.texas.TexasGui;
import view.texas.TexasLogic;

/**
 *
 * @author kim
 */
public class TexasPlayer implements Texas 
{
    TexasPlayer me = this;
    private Font Title = new Font("Serif", Font.PLAIN, 20);
    private Font Italic = new Font("Serif", Font.ITALIC, 12);
    private Font Plain = new Font("Serif", Font.PLAIN, 12);
    private Font IBold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    private Font PBold = Plain.deriveFont(Plain.getStyle() | Font.BOLD);
    private Font TBold = Title.deriveFont(Title.getStyle() | Font.BOLD);
    JPanel panel;
    private JButton raise;
    private JButton call;
    private JButton fold;
    JPanel buttons;
    private JPanel cardPanel;
    public JLabel cash;
    public JLabel bet;
    private JLabel deal;
    private JLabel turn;
    private JPanel dealpanel;
    private Player player;
    private BufferedImage image;
    private TexasCards tc;
    private JLabel card1;
    private JLabel card2;
    private ArrayList<JLabel> realcards;
    private ArrayList<JLabel> backcards;
    private JLabel turns;
    private JLabel dealer;
    private boolean folded;
    private TexasGui gui;
    private TexasLogic tl;
    private Round round;
    private Bet bets;
    public TexasPlayer(TexasCards tc, int cash, TexasGui gui, TexasLogic tl)
    {
        this.player = new User(cash);
        this.tc = tc;
        this.tl = tl;
        this.folded = false;
        this.gui = gui;
        backcards();
        image = tc.getTurn();
        turns = new JLabel(new ImageIcon(image));
        image = tc.getDealer();
        dealer = new JLabel(new ImageIcon(image));
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
        dealpanel = new JPanel(new MigLayout("wrap 2"));
        cardPanel = new JPanel(new MigLayout("wrap 2"));
        
        raise.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if(raise())
                {
                    RaiseFrame rf = new RaiseFrame(me);
                }
            }
        });
        fold.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                bet.setText("folded");
				folded = true;
                buttons.setVisible(false);
                turns.setVisible(false);
                pack();
                round.round(bets);
                
            }
        });
        call.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                int oldBet = player.getBet();
                if(player.getCash()>=bets.getCallAmount())
                {
                    player.call(bets.getCallAmount());
                    bets.addBet(bets.getCallAmount() - oldBet);
                }
                else
                {
                    player.call(player.getCash());
                    bets.addBet(player.getCash() - oldBet);
                    //All in (SHow cards?);
                }
                updateVals(Integer.toString(player.getBet()), Integer.toString(player.getCash()));
                if(player.getCash() == 0)
                    allIn();
                
                buttons.setVisible(false);
                turns.setVisible(false);
                pack();
                round.checkChanges(bets);
                round.round(bets);
            }
        });
        
        init();
    }
    public void pack()
    {
        tl.pack();
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
            cash = new JLabel(Integer.toString(player.getCash()));
            this.cash.setFont(PBold);
            panel.add(cash, "span 1, align center");
            text = new JLabel("Bet");
            text.setFont(PBold);
            panel.add(text, "span 1, align center");
            bet = new JLabel("0");
            this.bet.setFont(PBold);
            panel.add(bet, "span 1, align center");
            panel.add(cardPanel, "span");
            placeholders();
            dealpanel.add(dealer, "span 1, align center");
            dealpanel.add(turns, "span 1, align center");
            dealer.setVisible(false);
            turns.setVisible(false);
            panel.add(dealpanel, "span 2, align center");
            panel.add(buttons, "span,align center");
            buttons.setVisible(false);
    }
    @Override
    public JPanel getPanel()
    {
        return panel;
    }
    @Override
    public int getBet()
    {
        return player.getBet();
    }
    @Override
    public Player getPlayer()
    {
        return player;
    }
    public boolean raise()
    {
        if(bets.getCallAmount() < player.getCash())
        {
            return true;
        }
        else
            return false;
    }
    public void placeholders()
    {
        image = tc.getPlaceholder();
        card1 = new JLabel(new ImageIcon(image));
        card2 = new JLabel(new ImageIcon(image));
        cardPanel.removeAll();
        cardPanel.add(card1);
        cardPanel.add(card2);
    }
    public void backcards()
    {
        backcards = new ArrayList();
        image = tc.getBackCard();
        JLabel backcard1 = new JLabel(new ImageIcon(image));
        JLabel backcard2 = new JLabel(new ImageIcon(image));
        backcards.add(backcard1);
        backcards.add(backcard2);
        
    }
    @Override
    public boolean folded()
    {
        return this.folded;
    }
    public void newDeal()
    {
        player.init();
        bet.setText(Integer.toString(player.getBet()));
    }
    @Override
    public void turn(Bet bet, Round r)
    {
        if(bet.getCallAmount() == player.getBet())
        {
            call.setText("Check");
        }
        else
        {
            call.setText("Call");
        }
        this.bets = bet;
        this.round = r;
        turns.setVisible(true);
        buttons.setVisible(true);
        tl.pack();
    }
    @Override
    public void newCards()
    {
        realcards = new ArrayList();
        gui.texasplayerDeal(player);
        ArrayList<Card> cards = player.getHand();
        
        Card c = cards.get(0);
        image = tc.readImage(c.getId());
        JLabel Card = new JLabel(new ImageIcon(image));
        this.card1 = Card;
        
        c = cards.get(1);
        image = tc.readImage(c.getId());
        Card = new JLabel(new ImageIcon(image));
        this.card2 = Card;
        
        cardPanel.removeAll();
        cardPanel.add(card1);
        cardPanel.add(card2);
        realcards.add(card1);
        realcards.add(card2);
    }
    @Override
    public void youDeal()
    {
        dealer.setVisible(true);
        tl.pack();
    }
    @Override
    public void dealDone()
    {
        dealer.setVisible(false);
        tl.pack();
    }
	public boolean raise(String val)
	{
		int raiseAmount = Integer.parseInt(val) + bets.getCallAmount();
		if(raiseAmount > player.getCash())
			{
				return false;
			}
		else
			{
				player.raise(Integer.parseInt(val) + (bets.getCallAmount() - player.getBet()));
				bets.raise(Integer.parseInt(val));
				updateVals(Integer.toString(player.getBet()), Integer.toString(player.getCash()));
                                if(player.getCash() == 0)
                                    allIn();
				buttons.setVisible(false);
				turns.setVisible(false);
				pack();
				round.checkChanges(bets);
				round.round(bets); 
						   return true;		   
			}
	}
	public void updateVals(String betting, String cash)
	{
		this.bet.setText(betting);
		this.cash.setText(cash);
		pack();
	}
        @Override
        public void youWin(int amount)
        {
            player.winCash(amount);
            cash.setText(Integer.toString(player.getCash()));
            bet.setText("<html><font color=green>You won " + (amount - player.getBet()) + "</font></html>");
        }
        @Override
        public void youLoose()
        {
            cash.setText(Integer.toString(player.getCash()));
            bet.setText("<html> <font color=red> You lost " + player.getBet() + "</font> </html>");
        }
        @Override public int getCash()
        {
            return player.getCash();
        }
        @Override
        public void hideCards()
        {
            cardPanel.removeAll();
            for(JLabel b : backcards)
            {
                cardPanel.add(b);
            }
        }
        @Override
        public void showCards()
        {
            cardPanel.removeAll();
            for(JLabel c : realcards)
            {
                cardPanel.add(c);
            }
        }
        @Override
        public void newRound()
        {
            player.newRound();
            cash.setText(Integer.toString(player.getCash()));
            bet.setText(Integer.toString(player.getBet()));
            this.folded = false;
            placeholders();
        }
        @Override
        public void allIn()
        {
            cash.setText("All in");
        }
}
