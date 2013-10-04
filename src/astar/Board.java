/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author vforteli
 */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.AbstractMap;
import java.util.Random; 
import javax.imageio.ImageIO;

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
            
    
    private int terrainMaxValue;
    public int getTerrainMaxValue()
    {
        return terrainMaxValue;
    }
    
    
    /**
     * Create a new board with specified size
     * 
     * @param Size Integer size of one side of the board
     */
    public Board(int Size, int terrainvariation, File bitmap) 
    {
        terrainMaxValue = terrainvariation;
        board = new int[Size][Size];
        if (bitmap != null)
        {
            try 
            { 
                BufferedImage image = ImageIO.read(bitmap);
                if (image != null)
                {
                    for (int i = 0; i < Size; i++)
                    {
                        for (int j = 0; j < Size; j++)
                        {
                            int weight;
                            float[] hsb = new float[3];
                            int p = image.getRGB(j, i);
                            Color.RGBtoHSB((p>>16)&0xff, (p>>8)&0xff, p&0xff, hsb);
                            float brightness = hsb[2];
                            if (brightness < 0.05f)
                            {
                                weight = -1;
                            }
                            else 
                            {
                                float min = 0;
                                float max = 1;
                                float outmax = terrainMaxValue;
                                float outmin = 1;

                                // Reverse brightness... brighter in this case means lower weight
                                brightness = (brightness - max) * -1;
                                weight = Math.round(outmin + (brightness - min) * (outmax - outmin) / (max - min));
                            }
                            board[i][j] = weight;
                        }
                    }   
                }
            }
            catch (Exception ex)
            {
                // ...
            }
        }   
        else
        {
            for (int i = 0; i < Size; i++)
            {
                for (int j = 0; j < Size; j++)
                {
                    int random = new Random().nextInt(terrainMaxValue);
                    board[i][j] = random == 0 ? 1 : random; // yeye a bit biased...
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
    
    
    public AbstractMap<Coordinates, Integer> closedset;  // Moved here for diagnostics...
    public AbstractMap<Coordinates, Coordinates> FindPath(Coordinates start, Coordinates end, int tolerance)
    {          
        HybridHeap<Coordinates> openset = new HybridHeap();
        closedset = new MapHache(701);
        AbstractMap<Coordinates, Coordinates> camefrom = new MapHache<>(701);
        AbstractMap<Coordinates, Float> g_score = new MapHache<>(701);
        
        g_score.put(start, 0f);
             
        openset.Insert(Heuristic.GetDistance(start, end, tolerance), start);

        while (!openset.IsEmpty())
        {
            Coordinates current = openset.DeleteMin();
            closedset.put(current, 0);
            
            if (current.equals(end))
                return ReconstructPath(current, camefrom);
                     
            for (Coordinates neighbour : GetNeighbours(current))
            {                
                float weight = CalculateWeight(current, neighbour);
                
                if (weight == -1)    // Wall...
                    continue;
                
                float tentative_gscore = g_score.get(current) + weight;
                float tentative_fscore = tentative_gscore + Heuristic.GetDistance(neighbour, end, tolerance);
                Float g_scoreneighbour = g_score.get(neighbour);
                //System.out.println(currentnode.coordinates + " to " + neighbour + "\t, tentative g_score: " + tentative_gscore + "\t, f_score: " + tentative_fscore);
                           
                if (closedset.containsKey(neighbour) && (g_scoreneighbour != null && g_scoreneighbour <= tentative_gscore))
                    continue;
                
                if (g_scoreneighbour == null || tentative_gscore < g_scoreneighbour)
                {
                    g_score.put(neighbour, tentative_gscore);
                    camefrom.put(neighbour, current);
                }
                
                if (!openset.containsKey(neighbour) && !closedset.containsKey(neighbour))
                    openset.Insert(tentative_fscore, neighbour);             
                else
                    openset.DecreaseKey(tentative_fscore, neighbour);  // We can safely try to decrease the key, if the value is higher it wont change        
            }
        }
        
        // If we get here, no path was found...
        return null;
    }
    
    
    private AbstractMap<Coordinates, Coordinates> ReconstructPath(Coordinates coordinates, AbstractMap<Coordinates, Coordinates> camefrom)
    {
        MapHache<Coordinates, Coordinates> nodes = new MapHache(701);      
        while (camefrom.containsKey(coordinates))
        {
            nodes.put(coordinates, coordinates);
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
     * Gets the neighbournode cells for specified coordinates. Obviously does not include out of bounds cells
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

