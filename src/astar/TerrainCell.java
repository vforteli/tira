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
    public TerrainCell(Integer weight, TerrainTypes type, float[] hsbcolor)
    {
        this.weight = weight;
        this.terrainType = type;
        this.hsbcolor = hsbcolor;
    }
    
    public Integer weight;
    public TerrainTypes terrainType;
    public float[] hsbcolor;
}