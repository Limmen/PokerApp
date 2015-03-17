/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import util.Card;

/**
 *
 * @author kim
 */
public class Shuffle 
{
    public Shuffle()
    {
        
    }
    
        // swaps array elements i and j
    public static void exch(Card[] a, int i, int j) {
        Card swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // take as input an array of strings and rearrange them in random order
    public static Card[] shuffle(Card[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = i + (int) (Math.random() * (N-i));   // between i and N-1
            exch(a, i, r);
        }
        return a;
    }
}
