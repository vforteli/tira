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
    public HeapItem(float _key, Object _item)
    {
        this.key = _key;
        this.item = _item;
    }
    
    public float key;
    public Object item;
}
