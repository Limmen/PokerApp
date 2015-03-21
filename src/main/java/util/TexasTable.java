/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kim
 */
public class TexasTable 
{
    private JPanel panel;
    private ArrayList<TexasTableCard> cards;
    
    public TexasTable(ArrayList<TexasTableCard> cards)
    {
       this.panel = new JPanel();
       this.cards = cards;
       addCards();
    }
    
   public JPanel getPanel()
   {
       return this.panel;
   }
   public ArrayList<TexasTableCard> getCards()
   {
       return this.cards;
   }
   public void Deal(ArrayList<JLabel> dealcards, ArrayList<Card> dcards)
    {
        panel.removeAll();
        for(int i = 0; i<cards.size(); i++)
        {
            cards.get(i).setCard(dcards.get(i));
            cards.get(i).setVCard(dealcards.get(i));
            panel.add(cards.get(i).getVCard());
        }
        
    }
      public void secondDeal(JLabel dealcard, int n)
    {
        for(TexasTableCard tbc: this.cards)
        {
           if(tbc.getId() == n)
           {
               tbc.updateVCard(dealcard);
               return;
           }
        }
        
    }
    public void placeholders(ArrayList<TexasTableCard> placeholders)
    {

        for(TexasTableCard c : cards)
        {
            panel.remove(c.getVCard());
        }
        for(TexasTableCard t: placeholders)
        {
            panel.add(t.getVCard());
        }
    }
    public void addCards()
    {
        for(TexasTableCard t: cards)
        {
            panel.add(t.getVCard());
        }
    }


}
