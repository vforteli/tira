/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.AbstractMap;
import java.util.Set;

/**
 *
 * @author verne_000
 */
public class MapHache<K, V> extends AbstractMap<K, V>
{
    private Item<K, V>[] array;
    private long items = 0;
    
    
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
    
    
    private V insert(K key, V value, Item<K, V>[] targetarray)
    {
        int index = key.hashCode() % targetarray.length;
        Item item = targetarray[index];
        
        if (item == null)   // This is an empty slot, so the item can be added directly in the array
        {
            targetarray[index] = new Item(key, value);
            items++;
        }
        else 
        {
            while (item.getNext() != null)
            {
                if (item._key.equals(key))
                {
                    V previous = (V)item._value;
                    item._value = value;
                    return previous;
                }
                item = item.getNext();
            }
            
            // If we get here, the item has been put last in the linked list
            item.setNext(new Item(key, value));
            items++;
        }
        return null;
    }
    
    
    @Override
    public V remove(Object key)
    {
        int index = key.hashCode() % array.length;
        Item item = array[index];
        
        if (item != null)
        {
            if (item._key.equals(key))
            {
                if (item.getNext() == null) // No list, just remove from array
                {
                    array[index] = null;   
                }
                else
                {
                    array[index] = item.getNext();
                }
                items--;
                return (V)item._value;
            }
            
            while (item.getNext() != null)
            {
                if (item.getNext()._key.equals(key))
                {
                    V delete = (V)item.getNext()._value;
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
    
    
    private V getKeyValue(K key)
    {
        int index = key.hashCode() % array.length;
        Item item = array[index];
               
        while (item != null)
        {
            if (item._key.equals(key))
            {
                return (V)item._value; 
            }
            item = item.getNext();
        }
        return null;
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
        System.out.println("Increase size, fill factor: " + (double)items / (double)array.length);
        
        Item<K, V>[] newarray = new Item[array.length * 2];
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != null)
            {
                Item item = array[i];                     
                while (item != null)
                {
                    insert((K)item._key, (V)item._value, newarray);
                    item = item.getNext();
                }
            }
        }
        System.out.println("New size: " + newarray.length + ", fill factor: " + (double)items / (double)newarray.length);
        array = newarray;
    }
}
