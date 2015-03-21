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
    private JLabel Vcard;
    private Card card;
    
    public TexasTableCard(int id, JLabel Vcard)
    {
        this.id = id;
        this.Vcard = Vcard;
    }
    public int getId()
    {
        return id;
    }
    public JLabel getVCard()
    {
        return Vcard;
    }
    public void updateVCard(JLabel Vcard)
    {
        this.Vcard = Vcard;
    }
    public void setCard(Card c)
    {
        this.card = c;
    }
}