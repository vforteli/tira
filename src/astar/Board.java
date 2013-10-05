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
import java.math.BigDecimal;
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
    public TerrainCell[][] GetBoard() 
    {
        return this.board;        
    }
    private TerrainCell[][] board;
    
    public TerrainCell getCellValue(Coordinates c)
    {
        return this.board[c.y][c.x];
    }
    
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
    public int getTerrainMaxWeight()
    {
        return terrainMaxValue;
    }
    
    private int terrainMinWeight;
    public int getTerrainMinWeight()
    {
        return terrainMinWeight;
    }
    
   
    
    
    /**
     * Create a new board with specified size
     * 
     * @param Size Integer size of one side of the board
     */
    public Board(int Size, int terrainMinWeight, int terrainMaxWeight, File bitmap) 
    {
        this.terrainMaxValue = terrainMaxWeight;
        this.terrainMinWeight = terrainMinWeight;
        this.board = new TerrainCell[Size][Size];
        
        if (bitmap != null)
        {
            try 
            { 
                BufferedImage image = ImageIO.read(bitmap);
                
                float inputmin = 0;
                float inputmax = 1;
                float outputmin = this.terrainMinWeight;
                float outputmax = this.terrainMaxValue;
                             
                for (int i = 0; i < Size; i++)
                {
                    for (int j = 0; j < Size; j++)
                    {
                        float[] hsb = new float[3];
                        int pixel = image.getRGB(j, i);
                        Color.RGBtoHSB((pixel>>16)&0xff, (pixel>>8)&0xff, pixel&0xff, hsb);
                        float brightness = hsb[2];
                        float hue = hsb[0];
                        
                        TerrainTypes type = TerrainTypes.Ground;
                        
                        if (hue > 0.50 && hue < 0.75)
                            type = TerrainTypes.Water;
                        
                        else if (hue > 0.045 && hue < 0.13)
                            type = TerrainTypes.Road;
                        
                        else if (hue > 0.22 && hue < 0.39)
                            type = TerrainTypes.Forest;
                        
                        else if (hue < 0.025 || hue > 0.95)
                            type = TerrainTypes.Dragon;
                        
                        if (brightness > 0.95)
                            type = TerrainTypes.Road;
                        
                        if (brightness < 0.05f) // Pretty close to black...
                        {
                            board[i][j] = new TerrainCell(-1, TerrainTypes.Impassible);    // -1 denotes a wall that cannot be traversed at all
                        }
                        else 
                        {
                            // Reverse brightness... brighter in this case means lower weight
                            brightness = Math.abs(brightness - inputmax);
                            board[i][j] = new TerrainCell(Math.round(AstarMath.ConvertRange(inputmin, inputmax, outputmin, outputmax, brightness)), type);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                // ... current best practice.
            }
        }   
        else
        {
            for (int i = 0; i < Size; i++)
            {
                for (int j = 0; j < Size; j++)
                {
                    int random = new Random().nextInt(terrainMaxValue + 1);
                    board[i][j] = new TerrainCell(random == 0 ? 1 : random, TerrainTypes.Ground); // yeye a bit biased...
                }
            }
        }
    }   
    
    
    public PathInfo FindPath(Coordinates start, Coordinates end, int heuristicMultiplier)
    {      
        HybridHeap<Float, Coordinates> openset = new HybridHeap();
        AbstractMap<Coordinates, Integer> closedset = new MapHache(701);
        AbstractMap<Coordinates, Coordinates> camefrom = new MapHache<>(701);
        AbstractMap<Coordinates, Float> g_score = new MapHache<>(701);
        
        // Distance from start is obviously 0... good place to start
        g_score.put(start, 0f);
        openset.Insert(GetHDistance(start, end, heuristicMultiplier, terrainMinWeight), start);

        while (!openset.IsEmpty())
        {
            Coordinates current = openset.DeleteMin();
            closedset.put(current, 0);
            
            if (current.equals(end))
                return new PathInfo(ReconstructPath(current, camefrom), closedset, g_score.get(end));
                                 
            for (Coordinates neighbour : GetNeighbours(current))
            {                
                float weight = CalculateWeight(current, neighbour);
                
                if (weight == -1)    // Wall...
                    continue;
                
                float tentative_gscore = g_score.get(current) + weight;      
                Float g_scoreneighbour = g_score.get(neighbour);
                           
                // If this neighbour is already processed and the gscore through the current node is not lower, we can skip to the next
                if (closedset.containsKey(neighbour) && g_scoreneighbour <= tentative_gscore)
                    continue;
                
                // If this is the first time at the neighbour, or the gscore through the current node is better, update stuff
                if (g_scoreneighbour == null || tentative_gscore < g_scoreneighbour)
                {
                    g_score.put(neighbour, tentative_gscore);
                    camefrom.put(neighbour, current);
                }
                
                // If the neighbour node is seen for the first time, ie not open and not closed, put it in the openset
                float tentative_fscore = tentative_gscore + GetHDistance(neighbour, end, heuristicMultiplier, terrainMinWeight);
                if (!openset.containsKey(neighbour) && !closedset.containsKey(neighbour))
                    openset.Insert(tentative_fscore, neighbour);      
                
                // We can safely try to decrease the key, if the value is higher or doesnt exist, nothing will happen        
                openset.DecreaseKey(tentative_fscore, neighbour);  
            }
        }
        
        // If we get here, no path was found... return stuff anyway to show pretty graphs
        return new PathInfo(null, closedset, null);
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
     * Gets the neighbour cells for specified coordinates. Obviously does not include out of bounds cells
     * @param neighbour
     * @return 
     */
    private Coordinates[] GetNeighbours(Coordinates c)
    {      
        int topleft_x = c.x <= 0 ? 0 : c.x - 1;
        int topleft_y = c.y <= 0 ? 0 : c.y - 1;
        
        int bottomright_x = c.x >= this.getWidth()- 1 ? this.getWidth() - 1 : c.x + 1;
        int bottomright_y = c.y >= this.getHeight() - 1 ? this.getHeight() - 1 : c.y + 1;
        
        Coordinates[] coordinates = new Coordinates[8]; // Cant be more than 8 neighbours aye?
        int n = 0;
        for (int i = topleft_x; i <= bottomright_x; i++) 
        {
            for (int j = topleft_y; j <= bottomright_y; j++) 
            {
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
    
    
    private float CalculateWeight(Coordinates from, Coordinates to)
    {
        float fromweight = getCellValue(from).weight;
        float toweight = getCellValue(to).weight;
        
        if (toweight == -1) // Wall.. again
            return toweight;
        
        if (from.x != to.x && from.y != to.y)
        {
            toweight = (float)Math.sqrt(2 * Math.pow(toweight, 2));
            fromweight = (float)Math.sqrt(2 * Math.pow(fromweight, 2));
        }
        return toweight / 2 + fromweight / 2;
    }
    
    
    private float GetHDistance(Coordinates from, Coordinates to, int multiplier, int terrainMinWeight)
    {
        int x = (from.x - to.x) * terrainMinWeight;
        int y = (from.y - to.y) * terrainMinWeight;
        return (float)Math.sqrt(x * x + y * y) * multiplier;   // Euclidean distance
    }
}