/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author vforteli
 */
import java.util.Random; 

/**
 *
 * @author vforteli
 */
public class Board 
{    
    /**
     * Represents the states a cell on the board can be in. Note that null is also a valid state...
     */
    public enum cellstate {
        /**
         *
         */
        Obstacle,
        /**
         *
         */
        Hit,
        /**
         *
         */
        Miss        
    }
    
    // Row major, ie yx. Not xy!
    private cellstate[][] board;
    private int size;
    
    /**
     * Indicates if the game is running
     * @return
     */
    public boolean getIsRunning()
    {
        return this.isRunning;
    }
    private boolean isRunning;
        
    
    /**
     * Create a new board with specified size
     * 
     * @param Size Integer size of one side of the board
     */
    public Board(int Size) 
    {
        this.size = Size;
        this.board = new cellstate[size][size];
    }
    
    /**
     * Add a obstacle to the board.
     * 
     * Add a obstacle to the board. 
     * An exception is thrown if the obstacle would collide with another obstacle already placed.
     * Obstacles cannot be placed next to each other, not even diagonally.
     * Obstacles cannot be placed diagonally.
     * x2 and y2 should be greater than x1 and y1 respectively
     * 
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return True if the obstacle was successfully placed, False if another obstacle already occupies the cells
     * @throws Exception 
     */
    public boolean AddObstacle(int x1, int y1, int x2, int y2) throws Exception 
    {
        if (this.isRunning)
        {
            throw new Exception("Game is already started");
        }
        
        // No negative 4th dimension quantum obstacles allowed
        if (x1 > x2 || y1 > y2)
        {
            throw new Exception("x1 and y1 must be smaller than x2 and y2");
        }
        
        // Make sure the obstacle isnt diagonal
        if (!(x1 == x2 | y1 == y2))
        {
            throw new Exception("Obstacles cannot be diagonally placed");  
        }
        
        // Check adjecent cells, apparently obstacles are not allowed to touch, not even diagonally
        if (CheckAdjecentCells(x1, y1, x2, y2)) {
            return false;
        }
        
        // Place the obstacle
        if (x1 == x2)   // Vertical
        {
            for (int i = y1; i <= y2; i++)
            {
                board[i][x1] = cellstate.Obstacle;
            } 
        }
        else    // Horizontal
        {
            for (int i = x1; i <= x2; i++)
            {
                board[y1][i] = cellstate.Obstacle;
            } 
        }        
        return true;
    }
      
    
    /**
     * Add a random obstacle to the board with the specified length
     * 
     * @param length
     * @return True if successful
     * @throws Exception
     */
    public boolean AddRandomObstacle(int length) throws Exception 
    {
        // Loop forever until a obstacle has successfully been placed... :p
        while (true)
        {
            Random r = new Random();
            int i = r.nextInt(this.size - length + 1);
            int j = r.nextInt(this.size); 
            
            if (r.nextBoolean())    // Horizontal
            {                
                if (this.AddObstacle(i, j, i + length - 1, j))
                {
                    return true;                    
                }
            }
            else    // Vertical
            {
                if (this.AddObstacle(j, i, j, i + length - 1))
                {
                    return true;
                }
            }
        }               
    }
    
    
    /**
     * Get the board as a cellstate array of arrays. Coordinates are yx.
     * 
     * @return cellstate array of arrays
     */
    public cellstate[][] GetBoard() 
    {
        return this.board;        
    }
    
    
    /**
     * Click at the specified coordinates.
     * 
     * It is possible to fire at the same coordinates more than once. What does the rules say?
     * 
     * @param x
     * @param y
     * @return The new cellstate of the cell fire on
     * @throws Exception 
     */
    public cellstate Click(int x, int y) throws Exception 
    {
        if (!this.isRunning)
        {
            throw new Exception("Game is not started");
        }
        if (!this.isValidCoordinates(x, y))
        {
            throw new Exception("Invalid coordinates");
        }
        
        if (board[y][x] == cellstate.Obstacle)
        {           
            return board[y][x] = cellstate.Hit;
        }       
        else if (board[y][x] == cellstate.Hit)
        {
            return cellstate.Hit;            
        }
        return board[y][x] = cellstate.Miss;       
    }
    
    
    
    /**
     * Checks if the specified coordinates are valid on the board, ie not out of bounds
     * 
     * @param x
     * @param y
     * @return True if valid coordinates
     */
    private boolean isValidCoordinates(int x, int y) 
    {
        if ((x > board[0].length | x < 0) | (y > board.length | y < 0))
        {
            return false;
        }
        return true;
    }
    
    
    /**
     * Checks a obstacles surrounding cells to make sure no other obstacle occupies any of these cells
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return 
     */
    private boolean CheckAdjecentCells(int x1, int y1, int x2, int y2) 
    {
        int box1x = x1 - 1;
        box1x = box1x < 0 ? 0 : box1x;
        int box1y = y1 - 1;
        box1y = box1y < 0 ? 0 : box1y;
        int box2x = x2 + 1;
        box2x = box2x >= size - 1 ? size - 1 : box2x;
        int box2y = y2+1;
        box2y = box2y >= size - 1 ? size - 1 : box2y;
        for (int i = box1x; i <= box2x; i++) {
            for (int j = box1y; j <= box2y; j++) {
                if (board[j][i] == cellstate.Obstacle) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Gets the neighbour cells for specified coordinates. Obviously does not include out of bounds cells
     * Do some tests and make sure this doesnt explode...
     * @param c
     * @return 
     */
    public Coordinates[] GetNeighbours(Coordinates c)
    {      
        int topleft_x = c.x <= 0 ? 0 : c.x - 1;
        int topleft_y = c.y <= 0 ? 0 : c.y - 1;
        
        int bottomright_x = c.x >= size - 1 ? size - 1 : c.x + 1;
        int bottomright_y = c.y >= size - 1 ? size - 1 : c.y + 1;
        
        Coordinates[] coordinates = new Coordinates[8]; // Cant be more than 8 neighbours aye?
        int n = 0;
        for (int i = topleft_x; i <= bottomright_x; i++) {
            for (int j = topleft_y; j <= bottomright_y; j++) {
                coordinates[n++] = new Coordinates(i, j);
            }
        }
        
        return coordinates;
    }
    
    
    /**
     * Start the game
     * Returns true if successful. Make sure there are obstacles placed on the board..
     * @return 
     */
    public boolean StartGame()
    {
        if (!this.isRunning)
        {
            this.isRunning = true;           
            return true;
        }
        return false;
    }
}

