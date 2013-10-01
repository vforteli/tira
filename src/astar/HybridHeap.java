/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.HashMap;

/**
 *
 * @author verne_000
 */
public class HybridHeap
{
    protected HeapItem[] heap;
    protected int tail;
    protected HashMap<Coordinates, Integer> hashmap;
    
    
    public HybridHeap()
    {
        // Hmm, what should the initial size be, and when should it grow/shrink?
        heap = new HeapItem[1000];
        hashmap = new HashMap<>(1000);
        tail = 0;
    }
    
    
    public boolean Contains (Coordinates key)
    {
        return hashmap.containsKey(key);
    }
    
   
    public boolean DecreaseKey(float value, Coordinates key)
    {
        Integer index = hashmap.get(key);
        if (index != null)
        {
            HeapItem item = heap[index];
            if (value < item.key)
            {
                item.key = value;
                BubbleUp(index);
                return true;
            }
        }
        return false;
    }
  
    
    public boolean IncreaseKey(float value, Coordinates key)
    {
        Integer index = hashmap.get(key);
        if (index != null)
        {
            HeapItem item = heap[index];
            if (value > item.key)
            {
                item.key = value;
                BubbleDown(index);
                return true;
            }
        }
        return false;
    }
    
      
    public void Insert(float value, Coordinates coordinates)
    {    
        if (hashmap.containsKey(coordinates))
        {
            return;
            // Got the index to the old entry with the same key
            //old = heap[hashmap.get(node.coordinates.toString())];       
        }
        
        HeapItem item = new HeapItem(value, coordinates);
        
        heap[tail] = item;
        int index = BubbleUp(tail);
        hashmap.put(coordinates, index);
        tail++;
        if (tail == heap.length)
        {
            System.out.println("Kabooom! Increase size maybe...");
            // increase the array size...            
        }
    }
 
    
    private int BubbleUp(int index)
    {  
        HeapItem last = heap[index];
        int parentindex = (index - 1) / 2;
        
        while (index > 0 && heap[parentindex].key >= last.key)
        {
            hashmap.put((Coordinates)heap[parentindex].item, index);
            heap[index] = heap[parentindex];
            index = parentindex;           
            parentindex = (index - 1) / 2;
        }
        
        heap[index] = last;
        hashmap.put((Coordinates)last.item, index);
        return index;
    }   
  
    
    private int BubbleDown(int index)
    {
        // To do, add code to maintain the hashmap...
        HeapItem node = heap[index];
        
        while(index < tail / 2)
        {
            int smallerNodeIndex;
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;
            
            if (rightChildIndex < tail && heap[rightChildIndex].key < heap[leftChildIndex].key)
            {
                smallerNodeIndex = rightChildIndex;
            }
            else 
            {
                smallerNodeIndex = leftChildIndex;       
            }
            
            if (node.key <= heap[smallerNodeIndex].key)
            {
                break;                
            }
            
            // Swap
            hashmap.put((Coordinates)heap[smallerNodeIndex].item, index);
            heap[index] = heap[smallerNodeIndex];
            index = smallerNodeIndex;
        }
        
        // Finally set the current node wherever it ends up...
        heap[index] = node; 
        hashmap.put((Coordinates)node.item, index);
        return index;
    }
    
    
    public Coordinates DeleteMin()
    {
        if (tail > 0)
        {            
            HeapItem root = heap[0];
            heap[0] = heap[--tail];
            BubbleDown(0);
            
            // Dont forget to remove it from the hashmap as well, or cockup ensured
            hashmap.remove(((Coordinates)root.item));   
            
            return (Coordinates)root.item;
        }
        return null;
    }
    
    
    public Object Peek()
    {
        if (tail > 0)
        {
            // Get the root item, ie the lowest key
            HeapItem root = heap[0];                
            return root.item;
        }
        return null;
        
    }  
    
    
    public boolean getIsEmpty()
    {
        return tail == 0;
    }
}
