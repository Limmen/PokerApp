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
public class Card 
{
    private int id;
    private String color;
    private int value;
    
    public Card(int id, String color, int value)
    {
        this.id = id;
        this.color = color;
        this.value = value;
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
    public void print()
    {
        System.out.println("Card: " + "Id: " + this.id + " color: " 
                    + this.color + " value: " + this.value);
    }
}
