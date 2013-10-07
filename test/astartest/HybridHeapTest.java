package astartest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import astar.Coordinates;
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
public class HybridHeapTest
{
    private HybridHeap<Integer, Coordinates> h;
    
    public HybridHeapTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
        h = new HybridHeap();
        h.insert(30, new Coordinates(1,1));
        h.insert(10, new Coordinates(1,2));
        h.insert(40, new Coordinates(1,3));
        h.insert(110, new Coordinates(1,4));
        h.insert(80, new Coordinates(1,5));
        h.insert(20, new Coordinates(1,6));
        h.insert(50, new Coordinates(1,7));
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
    public void TestDecreaseKey()
    {
        h.decreaseKey(3, new Coordinates(1,4));
        Coordinates result = (Coordinates)h.peek();
        Coordinates expected = new Coordinates(1, 4);
        assertEquals(expected, result);
    }
    
    
    @Test
    public void TestDecreaseKeyInsert()
    {
        h.decreaseKey(3, new Coordinates(1,4));
        h.insert(2, new Coordinates(2, 1));
        
        Coordinates result = (Coordinates)h.peek();
        Coordinates expected = new Coordinates(2, 1);
        assertEquals(expected, result);
    }
    
    
     @Test
    public void TestDecreaseKeyInsertDecrease()
    {
        h.decreaseKey(3, new Coordinates(1,4));        
        h.insert(2, new Coordinates(2, 1));
        h.decreaseKey(1, new Coordinates(1,4));
        
        Coordinates result = (Coordinates)h.peek();
        Coordinates expected = new Coordinates(1, 4);
        assertEquals(expected, result);
    }
     
     
     
     
     
     @Test
    public void TestIncreaseKey()
    {
        h.increaseKey(200, new Coordinates(1,2));
        Coordinates result = (Coordinates)h.peek();
        Coordinates expected = new Coordinates(1, 6);
        assertEquals(expected, result);
    }
    
    
    @Test
    public void TestIncreaseKeyInsert()
    {
        h.increaseKey(200, new Coordinates(1,2));
        h.insert(2, new Coordinates(2, 1));
        
        Coordinates result = (Coordinates)h.peek();
        Coordinates expected = new Coordinates(2, 1);
        assertEquals(expected, result);
    }
    
    
    @Test
    public void TestIncreaseKeyInsertIncrease()
    {
        h.increaseKey(200, new Coordinates(1,2));
        h.insert(2, new Coordinates(2, 1));
        h.increaseKey(210, new Coordinates(2,1));
        
        Coordinates result = (Coordinates)h.peek();
        Coordinates expected = new Coordinates(1, 6);
        assertEquals(expected, result);
    }
}