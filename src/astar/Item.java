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
    public K Key;
    /**
     *
     */
    public V Value;
    
    private Item _next;
    
    /**
     *
     * @param key
     * @param value
     */
    public Item(K key, V value)
    {
        Key = key;
        Value = value;
    }
    
    /**
     *
     * @param next
     */
    public void setNext(Item next)
    {
        _next = next;
    }
    
    /**
     *
     * @return
     */
    public Item getNext()
    {
        return _next;
    }
}
