/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.texas;

import java.util.ArrayList;
import util.Card;
import util.TexasPlayer;
import util.TexasTable;
import util.TexasTableCard;

/**
 *
 * @author kim
 */
public class TexasManager 
{
    private HandEvaluator he;
    
    public TexasManager()
    {
        this.he = new HandEvaluator();
    }
    public Player newBot(int id, int cash)
    {
        return new Bot(id, cash);
    }
    public Player newUser(int cash)
    {
        return new User(cash);
    }
    public void newCard(ArrayList<Card> cards, Player p)
    {
        p.newCards(cards);
    }
    public void newDeck(ArrayList<TexasPlayer> players)
    {
        for(TexasPlayer p : players)
        {
           // p.newDeal();
        }
    }
    public String botBet(Player p, ArrayList<TexasTableCard> table, int callAmount)
    {
        ArrayList<Card> hand = p.getHand();
        ArrayList<Card> evaluate = new ArrayList();
        for(Card k : hand)
        {
            evaluate.add(k);
        }
        Bot b = (Bot) p;
        for(TexasTableCard c : table)
        {
            Card g = c.getCard();
            if(g != null)
            {
                evaluate.add(g);
            }
        }
        int res = he.evaluate(evaluate);
        if(callAmount > b.getCash() && b.getCash() != 0)
        {
            if(res > 5) //all IN
            {
                b.call(callAmount);
                return "call";
            }
            if(res <= 5)
            {
                return "fold";
            }  
        }
        if(res > 7)
        {
            System.out.println("All in, res > 7 ");
            b.raise(b.getCash()); // All in
            return "raise";
        }
        if(res > 5)
        {
            b.raise(callAmount + ((b.getCash()- callAmount)/3)); 
            return "raise";
        }
        if(res > 2 && callAmount < b.getCash()/5) //raise
        {
            b.raise(callAmount + ((b.getCash()- callAmount)/6));
            return "raise";
        }
        if(res > 1 && evaluate.size() < 3)
        {
            b.raise(callAmount + ((b.getCash()- callAmount)/6));
            return "raise";
        }
        if(res == 1 && evaluate.size()> 2) //fold
        {
            return "fold";
        }
        if((res > 2 && callAmount < b.getCash()/5) || (res > 2 && evaluate.size() < 5 && callAmount < (b.getCash()/2))) //raise
        {
            b.call(callAmount);
            return "call";
        }
        if(callAmount > b.getCash()/5 && evaluate.size() < 3)
        {
            b.call(callAmount);
            return "call";
        }
        b.call(callAmount);
        return "call";
    }
    public int getTotal(ArrayList<TexasPlayer> players)
    {
        int total = 0;
        for(TexasPlayer p : players)
        {
            total = total + p.getPlayer().getBet();
        }
        return total;
    }/*
    public void whoWins(ArrayList<TexasPlayer> players, ArrayList<TexasTableCard> table)
    {
        TexasPlayer winner = null;
        int win = 0;
        int res = 0;
        for(TexasPlayer p: players)
        {
            ArrayList<Card> hand = p.getCards();
            ArrayList<Card> evaluate = new ArrayList();
            for(Card k : hand)
            {
                evaluate.add(k);
            }
            for(TexasTableCard c : table)
            {
            Card g = c.getCard();
            if(g != null)
            {
                evaluate.add(g);
            }
            }
            res = he.evaluate(evaluate);
            
            if(res > win)
            {
                winner = p;
                win = res;
            }
            res = 0;
            hand = null;
        }
        winner.cash.setText(Integer.toString(winner.getPlayer().getCash() + getTotal(players)));
        winner.getPlayer().winCash(getTotal(players));
        winner.bet.setText("Congratulations! you won: " + (winner.bets.getTotalBet() - winner.getPlayer().getBet()));
        for(TexasPlayer p: players)
        {
            if(p != winner)
                p.bet.setText("you lost: " + p.getPlayer().getBet());
        }
    }*/
}
