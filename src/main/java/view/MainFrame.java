/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Font;
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
    private JButton Start;
    BufferedImage UserCard1;
    BufferedImage UserCard2;
    BufferedImage HouseCard1;
    BufferedImage HouseCard2;
    JLabel Card1;
    JLabel Card2;
    JLabel Card3;
    JLabel Card4;
    JLabel House;
    JLabel You;
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
        UserCard1 = getCard();
        Card1 = new JLabel(new ImageIcon(UserCard1));
        Ucard1.add(Card1);
        container.add(Ucard1, "align center");
        
        Ucard2 = new JPanel();
        UserCard2 = getCard();
        Card2 = new JLabel(new ImageIcon(UserCard2));
        Ucard2.add(Card2);
        container.add(Ucard2, "align center");
        
        Hcard1 = new JPanel();
        HouseCard1 = getCard();
        Card3 = new JLabel(new ImageIcon(HouseCard1));
        Hcard1.add(Card3);
        container.add(Hcard1);
        
        Hcard2 = new JPanel();
        HouseCard2 = getCard();
        Card4 = new JLabel(new ImageIcon(HouseCard2));
        Hcard2.add(Card4);
        container.add(Hcard2, "align center");
        
        Start = new JButton("Start");
        Start.setFont(Bold);
        container.add(Start,"span 4, align center");
        
        add(container);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);    // centers on screen
        setVisible(true);
    }
    
    public BufferedImage getCard()
    {
        try
        {
            return readImage();
        }
        catch(Exception e)
        {
            System.out.println("Could not read image");
            return null;
        }
            
    }
    public BufferedImage readImage() throws Exception
    {
        return ImageIO.read(new File("src/main/resources/images/card_00.png"));
    }
}
