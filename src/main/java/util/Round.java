/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
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
    ArrayList<Texas> copy;
    int dealer;
    public Round(TexasLogic tl, TexasFrame tf, int dealer, Bet bets){
        this.tl = tl;
        this.tf = tf;
        this.count = 0;
        this.dealer = dealer;
        this.turn  = dealer+1;
        copy = new ArrayList();
            for(Texas t : tf.players)
            {
                if(!t.folded())
                {
                    copy.add(t);
                }
            }
        copy.get(dealer).youDeal();
		setCall(bets.getCallAmount());
    }
    
     public void round(Bet bets)
        {
            if(count >= copy.size())
            {
                copy.get(dealer).dealDone();
                tl.whatsNext();
            }
            else
            {
                int oldCall = bets.getCallAmount();
                if(turn == copy.size())
                {
                    turn = 0;
                }
                Texas p = copy.get(turn);
                if(!p.folded())
                {
                   p.turn(bets, this);
                }
                
                if(oldCall != bets.getCallAmount())
                {
                    count = 0;
					setCall(bets.getCallAmount());
                }
                else
                    count++;
                turn++;
                return;
            }
            
        }
	public void setCall(int callz)
	{
		for(Texas t : copy)
			{
				if(!t.folded())
					{
						t.setCall(callz);	
					}
			}
	}
}
