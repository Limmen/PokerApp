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
	int oldCall;
    ArrayList<Texas> copy;
    int dealer;
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
        copy.get(dealer).youDeal();
		setCall(bets.getCallAmount());
    }
    
     public void round(Bet bets)
        {
            if(count >= copy.size()-1)
            {
                copy.get(dealer).dealDone();
				bets.callAmount = blind + bets.callAmount;
				setCall(bets.getCallAmount());
                tl.whatsNext(bets);
            }
            else
            {
                if(turn == copy.size())
                {
                    turn = 0;
                }
                Texas p = copy.get(turn);
                if(!p.folded())
                {
                   p.turn(bets, this);
                }
                turn++;
				//                oldCall = bets.getCallAmount();
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
	}
}
