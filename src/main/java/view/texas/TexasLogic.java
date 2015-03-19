/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.texas;

import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kim
 */
public class TexasLogic 
{
    TexasGui gui;
    TexasFrame tf;
    BufferedImage image;
    TexasCards tc;
    Font Italic = new Font("Serif", Font.ITALIC, 12);
    Font Bold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    public TexasLogic(TexasGui gui, TexasFrame tf, TexasCards tc)
    {
        this.gui = gui;
        this.tf = tf;
        this.tc = tc;
    }
    public JPanel addTableCards(int number, JPanel panel)
    {
        for(int i = 0; i<number; i++)
        {
            image = tc.getTableCard();
            JLabel Card = new JLabel(new ImageIcon(image));;
            panel.add(Card);
        }
        return panel;
    }
    public JPanel generateTemplate(JPanel panel, JLabel cash, JLabel bet)
    {
        JLabel text = new JLabel("Cash");
        text.setFont(Bold);
        panel.add(text, "span 1, align center");
        cash = new JLabel("500");
        cash.setFont(Bold);
        panel.add(cash, "span 1, align center");
        text = new JLabel("Bet");
        text.setFont(Bold);
        panel.add(text, "span 1, align center");
        bet = new JLabel("0");
        bet.setFont(Bold);
        panel.add(bet, "span 1, align center");
        panel = addTableCards(2, panel);
        return panel;
    }
   
}
