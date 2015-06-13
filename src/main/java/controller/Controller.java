/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.JLabel;
import model.blackjack.BlackJackManager;
import model.DeckManager;
import model.texas.Bet;
import model.texas.Player;
import model.texas.TexasManager;
import util.Card;
import util.Texas;
import util.TexasPlayer;
import util.TexasTable;
import util.TexasTableCard;
import view.blackjack.BlackJackGui;
import view.texas.TexasGui;

/**
 *
 * @author kim
 */
public class Controller 
{
    private DeckManager dm;
    private BlackJackManager bm;
    private TexasManager tm;
    public Controller()
    {
        dm = new DeckManager();
        bm = new BlackJackManager();
        tm = new TexasManager();
    }
    
    public void printCards()
    {
        dm.printCards();
    }
    public int getRandomCard(String who, BlackJackGui g)
    {
            if(bm.checkChoice(who))
            {
                Card c =  dm.getRandomCard();
                int id = c.getId();
                if(id == 12 || id == 25 || id == 38 || id == 51)
                {
                    bm.addAce(c,who);
                }
                else
                    bm.updateValue(c, who);
                return c.getId();
            }
            else
                return -1;
    }
    public void newDeck(BlackJackGui g)
    {
        dm.newDeck();
        bm.newDeck();
    }
    public void newDeck(ArrayList<Texas> players, TexasGui g)
    {
        dm.newDeck();
        tm.newDeck(players);
    }
    public void newGame(BlackJackGui g)
    {
        dm.newDeck();
        bm.newGame();
    }
    public String getScore(String who, BlackJackGui g)
    {
        return bm.getScore(who);
    }
    public int getValue(String who, BlackJackGui g)
    {
        return bm.getValue(who);
    }
    public void updateScore(String who,BlackJackGui g)
    {
        bm.updateScore(who);
    }
    public String getResult(BlackJackGui g)
    {
        return bm.getResult();
    }
    public ArrayList<Card> getCards(String who, BlackJackGui g)
    {
        return bm.getCards(who);
    }
        public int getVisible(BlackJackGui g)
    {
        return bm.getVisible();
    }
   /* public TexasPlayer newPlayer(int id, ArrayList<JLabel> cards, TexasLogic tl, Bet bets, TexasGui gui, int cash)
    {
        Player player;
        if(id == -1)
        {
            player = tm.newUser(cash);
        }
        else
            player = tm.newBot(id, cash);
        return new TexasPlayer(player,cards, tl, bets);
    }*/
    public TexasTableCard newTableCard(int id, JLabel card, TexasGui gui)
    {
        return new TexasTableCard(id, card);
    }
    public TexasTable newTable(ArrayList<TexasTableCard> cards, TexasGui gui)
    {
        return new TexasTable(cards);
    }
    public void texasplayerDeal(Player p)
    {
            ArrayList<Card> cards = new ArrayList();
            cards.add(dm.getRandomCard());
            cards.add(dm.getRandomCard()); 
            tm.newCard(cards, p);
    }
    public String botBet(Player p,ArrayList<TexasTableCard> table, Bet bet)
    {
        return tm.botBet(p, table, bet);
    }
    public ArrayList<Card> tableDeal(int nr, TexasGui gui)
    {
        ArrayList<Card> cards = new ArrayList();
        for(int i = 0; i< nr; i++)
        {
            cards.add(dm.getRandomCard());
        }
        return cards;
    }
    public int getTotal(ArrayList<Texas> players)
    {
        return tm.getTotal(players);
    }
    
    public void whoWins(ArrayList<Texas> players, ArrayList<TexasTableCard> table)
    {
        tm.whoWins(players, table);
    }
}
