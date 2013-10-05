/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.AbstractMap;
import java.util.Set;

/**
 * Hash map aka MapHaché. Can increase in size, but not decrease...
 * @param <K> 
 * @param <V> 
 * @author verne_000
 */
public class MapHache<K, V> extends AbstractMap<K, V>
{
    private Item<K, V>[] array;
    private long items = 0;
    
    
    /**
     * Create a new MapHaché with initial size
     * @param initialSize
     */
    public MapHache(int initialSize)
    {
        // Set size to closest prime number maybe?
        array = new Item[initialSize];
    }
    
    
    @Override
    public V put(K key, V value)
    {
        if ((float)items / (float)array.length > 0.75)
        {
            IncreaseSize();
        }
        
        return insert(key, value, array);
    }
    
    
    @Override
    public V remove(Object key)
    {
        int index = GetIndex(key, array.length);
        Item item = array[index];
        
        if (item != null)
        {
            if (item.Key.equals(key))
            {
                array[index] = item.getNext();  // Next item, or null...   
                items--;
                return (V)item.Value;
            }
            
            while (item.getNext() != null)
            {
                if (item.getNext().Key.equals(key))
                {
                    V delete = (V)item.getNext().Value;
                    Item newnext = item.getNext().getNext();
                    item.setNext(newnext);
                    items--;
                    return delete;
                }
                
                item = item.getNext();
            }        
        }
        
        return null;
    }
    
     
    @Override
    public V get(Object key)
    {
        return getKeyValue((K)key);
    }
    
    
    @Override
    public boolean containsKey(Object key)
    {
        if (getKeyValue((K)key) != null)
        {
            return true;
        }
        return false;
    } 

    
    @Override
    public Set<Entry<K, V>> entrySet()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    private void IncreaseSize()
    {
        // Increase size! (thats what she said...)        
        Item<K, V>[] newarray = new Item[array.length * 2];
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != null)
            {
                Item item = array[i];                     
                while (item != null)
                {
                    insert((K)item.Key, (V)item.Value, newarray);
                    item = item.getNext();
                }
            }
        }
        array = newarray;
    }
    
    
    private V getKeyValue(K key)
    {
        Item item = array[GetIndex(key, array.length)];
               
        while (item != null)
        {
            if (item.Key.equals(key))
            {
                return (V)item.Value; 
            }
            item = item.getNext();
        }
        return null;
    }
    
    
    private int GetIndex(Object key, int modulo)
    {
        return Math.abs(key.hashCode() % modulo);
    }
    
    
    private V insert(K key, V value, Item<K, V>[] targetarray)
    {
        int index = GetIndex(key, targetarray.length);
        Item item = targetarray[index];
        
        if (item == null)   // This is an empty slot, so the item can be added directly in the array
        {
            targetarray[index] = new Item(key, value);
        }
        else // Add it to the linked list
        {
            while (item != null)
            {
                // If the key already exists, it should be updated and the old returned
                if (item.Key.equals(key))
                {
                    V previous = (V)item.Value;
                    item.Value = value;
                    return previous;
                }
                if (item.getNext() == null) // If we get here, the item has been put last in the linked list
                {
                    item.setNext(new Item(key, value));
                    break;
                }
                item = item.getNext();
            }    
        }
        items++;
        return null;
    }
}