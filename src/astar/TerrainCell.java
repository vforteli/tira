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
    public TerrainCell(Integer weight, TerrainTypes type)
    {
        this.weight = weight;
        this.terrainType = type;
    }
    
    public Integer weight;
    public TerrainTypes terrainType;
}