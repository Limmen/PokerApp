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
        if(res == 1 && evaluate.size()> 2) //fold
        {
            return "fold";
        }
        if((res > 1 && evaluate.size() < 3) || (res > 2)) //raise
        {
            if(callAmount > b.getCash()/5 && evaluate.size() < 3)
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
    public int getTotal(ArrayList<TexasPlayer> players)
    {
        int total = 0;
        for(TexasPlayer p : players)
        {
            total = total + p.getPlayer().getBet();
        }
        return total;
    }
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
            System.out.println("Hand+table size: " + evaluate.size());
            System.out.println("Hand to evaluate: ");
            for (Card l : evaluate)
            {
                System.out.println(l.getNr() +"  " + l.getColor());
            }
            res = he.evaluate(evaluate);
            
            System.out.println("res = " + res);
            System.out.println("win = " + win);
            if(res > win)
            {
                winner = p;
                win = res;
            }
            res = 0;
            hand = null;
        }
        winner.cash.setText(Integer.toString(getTotal(players)));
        winner.getPlayer().winCash(getTotal(players));
        winner.bet.setText("Congratulations! you won: " + winner.getPlayer().getCash());
        for(TexasPlayer p: players)
        {
            if(p != winner)
                p.bet.setText("you lost: " + p.getPlayer().getBet());
        }
    }
}
