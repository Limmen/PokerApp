/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.texas;

import java.util.ArrayList;
import util.Card;
import util.Texas;
import util.TexasBot;
import util.TexasPlayer;
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
    public void newDeck(ArrayList<Texas> players)
    {
        for(Texas p : players)
        {
           // p.newDeal();
        }
    }
    public String botBet(Player p, ArrayList<TexasTableCard> table, Bet bet)
    {
		int callAmount = bet.getCallAmount();
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
		int oldBet = b.getBet();
        if(callAmount > b.getCash() && b.getCash() != 0)
        {
            if(res > 5) //all IN
            {
				bet.addBet(callAmount - oldBet);
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
			bet.raise(b.getCash() - (bet.getCallAmount() - b.getBet()));
            b.raise(b.getCash()); // All in
            return "raise";
        }
        if(res > 5)
        {
			bet.raise((b.getCash()- callAmount)/3);
            b.raise((b.getCash()- callAmount)/3 + (callAmount - b.getBet())); 
            return "raise";
        }
        if(res > 2 && callAmount < b.getCash()/5) //raise
        {
            bet.raise((b.getCash()- callAmount)/6);
            b.raise((b.getCash()- callAmount)/6 + (callAmount - b.getBet()));
            return "raise";
        }
        if(res > 1 && evaluate.size() < 3)
        {
            bet.raise((b.getCash()- callAmount)/6);
            b.raise((b.getCash()- callAmount)/6 + (callAmount - b.getBet()));
            return "raise";
        }
        if(res == 1 && evaluate.size()> 2) //fold
        {
            return "fold";
        }
        if((res > 2 && callAmount < b.getCash()/5) || (res > 2 && evaluate.size() < 5 && callAmount < (b.getCash()/2))) //raise
        {
            bet.addBet(callAmount - oldBet);
            b.call(callAmount);
            return "call";
        }
        if(callAmount > b.getCash()/5 && evaluate.size() < 3)
        {
            bet.addBet(callAmount - oldBet);
            b.call(callAmount);
            return "call";
        }
		bet.addBet(callAmount - oldBet);
        b.call(callAmount);
        return "call";
    }
    public int getTotal(ArrayList<Texas> players)
    {
        int total = 0;
        for(Texas p : players)
        {
            total = total + p.getPlayer().getBet();
        }
        return total;
    }
    public void whoWins(ArrayList<Texas> players, ArrayList<TexasTableCard> table)
    {
        Texas winner = null;
        int win = 0;
        int res = 0;
        for(Texas p: players)
        {
            if(!p.folded())
            {
                ArrayList<Card> hand = p.getPlayer().getHand();
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
        }
        winner.youWin(getTotal(players));
        for(Texas p: players)
        {
            if(p != winner)
                p.youLoose();
        }
    }
}
