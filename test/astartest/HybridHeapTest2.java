/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astartest;

import astar.Coordinates;
import astar.HybridHeap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author verne_000
 */
public class HybridHeapTest2
{
    public HybridHeapTest2()
    {
    }
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void TestInsert()
    {
        HybridHeap<String> heap = new HybridHeap<>();
        heap.Insert(10, "first");
        
        String result = heap.Peek();
        String expected = "first";
        assertEquals(expected, result);
    }
    
    @Test
    public void TestDeleteMin()
    {
        HybridHeap<String> heap = new HybridHeap<>();
        heap.Insert(10, "first");
        heap.Insert(5, "second");
        heap.Insert(15, "third");
        String result = heap.DeleteMin();
        String expected = "second";
        assertEquals(expected, result);
    }
    
    @Test
    public void TestDeleteMin2()
    {        
        HybridHeap<String> heap = new HybridHeap<>();
        heap.Insert(10, "first");
        heap.Insert(5, "second");
        heap.Insert(15, "third");
        heap.DeleteMin();
        heap.DeleteMin();
        String result = heap.DeleteMin();
        String expected = "third";
        assertEquals(expected, result);
    }
    
    
    @Test
    public void TestDeleteMinEmpty()
    {        
        HybridHeap<String> heap = new HybridHeap<>();
        String result = heap.DeleteMin();  
        assertNull(result);
    }
    
    @Test
    public void TestGetValue()
    {        
        HybridHeap<String> heap = new HybridHeap<>();
        heap.Insert(10, "first");
        heap.Insert(5, "second");
        heap.Insert(15, "third");
        Float result = heap.getValue("second");
        Float expected = 5f;
        assertEquals(expected, result);
    }
    
    @Test
    public void TestGetValueFail()
    {        
        HybridHeap<String> heap = new HybridHeap<>();
        heap.Insert(10, "first");
        heap.Insert(5, "second");
        heap.Insert(15, "third");
        Float result = heap.getValue("sechhond");
        assertNull(result);
    }
}