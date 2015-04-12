/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;

/**
 *
 * @author kim
 */
public class Deck 
{
    private ArrayList <Card> Cards;
    
    public Deck() 
    {
        Cards = getSpades(getHearts(getClubs(getDiamonds(new ArrayList<Card>()))));
    }
    public ArrayList<Card> getDeck()
    {
        return this.Cards;
    }
    private ArrayList<Card> getSpades(ArrayList<Card> C)
    {
        int id = C.size();
        int Value = 2;
        int nr = 2;
        for(int i = 0; i<13; i++)
        {
            C.add(new Card(id,"spade",Value, nr));
            id++;
            if(Value <10)
            Value++;
            nr++;
        }
        return C;
    }
    private ArrayList<Card> getClubs(ArrayList<Card> C)
    {
        int id = C.size();
        int Value = 2;
        int nr = 2;
        for(int i = 0; i<13; i++)
        {
            C.add(new Card(id,"club",Value, nr));
            id++;
            if(Value <10)
            Value++;
            nr++;
        }
        return C;   
    }
    private ArrayList<Card> getHearts(ArrayList<Card> C)
    {
        int id = C.size();
        int Value = 2;
        int nr = 2;
        for(int i = 0; i<13; i++)
        {
            C.add(new Card(id,"heart",Value, nr));
            id++;
            if(Value <10)
            Value++;
            nr++;
        }
        return C;
    }
    private ArrayList<Card> getDiamonds(ArrayList<Card> C)
    {
        int id = C.size();
        int Value = 2;
        int nr = 2;
        for(int i = 0; i<13; i++)
        {
            C.add(new Card(id,"diamond",Value, nr));
            id++;
            if(Value <10)
            Value++;
            nr++;
        }
        return C;
    }
    public void deleteCard(int id)
    {
        for(int i = 0; i<Cards.size(); i++)
        {
            Card c = Cards.get(i);
            if(c.getId() == id)
            {
                Cards.remove(i);
                return;
            }
        }
    }
}
