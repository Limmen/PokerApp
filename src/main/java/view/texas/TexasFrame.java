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
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
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
    TexasLogic tl;
    TexasCards tc;
    private TexasPlayer user;
    public ArrayList<TexasPlayer> bots;
    public ArrayList<TexasPlayer> players = new ArrayList();
    public ArrayList<TexasPlayer> folded = new ArrayList();
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
    public TexasFrame(TexasGui gui, int cash)
    {
        super("Texas Hold'em");
        this.gui = gui;
        this.cash = cash;
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
        
        newGame();
        
        
               
        setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);    // centers on screen
        setVisible(true);
    }
    
    public void newGame()
    {
        remove(container);
        this.tc = new TexasCards(gui);
        tl = new TexasLogic(gui, this,tc, cash);
        players = new ArrayList();
        int bet = 0;
        int dealer = 0;
        container = new JPanel(new MigLayout("wrap 4"));
        text = new JLabel("Table:");
        text.setFont(PBold);
        container.add(text, "span 4, align center");
        
        table = tl.generateTable();
        container.add(table.getPanel(), "span 4, align center");
        
        JPanel pp = new JPanel(new MigLayout("wrap 2"));
        JLabel txt = new JLabel("Total money at stake: ");
        txt.setFont(PBold);
        pp.add(txt, "span 1, align center");
        total = new JLabel(Integer.toString(gui.getTotal(players)));
        total.setFont(PBold);
        pp.add(total, "span 1, align center");
        container.add(pp, "span, gaptop 20, gapbottom 20, align center");
        
        user = tl.generateUser();
        bots = tl.generateBots(3);
        players.add(user);
        for(TexasPlayer p : bots)
        {
            players.add(p);
        }
        for(TexasPlayer p: players)
        {
            container.add(p.getPanel(),"span 1, align center");
        }
        buttons = new JPanel(new MigLayout("wrap 2"));
        deal = new JButton("Deal");
        deal.setFont(PBold);
        buttons.add(deal,"span 1");
        restart = new JButton("New game");
        restart.setFont(PBold);
        buttons.add(restart, "span 1");
        container.add(buttons,"span, align center");
        deal.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    if(!playersdeal)
                    {
                        for(TexasPlayer p : folded)
                        {
                            if(p.getPlayer().getCash() > 0)
                            {
                                players.add(p);
                                folded.remove(p);
                            }
                        }
                        for(TexasPlayer p : players)
                        {
                            if(p.getPlayer().getCash() < 1)
                            {
                                players.remove(p);
                                folded.add(p);
                            }
                        }
                        for(TexasPlayer p : folded)
                        {
                            container.remove(p.getPanel());
                        }
                        folded = new ArrayList();
                        playersdeal = true;
                        tl.newDeck(players);
                        tl.playersDeal(players);
                        bet();
                        pack();
                    }
	        }
	});
        restart.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    playersdeal = false;
                    newGame();
                    pack();
	        }
	});
        txt = new JLabel("Copyright \u00a9 Kim Hammar all rights reserved");
        txt.setFont(Plain);
        container.add(txt, "span 1, gaptop 20");
        add(container, BorderLayout.CENTER);
    }
    public ArrayList<TexasPlayer> getPlayers()
    {
        return players;
    }
    public void bet()
    {
        tl.chooseDealer();
        tl.bet(dealer, bet, players.size());
    }
    public void updateTotal()
    {
         total.setText(Integer.toString(gui.getTotal(players)));
         pack();
    }
}
