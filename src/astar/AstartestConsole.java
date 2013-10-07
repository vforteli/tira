/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.Random;

/**
 *
 * @author verne_000
 */
public class AstartestConsole
{
    /**
     *
     * @param args
     */
    public static void main(String[] args) 
    {
        StopWatch sw = new StopWatch();
        HybridHeap<Long, Long> heap = new HybridHeap<>();
        
        
        int n = 50000;
        Random r = new Random();
        for (int i = 0; i < n; i++)
        {
            long value = r.nextLong(); 
            heap.Insert(value, value);
        }
        
      
        
        

        sw.start();     
        for (int i = 0; i < 1000; i++)
        {
            long value = r.nextLong();
            heap.Insert(value, value);
        }
        sw.stop();
        
        System.out.println(100 + " inserts took " + ((sw.getElapsedTime() / 1000) - 1) + " us");
    }
}
