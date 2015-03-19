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
public class MainFrame extends JFrame
{
    private final Gui gui;
    private final JPanel container;
    private final JButton BlackJack;
    private final JButton Texas;
    private BlackJackFrame bjf;
    
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
        Font Italic = new Font("Serif", Font.ITALIC, 16);
        Font Bold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
        
        BlackJack = new JButton("BlackJack");
        BlackJack.setFont(Bold);
        container.add(BlackJack, "span");
        
        Texas = new JButton("Texas hold'em");
        Texas.setFont(Bold);
        container.add(Texas,"wrap");
        
        BlackJack.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    bjf = blackJack();
                }
	});
        
        
        add(container);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);    // centers on screen
        setVisible(true);
    }
    public BlackJackFrame blackJack()
    {
        return new BlackJackFrame(gui);
    }
}
