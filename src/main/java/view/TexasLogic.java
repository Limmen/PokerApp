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
public class TexasLogic 
{
    Gui gui;
    TexasFrame tf;
    BufferedImage image;
    ReadCards rc;
    public TexasLogic(Gui gui, TexasFrame tf, ReadCards rc)
    {
        this.gui = gui;
        this.tf = tf;
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
            tf.Value1.setText("Busted! (" + user + ")");
            tf.Value2.setText("Busted! House wins (" + house + ")");
            updateScore("house");
            setScore("house");
            showCards();
            tf.gameover = true;
        }
        if(user > 21)
        {
            tf.Value1.setText("Busted! (" + user + ")");
            tf.Value2.setText("House wins!" + " (" + house + ")");
            updateScore("house");
            setScore("house");
            showCards();
            tf.gameover = true;
            return;
        }
        if(house > 21)
        {
            tf.Value1.setText("You win!" + " (" + user + ")");
            tf.Value2.setText("Busted! (" + house + ")");
            updateScore("user");
            setScore("user");
            showCards();
            tf.gameover = true;
            return;
        }
        tf.Value1.setText(Integer.toString(user));
        tf.Value2.setText(Integer.toString(getVisible()));
    }
    public void updateScore(String who)
    {
        tf.gameover = true;
        gui.updateScore(who);
    }
    public void setScore(String who)
    {
        if(who.equalsIgnoreCase("user"))
        {
            String userScore = gui.getScore(who);
            tf.Score1.setText("Score: " + userScore);
        }
        else
        {
            String houseScore = gui.getScore(who);
            tf.Score2.setText("Score: " + houseScore);
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
        tf.gameover = true;
        int user = gui.getValue("user");
        int house = gui.getValue("house");
        if(user > house)
        {
            tf.Value1.setText("You win! (" + user + ")");
            tf.Value2.setText("Looser (" + house + ")");
            updateScore("user");
            setScore("user");
            showCards();
            return;
        }
        if(house > user)
        {
            tf.Value1.setText("Looser (" + user + ")");
            tf.Value2.setText("House wins! (" + house + ")");
            updateScore("house");
            setScore("house");
            showCards();
            return;
        }
        if(user == house)
        {
            tf.Value1.setText("Tie (" + user + ")");
            tf.Value2.setText("Tie, House wins! (" + house + ")");
            updateScore("house");
            setScore("house");
            showCards();
        }
    }

    public void removeCards()
    {
        for(int i = 0; i < tf.uCards.size(); i++)
        {
            tf.Usercards.remove(tf.uCards.get(i));
        }
        for(int i = 0; i < tf.hCards.size(); i++)
        {
             tf.Housecards.remove(tf.hCards.get(i));
        }
        tf.hCards = new ArrayList<JLabel>();
        tf.uCards = new ArrayList<JLabel>();
    }
    public void showCards()
    {
        ArrayList<Card> house = gui.getCards("house");
        for(int i = 0; i < tf.hCards.size(); i++)
        {
            tf.Housecards.remove(tf.hCards.get(i));
        }
        tf.hCards = new ArrayList<JLabel>();
        for(int i = 0; i < house.size(); i++)
        {
            BufferedImage image = rc.readImage(house.get(i).getId());
            JLabel Card = new JLabel(new ImageIcon(image));
            tf.Housecards.add(Card);
            tf.hCards.add(Card);
        }
        tf.pack();
    }
    public int getVisible()
    {
        return gui.getVisible();
    }
}
