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
     * Converts human coordinates like x,y to zero based x and y coordinates
     * 
     * @param humanform
     * @return zero based x and y coordinates
     */
    public static Coordinates ParseCoordinates(String humanform)
    {
        try
        {
            String[] foo = humanform.split(",");
            int x = Integer.parseInt(foo[0]);
            int y = Integer.parseInt(foo[1]);
            
            return new Coordinates(x, y);
        }
        catch (Exception ex)
        {
            // Just return null if parsing fails...
            return null;
        }        
    }
     
    
    @Override
    public boolean equals(Object other)
    {
        if (other instanceof Coordinates)
        {
            Coordinates c = (Coordinates)other;
            if (this.x == c.x && this.y == c.y)
            {
                return true;
            }
        }
        return false;
    }

    
    @Override
    public int hashCode()
    {
        // Umm... probably insanely crappy...
        int hash = 7;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
        return hash;
    }
    
    
    @Override
    public String toString()
    {
        return this.x + "," + this.y;
    }
}  