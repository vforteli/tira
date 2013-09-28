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
    // Row major, ie yx. Not xy!
    private int[][] board;
    
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
     * Height of the board. Currently only supports square boards so width is redundant...
     * @return 
     */
    public int getHeight()    
    {
        return this.board.length;
    }
    public int getWidth()
    {
        // There cannot be width without height.
        // - Old Chinese saying
        return this.board[0].length;
    }
            
    
    
    /**
     * Create a new board with specified size
     * 
     * @param Size Integer size of one side of the board
     */
    public Board(int Size) 
    {
        this.board = new int[Size][Size];
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
                board[i][x1] = -1;
            } 
        }
        else    // Horizontal
        {
            for (int i = x1; i <= x2; i++)
            {
                board[y1][i] = -1;
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
            int i = r.nextInt(this.getHeight() - length + 1);
            int j = r.nextInt(this.getHeight()); 
            
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
     * Get the board as a int array of arrays. Coordinates are yx.
     * 
     * @return int array of arrays
     */
    public int[][] GetBoard() 
    {
        return this.board;        
    }
    
    
    private Coordinates previousCellClicked;
    
    /**
     * Click at the specified coordinates.
     * 
     * @param x
     * @param y
     * @return The value of the cell clicked
     * @throws Exception 
     */
    public int Click(int x, int y) throws Exception 
    {
        Coordinates c = new Coordinates(x, y);
        if (!this.isRunning)
        {
            throw new Exception("Game is not started");
        }
        if (!this.isValidCoordinates(x, y))
        {
            throw new Exception("Invalid coordinates");
        }
        
        
        // Debug stuff
//        Coordinates[] cs = this.GetNeighbours(new Coordinates(x, y));
//        for (Coordinates c : cs)
//        {
//            if (c != null)
//            {
//                System.out.println("Neighbour" + c.x + ", " + c.y);
//            }
//        }
        
        if (previousCellClicked != null)
        {
            // Do the astar search here
            FindPath(previousCellClicked, c);
        }
        
        
        previousCellClicked = c;
        
        return board[y][x];     
    }
    
    
    
        
    private void FindPath(Coordinates start, Coordinates end)
    {    
        MinHeap openset = new MinHeap();
        Node startnode = new Node();
        startnode.g_score = 0;
        startnode.h_score = HeuristicDistance.CalculateOptimalDistance(start, end);
        startnode.coordinates = start;
        openset.Insert(startnode.getF_score(), startnode);

        int loopbreaker = 100;
        while (loopbreaker >= 0 && !openset.getIsEmpty())
        {
            Node currentnode = (Node)openset.DeleteMin();
            System.out.println("Current node: " + currentnode.coordinates.x + ", " + currentnode.coordinates.y);
            
            if (currentnode.coordinates.equals(end))
            {
                System.out.println("Return path...");
                return;     // Reconstruct and return the path
            }
            
            Coordinates[] neighbours = GetNeighbours(currentnode.coordinates);
            
            for (Coordinates c : neighbours)
            {
                // Skip empty nodes... yeye the array could be trimmed, or a list could be used
                if (c == null)
                {
                    continue;
                }
                
                // Continue if this is a wall
                if (getCellValue(c) == -1)
                {
                    continue;
                }
                
                
                Node node = new Node();
                node.h_score = HeuristicDistance.CalculateOptimalDistance(c, end);
                node.g_score = currentnode.g_score + 1;
                node.coordinates = c;
                
                openset.Insert(node.getF_score(), node);
                
                
            }
            
            // Debug stuff
           
                Node node = (Node)openset.Peek();
                System.out.println(node.coordinates.x + "," + node.coordinates.y + " fscore: " + node.getF_score() + ", gscore: " + node.g_score + ", hscore: " + node.h_score);
            
            
            loopbreaker--;
        }
        
        // If we get here, no path was found...
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
     * Assumes x2 > x1 and y2 > y1, otherwise cockup ensured
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return 
     */
    private boolean CheckAdjecentCells(int x1, int y1, int x2, int y2) 
    {
        int topleft_x = x1 <= 0 ? 0 : x1 - 1;
        int topleft_y = y1 <= 0 ? 0 : y1 - 1;
        
        int bottomright_x = x2 >= this.getHeight() - 1 ? this.getHeight() - 1 : x2 + 1;
        int bottomright_y = y2 >= this.getHeight() - 1 ? this.getHeight() - 1 : y2 + 1;
              
        for (int i = topleft_x; i <= bottomright_x; i++) {
            for (int j = topleft_y; j <= bottomright_y; j++) {
                if (board[j][i] == -1) {
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
        
        int bottomright_x = c.x >= this.getHeight() - 1 ? this.getHeight() - 1 : c.x + 1;
        int bottomright_y = c.y >= this.getHeight() - 1 ? this.getHeight() - 1 : c.y + 1;
        
        Coordinates[] coordinates = new Coordinates[8]; // Cant be more than 8 neighbours aye?
        int n = 0;
        for (int i = topleft_x; i <= bottomright_x; i++) {
            for (int j = topleft_y; j <= bottomright_y; j++) {
                // Dont include self... daah
                if (i == c.x && j == c.y)
                {
                    continue;
                }
                coordinates[n++] = new Coordinates(i, j);
            }
        }
        
        return coordinates;
    }
    
    
    public int getCellValue(Coordinates c)
    {
        return this.board[c.y][c.x];
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

