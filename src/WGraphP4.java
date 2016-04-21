import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class WGraphP4<VT> implements WGraph<VT> {

	/** The number of edges in graph */
	private int numEdges;

	/** The number of vertices in graph */
	private int numVerts;

	/** The ID of the next vertex */
	private int nextID;

	/** The list of vertices */
	private ArrayList<GVertex<VT>> vertices;

	/** The list of edges for each vertex */
	private LinkedList<ArrayList<WEdge<VT>>> edges;


	/** Constructor for weighted graph implementation */
	public WGraphP4() {
		this.numEdges = 0;
		this.numVerts = 0;
		this.nextID = 0;
		this.vertices = new ArrayList<>();
		this.edges = new LinkedList<>();
	}

	/** Get the number of edges. 
     *  @return the number
     */
    public int numEdges() {
    	return numEdges;
    }

    /** Get the number of vertices. 
     *  @return the number
     */
    public int numVerts() {
    	return numVerts;
    }

    /** Get the next ID to use in making a vertex. 
     *  @return the id
     */
    public int nextID() {
    	return nextID;
    }

    /** Create and add a vertex to the graph.
     *  @param d the data to store in the vertex
     *  @return true if successful, false otherwise
     */
    public boolean addVertex(VT d) {
    	//TODO: when would this fail?
    	try {
    		vertices.add(new GVertex<VT>(d, nextID()));
    		this.numVerts++;
    		this.nextID++;
    		return true;
    	} catch (Exception e) {
    		return false;
    	}
    }

    /** Add a vertex if it doesn't exist yet. 
     *  @param v the vertex to add
     *  @return false if already there, true if added
     */
    public boolean addVertex(GVertex<VT> v) {
    	//TODO: what happens if v has same ID as already present vertex?

    	//check if the vertex is there
    	if (vertices.get(v.id()) == null) {
    		return false;
    	}

    	return true;
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
    	//TODO: w.isIncident(u) giving warning

    	//search the list of edges of vertex with lesser degree

    	//if either vertex not present, do not check
    	if (vertices.get(v.id()) == null || vertices.get(u.id()) == null) {
    		return false;
    	}

    	//v has lesser degree
    	if (degree(v) <= degree(u)) {
    		for (WEdge w : edges.get(v.id())) {
    			if (w.isIncident(u)) {
    				//incident edge found
    				return true;
    			}
    		}
    		//incident edge not found
    		return false;
    	} else {
    		for (WEdge w : edges.get(u.id())) {
    			if (w.isIncident(v)) {
    				//incident edge found
    				return true;
    			}
    		}
    		//incident edge not found
    		return false;
    	}
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
    	//degree is defined as number of edges on given vertex
    	int verId = v.id();
    	return edges.get(verId).size();
    }

    /** See if an edge and vertex are incident to each other.
     *  @param e the edge
     *  @param v the vertex to check
     *  @return true if v is an endpoint of edge e
     */
    public boolean areIncident(WEdge<VT> e, GVertex<VT> v) {
    	return false;
    }

    /** Return a list of all the edges.  
     *  @return the list
     */
    public List<WEdge<VT>> allEdges() {
    	return null;
    }

    /** Return a list of all the vertices.  
     *  @return the list
     */
    public List<GVertex<VT>> allVertices() {
    	return vertices;
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
    public List<WEdge<VT>> incidentEdges(GVertex<VT> v) {
    	return null;
    }

    /** Return a list of edges in a minimum spanning forest by
     *  implementing Kruskal's algorithm using fast union/finds.
     *  @return a list of the edges in the minimum spanning forest
     */
    public List<WEdge<VT>> kruskals() {
    	return null;
    }
}