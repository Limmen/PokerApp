/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author kim
 */
public class Card implements Comparable
{
    public int id;
    public String color;
    public int value;
    public int nr;
    
    public Card(int id, String color, int value, int nr)
    {
        this.id = id;
        this.color = color;
        this.value = value;
        this.nr = nr;
    }
    
    public int getId()
    {
        return this.id;
    }
    public String getColor()
    {
        return this.color;
    }
    public int getValue()
    {
        return this.value;
    }
    public int getNr()
    {
        return this.nr;
    }
    public void print()
    {
    }

    @Override
    public int compareTo(Object o) 
    {
        Card c = (Card) o;
        if(this.value > c.value)
            return 1;
        if(this.value < c.value)
            return -1;
        else
            return 0;
    }
 
}
