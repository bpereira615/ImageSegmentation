import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class GraphAdjMatrix implements Graph {

    /** Used to sequentially generate vertex IDs for this graph! */
    private int nextID;

    /** the vertices */
    private ArrayList<Vertex> verts;
    private boolean[][] matrix;
    private int numEdges;

    public GraphAdjMatrix(int maxVerts) {
        this.nextID = 0;
        this.numEdges = 0;
        this.verts = new ArrayList<Vertex>(maxVerts);
        this.matrix = new boolean[maxVerts][maxVerts];
    }

    @Override
    public int numVerts() {
        return this.verts.size();
    }

    @Override
    public int numEdges() {
        return this.numEdges;
    }

    @Override
    public int nextID() {
        return nextID++;
    }

    @Override
    public boolean addVertex(Object data) {
        if (this.verts.size() == this.matrix.length) // full
            return false;
        this.verts.add(new Vertex(data, nextID++));
        return true;
    }

    @Override
    public boolean addVertex(Vertex v) {
        if (this.verts.size() == this.matrix.length) // full
            return false;
        if (this.verts.contains(v))
            return false;  // there 
        this.verts.add(v);
        return true;
    }
    
    @Override
    public boolean addEdge(Edge e) {
        boolean added = false;
        added = addEdge(e.source(), e.end());
        if (added && !e.isDirected()) {
            added = addEdge(e.end(), e.source());
            this.numEdges--;  // don't count it twice
        }
        return added;
    }

    @Override
    public boolean addEdge(Vertex v, Vertex u) {
        boolean success = true;
        if (!this.verts.contains(v))
            success = this.addVertex(v);
        if (success && !this.verts.contains(u))
            success = this.addVertex(u);
        if (!success)
            return false;
        // put the edge in, if not already there
        if (! this.matrix[v.id()][u.id()]) {
            this.matrix[v.id()][u.id()] = true;
            this.numEdges++;
            return true;
        }
        return false;  // was already there
    }

    @Override
    public boolean deleteEdge(Vertex v, Vertex u) {
        if (this.verts.contains(v) && this.verts.contains(u)) {
            if (this.matrix[v.id()][u.id()]) {
                this.matrix[v.id()][u.id()] = false;
                this.numEdges--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean areAdjacent(Vertex v, Vertex u) {
        return this.matrix[v.id()][u.id()];
    }

    @Override
    public ArrayList<Vertex> neighbors(Vertex v) {
        ArrayList<Vertex> nbs = new ArrayList<Vertex>(this.numVerts());
        int row = v.id();
        for (int col=0; col < matrix.length; col++) {
            if (this.matrix[row][col]) {
                // add vertex associated with col to nbs
                nbs.add(this.verts.get(col));
            }
        }
        return nbs;
    }

    @Override
    public int degree(Vertex v) {
        return this.neighbors(v).size();
    }

    @Override
    public boolean areIncident(Edge e, Vertex v) {
        return e.source().equals(v) || e.end().equals(v);
    }

    @Override
    public List<Edge> allEdges() {
        int nv = this.numVerts();
        ArrayList<Edge> edges = new ArrayList<Edge>(nv);
        for (int r = 0; r < nv; r++) {
            for (int c = 0; c < nv; c++) {
                if (this.matrix[r][c]) {
                    // there is an edge, add to list
                    edges.add(new Edge(this.verts.get(r), this.verts.get(c)));
                }
                // will create duplicate edges for an undirected graph
            }
        }
        return edges;
    }

    @Override
    public List<Vertex> allVertices() {
        return this.verts;
    }

    public List<Vertex> depthFirst(Vertex v) {
        ArrayList<Vertex> reaches = new ArrayList<Vertex>(this.numVerts());
        // using LinkedList<Vertex> as a Stack
        LinkedList<Vertex> stack = new LinkedList<Vertex>();
        boolean[] visited = new boolean[this.numVerts()];  // inits to false
        stack.addFirst(v);
        visited[v.id()] = true;
        while (! stack.isEmpty()) {
            v = stack.removeFirst();
            reaches.add(v);
            for (Vertex u: this.neighbors(v)) {
                if (! visited[u.id()]) {
                    visited[u.id()] = true;
                    stack.addFirst(u);
                }
            }
        }
        return reaches;
    }

    
}
