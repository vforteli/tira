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
import java.io.IOException;
import java.util.AbstractMap;

/**
 *
 * @author vforteli
 */
public class Board 
{       
    private TerrainCell[][] board;
    
    /**
     * Gets the Terrain cell at the specified coordinates
     * @param c
     * @return TerrainCell
     */
    public TerrainCell getCellValue(Coordinates c)
    {
        return this.board[c.y][c.x];
    }
    
    /**
     * Height of the board in cells. Currently only supports square boards so width is redundant...
     * @return 
     */
    public final int getHeight()    
    {
        return this.board.length;
    }
    /**
     * Width of the board in cells.
     * @return
     */
    public final int getWidth()
    {
        // There cannot be width without height.
        // - Old Chinese saying
        return this.board[0].length;
    }

    
    private int terrainMaxValue;
    /**
     * Maximum terrain weight
     * @return
     */
    public int getTerrainMaxWeight()
    {
        return terrainMaxValue;
    }
    
    private int terrainMinWeight;
    /**
     * Minimum terrain weight
     * @return
     */
    public int getTerrainMinWeight()
    {
        return terrainMinWeight;
    }
    
   
    
    
    /**
     * Create a new board with specified size
     * 
     * @param Size Integer size of one side of the board
     * @param terrainMinWeight 
     * @param terrainMaxWeight 
     * @param bitmap
     * @throws IOException  
     */
    public Board(int terrainMinWeight, int terrainMaxWeight, BufferedImage image)
    {
        this.terrainMaxValue = terrainMaxWeight;
        this.terrainMinWeight = terrainMinWeight;
        
        if (image != null)
        {
            this.board = new TerrainCell[image.getHeight()][image.getWidth()];
            float inputmin = 0;
            float inputmax = 1;
            float outputmin = this.terrainMinWeight;
            float outputmax = this.terrainMaxValue;

            for (int y = 0; y < getHeight(); y++)
            {
                for (int x = 0; x < getWidth(); x++)
                {
                    float[] hsb = new float[3];
                    int pixel = image.getRGB(x, y);
                    Color.RGBtoHSB((pixel>>16)&0xff, (pixel>>8)&0xff, pixel&0xff, hsb);
                    float brightness = hsb[2];
                    float hue = hsb[0];

                    TerrainTypes type = getTerrainTypeForColor(hue, brightness);
                    if (type ==  TerrainTypes.Impassible) // Pretty close to black...
                    {
                        board[y][x] = new TerrainCell(-1, type, hsb);    // -1 denotes a wall that cannot be traversed at all
                    }
                    else 
                    {
                        // Higher brightness means lower weight, therefore brightness has to be reversed
                        brightness = Math.abs(brightness - 1);
                        //brightness = AstarMath.convertRange(0f, 0.6f, 0f, 1f, brightness);  // Truncate the upper end. OTherwiser almost black is needed for maximum cell weight.. not pretty
                        board[y][x] = new TerrainCell(Math.round(AstarMath.convertRange(inputmin, inputmax, outputmin, outputmax, brightness)), type, hsb);
                    }
                }
            }
        }   
    }   
    
    
    /**
     * Find a path from start to end coordinates
     * @param start
     * @param end
     * @param heuristicMultiplier
     * @return PathInfo containing the path and path length if available, and the nodes visited
     */
    public PathInfo findPath(Coordinates start, Coordinates end, int heuristicMultiplier)
    {      
        int initialsize = 1000;
        HybridHeap<Float, Coordinates> openset = new HybridHeap<>();
        AbstractMap<Coordinates, Integer> closedset = new MapHache<>(initialsize);
        AbstractMap<Coordinates, Coordinates> camefrom = new MapHache<>(initialsize);
        AbstractMap<Coordinates, Float> g_score = new MapHache<>(initialsize);
        
        // Distance from start is obviously 0... good place to start
        g_score.put(start, 0f);
        openset.insert(getHDistance(start, end, heuristicMultiplier, terrainMinWeight), start);

        while (!openset.isEmpty())
        {
            Coordinates current = openset.deleteMin();  // Get an open node with the lowest f_score, ie the one which looks best at the time
            closedset.put(current, 0);
            
            if (current.equals(end))    // Yaayy! A path was found, and if A* works it should be the shortest one :p
                return new PathInfo(reconstructPath(current, camefrom), closedset, g_score.get(end));
                                 
            for (Coordinates neighbour : getNeighbours(current))
            {                
                float weight = calculateWeight(current, neighbour);
                
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
                float tentative_fscore = tentative_gscore + getHDistance(neighbour, end, heuristicMultiplier, terrainMinWeight);
                if (!openset.containsKey(neighbour) && !closedset.containsKey(neighbour))
                    openset.insert(tentative_fscore, neighbour);      
                
                // We can safely try to decrease the key, if the value is higher or doesnt exist, nothing will happen        
                openset.decreaseKey(tentative_fscore, neighbour);  
            }
        }
        
        // If we get here, no path was found... return stuff anyway to show pretty graphs
        return new PathInfo(null, closedset, null);
    }
    
    
    private AbstractMap<Coordinates, Coordinates> reconstructPath(Coordinates coordinates, AbstractMap<Coordinates, Coordinates> camefrom)
    {
        MapHache<Coordinates, Coordinates> nodes = new MapHache<>(701);      
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
    private Coordinates[] getNeighbours(Coordinates c)
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
    
    /**
     * Calculates the weight from one cell to a neighbour. The weight is from the middle of the first cell to the middle of the second cell
     * Moving diagonally increases weight.
     * @param from
     * @param to
     * @return 
     */
    private float calculateWeight(Coordinates from, Coordinates to)
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
    
    
    /**
     * Heuristically calculate the total distance from one node to another.
     * Currently uses euclidean distance with fixed adjustable weight
     * @param from
     * @param to
     * @param multiplier Can be used to overestimate the distance, resulting in a possibly longer path, but fewer number of visited nodes
     * @param terrainMinWeight Should be set to the minimum distance between two cells.
     * @return 
     */
    private float getHDistance(Coordinates from, Coordinates to, int multiplier, int terrainMinWeight)
    {
        int x = (from.x - to.x) * terrainMinWeight;
        int y = (from.y - to.y) * terrainMinWeight;
        return (float)Math.sqrt(x * x + y * y) * multiplier;   // Euclidean distance
    }

    private TerrainTypes getTerrainTypeForColor(float hue, float brightness)
    {
        TerrainTypes type = TerrainTypes.Ground;
        
        if (hue > 0.50 && hue < 0.75)   // Blueish
            type = TerrainTypes.Water;

        else if (hue > 0.045 && hue < 0.13) // Brownish
            type = TerrainTypes.Road;

        else if (hue > 0.22 && hue < 0.39)  // Greenish
            type = TerrainTypes.Forest;

        else if (hue < 0.025 || hue > 0.95) // Redish
            type = TerrainTypes.Dragon;
        
        if (brightness > 0.95)  // Pretty close to white...
            type = TerrainTypes.Road;
        
        if (brightness < 0.05f) // Pretty close to black...
            type = TerrainTypes.Impassible;
                    
        return type;
    }
}