/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author verne_000
 */
public class MinHeapItem
{
    public MinHeapItem(int _value, Object _item)
    {
        this.value = _value;
        this.item = _item;
    }
    
    public int value;
    public Object item;
}
