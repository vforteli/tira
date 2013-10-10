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
@SuppressWarnings({"unchecked"})    // Java and generics and arrays dont go well together...
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
        int initialSize = 1000;
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
            return heap[position].key;
        
        return null;
    }
    
   
    /**
     *
     * @param value
     * @param key
     * @return
     */
    public boolean decreaseKey(V value, T key)
    {
        Integer index = hashmap.get(key);
        if (index != null)
        {
            Item<V, T> item = heap[index];
            if (value.compareTo(item.key) < 0)
            {
                item.key = value;
                bubbleUp(index);
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
    public boolean increaseKey(V value, T key)
    {
        Integer index = hashmap.get(key);
        if (index != null)
        {
            Item<V, T> item = heap[index];
            if (value.compareTo(item.key) > 0)
            {
                item.key = value;
                bubbleDown(index);
                return true;
            }
        }
        return false;
    }
    
      
    /**
     * insert an item in the heap with the specified value
     * @param value
     * @param object
     */
    public void insert(V value, T object)
    {    
        if (hashmap.containsKey(object))
        {
            return;
            // Got the index to the old entry with the same key
            //old = heap[hashmap.get(node.object.toString())];       
        }
        
        heap[tail] = new Item(value, object);
        int index = bubbleUp(tail);
        hashmap.put(object, index);
        tail++;
        
        if (tail == heap.length)
        {
            System.out.println("Kabooom! Increase size maybe...");
            increaseSize();
        }
    }
    
    
    private void increaseSize()
    {
        Item<V, T>[] newheap = new Item[heap.length * 2];        
        System.arraycopy(heap, 0, newheap, 0, heap.length);
        heap = newheap;
    }
 
    
    private int bubbleUp(int index)
    {  
        Item<V, T> last = heap[index];
        int parentindex = (index - 1) / 2;
        
        while (index > 0 && heap[parentindex].key.compareTo(last.key) >= 0)
        {
            hashmap.put(heap[parentindex].value, index);
            heap[index] = heap[parentindex];
            index = parentindex;           
            parentindex = (index - 1) / 2;
        }
        
        heap[index] = last;
        hashmap.put(last.value, index);
        return index;
    }   
  
    
    private int bubbleDown(int index)
    {
        Item<V, T> node = heap[index];
        
        while(index < tail / 2)
        {
            int smallerNodeIndex;
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;
            
            if (rightChildIndex < tail && (heap[rightChildIndex].key).compareTo(heap[leftChildIndex].key) < 0)
            {
                smallerNodeIndex = rightChildIndex;
            }
            else 
            {
                smallerNodeIndex = leftChildIndex;       
            }
            
            if ((node.key).compareTo(heap[smallerNodeIndex].key) <= 0)
            {
                break;                
            }
            
            // Swap
            hashmap.put(heap[smallerNodeIndex].value, index);
            heap[index] = heap[smallerNodeIndex];
            index = smallerNodeIndex;
        }
        
        // Finally set the current node wherever it ends up...
        heap[index] = node; 
        hashmap.put(node.value, index);
        return index;
    }
    
    
    /**
     * Removes and returns the minimum item from the heap.
     * @return
     */
    public T deleteMin()
    {
        if (tail > 0)
        {            
            Item<V, T> root = heap[0];
            heap[0] = heap[--tail];
            bubbleDown(0);
            
            // Dont forget to remove it from the hashmap as well, or cockup ensured
            hashmap.remove(root.value);   
            
            return root.value;
        }
        return null;
    }
    
    
    /**
     * Returns the minimum item from the heap, but does not remove it.
     * @return
     */
    public T peek()
    {
        if (tail > 0)
        {
            // Get the root item, ie the lowest key              
            return heap[0].value;
        }
        return null;
        
    }  
    
    
    /**
     * Check if the heap is empty
     * @return True if empty.
     */
    public boolean isEmpty()
    {
        return tail == 0;
    }
}