/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JLabel;

/**
 *
 * @author kim
 */
public class TexasTableCard
{
    private int id;
    private JLabel card;
    
    public TexasTableCard(int id, JLabel card)
    {
        this.id = id;
        this.card = card;
    }
    public int getId()
    {
        return id;
    }
    public JLabel getCard()
    {
        return card;
    }
    public void updateCard(JLabel card)
    {
        this.card = card;
    }
}