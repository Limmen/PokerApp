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
import net.coobird.thumbnailator.Thumbnails;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author kim
 */
public class MainFrame extends JFrame
{
    private Gui gui;
    private JPanel container;
    private JPanel Ucard1;
    private JPanel Ucard2;
    private JPanel Hcard1;
    private JPanel Hcard2;
    private JPanel buttonpanel;
    private JButton Deal;
    private BufferedImage UserCard1;
    private BufferedImage UserCard2;
    private BufferedImage HouseCard1;
    private BufferedImage HouseCard2;
    private JLabel Card1;
    private JLabel Card2;
    private JLabel Card3;
    private JLabel Card4;
    private JLabel House;
    private JLabel You;
    private JButton Hit;
    private JButton Stand;
    private JButton Restart;
    private JLabel Score1;
    private JLabel Score2;
    private JLabel Value1;
    private JLabel Value2;
    private boolean gameover;
    private ArrayList<JLabel> Cards;
    Stack temp;
    public MainFrame(Gui gui)
    {
        super("Poker");
        gameover = false;
        this.gui = gui;
        Cards = new ArrayList();
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
        
        You = new JLabel("You");
        You.setFont(Bold);
        container.add(You, "span 1, align center");
        Score1 = new JLabel("score: 0");
        Score1.setFont(Bold);
        container.add(Score1, "span1, align center");
        House = new JLabel("House");
        House.setFont(Bold);
        container.add(House, "span 1, align center, gap 80");
        Score2 = new JLabel("score: 0");
        Score2.setFont(Bold);
        container.add(Score2, "span1, align center");
        
        Ucard1 = new JPanel();
        UserCard1 = getCard("user");
        Card1 = new JLabel(new ImageIcon(UserCard1));
        Ucard1.add(Card1);
        container.add(Ucard1, "align center");
        
        Ucard2 = new JPanel(new MigLayout());
        UserCard2 = getCard("user");
        Card2 = new JLabel(new ImageIcon(UserCard2));
        Cards.add(Card2);
        Cards.add(Card1);
        Ucard2.add(Card2, "align center");
        container.add(Ucard2, "align center");
        
        Hcard1 = new JPanel();
        HouseCard1 = getCard("house");
        Card3 = new JLabel(new ImageIcon(HouseCard1));
        Hcard1.add(Card3);
        container.add(Hcard1, "align center, gap 80");
        
        Hcard2 = new JPanel();
        HouseCard2 = getBackCard("house");
        Card4 = new JLabel(new ImageIcon(HouseCard2));
        Hcard2.add(Card4);
        container.add(Hcard2, "align center");
        
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
                    Cards = new ArrayList<JLabel>();
                    UserCard1 = getCard("user");
                    UserCard2 = getCard("user");
                    HouseCard1 = getCard("house");
                    HouseCard2 = getBackCard("house");
                    JLabel NewCard1 = new JLabel(new ImageIcon(UserCard1));
                    JLabel NewCard2 = new JLabel(new ImageIcon(UserCard2));
                    Ucard1.add(NewCard1, "align center");
                    Ucard2.add(NewCard2, "align center");
                    Cards.add(NewCard1);
                    Cards.add(NewCard2);
                    Card3.setIcon(new ImageIcon(HouseCard1));
                    Card4.setIcon(new ImageIcon(HouseCard2));
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
                        JLabel newCard2;
                        BufferedImage nCard1 = getCard("user");
                        BufferedImage nCard2 = getBackCard("house");
                        if(nCard2 != null)
                            newCard2 = new JLabel(new ImageIcon(nCard2));
                        JLabel newCard = new JLabel(new ImageIcon(nCard1));
                        JLabel firstCard = Cards.get(Cards.size()-1);
                        Ucard1.remove(firstCard);
                        Ucard1.add(newCard, "align center");
                        Cards.add(newCard);
                        updateCards(firstCard);
                        pack();
                        updateValue();
                    }
	        }
	});
        Stand.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    if(gameover != true)
                    {
                        BufferedImage newCard1 = getCard("house");
                        while(newCard1 != null)
                        {
                            newCard1 = getCard("house");
                            if(newCard1 != null)
                            Card3.setIcon(new ImageIcon(newCard1));
                            updateValue();
                        }
                        updateValue();
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
                    Cards = new ArrayList<JLabel>();
                    UserCard1 = getCard("user");
                    UserCard2 = getCard("user");
                    HouseCard1 = getCard("house");
                    HouseCard2 = getBackCard("house");
                    JLabel NewCard1 = new JLabel(new ImageIcon(UserCard1));
                    JLabel NewCard2 = new JLabel(new ImageIcon(UserCard2));
                    Ucard1.add(NewCard1, "align center");
                    Ucard2.add(NewCard2, "align center");
                    Cards.add(NewCard1);
                    Cards.add(NewCard2);
                    Card3.setIcon(new ImageIcon(HouseCard1));
                    Card4.setIcon(new ImageIcon(HouseCard2));
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
        try
        {
            return readImage(id);
        }
        catch(Exception e)
        {
            System.out.println("Could not read image");
            return null;
        }
            
    }
    public BufferedImage readImage(int id) throws Exception
    {
        return ImageIO.read(new File("src/main/resources/images/card_" + id + ".png"));
    }
    public BufferedImage readImage(String id) throws Exception
    {
        return ImageIO.read(new File("src/main/resources/images/card_" + id + ".png"));
    }
    public BufferedImage getBackCard(String who)
    {
        int id = gui.getRandomCard(who);
        if(id == -1)
            return null;
        try
        {
            return readImage("back");
        }
        catch(Exception e)
        {
            System.out.println("Could not read image");
            return null;
        }
    }
    public void printCards()
    {
        this.gui.printCards();
    }
    public void updateValue()
    {
        String user = gui.getValue("user");
        String house = gui.getValue("house");
        if(user.equalsIgnoreCase("Busted!") && house.equalsIgnoreCase("Busted!"))
        {
            Value1.setText("Tie");
            Value2.setText("Tie");
            gui.newDeck();
        }
        if(user.equalsIgnoreCase("Busted!"))
        {
            Value1.setText(user);
            Value2.setText("House wins!" + " (" + house + ")");
            updateScore("house");
            setScore("house");
            gui.newDeck();
            return;
        }
        if(house.equalsIgnoreCase("Busted!"))
        {
            Value1.setText("You win!" + " (" + user + ")");
            Value2.setText(house);
            updateScore("user");
            setScore("user");
            gui.newDeck();
            return;
        }
        Value1.setText(user);
        Value2.setText(house);
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
        String winner = gui.getResult();
        if(winner.equalsIgnoreCase("user"))
        {
            Value1.setText("You win!");
            Value2.setText("Looser");
            updateScore("user");
            setScore("user");
            gui.newDeck();
            return;
        }
        if(winner.equalsIgnoreCase("house"))
        {
            Value1.setText("Looser");
            Value2.setText("House wins!");
            updateScore("house");
            setScore("house");
            gui.newDeck();
            return;
        }
    }
    public static BufferedImage resize(BufferedImage img, int newW, int newH) 
    {
        try
        {
            return Thumbnails.of(img).size(newW, newH).asBufferedImage();
        }
        catch(Exception e)
                {
                    return null;
                }
        
    }
    public void removeCards()
    {
        for(int i = 0; i < Cards.size()-1; i++)
        {
            Ucard2.remove(Cards.get(i));
        }
        Ucard1.remove(Cards.get(Cards.size()-1));
    }
    public void updateCards(JLabel firstCard)
    {
        int size = 0;
        for(int i = 0; i < Cards.size()-2; i++)
        {
            Ucard2.remove(Cards.get(i));
            temp.push(Cards.get(i));
            size = size +1;
        }
        temp.push(firstCard);
        while(temp.empty() != true)
        {
            JLabel j= (JLabel)temp.pop();
            Ucard2.add(j, "align center");
        }
    }
}
