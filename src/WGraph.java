import java.util.List;

/** Interface for a weighted undirected graph.
 *  @param <VT> the type of data stored in each vertex
 */
public interface WGraph<VT> {

    /** Get the number of edges. 
     *  @return the number
     */
    int numEdges();

    /** Get the number of vertices. 
     *  @return the number
     */
    int numVerts();

    /** Get the next ID to use in making a vertex. 
     *  @return the id
     */
    int nextID();

    /** Create and add a vertex to the graph.
     *  @param d the data to store in the vertex
     *  @return true if successful, false otherwise
     */
    boolean addVertex(VT d);

    /** Add a vertex if it doesn't exist yet. 
     *  @param v the vertex to add
     *  @return false if already there, true if added
     */
    boolean addVertex(Vertex v);

    /** Add a weighted edge, may also add the incident vertices. 
     *  @param e the edge to add
     *  @return false if already there, true if added
     */
    boolean addEdge(WEdge<VT> e);

    /** Add a weighted edge, may also add vertices. 
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @param weight the weight of the edge
     *  @return false if already there, true if added
     */
    boolean addEdge(GVertex<VT> v, GVertex<VT> u, double weight);

    /** Remove an edge if there.  
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @return true if delete, false if wasn't there
     */
    boolean deleteEdge(GVertex<VT> v, GVertex<VT> u);

    /** Return true if there is an edge between v and u. 
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @return true if there is an edge between them, false otherwise
     */
    boolean areAdjacent(GVertex<VT> v, GVertex<VT> u);

    /** Return a list of all the neighbors of vertex v.  
     *  @param v the vertex source
     *  @return the neighboring vertices
     */
    List<GVertex<VT>> neighbors(GVertex<VT> v);

    /** Return the number of edges incident to v.  
     *  @param v the vertex source
     *  @return the number of incident edges
     */
    int degree(GVertex<VT> v);

    /** See if an edge and vertex are incident to each other.
     *  @param e the edge
     *  @param v the vertex to check
     *  @return true if v is an endpoint of edge e
     */
    boolean areIncident(WEdge<VT> e, GVertex<VT> v);

    /** Return a list of all the edges.  
     *  @return the list
     */
    List<WEdge<VT>> allEdges();

    /** Return a list of all the vertices.  
     *  @return the list
     */
    List<GVertex<VT>> allVertices();

    /** Return a list of all the vertices that can be reached from v,
     *  in the order in which they would be visited in a depth-first
     *  search starting at v.  
     *  @param v the starting vertex
     *  @return the list of reachable vertices
     */
    List<GVertex<VT>> depthFirst(GVertex<VT> v);

    /** Return a list of all the edges incident on vertex v.  
     *  @param v the starting vertex
     *  @return the incident edges
     */
    List<WEdge<VT>> incidentEdges(GVertex<VT> v);

    /** Return a list of edges in a minimum spanning forest by
     *  implementing Kruskal's algorithm using fast union/finds.
     *  @return a list of the edges in the minimum spanning forest
     */
    List<WEdge<VT>> kruskals();
    
}
