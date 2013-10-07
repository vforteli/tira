/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.AbstractMap;

/**
 * HybridHeap is a minimum heap indexed by a hash map
 * @param <V> Must be numeric comparable type
 * @param <T> Whatever
 * @author verne_000
 */
public class HybridHeap<V extends Number & Comparable<? super V>, T>
{
    private Item<V, T>[] heap;
    private int tail;
    private AbstractMap<T, Integer> hashmap;
    
    
    /**
     *
     */
    public HybridHeap()
    {
        int initialSize = 10000000;
        heap = new Item[initialSize];
        hashmap = new MapHache<>(initialSize);
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
    public V getValue(T key)
    {
        Integer position = hashmap.get(key);
        if (position != null)
            return heap[position].Key;
        
        return null;
    }
    
   
    /**
     *
     * @param value
     * @param key
     * @return
     */
    public boolean DecreaseKey(V value, T key)
    {
        Integer index = hashmap.get(key);
        if (index != null)
        {
            Item<V, T> item = heap[index];
            if (value.compareTo(item.Key) < 0)
            {
                item.Key = value;
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
    public boolean IncreaseKey(V value, T key)
    {
        Integer index = hashmap.get(key);
        if (index != null)
        {
            Item<V, T> item = heap[index];
            if (value.compareTo(item.Key) > 0)
            {
                item.Key = value;
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
    public void Insert(V value, T object)
    {    
        if (hashmap.containsKey(object))
        {
            return;
            // Got the index to the old entry with the same key
            //old = heap[hashmap.get(node.object.toString())];       
        }
        
        heap[tail] = new Item(value, object);
        int index = BubbleUp(tail);
        hashmap.put(object, index);
        tail++;
        
        if (tail == heap.length)
        {
            System.out.println("Kabooom! Increase size maybe...");
            IncreaseSize();
        }
    }
    
    
    private void IncreaseSize()
    {
        Item<V, T>[] newheap = new Item[heap.length * 2];        
        System.arraycopy(heap, 0, newheap, 0, heap.length);
        heap = newheap;
    }
 
    
    private int BubbleUp(int index)
    {  
        Item<V, T> last = heap[index];
        int parentindex = (index - 1) / 2;
        
        while (index > 0 && heap[parentindex].Key.compareTo(last.Key) >= 0)
        {
            hashmap.put(heap[parentindex].Value, index);
            heap[index] = heap[parentindex];
            index = parentindex;           
            parentindex = (index - 1) / 2;
        }
        
        heap[index] = last;
        hashmap.put(last.Value, index);
        return index;
    }   
  
    
    private int BubbleDown(int index)
    {
        Item<V, T> node = heap[index];
        
        while(index < tail / 2)
        {
            int smallerNodeIndex;
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;
            
            if (rightChildIndex < tail && (heap[rightChildIndex].Key).compareTo(heap[leftChildIndex].Key) < 0)
            {
                smallerNodeIndex = rightChildIndex;
            }
            else 
            {
                smallerNodeIndex = leftChildIndex;       
            }
            
            if ((node.Key).compareTo(heap[smallerNodeIndex].Key) <= 0)
            {
                break;                
            }
            
            // Swap
            hashmap.put(heap[smallerNodeIndex].Value, index);
            heap[index] = heap[smallerNodeIndex];
            index = smallerNodeIndex;
        }
        
        // Finally set the current node wherever it ends up...
        heap[index] = node; 
        hashmap.put(node.Value, index);
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
            Item<V, T> root = heap[0];
            heap[0] = heap[--tail];
            BubbleDown(0);
            
            // Dont forget to remove it from the hashmap as well, or cockup ensured
            hashmap.remove(root.Value);   
            
            return root.Value;
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
            return heap[0].Value;
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