/********************************************************************
 * Lydia Carroll, Benjamin Hoertnagl-Pereira, Ryan Walter
 * JHED: lcarro12, bhoertn1, rwalte25
 * lcarro12 @jhu.edu, bhoertn1@jhu.edu, rwalte25@jhu.edu
 *
 * 600.226.01 | CS226 Data Structures
 * Project 4 - Image Segmentation
 *******************************************************************/


public class storeInfo {

    /** Starting vertex of an edge. */
    public int maxR;
    public int maxG;
    public int maxB;

    public int minR;
    public int minG;
    public int minB;

    private int numVerts;

    public PartitionData(){
        this.maxR = -1;
        this.minR = 1000;

        this.maxG = -1;
        this.minG = 1000;

        this.maxB = -1;
        this.minB = 1000;

        this.numVerts = 0;
    }

    public PartitionData(int upR, int downR, int upG, int downG, int upB, int downB) {
        this.maxR = upR;
        this.minR = downR;

        this.maxG = upG;
        this.minG = downG;

        this.maxB = upB;
        this.minB = downB;

        this.numVerts = 1;
    }

    public void addVert() {
        this.numVerts++;
    }

    public int getVerts() {
        return this.numVerts;
    }
}