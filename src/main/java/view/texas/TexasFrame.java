/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.texas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
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
    private BufferedImage image;
    private TexasTable table;
    TexasLogic tl;
    TexasCards tc;
    private ArrayList<TexasTable> tableCards;
    private TexasPlayer user;
    private ArrayList<TexasPlayer> bots;
    private ArrayList<TexasPlayer> players;
    private ArrayList<TexasPlayer> folded;
    private Font Italic = new Font("Serif", Font.ITALIC, 12);
    private Font Bold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    int bet;
    int dealer;
    public TexasFrame(TexasGui gui)
    {
        super("Texas Hold'em");
        this.gui = gui;
        this.tc = new TexasCards(gui);
        tl = new TexasLogic(gui, this,tc);
        players = new ArrayList();
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
        this.setLayout(new MigLayout());
        
        newGame();
        
        deal.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    tl.newDeck(players);
                    tl.playersDeal(players);
                    pack();
                    if(dealer == 0)
                    tl.userBet(bet, players.size(),user);
                    else
                        tl.bet(dealer, bet, players.size());
                    pack();
	        }
	});
       
        add(container);
        setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);    // centers on screen
        setVisible(true);
    }
    
    public void newGame()
    {
        int bet = 0;
        int dealer = 0;
        container = new JPanel(new MigLayout("wrap 4"));
        text = new JLabel("Table:");
        text.setFont(Bold);
        container.add(text, "span 4, align center");
        
        table = tl.generateTable();
        container.add(table.getPanel(), "span 4, align center");
        
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
        deal.setFont(Bold);
        buttons.add(deal,"span 1");
        restart = new JButton("New game");
        restart.setFont(Bold);
        buttons.add(restart, "span 1");
        container.add(buttons,"span, align center");
    }
    public ArrayList<TexasPlayer> getPlayers()
    {
        return players;
    }
}
