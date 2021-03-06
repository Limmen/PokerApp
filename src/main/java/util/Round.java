/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import model.texas.Bet;
import view.texas.TexasFrame;
import view.texas.TexasLogic;

/**
 *
 * @author kim
 */
public class Round
{
    TexasLogic tl;
    TexasFrame tf;
    int count;
    int turn;
    int oldCall;
    ArrayList<Texas> copy;
    int dealer;
    Texas delah;
    int blind;
    public Round(TexasLogic tl, TexasFrame tf, int dealer, Bet bets, int blind){
        this.tl = tl;
        this.tf = tf;
        this.count = 0;
        this.dealer = dealer;
        this.turn  = dealer+1;
        this.blind = blind;
        copy = new ArrayList();
        for(Texas t : tf.players)
            {
                if(!t.folded())
                    {
                        copy.add(t);
                    }
            }
        for(Texas t : copy)
            {
                if(t instanceof TexasBot && t.getCash() == 0)
                    {
                        t.showCards();
                    }
            }
        if(copy.size() == 0)
            return;
        if(dealer >= copy.size())
            {
                dealer = 0;
            }
        delah = copy.get(dealer);
        delah.youDeal();
        setCall(bets.getCallAmount());
    }
    
    public void round(Bet bets)
    {
        if(count >= copy.size() || copy.size() < 2)
            {
                delah.dealDone();
                delay(bets);
            }
        else
            {
                if(turn >= copy.size())
                    {
                        turn = 0;
                    }
                Texas p = copy.get(turn);
                turn++;
                if(!p.folded())
                    {
                        p.turn(bets, this);
                    }
            }
        
    }
    public void setCall(int callz)
    {
        tf.setCall(callz);
    }
    public void checkChanges(Bet bets)
    {
        if(oldCall != bets.getCallAmount())
            {
                count = 0;
                setCall(bets.getCallAmount());
            }
        if(oldCall == bets.getCallAmount())
            {
                count++;
            }
        oldCall = bets.getCallAmount();
        tl.updateTotal(bets);
    }
    public void whatsNext(Bet bets)
    {
        if(copy.size() > 1)
            {
                boolean addblind = false;
                for(Texas t : copy)
                    {
                        if(t.getCash() >= (blind + (bets.getCallAmount() - t.getBet())))
                            addblind = true;
                    }
                if(addblind)
                    {
                        bets.callAmount = blind + bets.callAmount;
                    }
            }
        setCall(bets.getCallAmount());
        tl.updateTotal(bets);
        tl.whatsNext(bets);
    }
    public void delay(Bet bets)
    {
        int delay = 2000;
        final Bet bet = bets;
        Timer timer = new Timer( delay, new ActionListener(){
                @Override
                public void actionPerformed( ActionEvent e ){
                    whatsNext(bet);
                }
            });
        timer.setRepeats( false );
        timer.start();
    }
    public void iFolded(Texas g)
    {
        count--;
        if(turn <= copy.size()-1)
            {
                turn --;
            }
        copy.remove(g);
    }
}
