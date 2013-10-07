/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;


/**
 *
 * @author verne_000
 */
public class TerrainCell
{
    /**
     * Encapsulates useful stuff about a cell/node/whatever its called today
     * @param weight
     * @param type
     * @param hsbcolor
     */
    public TerrainCell(Integer weight, TerrainTypes type, float[] hsbcolor)
    {
        this.weight = weight;
        this.terrainType = type;
        this.hsbcolor = hsbcolor;
    }
    
    /**
     * The weight _across_ the cell (not diagonally)
     */
    public Integer weight;
    /**
     *
     */
    public TerrainTypes terrainType;
    /**
     * Color in hsb format from the original bitmap
     */
    public float[] hsbcolor;
}