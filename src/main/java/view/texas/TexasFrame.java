/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.texas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.texas.Bet;
import net.miginfocom.swing.MigLayout;
import util.Texas;
import util.TexasBot;
import util.TexasPlayer;
import util.TexasTable;
/**
 *
 * @author kim
 */
public class TexasFrame extends JFrame
{
    private TexasGui gui;
    private JPanel container;
    private JLabel text;
    private JPanel buttons;
    private JButton deal;
    private JButton restart;
    public TexasTable table;
    public JLabel total;
    public JLabel blindlabel;
    public JLabel calllabel;
    TexasLogic tl;
    TexasCards tc;
    private TexasPlayer user;
    //public ArrayList<TexasPlayer> bots;
    public ArrayList<Texas> players = new ArrayList();
    //public ArrayList<TexasPlayer> folded = new ArrayList();
    private Font Title = new Font("Serif", Font.PLAIN, 20);
    private Font Italic = new Font("Serif", Font.ITALIC, 12);
    private Font Plain = new Font("Serif", Font.PLAIN, 12);
    private Font IBold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    private Font PBold = Plain.deriveFont(Plain.getStyle() | Font.BOLD);
    private Font TBold = Title.deriveFont(Title.getStyle() | Font.BOLD);
    int bet;
    int dealer;
    boolean playersdeal = false;
    int cash;
    int  blind;
    public TexasFrame(TexasGui gui, int cash, int blind)
    {
        super("Texas Hold'em");
        this.gui = gui;
        this.tc = new TexasCards(gui);
        this.cash = cash;
        this.blind = blind;
        container = new JPanel(new MigLayout("wrap 4"));
        try 
        {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
        {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        } 
        catch (Exception e) 
        {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        tl = new TexasLogic(gui, this,tc, blind);
        generatePlayers(cash);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                newGame();
                setBackground(Color.BLACK);
                pack();
                setLocationRelativeTo(null);    // centers on screen
                setVisible(true);
            }
        });

    }
    public void generatePlayers(int cash)
    {
        this.user = new TexasPlayer(tc, cash, gui, tl);
        this.players.add(this.user);
        for(int i = 0; i < 3; i++)
        {
            this.players.add(new TexasBot(tc, i+1, cash, gui, tl));
        }
    }
    public void newGame()
    {
        remove(container);
        int bet = 0;
        int dealer = 0;
        container = new JPanel(new MigLayout("wrap 4"));
        text = new JLabel("Table:");
        text.setFont(PBold);
        container.add(text, "span 4, align center");
        
        table = tl.generateTable();
        container.add(table.getPanel(), "span 4, align center");
        
        JPanel pp = new JPanel(new MigLayout("wrap 6"));
        JLabel txt = new JLabel("Cash in pot: ");
        txt.setFont(PBold);
        pp.add(txt, "span 1, align center");
        total = new JLabel("0");
        total.setFont(PBold);
        pp.add(total, "span 1, align center");
        txt = new JLabel("Blind: ");
        txt.setFont(PBold);
        pp.add(txt, "span 1, align center, gapleft 20");
        blindlabel = new JLabel(Integer.toString(blind));
        blindlabel.setFont(PBold);
        pp.add(blindlabel, "span 1, align center");
        txt = new JLabel("Call amount: ");
        txt.setFont(PBold);
        pp.add(txt, "span 1, align center, gapleft 20");
        calllabel = new JLabel(Integer.toString(blind));
        calllabel.setFont(PBold);
        pp.add(calllabel, "span 1, align center");
        container.add(pp, "span, gaptop 20, gapbottom 20, align center");

        for(Texas p: players)
        {
            container.add(p.getPanel(),"span 1, align center");
        } 
        buttons = new JPanel(new MigLayout("wrap 2"));
        deal = new JButton("Begin new round");
        deal.setFont(PBold);
        buttons.add(deal,"span 1");
        restart = new JButton("New game");
        restart.setFont(PBold);
        buttons.add(restart, "span 1");
        container.add(buttons,"wrap, span, align center");
        deal.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
			{   
                            for(Texas t : players)
                            {
                                if(t.getCash() <= 0)
                                {
                                    container.remove(t.getPanel());
                                    players.remove(t);
                                }
                            }
                            tl.newRound();
				//tl.firstRound();
				//pack(); 
	        }
	});
        restart.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    playersdeal = false;
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
							reset();
                        }
                    });
                   
	        }
	});
        txt = new JLabel("Copyright \u00a9 Kim Hammar all rights reserved");
        txt.setFont(Plain);
        container.add(txt, "span 1, gaptop 20");
        add(container, BorderLayout.CENTER);
        pack();
    }
    public ArrayList<Texas> getPlayers()
    {
        return players;
    }
    public void bet()
    {
//        tl.chooseDealer();
  //      tl.bet(dealer, bet, players.size());
    }
    public void updateTotal(Bet bets)
    {
		total.setText(Integer.toString(bets.getTotalBet()));
        SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					pack();
				}
			});
    }
    public void updateBlind(int b)
    {
		blindlabel.setText(Integer.toString(b));
        SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					pack();
				}
			});
    }
	public void reset()
	{
		new TexasOptions(gui);
		dispose();
	}
	public void dealText(String txt)
	{
		deal.setText(txt);
        SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					pack();
				}
			});
	}
	public void hideButton()
	{
	deal.setVisible(false);
        SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					pack();
				}
			});		
	}
        public void showButton()
	{
	deal.setVisible(true);
        SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					pack();
				}
			});		
	}
        public void setCall(int call)
        {
            calllabel.setText(Integer.toString(call));
            SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					pack();
				}
			});	
            
        }
}
