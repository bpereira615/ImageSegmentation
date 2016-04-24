import java.util.LinkedList;
import java.util.HashSet;

/** 
 * Class to represent a vertex (in a graph).
 * Modified from Vertex.java to implement generics.
 *
 */

public class GVertex<VT> implements Comparable<GVertex<VT>> {

    /* Note that the nextID variable had to be moved to the graph class. */

    /** Vertex unique ID number. */
    private int num;

    /** Data stored in the vertex. */
    private VT data;

    /** List of edges incident to this vertex */
    private LinkedList<WEdge<VT>> edges;

    /** Set of vertices that are neighbors to this vertex */
    private HashSet<GVertex<VT>> neighbors;

    /** Flag for traversals */
    private boolean visited;

    /** Create a new vertex.
     *  @param d the data to store in the node
     *  @param id the unique id of the node
     */
    public GVertex (VT d, int id) {
        this.data = d;
        this.num = id;
        this.edges = new LinkedList<>();
        this.neighbors = new HashSet<>();
        this.visited = false;
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
        return "(" + this.data + " - ID:" + this.num + ")";
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
    public int compareTo(GVertex<VT> other) {
        return this.num - other.id();
    }

    /** Return the value of data stored.
     *  @return negative if this < other, 0 if equal, positive if this > other
     */
    public VT getData() {
        return this.data;
    }

    /** Add edge to list of incident edges for this vertex
     *  @param e the edge to be added
     */
    public boolean addEdge(WEdge<VT> e) {
        //TODO: when should this fail?
        edges.add(e);
        if (e.source().id() == this.num) {
            neighbors.add(e.end());
        } else {
            neighbors.add(e.source());
        }
        return true;
    }

    /** Add edge to list of incident edges for this vertex
     *  @param e the edge to be added
     */
    public boolean removeEdge(WEdge<VT> e) {
        //TODO: when should this fail?
        edges.remove(e);
        if (e.source().id() == this.num) {
            neighbors.remove(e.end());
        } else {
            neighbors.remove(e.source());
        }
        return true;
    }

    /** Return list of edges
     *  @return list of edges
     */
    public LinkedList<WEdge<VT>> getEdges() {
        return this.edges;
    }

    /** Return set of neighbors
     *  @return set of neighbors
     */
    public HashSet<GVertex<VT>> getNeighbors() {
        return this.neighbors;
    }


    /** Checks if vertex has been visited
     *  @return true if visited, false if not
     */
    public boolean isVisited() {
        return this.visited;
    }


    /** Mark vertex as visited
     */
    public void markVisited() {
        this.visited = true;
    }

    /** Set flag to not visited
     */
    public void clearVisited() {
        this.visited = false;
    }

}
