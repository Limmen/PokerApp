/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.texas;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
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
public class TexasFrame extends JFrame
{
    private TexasGui gui;
    private final JPanel container;
    private JPanel table;
    private JPanel you;
    private JPanel bot1;
    private JPanel bot2;
    private JPanel bot3;
    private JLabel text;
    private JButton raise;
    private JButton fold;
    private JButton call;
    private BufferedImage image;
    private JLabel cash1;
    private JLabel cash2;
    private JLabel cash3;
    private JLabel cash4;
    private JLabel bet1;
    private JLabel bet2;
    private JLabel bet3;
    private JLabel bet4;
    TexasLogic tl;
    TexasCards tc;
    public TexasFrame(TexasGui gui)
    {
        super("Texas Hold'em");
        this.gui = gui;
        this.tc = new TexasCards(gui);
        tl = new TexasLogic(gui, this,tc);
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
        
        text = new JLabel("Table:");
        text.setFont(Bold);
        container.add(text, "span 4, align center");
        
        table = new JPanel();
        table = tl.addTableCards(5, table);
        container.add(table, "span 4, align center");
        
        you = new JPanel(new MigLayout("wrap 2"));
        JLabel text = new JLabel("You");
        text.setFont(Bold);
        you.add(text, "span, align center");
        you = tl.generateTemplate(you, cash1, bet1);
        container.add(you,"span 1, align center");
        
        bot1 = new JPanel(new MigLayout("wrap 2"));
        text = new JLabel("Bot1");
        text.setFont(Bold);
        bot1.add(text, "span, align center");
        bot1 = tl.generateTemplate(bot1, cash2, bet2);
        container.add(bot1,"span 1, align center");
        
        bot2 = new JPanel(new MigLayout("wrap 2"));
        text = new JLabel("Bot2");
        text.setFont(Bold);
        bot2.add(text, "span, align center");
        bot2 = tl.generateTemplate(bot2, cash3, bet3);
        container.add(bot2,"span 1, align center");
        
        bot3 = new JPanel(new MigLayout("wrap 2"));
        text = new JLabel("Bot3");
        text.setFont(Bold);
        bot3.add(text, "span, align center");
        bot3 = tl.generateTemplate(bot3, cash4, bet4);
        container.add(bot3,"span 1, align center");
        
        add(container);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);    // centers on screen
        setVisible(true);
    }
}
