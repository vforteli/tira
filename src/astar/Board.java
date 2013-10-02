/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author vforteli
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random; 

/**
 *
 * @author vforteli
 */
public class Board 
{       
    /**
     * Get the board as a int array of arrays. Coordinates are yx.
     * @return int array of arrays
     */
    public int[][] GetBoard() 
    {
        return this.board;        
    }
    private int[][] board;
    
    
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
    public Board(int Size, int terrainvariation) 
    {
        board = new int[Size][Size];
        for (int i = 0; i < Size; i++)
        {
            for (int j = 0; j < Size; j++)
            {
                int random = new Random().nextInt(terrainvariation);
                board[i][j] = random == 0 ? 1 : random; // yeye a bit biased...
            }
        }
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
        if (ValidObstacleLocation(x1, y1, x2, y2)) {
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
        if (!this.isValidCoordinates(c))
        {
            throw new Exception("Invalid coordinates");
        }
        
        return board[y][x];     
    }
    
    
    public HashSet<Coordinates> closedset;  // Moved here for diagnostics..
    public ArrayList<Coordinates> FindPath(Coordinates start, Coordinates end)
    {          
        HybridHeap<Coordinates> openset = new HybridHeap();
        closedset = new HashSet<>();
        HashMap<Coordinates, Coordinates> camefrom = new HashMap();
        HashMap<Coordinates, Float> g_score = new HashMap();
        
        g_score.put(start, 0f);
             
        openset.Insert(Heuristic.GetDistance(start, end), start);

        while (!openset.IsEmpty())
        {
            Coordinates current = openset.DeleteMin();
            closedset.add(current);
            
            if (current.equals(end))
                return ReconstructPath(current, camefrom);
                     
            for (Coordinates neighbour : GetNeighbours(current))
            {                
                float weight = CalculateWeight(current, neighbour);
                
                if (weight == -1)    // Wall...
                    continue;
                
                float tentative_gscore = g_score.get(current) + weight;
                float tentative_fscore = tentative_gscore + Heuristic.GetDistance(neighbour, end);
                Float g_scoreneighbour = g_score.get(neighbour);    // Float is nullable, float is not...
                //System.out.println(currentnode.coordinates + " to " + neighbour + "\t, tentative g_score: " + tentative_gscore + "\t, f_score: " + tentative_fscore);
                           
                if (closedset.contains(neighbour) && (g_scoreneighbour != null && g_scoreneighbour <= tentative_gscore))
                    continue;
                
                if (g_scoreneighbour == null || tentative_gscore < g_scoreneighbour)
                {
                    g_score.put(neighbour, tentative_gscore);
                    camefrom.put(neighbour, current);
                }
                
                if (!openset.Contains(neighbour) && !closedset.contains(neighbour))
                    openset.Insert(tentative_fscore, neighbour);             
                else
                    openset.DecreaseKey(tentative_fscore, neighbour);  // We can safely try to decrease the key, if the value is higher it wont change        
            }
        }
        
        // If we get here, no path was found...
        return null;
    }
    
    
    private ArrayList<Coordinates> ReconstructPath(Coordinates coordinates, HashMap<Coordinates, Coordinates> camefrom)
    {
        ArrayList<Coordinates> nodes = new ArrayList<>();      
        while (camefrom.containsKey(coordinates))
        {
            nodes.add(coordinates);
            coordinates = camefrom.get(coordinates);
        }
        return nodes;
    }
    
    
    
    
    /**
     * Checks if the specified coordinates are valid on the board, ie not out of bounds
     * 
     * @param x
     * @param y
     * @return True if valid coordinates
     */
    private boolean isValidCoordinates(Coordinates c) 
    {
        if ((c.x > this.getWidth() | c.x < 0) | (c.y > this.getHeight() | c.y < 0))
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
    private boolean ValidObstacleLocation(int x1, int y1, int x2, int y2) 
    {
        int topleft_x = x1;
        int topleft_y = y1;
        
        int bottomright_x = x2;
        int bottomright_y = y2;
        
        // Modified to allow obstacles to be placed next to each other
//        int topleft_x = x1 <= 0 ? 0 : x1 - 1;
//        int topleft_y = y1 <= 0 ? 0 : y1 - 1;
//        
//        int bottomright_x = x2 >= this.getHeight() - 1 ? this.getHeight() - 1 : x2 + 1;
//        int bottomright_y = y2 >= this.getHeight() - 1 ? this.getHeight() - 1 : y2 + 1;
              
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
     * Gets the neighbournode cells for specified coordinates. Obviously does not include out of bounds cells
     * Do some tests and make sure this doesnt explode...
     * @param neighbour
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
        
        // I dont want to have to check for empty array slots all the time...
        Coordinates[] returnarray = new Coordinates[n];
        System.arraycopy(coordinates, 0, returnarray, 0, n);
        return returnarray;
    }
    
    
    public int getCellValue(Coordinates c)
    {
        return this.board[c.y][c.x];
    }

    private float CalculateWeight(Coordinates from, Coordinates to)
    {
        float currentweight = getCellValue(from);
        float weight = getCellValue(to);
        
        if (weight == -1)
            return weight;
        
        if (from.x != to.x && from.y != to.y)
        {
            weight = (float)Math.sqrt(2 * Math.pow(weight, 2));
            currentweight = (float)Math.sqrt(2 * Math.pow(currentweight, 2));
        }
        weight = weight / 2 + currentweight / 2;
        return weight;
    }
}

