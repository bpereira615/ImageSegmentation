/********************************************************************
 * Lydia Carroll, Benjamin Hoertnagl-Pereira, Ryan Walter
 * JHED: lcarro12, bhoertn1, rwalte25
 * lcarro12 @jhu.edu, bhoertn1@jhu.edu, rwalte25@jhu.edu
 *
 * 600.226.01 | CS226 Data Structures
 * Project 4 - Image Segmentation
 *******************************************************************/

/**
 * Implementation of the Distance interface.
 * @param one first pixel
 * @param two second pixel
 * @return color difference between pixels
 */
public class PixelDistance implements Distance<Pixel> {

    /** Take the square root of the squares. */
    static final double PT5 = 0.5;

    /** Empty constructor for PixelDistance. */
    public PixelDistance() {

    }

    /** Finds the color distance between two pixels. */
    public double distance (Pixel one, Pixel two) {
        double square = Math.pow(one.r() - two.r(), 2) 
            + Math.pow(one.g() - two.g(), 2) 
            + Math.pow(one.b() - two.b(), 2);
        return Math.pow(square, PT5);
    }
}