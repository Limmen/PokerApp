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
public class GameManager 
{
    private User user;
    private House house;
    
    public GameManager()
    {
        this.user = new User();
        this.house = new House();
    }
    
    public void updateScore(int n, String who)
    {
        if(who.equalsIgnoreCase("user"))
        {
            user.updateScore(n);
        }
        else
            house.updateScore(n);
    }
    public String getScore(String who)
    {
         if(who.equalsIgnoreCase("user"))
        {
            return user.getScore();
        }
        else
            return house.getScore();
    }
    public void newGame()
    {
        user = new User();
        house = new House();
    }
}
