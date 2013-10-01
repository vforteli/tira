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
        h.Insert(10, new Coordinates(1,1));
        h.Insert(20, new Coordinates(1,2));
        h.Insert(30, new Coordinates(1,3));
        h.Insert(40, new Coordinates(1,4));
        h.Insert(50, new Coordinates(1,5));
        h.Insert(60, new Coordinates(1,6));
        h.Insert(70, new Coordinates(1,7));
        h.Insert(80, new Coordinates(1,8));
        PrintHeap(h);
        
        h.DecreaseKey(15, new Coordinates(1,8));
        PrintHeap(h);
        

        System.out.println(h.DeleteMin());
        PrintHeap(h);
        
        System.out.println(h.DeleteMin());
        PrintHeap(h);
        
        System.out.println(h.DeleteMin());
        PrintHeap(h);
        
        System.out.println(h.DeleteMin());
        PrintHeap(h);
        
        System.out.println(h.DeleteMin());
        PrintHeap(h);
        
        
        //h.Insert(55, new Coordinates(2,5));
        //PrintHeap(h);
//        h.Insert(2, new Coordinates(2, 1));
//        PrintHeap(h);
//        
//        h.DecreaseKey(1, new Coordinates(1,4));      
//        PrintHeap(h);
        //PrintInOrder(h);
    }
    
    private static void PrintHeap(HybridHeap heap)
    {
        for (int i = 0; i < heap.tail; i++)
        {
            System.out.println(i + "\t" + heap.heap[i].key + "\t" + heap.heap[i].item + "\t" + heap.hashmap.get((Coordinates)heap.heap[i].item));
        }
        System.out.println();
    }

    private static void PrintInOrder(HybridHeap h)
    {
        while (!h.getIsEmpty())
        {
            System.out.print(h.DeleteMin() + ", ");
        }
        System.out.println();
        System.out.println();
    }
}
