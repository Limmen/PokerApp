/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.texas;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author kim
 */
public class TexasCards 
{
    TexasGui gui;
    public TexasCards(TexasGui gui)
    {
        this.gui = gui;
    }
    public BufferedImage getPlaceholder()
    {   
            return readImage("placeholder");
    }
    public BufferedImage getDealer()
    {   
            return readImage("dealer");
    }
    public BufferedImage readImage(int id)
    {
        try
        {
            return ImageIO.read(new File("src/main/resources/images/card_" + id + ".png"));
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
            return ImageIO.read(new File("src/main/resources/images/card_" + id + ".png"));
        }
        catch(Exception e)
        {
            System.out.println("Could not read image");
            return null;
        }
    }

}


