/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author verne_000
 */
public class AstarMath
{
    /**
     *
     * @param inputmin
     * @param inputmax
     * @param outputmin
     * @param outputmax
     * @param value
     * @return
     */
    public static float ConvertRange(float inputmin, float inputmax, float outputmin, float outputmax, float value)
    {
        return outputmin + (Float.valueOf(value) - inputmin) * (outputmax - outputmin) / (inputmax - inputmin);
    }
}
