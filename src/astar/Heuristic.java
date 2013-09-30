/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author verne_000
 */
public class Heuristic
{
    /**
     * Uses some heuristic function to calculate the supposedly optimal distance.
     * Updated to use euclidean distance
     */
    public static float GetDistance(Coordinates from, Coordinates to)
    {
        int x = Math.abs(from.x - to.x);
        int y = Math.abs(from.y - to.y);
        
        return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
