import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.HashSet;

public class WGraphP4<VT> implements WGraph<VT> {

	/** The number of unique edges in graph */
	private int numEdges;

	/** The number of vertices in graph */
	private int numVerts;

	/** The ID of the next vertex */
	private int nextID;

	/** The list of vertices */
	private HashSet<GVertex<VT>> vertices;

	/** The list of unique edges */
	private ArrayList<WEdge<VT>> edges;


	/** Constructor for weighted graph implementation */
	public WGraphP4() {
		this.numEdges = 0;
		this.numVerts = 0;
		this.nextID = 0;
		this.vertices = new HashSet<>();
		this.edges = new ArrayList<>();
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
    	return nextID++;
    }


    /** Check vertices for given data.
     *  @param d data to be searched for
     *  @return true if data contained, false if not
     */
    public boolean hasData(VT d) {
    	for (GVertex<VT> v : vertices) {
    		if (v.getData().equals(d)) {
    			return true;
    		}
    	}
    	return false;
    }

    /** Create and add a vertex to the graph.
     *  @param d the data to store in the vertex
     *  @return true if successful, false otherwise
     */
    public boolean addVertex(VT d) {

    	//TODO: what sorts of tests should this have?

    	GVertex<VT> add = new GVertex<VT>(d, nextID());
    	//check if the vertex is there
    	if (this.vertices.contains(add)) {
    		return false;
    	}

		vertices.add(add);
		this.numVerts++;
		return true;

    }

    /** Add a vertex if it doesn't exist yet. 
     *  @param v the vertex to add
     *  @return false if already there, true if added
     */
    public boolean addVertex(GVertex<VT> v) {
    	//TODO: what happens if v has same ID as already present vertex?

    	//check if the vertex is there
    	if (this.vertices.contains(v)) {
    		return false;
    	}
    	this.vertices.add(v);
    	this.numVerts++;
    	return true;
    }




    //TODO: no deleteVertex method?







    /** Add a weighted edge, may also add the incident vertices. 
     *  @param e the edge to add
     *  @return false if already there, true if added
     */
    public boolean addEdge(WEdge e) {
    	boolean added = false;
        added = addEdge(e.source(), e.end(), e.weight());
        return added;
    }

    /** Add a weighted edge, may also add vertices. 
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @param weight the weight of the edge
     *  @return false if already there, true if added
     */
    public boolean addEdge(GVertex<VT> v, GVertex<VT> u, double weight) {
    	boolean success = true;
        if (!this.vertices.contains(v)) {
            success = this.addVertex(v);
        }
        if (success && !this.vertices.contains(u)) {
            success = this.addVertex(u);
        }
        if (!success) {
        	//when one of vertices cannot be added, edge cannot be added
            return false;
        }

        // put the edge in, if not already there, will be added to lists of
        // both vertices, so only need to check one

        WEdge<VT> add = new WEdge<VT>(v, u, weight);
        

        // check if edge is already in lists, look in shorter vertex list
        if (this.degree(v) <= this.degree(u)) {
        	if (v.getEdges().contains(add)) {
        		// edge already exists
        		return false;
        	}
        } else {
        	if (u.getEdges().contains(add)) {
        		//edge already exists
        		return false;
        	}
        }
        edges.add(add);
        v.addEdge(add);
        u.addEdge(add);
        this.numEdges++;
        return true;
    }

    /** Remove an edge if there.  
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @return true if delete, false if wasn't there
     */
    public boolean deleteEdge(GVertex<VT> v, GVertex<VT> u) {

    	//if either vertex not present, do not look for edge
    	if (!vertices.contains(v) || !vertices.contains(u)) {
    		return false;
    	}

    	// edge will be in both lists, check list of smaller vertex degree
        if (this.degree(v) <= this.degree(u)) {

        	Iterator<WEdge<VT>> it = v.getEdges().iterator();
        	WEdge<VT> w;
 			while (it.hasNext()) {
 				w = it.next();
 				//TODO: is this correct/efficient?
 				if (w.isIncident(v) && w.isIncident(u)) {
 					it.remove();
 					return true;
 				}
 			}

        } else {

        }
        //edges was not there
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
    	if (!vertices.contains(v) || !vertices.contains(u)) {
    		return false;
    	}

    	//v has lesser degree
    	if (this.degree(v) <= this.degree(u)) {
    		for (WEdge<VT> w : v.getEdges()) {
    			if (w.isIncident(u)) {
    				//incident edge found
    				return true;
    			}
    		}
    		//incident edge not found
    		return false;
    	} else {
    		for (WEdge<VT> w : u.getEdges()) {
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
    	HashSet<GVertex<VT>> neighbors = new HashSet<>();

    	//add all vertices to set, then remove original vertex
    	for (WEdge<VT> edge : v.getEdges()) {
    		neighbors.add(edge.source());
    		neighbors.add(edge.end());
    	}

    	neighbors.remove(v);

    	LinkedList<GVertex<VT>> list = new LinkedList<>();
    	list.addAll(neighbors);
    	return list;
    }

    /** Return the number of edges incident to v.  
     *  @param v the vertex source
     *  @return the number of incident edges
     */
    public int degree(GVertex<VT> v) {
    	//degree is defined as number of edges on given vertex
    	return v.getEdges().size();
    }

    /** See if an edge and vertex are incident to each other.
     *  @param e the edge
     *  @param v the vertex to check
     *  @return true if v is an endpoint of edge e
     */
    public boolean areIncident(WEdge<VT> e, GVertex<VT> v) {
    	return e.isIncident(v);
    }

    /** Return a list of all the edges.  
     *  @return the list
     */
    public List<WEdge<VT>> allEdges() {
    	return edges;
    }

    /** Return a list of all the vertices.  
     *  @return the list
     */
    public List<GVertex<VT>> allVertices() {
    	LinkedList<GVertex<VT>> list = new LinkedList<>();
    	list.addAll(vertices);
    	return list;
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