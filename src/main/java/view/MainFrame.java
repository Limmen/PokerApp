/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import view.texas.TexasFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import view.blackjack.BlackJackGui;

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
    private BlackJackGui bgui;
    private TexasFrame tf;
    private JLabel title;
    private Font Title = new Font("Serif", Font.PLAIN, 12);
    private Font Italic = new Font("Serif", Font.ITALIC, 12);
    private Font Plain = new Font("Serif", Font.PLAIN, 12);
    private Font IBold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    private Font PBold = Plain.deriveFont(Plain.getStyle() | Font.BOLD);
    private Font TBold = Title.deriveFont(Title.getStyle() | Font.BOLD);
    private Menu menu;
    BufferedImage image;
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
        menu = new Menu();
        this.setLayout(new MigLayout());
        container = new JPanel(new MigLayout("wrap 4"));
        setJMenuBar(menu.getMenu());
        menu.getHelp().addActionListener(new ActionListener() 
        {
           public void actionPerformed(ActionEvent arg0) 
           {
               Help();
           }
        });
        menu.getAbout().addActionListener(new ActionListener() 
        {
           public void actionPerformed(ActionEvent arg0) 
           {
               About();
           }
        });
        ImageReader ir = new ImageReader();
        title = new JLabel("Welcome to the casino, select a table you would like to visit");
        title.setFont(Title);
        container.add(title,"span 4, align center");
        
        JPanel panel = new JPanel(new MigLayout("wrap 1"));
        JLabel txt = new JLabel("BlackJack");
        txt.setFont(PBold);
        panel.add(txt, "span 1, align center");
        image = ir.readImage("Blackjack.jpg");
        BlackJack = new JButton();
        BlackJack.setIcon(new ImageIcon(image));
        panel.add(BlackJack, "span 1, align center");
        container.add(panel, "span 2, align center");
        
        panel = new JPanel(new MigLayout("wrap 1"));
        txt = new JLabel("Texas");
        txt.setFont(PBold);
        panel.add(txt, "span 1, align center");
        image = ir.readImage("texas.png");
        Texas = new JButton();
        Texas.setIcon(new ImageIcon(image));
        panel.add(Texas, "span 1, align center");
        container.add(panel,"span 2, align center");
        
        BlackJack.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    //newGame();
                    blackjack();
                }
	});
        Texas.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    //newGame();
                    texas();
                }
	});
        txt = new JLabel("Copyright \u00a9 Kim Hammar all rights reserved");
        txt.setFont(Plain);
        container.add(txt, "span 1, gaptop 20");
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                add(container, BorderLayout.CENTER);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBackground(Color.BLACK);
                pack();
                setLocationRelativeTo(null);    // centers on screen
                setVisible(true);
            }
        });
        
    }
    public void blackjack()
    {
        gui.blackjack();
    }
    public void texas()
    {
        gui.texas();
    }
    public void About()
    {
        new AboutFrame();
    }
    public void Help()
    {
        new HelpFrame();
    }
}
