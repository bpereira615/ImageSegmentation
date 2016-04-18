/** Implementation of an edge class (for graphs), could be directed or not.
 */
public class WEdge {

    /** Starting vertex of an edge. */
    private GVertex source;
    /** Ending vertex of an edge. */
    private GVertex end;
    /** Weight of edge */
    private double weight;

    /** Create an undirected edge.
     *  @param u the start
     *  @param v the end
     */
    public WEdge(GVertex u, GVertex v, double w) {
        this.source = u;
        this.end = v;
        this.weight = w;
    }


    /** Is a vertex incident to this edge.
     *  @param v the vertex
     *  @return true if source or end, false otherwise
     */
    public boolean isIncident(GVertex v) {
        return this.source.equals(v) || this.end.equals(v);
    }

    /** Get the starting endpoint vertex.
     *  @return the vertex
     */
    public GVertex source() {
        return this.source;
    }

    /** Get the ending endpoint vertex.
     *  @return the vertex
     */
    public GVertex end() {
        return this.end;
    }

    /** Create a string representation of the edge.
     *  @return the string as (source,end)
     */
    public String toString() {
        return "(" + this.source + "," + this.end + ")";
    }

    /** Check if two edges are the same.
     *  @param other the edge to compare to this
     *  @return true if directedness and endpoints match, false otherwise
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

    /** Make a hashCode based on the toString.
     *  @return the hashCode
     */
    public int hashCode() {
        return this.toString().hashCode();
    }

}
