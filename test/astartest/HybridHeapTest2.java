/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astartest;

import astar.HybridHeap;
import astar.HybridHeap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author verne_000
 */
public class HybridHeapTest2
{
    private static HybridHeap<String> heap;
    public HybridHeapTest2()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        heap = new HybridHeap();
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
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
        heap.Insert(10, "first");
        
        String result = heap.Peek();
        String expected = "first";
        assertEquals(expected, result);
    }
    
    @Test
    public void TestInsertDeleteMin()
    {
        heap.Insert(5, "second");
        heap.Insert(15, "third");
        
        String result = heap.DeleteMin();
        String expected = "second";
        assertEquals(expected, result);
    }
    
//    @Test
//    public void TestInsertDeleteMin2()
//    {        
//        heap.DeleteMin();
//        String result = heap.DeleteMin();
//        String expected = "third";
//        assertEquals(expected, result);
//    }
}