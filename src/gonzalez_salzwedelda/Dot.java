/*
 * Course: CS2852 - 021
 * Spring 2019
 * Lab 1 - Dot2Dot
 * Name: David Gonzalez-Salzwedel
 * Created: 03/05/2019
 */
package gonzalez_salzwedelda;

/**
 * This class creates a Dot object.  Each object stores an x and a y value
 * corresponding to the coordinates of the dot.
 */
public class Dot {
    protected double x;
    protected double y;

    /**
     * This is the constructor for the Dot object.
     * @param x the x-coordinate of the dot
     * @param y the y-coordinate of the dot
     */
    public Dot(double x, double y){
        this.x = x;
        this.y = y;
    }
}
