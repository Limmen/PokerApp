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
import java.util.Stack;
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
public class MainFrame extends JFrame
{
    private Gui gui;
    private JPanel container;
    private JPanel Usercards;
    private JPanel Housecards;
    private JButton Deal;
   /* private JLabel Card1;
    private JLabel Card2;
    private JLabel Card3;
    private JLabel Card4;*/
    private JLabel text; 
   // private JLabel You;
    private JButton Hit;
    private JButton Stand;
    private JButton Restart;
    private JLabel Score1;
    private JLabel Score2;
    private JLabel Value1;
    private JLabel Value2; 
    private boolean gameover;
    private ArrayList<JLabel> uCards;
    private ArrayList<JLabel> hCards;
    Stack temp;
    BufferedImage image;
    public MainFrame(Gui gui)
    {
        super("Poker");
        gameover = false;
        this.gui = gui;
        uCards = new ArrayList();
        hCards = new ArrayList();
        temp = new Stack();
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
        image = getCard("user");
        JLabel Card = new JLabel(new ImageIcon(image));
        uCards.add(Card);
        Usercards.add(Card);
        
        image = getCard("user");
        Card = new JLabel(new ImageIcon(image));
        uCards.add(Card);
        Usercards.add(Card);
        container.add(Usercards, "span 2, align center");
        
        Housecards = new JPanel();
        image = getCard("house");
        Card = new JLabel(new ImageIcon(image));
        Housecards.add(Card);
        hCards.add(Card);
        
        image = getBackCard("house");
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
        updateValue();
        Deal.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    gameover = false;
                    removeCards();
                    pack();
                    newDeck();
                    BufferedImage image;
                    JLabel Card;
                    uCards = new ArrayList<JLabel>();
                    hCards = new ArrayList<JLabel>();
                    image = getCard("user");
                    Card = new JLabel(new ImageIcon(image));
                    Usercards.add(Card);
                    uCards.add(Card);
                    image = getCard("user");
                    Card = new JLabel(new ImageIcon(image));
                    Usercards.add(Card);
                    uCards.add(Card);
                    image = getCard("house");
                    Card = new JLabel(new ImageIcon(image));
                    Housecards.add(Card);
                    hCards.add(Card);
                    image = getBackCard("house");
                    Card = new JLabel(new ImageIcon(image));
                    Housecards.add(Card);
                    hCards.add(Card);
                    updateValue();
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
                        image = getCard("user");
                        Card = new JLabel(new ImageIcon(image));
                        Usercards.add(Card);
                        uCards.add(Card);
                        image = getBackCard("house");
                        if(image != null)
                        {
                            Card = new JLabel(new ImageIcon(image));
                            hCards.add(Card);
                            Housecards.add(Card);
                        }
                        pack();
                        updateValue();
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
                            image = getBackCard("house");
                            if(image != null)
                            {
                                //letsWait();
                                Card = new JLabel(new ImageIcon(image));
                                hCards.add(Card);
                                Housecards.add(Card);
                                updateValue();
                                pack();
                                houseHit = true;
                            }
                            
                        }
                        updateValue();
                        //letsWait();
                        pack();
                        getResult();
                    }
	        }
	});
        Restart.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    gameover = false;
                    removeCards();
                    
                    pack(); 
                    newGame();
                    BufferedImage image;
                    JLabel Card; 
                    uCards = new ArrayList<JLabel>();
                    hCards = new ArrayList<JLabel>();
                    image = getCard("user");
                    Card = new JLabel(new ImageIcon(image));
                    Usercards.add(Card);
                    uCards.add(Card);
                    image = getCard("user");
                    Card = new JLabel(new ImageIcon(image));
                    Usercards.add(Card);
                    uCards.add(Card);
                    image = getCard("house");
                    Card = new JLabel(new ImageIcon(image));
                    Housecards.add(Card);
                    hCards.add(Card);
                    image = getBackCard("house");
                    Card = new JLabel(new ImageIcon(image));
                    Housecards.add(Card);
                    hCards.add(Card);
                    updateValue();
                    pack();  
	        }
	});
        
        add(container);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);    // centers on screen
        setVisible(true);
    }
    public void newDeck()
    {
        gui.newDeck();
    }
    public BufferedImage getCard(String who)
    {
        int id = gui.getRandomCard(who);
        if(id == -1)
            return null;
            return readImage(id);
       
    }
    public BufferedImage readImage(int id)
    {
        try
        {
            return ImageIO.read(new File("src/main/resources/images/card_" + id + ".png"));
        }
        catch(Exception e)
        {
            System.out.println("Could not read image");
            return null;
        }
    }
    public BufferedImage readImage(String id)
    {
         try
        {
            return ImageIO.read(new File("src/main/resources/images/card_" + id + ".png"));
        }
        catch(Exception e)
        {
            System.out.println("Could not read image");
            return null;
        }
    }
    public BufferedImage getBackCard(String who)
    {
        int id = gui.getRandomCard(who);
        if(id == -1)
            return null;
        
            return readImage("back");
    }
    public void printCards()
    {
        this.gui.printCards();
    }
    public void updateValue()
    {
        int user = gui.getValue("user");
        int house = gui.getValue("house");
        if(user > 21 && house > 21)
        {
            Value1.setText("Busted! (" + user + ")");
            Value2.setText("Busted! House wins (" + house + ")");
            showCards();
            gameover = true;
        }
        if(user > 21)
        {
            Value1.setText("Busted! (" + user + ")");
            Value2.setText("House wins!" + " (" + house + ")");
            updateScore("house");
            setScore("house");
            showCards();
            gameover = true;
            return;
        }
        if(house > 21)
        {
            Value1.setText("You win!" + " (" + user + ")");
            Value2.setText("Busted! (" + house + ")");
            updateScore("user");
            setScore("user");
            showCards();
            gameover = true;
            return;
        }
        Value1.setText(Integer.toString(user));
        Value2.setText(Integer.toString(house));
    }
    public void updateScore(String who)
    {
        gameover = true;
        gui.updateScore(who);
    }
    public void setScore(String who)
    {
        if(who.equalsIgnoreCase("user"))
        {
            String userScore = gui.getScore(who);
            Score1.setText("Score: " + userScore);
        }
        else
        {
            String houseScore = gui.getScore(who);
            Score2.setText("Score: " + houseScore);
        }
    }

    public void newGame()
    {
        gui.newGame();
        setScore("user");
        setScore("house");
    }
    public void getResult()
    {
        gameover = true;
        int user = gui.getValue("user");
        int house = gui.getValue("house");
        if(user > house)
        {
            Value1.setText("You win! (" + user + ")");
            Value2.setText("Looser (" + house + ")");
            updateScore("user");
            setScore("user");
            showCards();
            return;
        }
        if(house > user)
        {
            Value1.setText("Looser (" + user + ")");
            Value2.setText("House wins! (" + house + ")");
            updateScore("house");
            setScore("house");
            showCards();
            return;
        }
        if(user == house)
        {
            Value1.setText("Tie (" + user + ")");
            Value2.setText("Tie, House wins! (" + house + ")");
            showCards();
        }
    }

    public void removeCards()
    {
        for(int i = 0; i < uCards.size(); i++)
        {
            Usercards.remove(uCards.get(i));
        }
        for(int i = 0; i < hCards.size(); i++)
        {
            Housecards.remove(hCards.get(i));
        }
        hCards = new ArrayList<JLabel>();
        uCards = new ArrayList<JLabel>();
    }
    public void showCards()
    {
        ArrayList<Card> house = gui.getCards("house");
        for(int i = 0; i < hCards.size(); i++)
        {
            Housecards.remove(hCards.get(i));
        }
        hCards = new ArrayList<JLabel>();
        for(int i = 0; i < house.size(); i++)
        {
            image = readImage(house.get(i).getId());
            JLabel Card = new JLabel(new ImageIcon(image));
            Housecards.add(Card);
            hCards.add(Card);
        }
        pack();
    }
    public void letsWait()
    {
        try 
        {    
            Thread.sleep(1500);             //1000 milliseconds is one second.
        } 
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        } 
    }
    
}
