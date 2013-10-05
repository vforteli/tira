/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.AbstractMap;

/**
 *
 * @author verne_000
 */
public class Pathinfo
{
    public Pathinfo(AbstractMap<Coordinates, Coordinates> path, AbstractMap<Coordinates, Integer> closedset, Float pathlength)
    {
        this.path = path;
        this.closedset = closedset;
        this.pathlength = pathlength;
    }
    
    public AbstractMap<Coordinates, Integer> closedset;
    public AbstractMap<Coordinates, Coordinates> path;
    public Float pathlength;
}
