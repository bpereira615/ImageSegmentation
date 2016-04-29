/** Class to contain different function for pixels.
 * @param <T> type of the objects
 *
 */

public interface Distance<T> {

    /** Compute distance between two things.
     *  @param one the first thing
     *  @param two the second thing
     *  @return the distance, the square root or the sum
     *      of differences squared in each component.
     */
    double distance(T one, T two); 
}