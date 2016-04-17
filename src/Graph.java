import java.util.List;

public interface Graph {

    /** Get the number of edges. */
    int numEdges();

    /** Get the number of vertices. */
    int numVerts();

    /** Get the next ID to use in making a vertex. */
    int nextID();

    /** Create and add a vertex to the graph.
     *  @param d the data to store in the vertex
     *  @return true if successful, false otherwise
     */
    boolean addVertex(Object d);

    /** Add a vertex if it doesn't exist yet. */
    boolean addVertex(Vertex v);

    /** Add an edge, may also add the incident vertices. */
    boolean addEdge(Edge e);

    /** Add a (directed) edge, may also add the incident vertices. */
    boolean addEdge(Vertex v, Vertex u);

    /** Remove a (directed) edge if there.  */
    boolean deleteEdge(Vertex v, Vertex u);

    /** Return true if there is an edge between v and u. */
    boolean areAdjacent(Vertex v, Vertex u);

    /** Return a list of all the neighbors of vertex v.  */
    List<Vertex> neighbors(Vertex v);

    /** Return the number of edges incident to v.  */
    int degree(Vertex v);

    /** Return true if v is an endpoint of edge e.  */
    boolean areIncident(Edge e, Vertex v);

    /** Return a list of all the vertices that can be reached from v,
     * in the order in which they would be visited in a depth-first
     * search starting at v.  */
    List<Vertex> depthFirst(Vertex v);

    /** Return a list of all the edges.  */
    List<Edge> allEdges();

    /** Return a list of all the vertices.  */
    List<Vertex> allVertices();

}
