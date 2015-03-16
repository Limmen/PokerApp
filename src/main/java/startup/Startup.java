/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import controller.Controller;
import view.View;
/**
 *
 * @author kim
 */
public class Startup 
{
    private static Controller contr;
    private static View view;
    
    public static void main(String[] args)
    {
        contr = new Controller();
        view = new View(contr);
    }
    
}
