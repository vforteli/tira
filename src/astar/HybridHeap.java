/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.AbstractMap;

/**
 * HybridHeap is a minimum heap indexed by a hash map
 * @param <T> 
 * @author verne_000
 */
public class HybridHeap<T>
{
    private HeapItem[] heap;
    private int tail;
    private AbstractMap<T, Integer> hashmap;
    
    
    /**
     *
     */
    public HybridHeap()
    {
        int initialSize = 1000;
        // Hmm, what should the initial size be, and when should it grow/shrink?
        heap = new HeapItem[initialSize];
        hashmap = new MapHache<>(1701);
        tail = 0;
    }
    
    
    /**
     *
     * @param key
     * @return
     */
    public boolean containsKey(T key)
    {
        return hashmap.containsKey(key);
    }
    
    
    /**
     * Get the priority value by key.
     * @param key
     * @return Priority queue value if the key exists, else null.
     */
    public Float getValue(T key)
    {
        Integer position = hashmap.get(key);
        if (position != null)
        {
            return heap[hashmap.get(key)].key;
        }
        else 
            return null;
    }
    
   
    /**
     *
     * @param value
     * @param key
     * @return
     */
    public boolean DecreaseKey(float value, T key)
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
  
    
    /**
     *
     * @param value
     * @param key
     * @return
     */
    public boolean IncreaseKey(float value, T key)
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
    
      
    /**
     * Insert an item in the heap with the specified value
     * @param value
     * @param object
     */
    public void Insert(float value, T object)
    {    
        if (hashmap.containsKey(object))
        {
            return;
            // Got the index to the old entry with the same key
            //old = heap[hashmap.get(node.object.toString())];       
        }
        
        HeapItem item = new HeapItem(value, object);
        
        heap[tail] = item;
        int index = BubbleUp(tail);
        hashmap.put(object, index);
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
            hashmap.put((T)heap[parentindex].item, index);
            heap[index] = heap[parentindex];
            index = parentindex;           
            parentindex = (index - 1) / 2;
        }
        
        heap[index] = last;
        hashmap.put((T)last.item, index);
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
            hashmap.put((T)heap[smallerNodeIndex].item, index);
            heap[index] = heap[smallerNodeIndex];
            index = smallerNodeIndex;
        }
        
        // Finally set the current node wherever it ends up...
        heap[index] = node; 
        hashmap.put((T)node.item, index);
        return index;
    }
    
    
    /**
     * Removes and returns the minimum item from the heap.
     * @return
     */
    public T DeleteMin()
    {
        if (tail > 0)
        {            
            HeapItem root = heap[0];
            heap[0] = heap[--tail];
            BubbleDown(0);
            
            // Dont forget to remove it from the hashmap as well, or cockup ensured
            hashmap.remove(((T)root.item));   
            
            return (T)root.item;
        }
        return null;
    }
    
    
    /**
     * Returns the minimum item from the heap, but does not remove it.
     * @return
     */
    public T Peek()
    {
        if (tail > 0)
        {
            // Get the root item, ie the lowest key
            HeapItem root = heap[0];                
            return (T)root.item;
        }
        return null;
        
    }  
    
    
    /**
     * Check if the heap is empty
     * @return True if empty.
     */
    public boolean IsEmpty()
    {
        return tail == 0;
    }
}
