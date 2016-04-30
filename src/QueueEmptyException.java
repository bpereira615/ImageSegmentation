/********************************************************************
 * Lydia Carroll, Benjamin Hoertnagl-Pereira, Ryan Walter
 * JHED: lcarro12, bhoertn1, rwalte25
 * lcarro12 @jhu.edu, bhoertn1@jhu.edu, rwalte25@jhu.edu
 *
 * 600.226.01 | CS226 Data Structures
 * Project 4 - Image Segmentation
 *******************************************************************/

/** Exception class for empty Queues.
 */
public class QueueEmptyException extends RuntimeException {

    /** Create a default exception object.
     */
    public QueueEmptyException() {
        super("ERROR: queue is empty, invalid operation");
    }

    /** Create a specific exception object.
     *  @param err the error message
     */
    public QueueEmptyException(String err) {
        super(err);
    }
}
