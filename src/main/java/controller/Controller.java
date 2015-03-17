/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DeckManager;

/**
 *
 * @author kim
 */
public class Controller 
{
    private DeckManager dm;
    public Controller()
    {
        dm = new DeckManager();
    }
    
    public void printCards()
    {
        dm.printCards();
    }
    public int getRandomCard()
    {
        return dm.getRandomCard();
    }
}
