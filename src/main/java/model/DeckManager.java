/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collections;
import java.util.Random;
import util.Card;
import util.Deck;

/**
 *
 * @author kim
 */
public class DeckManager 
{
    private Deck deck;
    
    public DeckManager()
    {
        this.deck = generateDeck();
        shuffle();
    }
    public Deck generateDeck()
    {
        return new Deck();
    }
    public void printCards()
    {
        for(Card c : deck.getDeck())
        {
            c.print();
        }
    }
    public Card getRandomCard()
    {
        if(deck.getDeck().size() > 0)
        {
            Random rand = new Random();
            int  n = rand.nextInt(deck.getDeck().size());
            Card c = deck.getDeck().get(n);
            deck.deleteCard(c.getId());
            return c;
        }
        else
        {
            System.out.println("Deck is empty!");
            return null;
        }
    }
    public void newDeck()
    {
        this.deck = new Deck();
        shuffle();
    }
    public void shuffle()
    {
        Collections.shuffle(deck.getDeck());
    }
}
