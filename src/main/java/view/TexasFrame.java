/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import util.Card;

/**
 *
 * @author kim
 */
public class TexasFrame extends JFrame
{
    private Gui gui;
    private final JPanel container;
    JPanel Usercards;
    JPanel Housecards;
    private final JButton Deal;
    private JLabel text; 
    private final JButton Hit;
    private final JButton Stand;
    private final JButton Restart;
    final JLabel Score1;
    final JLabel Score2;
    final JLabel Value1;
    final JLabel Value2; 
    boolean gameover;
    ArrayList<JLabel> uCards;
    ArrayList<JLabel> hCards;
    private BufferedImage image;
    TexasLogic tl;
    ReadCards rc;
    public TexasFrame(Gui gui)
    {
        super("Texas Hold'em");
        gameover = false;
        this.gui = gui;
        uCards = new ArrayList();
        hCards = new ArrayList();
        tl.newGame();
        this.rc = new ReadCards(gui);
        tl = new TexasLogic(gui, this,rc);
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
        container = new JPanel(new MigLayout("wrap 4"));
        Font Italic = new Font("Serif", Font.ITALIC, 12);
        Font Bold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
        
        text = new JLabel("You");
        text.setFont(Bold);
        container.add(text, "span 1, align center");
        Score1 = new JLabel("score: 0");
        Score1.setFont(Bold);
        container.add(Score1, "span1, align center");
        text = new JLabel("House");
        text.setFont(Bold);
        container.add(text, "span 1, align center, gap 80");
        Score2 = new JLabel("score: 0");
        Score2.setFont(Bold);
        container.add(Score2, "span1, align center");
        
        Usercards = new JPanel();
        image = rc.getCard("user");
        JLabel Card = new JLabel(new ImageIcon(image));
        uCards.add(Card);
        Usercards.add(Card);
        
        image = rc.getCard("user");
        Card = new JLabel(new ImageIcon(image));
        uCards.add(Card);
        Usercards.add(Card);
        container.add(Usercards, "span 2, align center");
        
        Housecards = new JPanel();
        image = rc.getCard("house");
        Card = new JLabel(new ImageIcon(image));
        Housecards.add(Card);
        hCards.add(Card);
        
        image = rc.getBackCard("house");
        Card = new JLabel(new ImageIcon(image));
        Housecards.add(Card);
        hCards.add(Card);
        container.add(Housecards, "gap 80, span 2,align center");
        
        Value1 = new JLabel("0");
        Value1.setFont(Bold);
        container.add(Value1, "span 2, align center");
        Value2 = new JLabel("0");
        Value2.setFont(Bold);
        container.add(Value2, "span 2, align center, gap 80");
        
        Hit = new JButton("Hit");
        Hit.setFont(Bold);
        container.add(Hit,"span 1, align center");
        
        Stand = new JButton("Stand");
        Stand.setFont(Bold);
        container.add(Stand,"span 1, align center");
        
        Deal = new JButton("Deal");
        Deal.setFont(Bold);
        container.add(Deal,"span 2, align center, gap 80");
        
        Restart = new JButton("New Game");
        Restart.setFont(Bold);
        container.add(Restart, "span4, align center");
        tl.updateValue();
        Deal.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    if(gameover == false)
                    {
                        tl.updateScore("house");
                        tl.setScore("house");
                    }
                    gameover = false;
                    tl.removeCards();
                    pack();
                    tl.newDeck();
                    BufferedImage image;
                    JLabel Card;
                    uCards = new ArrayList<JLabel>();
                    hCards = new ArrayList<JLabel>();
                    image = rc.getCard("user");
                    Card = new JLabel(new ImageIcon(image));
                    Usercards.add(Card);
                    uCards.add(Card);
                    image = rc.getCard("user");
                    Card = new JLabel(new ImageIcon(image));
                    Usercards.add(Card);
                    uCards.add(Card);
                    image = rc.getCard("house");
                    Card = new JLabel(new ImageIcon(image));
                    Housecards.add(Card);
                    hCards.add(Card);
                    image = rc.getBackCard("house");
                    Card = new JLabel(new ImageIcon(image));
                    Housecards.add(Card);
                    hCards.add(Card);
                    tl.updateValue();
                    pack();
	        }
	});
        
        Hit.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    if(gameover != true)
                    {
                        JLabel Card;
                        BufferedImage image ;
                        image = rc.getCard("user");
                        Card = new JLabel(new ImageIcon(image));
                        Usercards.add(Card);
                        uCards.add(Card);
                        image = rc.getBackCard("house");
                        if(image != null)
                        {
                            Card = new JLabel(new ImageIcon(image));
                            hCards.add(Card);
                            Housecards.add(Card);
                        }
                        pack();
                        tl.updateValue();
                    }
	        }
	});
        Stand.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    Boolean houseHit = true;
                    if(gameover != true)
                    {
                        JLabel Card;
                        while(houseHit != false)
                        {
                            houseHit = false;
                            image = rc.getBackCard("house");
                            if(image != null)
                            {
                                Card = new JLabel(new ImageIcon(image));
                                hCards.add(Card);
                                Housecards.add(Card);
                                tl.updateValue();
                                pack();
                                houseHit = true;
                            }
                            
                        }
                        tl.updateValue();
                        pack();
                        if(gameover != true)
                        {
                            tl.getResult();
                            pack();
                        }
                    }
	        }
	});
        Restart.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    gameover = false;
                    tl.removeCards();
                    
                    pack(); 
                    tl.newGame();
                    BufferedImage image;
                    JLabel Card; 
                    uCards = new ArrayList<JLabel>();
                    hCards = new ArrayList<JLabel>();
                    image = rc.getCard("user");
                    Card = new JLabel(new ImageIcon(image));
                    Usercards.add(Card);
                    uCards.add(Card);
                    image = rc.getCard("user");
                    Card = new JLabel(new ImageIcon(image));
                    Usercards.add(Card);
                    uCards.add(Card);
                    image = rc.getCard("house");
                    Card = new JLabel(new ImageIcon(image));
                    Housecards.add(Card);
                    hCards.add(Card);
                    image = rc.getBackCard("house");
                    Card = new JLabel(new ImageIcon(image));
                    Housecards.add(Card);
                    hCards.add(Card);
                    tl.updateValue();
                    pack();  
	        }
	});
        
        add(container);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);    // centers on screen
        setVisible(true);
    }
}
