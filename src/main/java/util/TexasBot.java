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
import javax.swing.Timer;
import model.texas.Bet;
import model.texas.Bot;
import model.texas.Player;
import net.miginfocom.swing.MigLayout;
import view.texas.TexasCards;
import view.texas.TexasGui;
import view.texas.TexasLogic;

/**
 *
 * @author kim
 */
public class TexasBot implements Texas 
{
    TexasBot me = this;
    private Font Title = new Font("Serif", Font.PLAIN, 20);
    private Font Italic = new Font("Serif", Font.ITALIC, 12);
    private Font Plain = new Font("Serif", Font.PLAIN, 12);
    private Font IBold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    private Font PBold = Plain.deriveFont(Plain.getStyle() | Font.BOLD);
    private Font TBold = Title.deriveFont(Title.getStyle() | Font.BOLD);
    JPanel panel;
    private JPanel cardPanel;
    public JLabel cash;
    public JLabel bet;
    public JLabel call2;
    private JLabel deal;
    private JLabel turn;
    private JPanel dealpanel;
    private Player player;
    private TexasCards tc;
	private int callAmount = 0;
    private BufferedImage image;
    private JLabel card1;
    private JLabel card2;
    private JLabel turns;
    private JLabel dealer;
    private boolean folded;
    private TexasGui gui;
    private TexasLogic tl;
    JPanel buttons;
    public TexasBot(TexasCards tc, int id, int cash, TexasGui gui, TexasLogic tl)
    {
        this.player = new Bot(id, cash);
        this.tc = tc;
        this.tl = tl;
        this.gui = gui;
        image = tc.getTurn();
        turns = new JLabel(new ImageIcon(image));
        image = tc.getDealer();
        dealer = new JLabel(new ImageIcon(image));
        this.folded = false;
        JButton call = new JButton("Call");
        call.setFont(PBold);
        JButton fold = new JButton("Fold");
        fold.setFont(PBold);
        JButton raise = new JButton("Raise");
        raise.setFont(PBold);
        buttons = new JPanel(new MigLayout("wrap 3"));
        buttons.add(call, "span 1, align center");
        buttons.add(fold, "span 1, align center");
        buttons.add(raise, "span 1, align center");
        dealpanel = new JPanel(new MigLayout("wrap 2"));
        cardPanel = new JPanel(new MigLayout("wrap 2"));
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                init();
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
            cash = new JLabel(Integer.toString(player.getCash()));
            this.cash.setFont(PBold);
            panel.add(cash, "span 1, align center");
            text = new JLabel("Bet");
            text.setFont(PBold);
            panel.add(text, "span 1, align center");
            bet = new JLabel("0");
            this.bet.setFont(PBold);
            panel.add(bet, "span 1, align center");
			text = new JLabel("Call amount");
			text.setFont(PBold);
			panel.add(text, "span 1, align center");
			call2 = new JLabel("0");
			call2.setFont(PBold);
			panel.add(call2, "span 1, align center");
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
        public void placeholders()
    {
        image = tc.getPlaceholder();
        card1 = new JLabel(new ImageIcon(image));
        card2 = new JLabel(new ImageIcon(image));
        cardPanel.add(card1);
        cardPanel.add(card2);
        panel.add(cardPanel,  "span");   
    }
    @Override
    public boolean folded()
    {
        return this.folded;
    }
    @Override
    public void turn(Bet bet, Round r)
    {
        turns.setVisible(true);
        tl.pack();
        delay(r, bet);
    }
    public void newDeal()
    {
        player.init();
        bet.setText(Integer.toString(player.getBet()));
    }
    public void delay(Round r, Bet bet)
    {
        System.out.println("DELAY!");
        int delay = 2000;
        final Round round = r;
        final Bet bets = bet;
        Timer timer = new Timer( delay, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ){
                System.out.println("Delay done!");
                turns.setVisible(false);
                round.round(bets);
            }
        });
        timer.setRepeats( false );
        timer.start();
    }
    @Override
    public void newCards()
    {
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
	@Override
	public void setCall(int callz)
	{
		this.callAmount = callz;
		this.call2.setText(Integer.toString(callz));
		tl.pack();
	}
}
