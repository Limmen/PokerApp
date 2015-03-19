/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import util.Card;

/**
 *
 * @author kim
 */
public class BlackJackLogic 
{
    Gui gui;
    BlackJackFrame bjf;
    BufferedImage image;
    ReadCards rc;
    public BlackJackLogic(Gui gui, BlackJackFrame bjf, ReadCards rc)
    {
        this.gui = gui;
        this.bjf = bjf;
        this.rc = rc;
    }
    public void newDeck()
    {
        gui.newDeck();
    }
    public void updateValue()
    {
        int user = gui.getValue("user");
        int house = gui.getValue("house");
        if(user > 21 && house > 21)
        {
            bjf.Value1.setText("Busted! (" + user + ")");
            bjf.Value2.setText("Busted! House wins (" + house + ")");
            updateScore("house");
            setScore("house");
            showCards();
            bjf.gameover = true;
        }
        if(user > 21)
        {
            bjf.Value1.setText("Busted! (" + user + ")");
            bjf.Value2.setText("House wins!" + " (" + house + ")");
            updateScore("house");
            setScore("house");
            showCards();
            bjf.gameover = true;
            return;
        }
        if(house > 21)
        {
            bjf.Value1.setText("You win!" + " (" + user + ")");
            bjf.Value2.setText("Busted! (" + house + ")");
            updateScore("user");
            setScore("user");
            showCards();
            bjf.gameover = true;
            return;
        }
        bjf.Value1.setText(Integer.toString(user));
        bjf.Value2.setText(Integer.toString(getVisible()));
    }
    public void updateScore(String who)
    {
        bjf.gameover = true;
        gui.updateScore(who);
    }
    public void setScore(String who)
    {
        if(who.equalsIgnoreCase("user"))
        {
            String userScore = gui.getScore(who);
            bjf.Score1.setText("Score: " + userScore);
        }
        else
        {
            String houseScore = gui.getScore(who);
            bjf.Score2.setText("Score: " + houseScore);
        }
    }

    public void newGame()
    {
        gui.newGame();
        setScore("user");
        setScore("house");
    }
    public void getResult()
    {
        bjf.gameover = true;
        int user = gui.getValue("user");
        int house = gui.getValue("house");
        if(user > house)
        {
            bjf.Value1.setText("You win! (" + user + ")");
            bjf.Value2.setText("Looser (" + house + ")");
            updateScore("user");
            setScore("user");
            showCards();
            return;
        }
        if(house > user)
        {
            bjf.Value1.setText("Looser (" + user + ")");
            bjf.Value2.setText("House wins! (" + house + ")");
            updateScore("house");
            setScore("house");
            showCards();
            return;
        }
        if(user == house)
        {
            bjf.Value1.setText("Tie (" + user + ")");
            bjf.Value2.setText("Tie, House wins! (" + house + ")");
            updateScore("house");
            setScore("house");
            showCards();
        }
    }

    public void removeCards()
    {
        for(int i = 0; i < bjf.uCards.size(); i++)
        {
            bjf.Usercards.remove(bjf.uCards.get(i));
        }
        for(int i = 0; i < bjf.hCards.size(); i++)
        {
            bjf.Housecards.remove(bjf.hCards.get(i));
        }
        bjf.hCards = new ArrayList<JLabel>();
        bjf.uCards = new ArrayList<JLabel>();
    }
    public void showCards()
    {
        ArrayList<Card> house = gui.getCards("house");
        for(int i = 0; i < bjf.hCards.size(); i++)
        {
            bjf.Housecards.remove(bjf.hCards.get(i));
        }
        bjf.hCards = new ArrayList<JLabel>();
        for(int i = 0; i < house.size(); i++)
        {
            BufferedImage image = rc.readImage(house.get(i).getId());
            JLabel Card = new JLabel(new ImageIcon(image));
            bjf.Housecards.add(Card);
            bjf.hCards.add(Card);
        }
        bjf.pack();
    }
    public int getVisible()
    {
        return gui.getVisible();
    }
}
