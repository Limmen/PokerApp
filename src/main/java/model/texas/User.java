/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.texas;

import java.util.ArrayList;
import util.Card;

/**
 *
 * @author kim
 */
public class User 
{
    private int cash;
    private int currentbet;
    private ArrayList<Card> hand;
    public User()
    {
        this.cash = 500;
        this.currentbet = 0;
        this.hand = new ArrayList<Card>();
    }
}
