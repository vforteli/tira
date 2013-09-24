/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author verne_000
 */
public class MinHeap
{
    protected MinHeapItem[] heap;
    private int tail;
    
    public MinHeap()
    {
        // Hmm, what should the initial size be, and when should it grow/shrink?
        heap = new MinHeapItem[20];
        tail = 0;
    }
    
    
    
    public void Insert(int value, Coordinates coordinates)
    {        
        MinHeapItem item = new MinHeapItem(value, coordinates);
        
        // Insert the item in the last place and call bubbleup to get item in correct place
        heap[tail] = item;
        BubbleUp(tail);
        
        
        tail++;
        if (tail == heap.length)
        {
            System.out.println("Increase size...");
            // increase the array size...            
        }
    }
    
    private void BubbleUp(int index)
    {
        MinHeapItem last = heap[index];
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
        MinHeapItem node = heap[index];
        
        int smallerNodeIndex;
        while(index < tail / 2)
        {
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
            System.out.println("Smallernodeindex: " + smallerNodeIndex);
            
            // If the key is smaller, the heap is in order
            if (node.key <= heap[smallerNodeIndex].key)
            {
                break;                
            }
            
            // Swap
            heap[index] = heap[smallerNodeIndex];
            index = smallerNodeIndex;
        }
        heap[index] = node;    
    }
    
    public Coordinates DeleteMin()
    {
        if (tail > 0)
        {
            // Get the root item, ie the lowest key
            MinHeapItem root = heap[0];
            
            // Set last as new root
            heap[0] = heap[--tail];
            
            //System.out.println("Heap tail: " + heap[tail-1]);
            
            // Find correct place for new root
            BubbleDown(0);
                              
            return (Coordinates)root.item;
        }
        return null;
    }
}
