/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astartest;

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
        HybridHeap<Float, String> heap = new HybridHeap<>();
        heap.insert(10f, "first");
        
        String result = heap.peek();
        String expected = "first";
        assertEquals(expected, result);
    }
    
    @Test
    public void TestDeleteMin()
    {
        HybridHeap<Float, String> heap = new HybridHeap<>();
        heap.insert(10f, "first");
        heap.insert(5f, "second");
        heap.insert(15f, "third");
        String result = heap.deleteMin();
        String expected = "second";
        assertEquals(expected, result);
    }
    
    @Test
    public void TestDeleteMin2()
    {        
        HybridHeap<Float, String> heap = new HybridHeap<>();
        heap.insert(10f, "first");
        heap.insert(5f, "second");
        heap.insert(15f, "third");
        heap.deleteMin();
        heap.deleteMin();
        String result = heap.deleteMin();
        String expected = "third";
        assertEquals(expected, result);
    }
    
    
    @Test
    public void TestDeleteMinEmpty()
    {        
        HybridHeap<Float, String> heap = new HybridHeap<>();
        String result = heap.deleteMin();  
        assertNull(result);
    }
    
    @Test
    public void TestGetValue()
    {        
        HybridHeap<Float, String> heap = new HybridHeap<>();
        heap.insert(10f, "first");
        heap.insert(5f, "second");
        heap.insert(15f, "third");
        Float result = heap.getValue("second");
        Float expected = 5f;
        assertEquals(expected, result);
    }
    
    @Test
    public void TestGetValueFail()
    {        
        HybridHeap<Float, String> heap = new HybridHeap<>();
        heap.insert(10f, "first");
        heap.insert(5f, "second");
        heap.insert(15f, "third");
        Float result = heap.getValue("sechhond");
        assertNull(result);
    }
}