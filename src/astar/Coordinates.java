/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 * Encapsulates two dimensional coordinates
 * @author vforteli
 */
public class Coordinates
{
    /**
     * X
     */
    public int x;
    
    /**
     * Y
     */
    public int y;
    
    
    /**
     * Get the coordinates in human form, eg A2
     * @return 
     */
    public String getHumanCoordinates()
    {
        return (char)(this.x + 97) + Integer.toString(this.y + 1);
    }
    
    
    /**
     * Create a new coordinate object with specified coordinates
     * @param x
     * @param y 
     */
    public Coordinates(int x, int y)
    {
        this.x = x;
        this.y = y;        
    }
    
    
    /**
     * Converts human coordinates like A14 to zero based x and y coordinates
     * 
     * @param humanform
     * @return zero based x and y coordinates
     */
    public static Coordinates ParseCoordinates(String humanform)
    {
        try
        {
            humanform = humanform.toLowerCase();    // Lowercase means not having to check two ascii values...
            
            int x = ((int)humanform.charAt(0)) - 97;  // ASCII a is 97
            int y = Integer.parseInt(humanform.substring(1)) - 1; // Humans are stupid and count coordinates from 1
            
            return new Coordinates(x, y);
        }
        catch (Exception ex)
        {
            // Just return null if parsing fails...
            return null;
        }        
    }
    
    
    public boolean equals(Object other)
    {
        Coordinates c = (Coordinates)other;
        if (this.x == c.x && this.y == c.y)
        {
            return true;
        }
        return false;
    }
}  