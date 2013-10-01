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
    private HybridHeap h;
    
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
        h.Insert(30, new Coordinates(1,1));
        h.Insert(10, new Coordinates(1,2));
        h.Insert(40, new Coordinates(1,3));
        h.Insert(110, new Coordinates(1,4));
        h.Insert(80, new Coordinates(1,5));
        h.Insert(20, new Coordinates(1,6));
        h.Insert(50, new Coordinates(1,7));
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
        h.DecreaseKey(3, new Coordinates(1,4));
        Coordinates result = (Coordinates)h.Peek();
        Coordinates expected = new Coordinates(1, 4);
        assertEquals(expected, result);
    }
    
    
    @Test
    public void TestDecreaseKeyInsert()
    {
        h.DecreaseKey(3, new Coordinates(1,4));
        h.Insert(2, new Coordinates(2, 1));
        
        Coordinates result = (Coordinates)h.Peek();
        Coordinates expected = new Coordinates(2, 1);
        assertEquals(expected, result);
    }
    
    
     @Test
    public void TestDecreaseKeyInsertDecrease()
    {
        h.DecreaseKey(3, new Coordinates(1,4));        
        h.Insert(2, new Coordinates(2, 1));
        h.DecreaseKey(1, new Coordinates(1,4));
        
        Coordinates result = (Coordinates)h.Peek();
        Coordinates expected = new Coordinates(1, 4);
        assertEquals(expected, result);
    }
     
     
     
     
     
     @Test
    public void TestIncreaseKey()
    {
        h.IncreaseKey(200, new Coordinates(1,2));
        Coordinates result = (Coordinates)h.Peek();
        Coordinates expected = new Coordinates(1, 6);
        assertEquals(expected, result);
    }
    
    
    @Test
    public void TestIncreaseKeyInsert()
    {
        h.IncreaseKey(200, new Coordinates(1,2));
        h.Insert(2, new Coordinates(2, 1));
        
        Coordinates result = (Coordinates)h.Peek();
        Coordinates expected = new Coordinates(2, 1);
        assertEquals(expected, result);
    }
    
    
     @Test
    public void TestIncreaseKeyInsertIncrease()
    {
        h.IncreaseKey(200, new Coordinates(1,2));
        h.Insert(2, new Coordinates(2, 1));
        h.IncreaseKey(210, new Coordinates(2,1));
        
        Coordinates result = (Coordinates)h.Peek();
        Coordinates expected = new Coordinates(1, 6);
        assertEquals(expected, result);
    }
}