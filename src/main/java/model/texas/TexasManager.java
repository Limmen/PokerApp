/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.texas;

import java.util.ArrayList;
import util.Card;
import util.TexasPlayer;
import util.TexasTableCard;

/**
 *
 * @author kim
 */
public class TexasManager 
{
    private User user;
    private ArrayList<Bot> bots;
    private HandEvaluator he;
    
    public TexasManager()
    {
        this.user = new User();
        this.bots = new ArrayList();
        this.he = new HandEvaluator();
    }
    public Player newBot(int id)
    {
        return new Bot(id);
    }
    public Player newUser()
    {
        return new User();
    }
    public void newCard(ArrayList<Card> cards, Player p)
    {
        p.newCards(cards);
    }
    public void newDeck(ArrayList<TexasPlayer> players)
    {
        for(TexasPlayer p : players)
        {
            p.newDeal();
        }
    }
    public String botBet(Player p, ArrayList<TexasTableCard> table, int callAmount)
    {
        ArrayList<Card> hand = p.getHand();
        Bot b = (Bot) p;
        for(TexasTableCard c : table)
        {
            Card g = c.getCard();
            if(g != null)
            {
                hand.add(g);
            }
        }
        int res = he.evaluate(hand);
        if(res == 1 && hand.size()> 2) //fold
        {
            return "fold";
        }
        if((res > 1 && hand.size() < 3) || (res > 2)) //raise
        {
            if(callAmount > b.getCash()/5 && hand.size() < 3)
            {
                b.call(callAmount);
                return "call";
            }
            if(res > 5)
            {
                b.raise(callAmount + ((b.getCash()- callAmount)/3));   
            }
            if(res > 7)
            {
                b.raise(b.getCash());
            }
            return "raise";
        }
        b.call(callAmount);
        return "call";
            
        
    }
}
