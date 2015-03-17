/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author kim
 */
public class Gui 
{
    private View view;
    private MainFrame main;
    public Gui(View view)
    {
        this.view = view;
        this.main = new MainFrame(this);
    }
    
    public void printCards()
    {
        view.printCards();
    }
    public int getRandomCard(String who)
    {
        return view.getRandomCard(who);
    }
    public void newGame()
    {
        view.newGame();
    }
    public String getScore(String who)
    {
        return view.getScore(who);
    }
   
}
