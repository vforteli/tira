/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author verne_000
 */
public class HeapItem
{
    /**
     *
     * @param _key
     * @param _item
     */
    public HeapItem(Object _key, Object _item)
    {
        this.key = _key;
        this.item = _item;
    }
    
    /**
     *
     */
    public Object key;
    /**
     *
     */
    public Object item;
}
