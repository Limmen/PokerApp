/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.blackjack;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author kim
 */
public class BlackJackCards 
{
    BlackJackGui gui;
    public BlackJackCards(BlackJackGui gui)
    {
        this.gui = gui;
    }
    public BufferedImage getCard(String who)
    {
        int id = gui.getRandomCard(who);
        if(id == -1)
            return null;
            return readImage(id);
       
    }
    public BufferedImage readImage(int id)
    {
        try
        {
            return ImageIO.read(new File("../src/main/resources/images/card_" + id + ".png"));
        }
        catch(Exception e)
        {
            System.out.println("Could not read image");
            return null;
        }
    }
    public BufferedImage readImage(String id)
    {
         try
        {
            return ImageIO.read(new File("../src/main/resources/images/card_" + id + ".png"));
        }
        catch(Exception e)
        {
            System.out.println("Could not read image");
            return null;
        }
    }
    public BufferedImage getBackCard(String who)
    {
        int id = gui.getRandomCard(who);
        if(id == -1)
            return null;
        
            return readImage("back");
    }
}

