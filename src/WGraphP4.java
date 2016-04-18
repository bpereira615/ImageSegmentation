import java.util.List;

public class WGraphP4<VT> implements WGraph<VT> {

	/** Get the number of edges. 
     *  @return the number
     */
    public int numEdges() {
    	return -1;
    }

    /** Get the number of vertices. 
     *  @return the number
     */
    public int numVerts() {
    	return -1;
    }

    /** Get the next ID to use in making a vertex. 
     *  @return the id
     */
    public int nextID() {
    	return -1;
    }

    /** Create and add a vertex to the graph.
     *  @param d the data to store in the vertex
     *  @return true if successful, false otherwise
     */
    public boolean addVertex(VT d) {
    	return false;
    }

    /** Add a vertex if it doesn't exist yet. 
     *  @param v the vertex to add
     *  @return false if already there, true if added
     */
    public boolean addVertex(GVertex<VT> v) {
    	return false;
    }

    /** Add a weighted edge, may also add the incident vertices. 
     *  @param e the edge to add
     *  @return false if already there, true if added
     */
    public boolean addEdge(WEdge e) {
    	return false;
    }

    /** Add a weighted edge, may also add vertices. 
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @param weight the weight of the edge
     *  @return false if already there, true if added
     */
    public boolean addEdge(GVertex<VT> v, GVertex<VT> u, double weight) {
    	return false;
    }

    /** Remove an edge if there.  
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @return true if delete, false if wasn't there
     */
    public boolean deleteEdge(GVertex<VT> v, GVertex<VT> u) {
    	return false;
    }

    /** Return true if there is an edge between v and u. 
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @return true if there is an edge between them, false otherwise
     */
    public boolean areAdjacent(GVertex<VT> v, GVertex<VT> u) {
    	return false;
    }

    /** Return a list of all the neighbors of vertex v.  
     *  @param v the vertex source
     *  @return the neighboring vertices
     */
    public List<GVertex<VT>> neighbors(GVertex<VT> v) {
    	return null;
    }

    /** Return the number of edges incident to v.  
     *  @param v the vertex source
     *  @return the number of incident edges
     */
    public int degree(GVertex<VT> v) {
    	return -1;
    }

    /** See if an edge and vertex are incident to each other.
     *  @param e the edge
     *  @param v the vertex to check
     *  @return true if v is an endpoint of edge e
     */
    public boolean areIncident(WEdge e, GVertex<VT> v) {
    	return false;
    }

    /** Return a list of all the edges.  
     *  @return the list
     */
    public List<WEdge> allEdges() {
    	return null;
    }

    /** Return a list of all the vertices.  
     *  @return the list
     */
    public List<GVertex<VT>> allVertices() {
    	return null;
    }

    /** Return a list of all the vertices that can be reached from v,
     *  in the order in which they would be visited in a depth-first
     *  search starting at v.  
     *  @param v the starting vertex
     *  @return the list of reachable vertices
     */
    public List<GVertex<VT>> depthFirst(GVertex<VT> v) {
    	return null;
    }

    /** Return a list of all the edges incident on vertex v.  
     *  @param v the starting vertex
     *  @return the incident edges
     */
    public List<WEdge> incidentEdges(GVertex<VT> v) {
    	return null;
    }

    /** Return a list of edges in a minimum spanning forest by
     *  implementing Kruskal's algorithm using fast union/finds.
     *  @return a list of the edges in the minimum spanning forest
     */
    public List<WEdge> kruskals() {
    	return null;
    }
}