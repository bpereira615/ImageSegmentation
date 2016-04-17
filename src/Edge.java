/** Implementation of an edge class (for graphs), could be directed or not.
 */
public class Edge {

    /** Starting vertex of an edge. */
    private Vertex source;
    /** Ending vertex of an edge. */
    private Vertex end;
    /** Whether or not the edge is directed. */
    private boolean directed;

    /** Create an undirected edge.
     *  @param u the start
     *  @param v the end
     */
    public Edge(Vertex u, Vertex v) {
        this.source = u;
        this.end = v;
        this.directed = false;
    }

    /** Create an edge.
     *  @param u the start
     *  @param v the end
     *  @param dir true if directed, false otherwise
     */
    public Edge(Vertex u, Vertex v, boolean dir) {
        this.source = u;
        this.end = v;
        this.directed = dir;
    }

    /** Is the edge directed.
     *  @return true if yes, false otherwise
     */
    public boolean isDirected() {
        return this.directed;
    }

    /** Is a vertex incident to this edge.
     *  @param v the vertex
     *  @return true if source or end, false otherwise
     */
    public boolean isIncident(Vertex v) {
        return this.source.equals(v) || this.end.equals(v);
    }

    /** Get the starting endpoint vertex.
     *  @return the vertex
     */
    public Vertex source() {
        return this.source;
    }

    /** Get the ending endpoint vertex.
     *  @return the vertex
     */
    public Vertex end() {
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
    public boolean equals(Object other) {
        if (other instanceof Edge) {
            Edge e = (Edge) other;
            if (this.directed != e.directed) {
                return false;
            }
            if (this.directed) {
                return this.source.equals(e.source)
                    && this.end.equals(e.end);
            } else {
                return this.source.equals(e.source)
                    && this.end.equals(e.end)
                    || this.source.equals(e.end)
                    && this.end.equals(e.source);
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
