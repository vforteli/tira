/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author verne_000
 */
public class HeuristicDistance
{
    /**
     * Uses some heuristic function to calculate the supposedly optimal distance.
     * Currently just uses the Manhattan distance, aka taxicab geometry
     */
    public static int CalculateOptimalDistance(Coordinates from, Coordinates to)
    {
        int x = Math.abs(from.x - to.x);
        int y = Math.abs(from.y - to.y);
        
        return x + y;
    }
}
