/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @param <K> 
 * @param <V> 
 * @author verne_000
 */
public class Item<K, V>
{
    /**
     *
     */
    public K key;
    /**
     *
     */
    public V value;
    
    private Item _next;
    
    /**
     *
     * @param key
     * @param value
     */
    public Item(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
    
    /**
     * Set the next item in a linked list of items
     * @param next
     */
    public void setNext(Item next)
    {
        _next = next;
    }
    
    /**
     * Get the next item in the linked list, or null
     * @return
     */
    public Item getNext()
    {
        return _next;
    }
}
