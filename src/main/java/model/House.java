/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author kim
 */
public class House 
{
    private int score;
    public House()
    {
        this.score = 0;
    }
    
    public String getScore()
    {
        if(score > 21)
            return "Busted!";
        else
            return Integer.toString(score);
    }
    public void updateScore(int n)
    {
        score = score + n;
    }
    
}
