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
        MapHache<Long, Long> heap = new MapHache<>(2000);
        
        
        int n = 200000;
        Random r = new Random();
        for (int i = 0; i < n; i++)
        {
            long value = r.nextLong(); 
            heap.put(value, value);
        }
        
      
        
        

        sw.start();     
        for (int i = 0; i < 1000; i++)
        {
            long value = r.nextLong();
            heap.put(value, value);
        }
        sw.stop();
        
        System.out.println(100 + " inserts took " + ((sw.getElapsedTime() / 1000) - 1) + " us");
    }
}
