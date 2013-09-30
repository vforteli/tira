/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author verne_000
 */
public class MinHeapOnSteroids
{
    protected HeapItem[] heap;
    private int tail;
    private HashMap<String, Integer> hashmap;
    
    public MinHeapOnSteroids()
    {
        // Hmm, what should the initial size be, and when should it grow/shrink?
        heap = new HeapItem[2000];
        hashmap = new HashMap<>(2000);
        tail = 0;
    }
    
    
    public boolean Contains (String key)
    {
        return hashmap.containsKey(key);
    }
    
   
    public Boolean DecreaseKey(float value, String key)
    {
        if (hashmap.containsKey(key))
        {
            int index = hashmap.get(key);
            HeapItem item = heap[index];
            if (value < item.key)
            {
                System.out.println("Key " + key + " decreased to " + value);
                item.key = value;
                BubbleUp(index);
                return true;
            }
        }
        return false;
    }
    
    public Boolean IncreaseKey(float value, String key)
    {
        System.out.println("Increase key");
        if (hashmap.containsKey(key))
        {
            System.out.println("Found key to increase");
            int index = hashmap.get(key);
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
    
    
    
    
    public void Insert(float value, Node node)
    {    
        if (hashmap.containsKey(node.coordinates.toString()))
        {
            // Got the index to the old entry with the same key
            //old = heap[hashmap.get(node.coordinates.toString())];       
        }
        
        HeapItem item = new HeapItem(value, node);
        
        heap[tail] = item;
        int index = BubbleUp(tail);
        hashmap.put(node.coordinates.toString(), index);
        tail++;
        if (tail == heap.length)
        {
            System.out.println("Increase size...");
            // increase the array size...            
        }
    }
    
    private int BubbleUp(int index)
    {
        HeapItem last = heap[index];
        int parentindex = (int)Math.floor(((double)index - 1) / 2);
        
        while (index > 0 && heap[parentindex].key >= last.key)
        {
            heap[index] = heap[parentindex];
            index = parentindex;        
            parentindex = (int)Math.floor(((double)index - 1) / 2);
        }
        heap[index] = last;
        return index;
    }   
    
    private int BubbleDown(int index)
    {
        HeapItem node = heap[index];
        
        while(index < tail / 2)
        {
            int smallerNodeIndex;
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;
            
            // Determine which child node is smaller
            if (rightChildIndex < tail && heap[rightChildIndex].key < heap[leftChildIndex].key)
            {
                smallerNodeIndex = rightChildIndex;
            }
            else 
            {
                smallerNodeIndex = leftChildIndex;       
            }
            
            // If the current node key is smaller, the heap is in order
            if (node.key <= heap[smallerNodeIndex].key)
            {
                break;                
            }
            
            // Swap
            heap[index] = heap[smallerNodeIndex];
            index = smallerNodeIndex;
        }
        
        // Finally set the current node wherever it ends up...
        heap[index] = node; 
        return index;
    }
    
    public Object DeleteMin()
    {
        if (tail > 0)
        {            
            HeapItem root = heap[0];
            heap[0] = heap[--tail];
            BubbleDown(0);
            
            // Dont forget to remove it from the hashmap as well, or cockup ensured
            hashmap.remove(((Node)root.item).coordinates.toString());   
            
            return root.item;
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
