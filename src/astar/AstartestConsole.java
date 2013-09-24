/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author verne_000
 */
public class AstartestConsole
{
    public static void main(String[] args) 
    {
        MinHeap h = new MinHeap();
        h.Insert(34, new Coordinates(4,5));
        h.Insert(54, new Coordinates(4,5));
        h.Insert(4, new Coordinates(4,5));
        h.Insert(7, new Coordinates(4,5));
        h.Insert(9, new Coordinates(4,5));
        h.Insert(2, new Coordinates(4,5));
        h.Insert(13, new Coordinates(4,5));
        h.Insert(18, new Coordinates(4,5));
        h.Insert(100, new Coordinates(4,5));
        h.Insert(18, new Coordinates(4,5));
        System.out.println();
        
        
        for (MinHeapItem item : h.heap)
        {
            if (item != null)
            {
                System.out.println(item.key);            
            }
        }
        
        h.DeleteMin();
        System.out.println();
        for (MinHeapItem item : h.heap)
        {
            if (item != null)
            {
                System.out.println(item.key);            
            }
        }
        
        h.DeleteMin();
        System.out.println();
        for (MinHeapItem item : h.heap)
        {
            if (item != null)
            {
                System.out.println(item.key);            
            }
        }
        
         h.DeleteMin();
        System.out.println();
        for (MinHeapItem item : h.heap)
        {
            if (item != null)
            {
                System.out.println(item.key);            
            }
        }
        
    }
}
