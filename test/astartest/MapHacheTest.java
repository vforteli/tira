/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astartest;

import astar.MapHache;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author verne_000
 */
public class MapHacheTest
{
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    
    @Test
    public void InsertEmpty()
    {
        MapHache<Integer, Integer> hashmap = new MapHache(701);
        Integer result = hashmap.put(5, 5);
        assertNull(result);
    }
    
    
    @Test
    public void Contains()
    {
        MapHache<Integer, Integer> hashmap = new MapHache(701);
        hashmap.put(5, 5);
        boolean result = hashmap.containsKey(5);
        assertTrue(result);
    }
    
    
    @Test
    public void Get()
    {
        MapHache<Integer, Integer> hashmap = new MapHache(701);
        hashmap.put(5, 5);
        Integer result = hashmap.get(5);
        Integer expected = 5;
        assertEquals(expected, result);
    }
    
    
    @Test
    public void Get2()
    {
        MapHache<Integer, Integer> hashmap = new MapHache(701);
        hashmap.put(5, 5);
        hashmap.put(4, 4);
        hashmap.put(3, 3);
        Integer result = hashmap.get(4);
        Integer expected = 4;
        assertEquals(expected, result);
    }
    
    
    @Test
    public void Overwrite()
    {
        MapHache<Integer, Integer> hashmap = new MapHache(701);
        hashmap.put(5, 5);
        Integer result = hashmap.put(5, 4);
        Integer expected = 5;
        assertEquals(expected, result);
    }
    
    
    @Test
    public void Overwrite2()
    {
        MapHache<Integer, Integer> hashmap = new MapHache(701);
        hashmap.put(5, 5);
        hashmap.put(5, 4);
        Integer result = hashmap.get(5);
        Integer expected = 4;
        assertEquals(expected, result);
    }
    
    
    @Test
    public void GetEmpty()
    {
        
        MapHache<Integer, Integer> hashmap = new MapHache(701);     
        Integer result = hashmap.get(5);
        assertNull(result);
    }
    
    
    @Test
    public void GetEmpty2()
    {
        MapHache<Integer, Integer> hashmap = new MapHache(701);   
        hashmap.put(4, 4);
        Integer result = hashmap.get(5);
        assertNull(result);
    }
    
    
    @Test
    public void Delete()
    {
        MapHache<Integer, Integer> hashmap = new MapHache(701);   
        hashmap.put(4, 4);
        Integer result = hashmap.remove(4);
        Integer expected = 4;
        assertEquals(expected, result);
    }
    
    
    @Test
    public void DeleteNonExistent()
    {
        MapHache<Integer, Integer> hashmap = new MapHache(701);   
        hashmap.put(4, 4);
        Integer result = hashmap.remove(5);
        Integer expected = null;
        assertEquals(expected, result);
    }   
    
    
    @Test
    public void AutoGrow()
    {
        MapHache<Integer, Integer> hashmap = new MapHache(1);   
        hashmap.put(4, 4);
        Integer result = hashmap.put(5, 5);
        assertNull(result);
    }
    
    
    @Test
    public void AutoGrowGet()
    {
        MapHache<Integer, Integer> hashmap = new MapHache(1);   
        hashmap.put(4, 4);
        hashmap.put(3, 3);
        hashmap.put(2, 2);
        Integer result = hashmap.get(3);
        Integer expected = 3;
        assertEquals(expected, result);
    } 
}