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
public class PathInfo
{
    /**
     * PathInfo encapsulates the results of a path search including some diagnostic info
     * @param coordinates
     * @param closedset
     * @param pathlength
     */
    public PathInfo(AbstractMap<Coordinates, Coordinates> coordinates, AbstractMap<Coordinates, Integer> closedset, Float pathlength)
    {
        this.coordinates = coordinates;
        this.closedset = closedset;
        this.pathlength = pathlength;
    }
    
    /**
     * Contains all the nodes closed during the search
     */
    public AbstractMap<Coordinates, Integer> closedset;
    
    /**
     * Contains the nodes in the path
     */
    public AbstractMap<Coordinates, Coordinates> coordinates;
    
    /**
     * Total length of the path from start to end
     */
    public Float pathlength;
}
