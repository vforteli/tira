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
    /**
     *
     * @param args
     */
    public static void main(String[] args) 
    {
        MapHache<Coordinates, Integer> h = new MapHache(3);
        h.put(new Coordinates(1,1), 1);
        h.put(new Coordinates(1,2), 2);
        h.put(new Coordinates(5,3), 3);
        h.put(new Coordinates(3,4), 4);
        h.put(new Coordinates(3,9), 5);
        h.put(new Coordinates(4,4), 6);
        h.put(new Coordinates(7,4), 7);
        h.put(new Coordinates(2,9), 8);
        
        
        h.put(new Coordinates(4,4), 4);
        h.put(new Coordinates(7,4), 4);
        h.put(new Coordinates(1,1), 1);
        
        
        System.out.println(h.get(new Coordinates(5,3)));
        System.out.println(h.get(new Coordinates(2,9)));
        System.out.println(h.get(new Coordinates(7,4)));
        System.out.println(h.get(new Coordinates(1,1)));
        System.out.println(h.get(new Coordinates(1,10)));
        
        System.out.println(h.remove(new Coordinates(7,4)));
        System.out.println(h.remove(new Coordinates(5,3)));
        System.out.println(h.remove(new Coordinates(2,9)));
        
        System.out.println(h.get(new Coordinates(2,9)));
        
        //System.out.println(h.containsKey(new Coordinates(5,8)));
        
//          HybridHeap h = new HybridHeap();
//        h.Insert(10, new Coordinates(1,1));
//        h.Insert(20, new Coordinates(1,2));
//        h.Insert(30, new Coordinates(1,3));
//        h.Insert(40, new Coordinates(1,4));
//        h.Insert(50, new Coordinates(1,5));
//        h.Insert(60, new Coordinates(1,6));
//        h.Insert(70, new Coordinates(1,7));
//        h.Insert(80, new Coordinates(1,8));
//        PrintHeap(h);
//        
//        h.DecreaseKey(15, new Coordinates(1,8));
//        PrintHeap(h);
//        
//
//        System.out.println(h.DeleteMin());
//        PrintHeap(h);
//        
//        System.out.println(h.DeleteMin());
//        PrintHeap(h);
//        
//        System.out.println(h.DeleteMin());
//        PrintHeap(h);
//        
//        System.out.println(h.DeleteMin());
//        PrintHeap(h);
//        
//        System.out.println(h.DeleteMin());
//        PrintHeap(h);
        
        
        //h.Insert(55, new Coordinates(2,5));
        //PrintHeap(h);
//        h.Insert(2, new Coordinates(2, 1));
//        PrintHeap(h);
//        
//        h.DecreaseKey(1, new Coordinates(1,4));      
//        PrintHeap(h);
        //PrintInOrder(h);
    }
    
    
   
}
