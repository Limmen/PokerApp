/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.texas;

import java.util.ArrayList;
import util.Card;
import util.TexasPlayer;

/**
 *
 * @author kim
 */
public class TexasManager 
{
    private User user;
    private ArrayList<Bot> bots;
    
    public TexasManager()
    {
        this.user = new User();
        this.bots = new ArrayList();
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
    public void botBet(Player p)
    {
        ArrayList<Card> hand = p.getHand();
    }
}
