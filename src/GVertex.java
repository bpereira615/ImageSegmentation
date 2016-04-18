/** 
 * Class to represent a vertex (in a graph).
 * Modified from Vertex.java to implement generics.
 *
 */

public class GVertex<D> implements Comparable<GVertex<D>> {

    /* Note that the nextID variable had to be moved to the graph class. */

    /** Vertex unique ID number. */
    private int num;

    /** Data stored in the vertex. */
    private D data;

    /** Create a new vertex.
     *  @param d the data to store in the node
     *  @param id the unique id of the node
     */
    public GVertex(D d, int id) {
        this.data = d;
        this.num = id;
    }

    /** Get the id of this vertex.
     *  @return the id
     */
    public int id() {
        return this.num;
    }

    /** Get a string representation of the vertex.
     *  @return the string 
     */
    public String toString() {
        return this.num + "";
    }

    /** Check if two vertices are the same based on ID.
     *  @param other the vertex to compare to this
     *  @return true if the same, false otherwise
     */
    public boolean equals(Object other) {
        if (other instanceof GVertex) {
            GVertex v = (GVertex) other;
            return this.num == v.id();  // want these to be unique
        }
        return false;
    }

    /** Get the hashcode of a vertex based on its ID.
     *  @return the hashcode
     */
    public int hashCode() {
        return (new Integer(this.num)).hashCode();
    }

    /** Compare two vertices based on their IDs.
     *  @param other the vertex to compare to this
     *  @return negative if this < other, 0 if equal, positive if this > other
     */
    public int compareTo(GVertex<D> other) {
        return this.num - other.id();
    }
}
