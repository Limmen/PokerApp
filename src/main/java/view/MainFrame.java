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
import javax.imageio.ImageIO;
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
    public MainFrame(Gui gui)
    {
        super("Poker");
        this.gui = gui;
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
        
        House = new JLabel("House");
        House.setFont(Bold);
        You = new JLabel("You");
        You.setFont(Bold);
        container.add(You, "span 2, align center");
        container.add(House, "span 2, align center");
        
        Ucard1 = new JPanel();
        UserCard1 = getCard("user");
        Card1 = new JLabel(new ImageIcon(UserCard1));
        Ucard1.add(Card1);
        container.add(Ucard1, "align center");
        
        Ucard2 = new JPanel(new MigLayout());
        UserCard2 = getCard("user");
        Card2 = new JLabel(new ImageIcon(UserCard2));
        Ucard2.add(Card2, "align center");
        //Ucard2.add(Card1, "align left, gap 20");
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
        
        Score1 = new JLabel("0");
        Score1.setFont(Bold);
        container.add(Score1, "span 2, align center");
        Score2 = new JLabel("0");
        Score2.setFont(Bold);
        container.add(Score2, "span 2, align center");
        
        Hit = new JButton("Hit");
        Hit.setFont(Bold);
        container.add(Hit,"span 1, align center");
        
        Stand = new JButton("Stand");
        Stand.setFont(Bold);
        container.add(Stand,"span 1, align center");
        
        Deal = new JButton("Deal");
        Deal.setFont(Bold);
        container.add(Deal,"span 2, align center");
        
        Restart = new JButton("New Game");
        Restart.setFont(Bold);
        container.add(Restart, "span4, align center");
        updateScore();
        Deal.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    newGame();
                    UserCard1 = getCard("user");
                    UserCard2 = getCard("user");
                    HouseCard1 = getCard("house");
                    HouseCard2 = getBackCard("house");
                    Card1.setIcon(new ImageIcon(UserCard1));
                    Card2.setIcon(new ImageIcon(UserCard2));
                    Card3.setIcon(new ImageIcon(HouseCard1));
                    Card4.setIcon(new ImageIcon(HouseCard2));
                    updateScore();
	        }
	});
        
        Hit.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    BufferedImage newCard1 = getCard("user");
                    BufferedImage newCard12 = getCard("house");
                    Card1.setIcon(new ImageIcon(UserCard1));
                    Card2.setIcon(new ImageIcon(UserCard2));
	        }
	});
        
        add(container);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);    // centers on screen
        setVisible(true);
    }
    public void newGame()
    {
        gui.newGame();
    }
    public BufferedImage getCard(String who)
    {
        int id = gui.getRandomCard(who);
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
        gui.getRandomCard(who);
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
    public void updateScore()
    {
        String user = gui.getScore("user");
        String house = gui.getScore("house");
        if(user.equalsIgnoreCase("Busted!") && house.equalsIgnoreCase("Busted!"))
        {
            Score1.setText("Tie");
            Score2.setText("Tie");
        }
        if(user.equalsIgnoreCase("Busted!"))
        {
            Score1.setText(user);
            Score2.setText("House wins!");
            return;
        }
        if(house.equalsIgnoreCase("Busted!"))
        {
            Score1.setText("You win!");
            Score2.setText(user);
            return;
        }
        Score1.setText(user);
        Score2.setText(house);
    }
}
