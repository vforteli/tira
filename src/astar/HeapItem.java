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
    public HeapItem(int _key, Object _item)
    {
        this.key = _key;
        this.item = _item;
    }
    
    public int key;
    public Object item;
}
