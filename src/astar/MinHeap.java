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
        
        
    }
    
    public Coordinates DeleteMin()
    {
        if (tail > 0)
        {
            MinHeapItem item = heap[0];
            
            // put last item as first and call heapify
            
            heap[0] = heap[--tail];
            //System.out.println("Heap tail: " + heap[tail-1]);
            
            BubbleDown(0);
                              
            return (Coordinates)item.item;
        }
        return null;
    }
}
