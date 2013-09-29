/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author verne_000
 */
public class Node
{
    /**
     * Distance from start
     */
    public int g_score; 
    
    /**
     * Heuristic distance to end
     */
    public int h_score;
    
    /**
     * Estimated total distance through this node
     * @return 
     */
    public int getF_score()
    {
        return g_score + h_score;    
    }
    
    public Coordinates coordinates;
    public boolean visited;
    
    
    public Node parent;
}
