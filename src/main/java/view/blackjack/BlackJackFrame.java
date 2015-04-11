/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.blackjack;

import java.awt.BorderLayout;
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

/**
 *
 * @author kim
 */
public class BlackJackFrame extends JFrame
{
    private BlackJackGui gui;
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
    BlackJackLogic bl;
    BlackJackCards bc;
    private Font Title = new Font("Serif", Font.PLAIN, 20);
    private Font Italic = new Font("Serif", Font.ITALIC, 12);
    private Font Plain = new Font("Serif", Font.PLAIN, 12);
    private Font IBold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    private Font PBold = Plain.deriveFont(Plain.getStyle() | Font.BOLD);
    private Font TBold = Title.deriveFont(Title.getStyle() | Font.BOLD);
    public BlackJackFrame(BlackJackGui gui)
    {
        super("BlackJack");
        gameover = false;
        this.gui = gui;
        uCards = new ArrayList();
        hCards = new ArrayList();
        this.bc = new BlackJackCards(gui);
        bl = new BlackJackLogic(gui, this,bc);
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
        container = new JPanel(new MigLayout("wrap 4"));
        
        text = new JLabel("You");
        text.setFont(PBold);
        container.add(text, "span 1, align center");
        Score1 = new JLabel("score: 0");
        Score1.setFont(PBold);
        container.add(Score1, "span 1, align center");
        text = new JLabel("House");
        text.setFont(PBold);
        container.add(text, "span 1, align center, gap 80");
        Score2 = new JLabel("score: 0");
        Score2.setFont(PBold);
        container.add(Score2, "span1, align center");
        
        Usercards = new JPanel();
        image = bc.getCard("user");
        JLabel Card = new JLabel(new ImageIcon(image));
        uCards.add(Card);
        Usercards.add(Card);
        
        image = bc.getCard("user");
        Card = new JLabel(new ImageIcon(image));
        uCards.add(Card);
        Usercards.add(Card);
        container.add(Usercards, "span 2, align center");
        
        Housecards = new JPanel();
        image = bc.getCard("house");
        Card = new JLabel(new ImageIcon(image));
        Housecards.add(Card);
        hCards.add(Card);
        
        image = bc.getBackCard("house");
        Card = new JLabel(new ImageIcon(image));
        Housecards.add(Card);
        hCards.add(Card);
        container.add(Housecards, "gap 80, span 2,align center");
        
        Value1 = new JLabel("0");
        Value1.setFont(PBold);
        container.add(Value1, "span 2, align center");
        Value2 = new JLabel("0");
        Value2.setFont(PBold);
        container.add(Value2, "span 2, align center, gap 80");
        
        Hit = new JButton("Hit");
        Hit.setFont(PBold);
        container.add(Hit,"span 1, align center");
        
        Stand = new JButton("Stand");
        Stand.setFont(PBold);
        container.add(Stand,"span 1, align center");
        
        Deal = new JButton("Deal");
        Deal.setFont(PBold);
        container.add(Deal,"span 2, align center, gap 80");
        
        Restart = new JButton("New Game");
        Restart.setFont(PBold);
        container.add(Restart, "span4, align center");
        bl.updateValue();
        Deal.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    if(gameover == false)
                    {
                        bl.updateScore("house");
                        bl.setScore("house");
                    }
                    gameover = false;
                    bl.removeCards();
                    pack();
                    bl.newDeck();
                    BufferedImage image;
                    JLabel Card;
                    uCards = new ArrayList<JLabel>();
                    hCards = new ArrayList<JLabel>();
                    image = bc.getCard("user");
                    Card = new JLabel(new ImageIcon(image));
                    Usercards.add(Card);
                    uCards.add(Card);
                    image = bc.getCard("user");
                    Card = new JLabel(new ImageIcon(image));
                    Usercards.add(Card);
                    uCards.add(Card);
                    image = bc.getCard("house");
                    Card = new JLabel(new ImageIcon(image));
                    Housecards.add(Card);
                    hCards.add(Card);
                    image = bc.getBackCard("house");
                    Card = new JLabel(new ImageIcon(image));
                    Housecards.add(Card);
                    hCards.add(Card);
                    bl.updateValue();
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
                        image = bc.getCard("user");
                        Card = new JLabel(new ImageIcon(image));
                        Usercards.add(Card);
                        uCards.add(Card);
                        image = bc.getBackCard("house");
                        if(image != null)
                        {
                            Card = new JLabel(new ImageIcon(image));
                            hCards.add(Card);
                            Housecards.add(Card);
                        }
                        pack();
                        bl.updateValue();
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
                            image = bc.getBackCard("house");
                            if(image != null)
                            {
                                Card = new JLabel(new ImageIcon(image));
                                hCards.add(Card);
                                Housecards.add(Card);
                                bl.updateValue();
                                pack();
                                houseHit = true;
                            }
                            
                        }
                        bl.updateValue();
                        pack();
                        if(gameover != true)
                        {
                            bl.getResult();
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
                    bl.removeCards();
                    
                    pack(); 
                    bl.newGame();
                    BufferedImage image;
                    JLabel Card; 
                    uCards = new ArrayList<JLabel>();
                    hCards = new ArrayList<JLabel>();
                    image = bc.getCard("user");
                    Card = new JLabel(new ImageIcon(image));
                    Usercards.add(Card);
                    uCards.add(Card);
                    image = bc.getCard("user");
                    Card = new JLabel(new ImageIcon(image));
                    Usercards.add(Card);
                    uCards.add(Card);
                    image = bc.getCard("house");
                    Card = new JLabel(new ImageIcon(image));
                    Housecards.add(Card);
                    hCards.add(Card);
                    image = bc.getBackCard("house");
                    Card = new JLabel(new ImageIcon(image));
                    Housecards.add(Card);
                    hCards.add(Card);
                    bl.updateValue();
                    pack();  
	        }
	});
        JPanel panel = new JPanel(new MigLayout("wrap 1"));
        JLabel txt = new JLabel("Copyright \u00a9 Kim Hammar all rights reserved");
        txt.setFont(Plain);
        panel.add(txt, "span");
        container.add(panel, "span, gaptop 20");
        add(container, BorderLayout.CENTER);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);    // centers on screen
        setVisible(true);
    }
}
