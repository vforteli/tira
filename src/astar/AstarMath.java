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
    public static float convertRange(float inputmin, float inputmax, float outputmin, float outputmax, float value)
    {
        float out = outputmin + (Float.valueOf(value) - inputmin) * (outputmax - outputmin) / (inputmax - inputmin);
        return out > outputmax ? outputmax : out;
    }
}
