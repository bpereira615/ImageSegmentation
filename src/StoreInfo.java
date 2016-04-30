/********************************************************************
 * Lydia Carroll, Benjamin Hoertnagl-Pereira, Ryan Walter
 * JHED: lcarro12, bhoertn1, rwalte25
 * lcarro12 @jhu.edu, bhoertn1@jhu.edu, rwalte25@jhu.edu
 *
 * 600.226.01 | CS226 Data Structures
 * Project 4 - Image Segmentation
 *******************************************************************/

/** Hold min, max RBG values and number of vertices in partition. */
public class StoreInfo {

    /** Preallocate mins to 1000 so they're overwritten. */
    static final int THOU = 1000;

    /** Largest red value in partition. */
    private int maxR;
    /** Largest green value in partition. */
    private int maxG;
    /** Largest blue value in partition. */
    private int maxB;
    /** Smallest red value in partition. */
    private int minR;
    /** Smallest green value in partition. */
    private int minG;
    /** Smallest blue value in partition. */
    private int minB;

    /** Number of vertices in partition. */
    private int numVerts;

    /** Create an empty StoreInfo object. */
    public StoreInfo() {
        this.maxR = -1;
        this.minR = THOU;

        this.maxG = -1;
        this.minG = THOU;

        this.maxB = -1;
        this.minB = THOU;

        this.numVerts = 0;
    }

    /**
     * Set the maximum red value.
     * @param r red value [0,255]
     */
    public void setMaxR(int r) {
        this.maxR = r;
    }

    /**
     * Set the minimum red value. 
     * @param r red value 0-255
     */
    public void setMinR(int r) {
        this.minR = r;
    }

    /**
     * Set the maximum green value.
     * @param g green value 0-255
     */
    public void setMaxG(int g) {
        this.maxG = g;
    }

    /**
     * Set the minimum green value.
     * @param g green value 0-255
     */
    public void setMinG(int g) {
        this.minG = g;
    }

    /** 
     * Set the maximum blue value.
     * @param b blue value 0-255
     */
    public void setMaxB(int b) {
        this.maxB = b;
    }

    /** 
     * Set the minimum blue value. 
     * @param b blue value 0-255
     */
    public void setMinB(int b) {
        this.minB = b;
    }

    /** 
     * Get the maximum red value.
     * @return red max
     */
    public int getMaxR() {
        return this.maxR;
    }

    /** 
     * Get the minimum red value. 
     * @return red minimum
     */
    public int getMinR() {
        return this.minR;
    }

    /** 
     * Get the maximum green value. 
     * @return green maximum
     */
    public int getMaxG() {
        return this.maxG;
    }

    /**
     * Get the minimum green value.
     * @return green minimum
     */
    public int getMinG() {
        return this.minG;
    }

    /** 
     * Get the maximum blue value.
     * @return blue maximum
     */
    public int getMaxB() {
        return this.maxB;
    }

    /** 
     * Get the minimum blue value.
     * @return blue minimum
     */
    public int getMinB() {
        return this.minB;
    }

    /** Increment the number of vertices in partition. */
    public void addVert() {
        this.numVerts++;
    }

    /** 
     * Get the number of vertices in partition.
     * @return number of vertices
     */
    public int getVerts() {
        return this.numVerts;
    }
}