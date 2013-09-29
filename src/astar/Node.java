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
    public float g_score; 
    
    /**
     * Heuristic distance to end
     */
    public float h_score;
    
    
    public Node(float h_score, float g_score, Coordinates c)
    {
        this.h_score = h_score;
        this.g_score = g_score;
        this.coordinates = c;
    }
    
    /**
     * Estimated total distance through this node
     * @return 
     */
    public float getF_score()
    {
        return g_score + h_score;    
    }
    
    public Coordinates coordinates;
    public boolean visited;
    
    
    public Node parent;
}
