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
        return GetDistance(from, to, 1);
    }
    
    
    public static float GetDistance(Coordinates from, Coordinates to, int multiplier)
    {
        int x = from.x - to.x;
        int y = from.y - to.y;
        // return x + y;    // Manhattan.. remember the absolut stuff then..
        return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * multiplier;   // Euclidean 
    }
}
