/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.HashSet;

/**
 *
 * @author verne_000
 */
public class MinHeap
{
    protected HeapItem[] heap;
    private int tail;
    private HashSet<Object> hash;   // Replace with own implementation
    
    public MinHeap()
    {
        // Hmm, what should the initial size be, and when should it grow/shrink?
        heap = new HeapItem[2000];
        hash = new HashSet<>(2000);
        tail = 0;
    }
    
    
    
    public void Insert(float value, Object o)
    {    
        if (!hash.add(((Node)o).coordinates.getHumanCoordinates()))
        {
            System.out.println("nyet");
            return;
        }
        HeapItem item = new HeapItem(value, o);
        
        heap[tail] = item;
        BubbleUp(tail);
           
        tail++;
        if (tail == heap.length)
        {
            System.out.println("Increase size...");
            // increase the array size...            
        }
        System.out.println("Insert, tail: " + tail);
    }
    
    private void BubbleUp(int index)
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
    }   
    
    private void BubbleDown(int index)
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
    }
    
    public Object DeleteMin()
    {
        if (tail > 0)
        {
            // Get the root item, ie the lowest key
            HeapItem root = heap[0];
            
            // Set last as new root
            heap[0] = heap[--tail];
            
            //System.out.println("Heap tail: " + heap[tail-1]);
            
            // Find correct place for new root
            BubbleDown(0);
            System.out.println("Delete, tail: " + tail); 
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
