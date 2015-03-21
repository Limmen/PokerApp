/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.texas;

import java.util.ArrayList;
import java.util.Collections;
import util.Card;

/**
 *
 * @author kim
 */
public class HandEvaluator 
{
    private static final int ROYAL_FLUSH = 10;
    private static final int STRAIGHT_FLUSH = 9;
    private static final int FOUR_OF_A_KIND = 8;
    private static final int FULL_HOUSE = 7;
    private static final int FLUSH = 6;
    private static final int STRAIGHT = 5;
    private static final int THREE_OF_A_KIND = 4;
    private static final int TWO_PAIR = 3;
    private static final int PAIR = 2;
    private static final int HIGH_CARD = 1;
    
    public HandEvaluator()
    {
        
    }
    public int evaluate(ArrayList<Card> hand)
    {
        if(royalFlush(hand))
            return ROYAL_FLUSH;
        if(straightFlush(hand))
            return STRAIGHT_FLUSH;
        if(fourOfAKind(hand))
            return FOUR_OF_A_KIND;
        if(fullHouse(hand))
            return FULL_HOUSE;
        if(flush(hand))
            return FLUSH;
        if(straight(hand))
            return STRAIGHT;
        if(threeOfAKind(hand))
            return THREE_OF_A_KIND;
        if(twoPair(hand))
            return TWO_PAIR;
        if(pair(hand))
            return PAIR;
        return HIGH_CARD;
    }
    public boolean royalFlush(ArrayList<Card> hand)
    {
        if(flush(hand))
        {
            for(Card c : hand)
            {
                if(c.value < 10)
                    return false;
            }
            return true;
        }
                return false;
    }
    public boolean straightFlush(ArrayList<Card> hand)
    {
        if(flush(hand) && straight(hand))
        {
            return true;
        }
                return false;
    }
    public boolean fourOfAKind(ArrayList<Card> hand)
    {
        int count = 0;
        for(Card c : hand)
        {
            for(int i = 0; i<hand.size(); i++)
            {
                if(count == 4)
                {
                    return true;
                }
                if(c.value == hand.get(i).value)
                {
                    count = count + 1;
                }
            }
        }
        return false;
    }
    public boolean fullHouse(ArrayList<Card> hand)
    {
        int count = 0;
        for(Card c : hand)
        {
            for(int i = 0; i<hand.size(); i++)
            {
                if(count == 3)
                {
                    for(Card g: hand)
                    {
                        if(c.value == g.value)
                        {
                            hand.remove(g);
                        }
                    }
                    if(pair(hand))
                    {
                        return true;
                    }
                    return false;
                }
                if(c.value == hand.get(i).value)
                {
                    count = count + 1;
                }
            }
        }
                return false;
    }
    public boolean flush(ArrayList<Card> hand)
    {
        String flush = hand.get(0).color;
        for(int i = 1; i < hand.size(); i++)
        {
            if(hand.get(i).color.equals(flush) != true)
            {
                return false;
            }
        }
        return true;
    }
    public boolean straight(ArrayList<Card> hand)
    {
        Collections.sort(hand);
        int prev = hand.get(0).value;
        for(int i = 1; i<hand.size(); i++)
        {
            if((hand.get(i).value - prev) != 1)
            return false;
        }
            return true;
    }
    public boolean threeOfAKind(ArrayList<Card> hand)
    {
        int count = 0;
        for(Card c : hand)
        {
            for(int i = 0; i<hand.size(); i++)
            {
                if(count == 3)
                {
                    return true;
                }
                if(c.value == hand.get(i).value)
                {
                    count = count + 1;
                }
            }
        }
        return false;
    }
    public boolean twoPair(ArrayList<Card> hand)
    {
        int count = 0;
        for(Card c : hand)
        {
            for(int i = 0; i<hand.size(); i++)
            {
                if(count == 2)
                {
                    for(Card g: hand)
                    {
                        if(c.value == g.value)
                        {
                            hand.remove(g);
                        }
                    }
                    if(pair(hand))
                    {
                        return true;
                    }
                    return false;
                }
                if(c.value == hand.get(i).value)
                {
                    count = count + 1;
                }
            }
        }
                return false;
    }
    public boolean pair(ArrayList<Card> hand)
    {
        int count = 0;
        for(Card c : hand)
        {
            for(int i = 0; i<hand.size(); i++)
            {
                if(count == 2)
                {
                    return true;
                }
                if(c.value == hand.get(i).value)
                {
                    count = count + 1;
                }
            }
        }
        return false;
    }
        
}
