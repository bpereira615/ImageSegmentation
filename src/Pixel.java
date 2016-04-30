/********************************************************************
 * Lydia Carroll, Benjamin Hoertnagl-Pereira, Ryan Walter
 * JHED: lcarro12, bhoertn1, rwalte25
 * lcarro12 @jhu.edu, bhoertn1@jhu.edu, rwalte25@jhu.edu
 *
 * 600.226.01 | CS226 Data Structures
 * Project 4 - Image Segmentation
 *******************************************************************/

/** 
 * Stores color, row, and column of a pixel.
 *
 * @param row number of row in image
 * @param col number of column in image
 * @param val color value
 */





public class Pixel {

    /** Row index of pixel. */
    private int row;

    /** Column index of pixel. */
    private int col;

    /** Color value of the pixel. */
    private int val;

    /** Red value. */
    private int red;

    /** Green value. */
    private int green;

    /** Blue value. */
    private int blue;

    /** Constructor for Pixel class. */
    public Pixel(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
        this.blue = val & 0xFF;
        this.green = (val >> 8) & 0xFF;
        this.red = (val >> 16) & 0xFF;
    }

    /** Returns the row number. */
    public int row() {
        return this.row;
    }

    /** Returns the column number. */
    public int col() {
        return this.col;
    }

    /** Returns the color value. */
    public int value() {
        return this.val;
    }

    /** Returns the red value [0, 255]. */
    public int r() {
        return this.red;
    }

    /** Returns the green value [0, 255]. */
    public int g() {
        return this.green;
    }

    /** Returns the blue value [0, 255]. */
    public int b() {
        return this.blue;
    }

}