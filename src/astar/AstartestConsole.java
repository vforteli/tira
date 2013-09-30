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
        HybridHeap h = new HybridHeap();
//        h.Insert(35, new Node(1, 1, new Coordinates(4,5)));
//        h.Insert(39, new Node(1, 1, new Coordinates(5,5)));
//        h.Insert(2, new Node(1, 1, new Coordinates(1,5)));
//        h.Insert(60, new Node(1, 1, new Coordinates(3,5)));
//        h.Insert(4, new Node(1, 1, new Coordinates(7,5)));
//        h.Insert(100, new Node(1, 1, new Coordinates(8,5)));
//        h.Insert(17, new Node(1, 1, new Coordinates(9,5)));
        System.out.println();
        
        h.DecreaseKey(10, new Coordinates(8,5));
        h.IncreaseKey(1000, new Coordinates(8,5));
        h.IncreaseKey(1000, new Coordinates(3,5));
        
  
    }
}
