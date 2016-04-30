/********************************************************************
 * Lydia Carroll, Benjamin Hoertnagl-Pereira, Ryan Walter
 * JHED: lcarro12, bhoertn1, rwalte25
 * lcarro12 @jhu.edu, bhoertn1@jhu.edu, rwalte25@jhu.edu
 *
 * 600.226.01 | CS226 Data Structures
 * Project 4 - Image Segmentation
 *******************************************************************/

/**
 * Implements a weighted edge class for graph.
 * @param VT data type for vertices.
 */
public class WEdge<VT> implements Comparable<WEdge<VT>> {

    /** Starting vertex of an edge. */
    private GVertex<VT> source;
    /** Ending vertex of an edge. */
    private GVertex<VT> end;
    /** Weight of edge */
    private double weight;

    /** 
     * Create an undirected edge.
     * @param u the start
     * @param v the end
     */
    public WEdge(GVertex<VT> u, GVertex<VT> v, double w) {
        this.source = u;
        this.end = v;
        this.weight = w;
    }

    /** 
     * Is a vertex incident to this edge.
     * @param v the vertex
     * @return true if source or end, false otherwise
     */
    public boolean isIncident(GVertex<VT> v) {
        return this.source.equals(v) || this.end.equals(v);
    }

    /** 
     * Get the starting endpoint vertex.
     * @return the vertex
     */
    public GVertex<VT> source() {
        return this.source;
    }

    /** 
     * Get the ending endpoint vertex.
     * @return the vertex
     */
    public GVertex<VT> end() {
        return this.end;
    }

    /** 
     * Get the weight of the edge.
     * @return the weight
     */
    public double weight() {
        return this.weight;
    }

    /** 
     * Create a string representation of the edge.
     * @return the string as (source,end)
     */
    public String toString() {
        return "(" + this.source + "," + this.end + "," + this.weight + ")";
    }

    /** 
     * Check if two edges are the same.
     * @param other the edge to compare to this
     * @return true if directedness and endpoints match, false otherwise
     */
    @Override   
    public boolean equals(Object other) {
        if (other instanceof WEdge) {
            WEdge e = (WEdge) other;

            //check weights first, avoid unnecessary operations
            if (this.weight != e.weight) {
                return false;
            }

            //end and source do not need to be in given order
            if (!this.end.equals(e.end)) {

                return this.source.equals(e.end)
                    && this.end.equals(e.source);
            } else {
                return this.source.equals(e.source)
                    && this.end.equals(e.end);
            }
        }
        return false;
    }


    /** 
     * Compares two edges based on weights.
     * @return the hashCode
     */
    @Override
    public int compareTo(WEdge<VT> other) {
        // if (other instanceof WEdge) {
        WEdge<VT> e = other; //(WEdge)
        if (this.weight < e.weight) {
            return -1;
        } else if (this.weight > e.weight) {
            return 1;
        } else {
            return 0;
            }
    }



    /** 
     * Make a hashCode based on the toString.
     *  @return the hashCode
     */
    public int hashCode() {
        return this.toString().hashCode();
    }

}
