/********************************************************************
 * Lydia Carroll, Benjamin Hoertnagl-Pereira, Ryan Walter
 * JHED: lcarro12, bhoertn1, rwalte25
 * lcarro12 @jhu.edu, bhoertn1@jhu.edu, rwalte25@jhu.edu
 *
 * 600.226.01 | CS226 Data Structures
 * Project 4 - Image Segmentation
 *******************************************************************/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/** JUnit tests for the MaxPriorityQueue interface.
 */
public class WGraphP4Test {
    /** Integer graph. */
    static WGraphP4<Integer> intGraph;

    /** Sring graph. */
    static WGraphP4<String> strGraph;

    /** Array of integers. */
    static Integer[] iray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //size = 11

    /** Array of strings. */
    static String[] sray = {"zro", "one", "two", "tre", "for",
        "fyv", "six", "svn", "ate", "nyn", "ten"};

    /** String Vertex 1. */
    static GVertex<String> s1;

    /** String Vertex 2. */
    static GVertex<String> s2;

    /** String Vertex 3. */
    static GVertex<String> s3;

    /** Int Vertex 1. */
    static GVertex<Integer> i1;

    /** Int Vertex 2. */
    static GVertex<Integer> i2;

    /** Int Vertex 1. */
    static GVertex<Integer> i3;

    /** List of edges, int. */
    static List<WEdge<Integer>> mstInt;

    /** LIst of edges, string. */
    static List<WEdge<String>> mstStr;

    /** Vertices of ints. */
    static ArrayList<GVertex<Integer>> intVerts;

    /** Vertices of strings. */
    static ArrayList<GVertex<String>> strVerts;


    /** To be executed before every test. */
    @Before
    public void setup() {
        //fresh graphes for each test
        intGraph = new WGraphP4<>();
        strGraph = new WGraphP4<>();
        // string vertices
        s1 = new GVertex<>("a", strGraph.nextID());
        s2 = new GVertex<>("b", strGraph.nextID());
        s3 = new GVertex<>("c", strGraph.nextID());



        mstInt = new ArrayList<WEdge<Integer>>();
        mstStr = new ArrayList<WEdge<String>>();
        // make arrays of vertices
        intVerts = new ArrayList<GVertex<Integer>>();
        strVerts = new ArrayList<GVertex<String>>();
        for (Integer i : iray) {
            GVertex<Integer> vInt = new GVertex<Integer>(i, i);
            GVertex<String> vStr = new GVertex<String>(sray[i], i);
            intVerts.add(vInt);
            strVerts.add(vStr);
        }
    }

//--------------------  numEdges() --------------------
    /** Test for empty graph. */
    @Test
    public void numEdgesEmpty() {
        assertEquals(0, intGraph.numEdges());
        assertEquals(0, strGraph.numEdges());
    }

    /** After adding edges. */
    @Test
    public void numEdgesAdd() {
        //check after adding edge
        GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
        double weight = (double) 1;

        WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
        WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

        intGraph.addEdge(wInt);
        strGraph.addEdge(wStr);

        assertEquals(1, intGraph.numEdges());
        assertEquals(1, strGraph.numEdges());

        //dont increment if duplicate edge added
        intGraph.addEdge(wInt);
        strGraph.addEdge(wStr);

        assertEquals(1, intGraph.numEdges());
        assertEquals(1, strGraph.numEdges());
    }

    /** Test decrememnt after removing edges. */
    @Test
    public void numEdgesAddRemove() {
        int edges = strGraph.numEdges();
        // add something to graph
        strGraph.addEdge(s1, s2, 0.8);
        edges++;
        // check that add was successful
        assertEquals(strGraph.numEdges(), edges); // # edges
        assertTrue(strGraph.hasVertex(s1)); // has s1
        assertTrue(strGraph.hasVertex(s2)); // has s2
        // now remove and check for decrement
        strGraph.deleteEdge(s1, s2);
        edges--;
        assertEquals(strGraph.numEdges(), edges);
    }

//--------------------  hasData() --------------------

    /** New graph test. */
    @Test
    public void hasVertexNew() {
        GVertex<Integer> gInt = new GVertex<>((Integer) 1, 1);
        GVertex<String> gStr = new GVertex<>("one", 1);
        assertFalse(intGraph.hasData((Integer) 1));
        assertFalse(strGraph.hasData("one"));
    }

    /** After adding the vertex. */
    @Test
    public void hasVertexAdd() {
        GVertex<Integer> gInt = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr = new GVertex<>("one", strGraph.nextID());
        intGraph.addVertex(gInt);
        strGraph.addVertex(gStr);
        assertTrue(intGraph.hasData((Integer) 1));
        assertTrue(strGraph.hasData("one"));

        intGraph.addVertex((Integer) 2);
        strGraph.addVertex("two");
        assertTrue(intGraph.hasData((Integer) 1));
        assertTrue(strGraph.hasData("one"));

        gInt = new GVertex<>((Integer) 2, 1);
        gStr = new GVertex<>("two", 1);
        assertTrue(intGraph.hasData((Integer) 2));
        assertTrue(strGraph.hasData("two"));

        assertFalse(intGraph.hasData((Integer) 3));
        assertFalse(strGraph.hasData("three"));
    }

//--------------------  numVerts() --------------------
    /** New graph test. */
    @Test
    public void numVertsEmpty() {
        assertEquals(0, intGraph.numVerts());
        assertEquals(0, strGraph.numVerts());
    }

    /** After adding vertices, incrementation. */
    @Test
    public void numVertsAdd() {
        //check after adding vertex
        intGraph.addVertex((Integer) 0);
        strGraph.addVertex("zro");
        assertEquals(1, intGraph.numVerts());
        assertEquals(1, strGraph.numVerts());

        GVertex<Integer> gInt = new GVertex<>((Integer) 1, 1);
        GVertex<String> gStr = new GVertex<>("one", 1);
        intGraph.addVertex(gInt);
        strGraph.addVertex(gStr);
        assertEquals(2, intGraph.numVerts());
        assertEquals(2, strGraph.numVerts());
    }

    /** No update for contained vertices. */
    @Test
    public void numVertsAddContained() {
        GVertex<Integer> gInt = new GVertex<>((Integer) 1, 1);
        GVertex<String> gStr = new GVertex<>("one", 1);
        intGraph.addVertex(gInt);
        strGraph.addVertex(gStr);
        assertEquals(1, intGraph.numVerts());
        assertEquals(1, strGraph.numVerts());

        intGraph.addVertex(gInt);
        strGraph.addVertex(gStr);
        assertEquals(1, intGraph.numVerts());
        assertEquals(1, strGraph.numVerts());
    }


//--------------------  nextID() --------------------
    /** Starting incrementation correctly for new graph. */
    @Test
    public void nextIDNew() {
        //fresh graphes for each test
        intGraph = new WGraphP4<>();
        strGraph = new WGraphP4<>();

        assertEquals(0, intGraph.nextID());
        assertEquals(0, strGraph.nextID());
    }

    /** Correct incrementation after adding. */
    @Test
    public void nextIDAdd() {
        //fresh graphes for each test
        intGraph = new WGraphP4<>();
        strGraph = new WGraphP4<>();
        //check ID after adding vertex
        GVertex<Integer> gInt = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr = new GVertex<>("one", strGraph.nextID());
        intGraph.addVertex(gInt);
        strGraph.addVertex(gStr);
        assertEquals(1, intGraph.nextID());
        assertEquals(1, strGraph.nextID());
    }

//--------------------  addVertex(VT d) --------------------
    /** Checkstyle. */
    @Test
    public void addVertexNew() {
        assertTrue(intGraph.addVertex((Integer) 0));
        assertTrue(strGraph.addVertex("zro"));
        assertTrue(intGraph.hasData((Integer) 0));
        assertTrue(strGraph.hasData("zro"));

    }

//--------------------  addVertex(GVertex<VT> v) --------------------\
    /** Checkstyle. */
    @Test
    public void addVertexObjectNew() {
        GVertex<Integer> gInt = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr = new GVertex<>("one", strGraph.nextID());
        assertTrue(intGraph.addVertex(gInt));
        assertTrue(strGraph.addVertex(gStr));
        assertTrue(intGraph.hasData((Integer) 1));
        assertTrue(strGraph.hasData("one"));

    }

    /** Checkstyle. */
    @Test
    public void addVertexObjectContained() {
        GVertex<Integer> gInt = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr = new GVertex<>("one", strGraph.nextID());

        assertTrue(intGraph.addVertex(gInt));
        assertTrue(strGraph.addVertex(gStr));

        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());

        assertTrue(intGraph.addVertex(gInt2));
        assertTrue(strGraph.addVertex(gStr2));

        //vertices with the same ID as other vertices 
        //already contained cannot be added
        assertFalse(intGraph.addVertex(gInt));
        assertFalse(strGraph.addVertex(gStr));
    }

//--------------------  addEdge(WEdge e) --------------------
    /** Checkstyle. */
    @Test
    public void addEdgeObjectFromVertexObject() {
        GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
        double weight = (double) 1;

        WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
        WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

        assertTrue(intGraph.addEdge(wInt));
        assertTrue(strGraph.addEdge(wStr));

        GVertex<Integer> gInt3 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr3 = new GVertex<>("three", strGraph.nextID());
        GVertex<Integer> gInt4 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr4 = new GVertex<>("three", strGraph.nextID());

        WEdge<Integer> wInt2 = new WEdge<>(gInt3, gInt4, weight);
        WEdge<String> wStr2 = new WEdge<>(gStr3, gStr4, weight);

        assertTrue(intGraph.addEdge(wInt2));
        assertTrue(strGraph.addEdge(wStr2));

        //edge already added, should return false
        assertFalse(intGraph.addEdge(wInt));
        assertFalse(strGraph.addEdge(wStr));
    }

//--------  addEdge(GVertex<VT> v, GVertex<VT> u, double weight) ---------
    /** Checkstyle. */
    public void addEdgeFromVertsDuplicate() {
        // should be able to add vertices
        double weight = 1.0;
        strGraph.addEdge(s1, s2, weight);
        // check that vertices are included
        assertTrue(strGraph.hasVertex(s1));
        assertTrue(strGraph.hasVertex(s2));
        // check that edge is connecting them
        assertTrue(strGraph.areAdjacent(s1, s2));
        // try to add edge again, should fail
        assertFalse(strGraph.addEdge(s1, s2, weight));
    }

//--------------------  deleteEdge() --------------------
    /** Checkstyle. */
    @Test
    public void removeEdgeContained() {
        GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
        double weight = (double) 1;

        WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
        WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

        intGraph.addEdge(wInt);
        strGraph.addEdge(wStr);

        assertTrue(intGraph.deleteEdge(gInt1, gInt2));
        assertTrue(strGraph.deleteEdge(gStr1, gStr2));

        //make sure that edges removed from master list
        assertFalse(intGraph.allEdges().contains(wInt));
        assertFalse(strGraph.allEdges().contains(wStr));

        //edge will have been deleted, should be false
        assertFalse(intGraph.deleteEdge(gInt1, gInt2));
        assertFalse(strGraph.deleteEdge(gStr1, gStr2));
    }

    /** Checkstyle. */
    @Test
    public void removeEdgeNotContained() {
        GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
        double weight = (double) 1;

        WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
        WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);


        //edge not inserted, return false
        assertFalse(intGraph.deleteEdge(gInt1, gInt2));
        assertFalse(strGraph.deleteEdge(gStr1, gStr2));

        intGraph.addEdge(wInt);
        strGraph.addEdge(wStr);

        assertTrue(intGraph.deleteEdge(gInt1, gInt2));
        assertTrue(strGraph.deleteEdge(gStr1, gStr2));
    }

//--------------------  areAdjacent() --------------------
    /** Checkstyle. */
    @Test
    public void areAdjacentNew() {
        GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
        double weight = (double) 1;

        WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
        WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);


        //edge not inserted, return false
        assertFalse(intGraph.areAdjacent(gInt1, gInt2));
        assertFalse(strGraph.areAdjacent(gStr1, gStr2));

        intGraph.addEdge(wInt);
        strGraph.addEdge(wStr);

        assertTrue(intGraph.areAdjacent(gInt1, gInt2));
        assertTrue(strGraph.areAdjacent(gStr1, gStr2));
    }

    /** Checkstyle. */
    @Test
    public void areAdjacentRemoved() {
        GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
        double weight = (double) 1;

        WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
        WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

        intGraph.addEdge(wInt);
        strGraph.addEdge(wStr);

        assertTrue(intGraph.areAdjacent(gInt1, gInt2));
        assertTrue(strGraph.areAdjacent(gStr1, gStr2));


        intGraph.deleteEdge(gInt1, gInt2);
        strGraph.deleteEdge(gStr1, gStr2);

        //edge not inserted, return false
        assertFalse(intGraph.areAdjacent(gInt1, gInt2));
        assertFalse(strGraph.areAdjacent(gStr1, gStr2));

    }

//--------------------  neighbors() --------------------
    /** Checkstyle. */
    @Test
    public void neighborsEmptyList() {
        strGraph.addVertex(s1);
        strGraph.addVertex(s2);
        LinkedList<GVertex<String>> nbs = new LinkedList<GVertex<String>>();
        assertEquals(nbs, strGraph.neighbors(s1));
        assertTrue(strGraph.hasVertex(s1));
        assertEquals(nbs, strGraph.neighbors(s2));
        assertTrue(strGraph.hasVertex(s2));
    }

    /** Checkstyle. */
    @Test
    public void neighborsAddEdge() {
        GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
        double weight = (double) 1;

        WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
        WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

        assertTrue(intGraph.addEdge(wInt));
        assertTrue(strGraph.addEdge(wStr));

        GVertex<Integer> gInt3 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr3 = new GVertex<>("three", strGraph.nextID());
        GVertex<Integer> gInt4 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr4 = new GVertex<>("three", strGraph.nextID());


        WEdge<Integer> wInt2 = new WEdge<>(gInt1, gInt3, weight);
        WEdge<String> wStr2 = new WEdge<>(gStr1, gStr3, weight);

        assertTrue(intGraph.addEdge(wInt2));
        assertTrue(strGraph.addEdge(wStr2));

        WEdge<Integer> wInt3 = new WEdge<>(gInt1, gInt4, weight);
        WEdge<String> wStr3 = new WEdge<>(gStr1, gStr4, weight);

        assertTrue(intGraph.addEdge(wInt3));
        assertTrue(strGraph.addEdge(wStr3));

        LinkedList<GVertex<Integer>> list = new LinkedList<>();
        list.add(gInt2);
        list.add(gInt3);
        list.add(gInt4);

        assertEquals(list, intGraph.neighbors(gInt1));
    }

    /** Checkstyle. */
    @Test
    public void neighborsRemoveEdge() {
        GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
        double weight = (double) 1;

        WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
        WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

        assertTrue(intGraph.addEdge(wInt));
        assertTrue(strGraph.addEdge(wStr));

        GVertex<Integer> gInt3 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr3 = new GVertex<>("three", strGraph.nextID());
        GVertex<Integer> gInt4 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr4 = new GVertex<>("three", strGraph.nextID());


        WEdge<Integer> wInt2 = new WEdge<>(gInt1, gInt3, weight);
        WEdge<String> wStr2 = new WEdge<>(gStr1, gStr3, weight);

        assertTrue(intGraph.addEdge(wInt2));
        assertTrue(strGraph.addEdge(wStr2));

        WEdge<Integer> wInt3 = new WEdge<>(gInt1, gInt4, weight);
        WEdge<String> wStr3 = new WEdge<>(gStr1, gStr4, weight);

        assertTrue(intGraph.addEdge(wInt3));
        assertTrue(strGraph.addEdge(wStr3));

        LinkedList<GVertex<Integer>> iList = new LinkedList<>();
        LinkedList<GVertex<String>> sList = new LinkedList<>();
        iList.add(gInt2);
        iList.add(gInt3);
        iList.add(gInt4);
        sList.add(gStr2);
        sList.add(gStr3);
        sList.add(gStr4);


        assertEquals(iList, intGraph.neighbors(gInt1));
        assertEquals(sList, strGraph.neighbors(gStr1));

        //delete ende and check neighbors
        assertTrue(intGraph.deleteEdge(gInt1, gInt4));
        assertTrue(strGraph.deleteEdge(gStr1, gStr4));

        iList.remove(gInt4);
        sList.remove(gStr4);

        assertEquals(iList, intGraph.neighbors(gInt1));
        assertEquals(sList, strGraph.neighbors(gStr1));


    }

//--------------------  degree() --------------------


    /** Checkstyle. */
    @Test
    public void degreeNew() {
        GVertex<Integer> gInt = new GVertex<>((Integer) 1, 1);
        GVertex<String> gStr = new GVertex<>("one", 1);

        intGraph.addVertex(gInt);
        strGraph.addVertex(gStr);

        assertEquals(0, intGraph.degree(gInt));
        assertEquals(0, strGraph.degree(gStr));
    }

    /** Checkstyle. */
    @Test
    public void degreeAdd() {
        GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
        double weight = (double) 1;

        WEdge<Integer> wInt1 = new WEdge<>(gInt1, gInt2, weight);
        WEdge<String> wStr1 = new WEdge<>(gStr1, gStr2, weight);

        int i = 0;

        assertEquals(i, intGraph.degree(gInt1));
        assertEquals(i, strGraph.degree(gStr1));


        assertTrue(intGraph.addEdge(wInt1));
        assertTrue(strGraph.addEdge(wStr1));
        i++;

        assertEquals(i, intGraph.degree(gInt1));
        assertEquals(i, strGraph.degree(gStr1));

        GVertex<Integer> gInt3 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr3 = new GVertex<>("three", strGraph.nextID());
        GVertex<Integer> gInt4 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr4 = new GVertex<>("three", strGraph.nextID());

        WEdge<Integer> wInt2 = new WEdge<>(gInt1, gInt3, weight);
        WEdge<String> wStr2 = new WEdge<>(gStr1, gStr3, weight);

        WEdge<Integer> wInt3 = new WEdge<>(gInt1, gInt4, weight);
        WEdge<String> wStr3 = new WEdge<>(gStr1, gStr4, weight);

        assertTrue(intGraph.addEdge(wInt2));
        assertTrue(strGraph.addEdge(wStr2));
        i++;

        assertTrue(intGraph.addEdge(wInt3));
        assertTrue(strGraph.addEdge(wStr3));
        i++;

        assertEquals(i, intGraph.degree(gInt1));
        assertEquals(i, strGraph.degree(gStr1));
    }


    /** Checkstyle. */
    @Test
    public void degreeRemove() {
        GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
        double weight = (double) 1;

        WEdge<Integer> wInt1 = new WEdge<>(gInt1, gInt2, weight);
        WEdge<String> wStr1 = new WEdge<>(gStr1, gStr2, weight);

        int i = 0;

        assertEquals(i, intGraph.degree(gInt1));
        assertEquals(i, strGraph.degree(gStr1));


        assertTrue(intGraph.addEdge(wInt1));
        assertTrue(strGraph.addEdge(wStr1));
        i++;

        assertEquals(i, intGraph.degree(gInt1));
        assertEquals(i, strGraph.degree(gStr1));

        GVertex<Integer> gInt3 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr3 = new GVertex<>("three", strGraph.nextID());
        GVertex<Integer> gInt4 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr4 = new GVertex<>("three", strGraph.nextID());

        WEdge<Integer> wInt2 = new WEdge<>(gInt1, gInt3, weight);
        WEdge<String> wStr2 = new WEdge<>(gStr1, gStr3, weight);

        WEdge<Integer> wInt3 = new WEdge<>(gInt1, gInt4, weight);
        WEdge<String> wStr3 = new WEdge<>(gStr1, gStr4, weight);

        assertTrue(intGraph.addEdge(wInt2));
        assertTrue(strGraph.addEdge(wStr2));
        i++;

        assertTrue(intGraph.addEdge(wInt3));
        assertTrue(strGraph.addEdge(wStr3));
        i++;

        assertEquals(i, intGraph.degree(gInt1));
        assertEquals(i, strGraph.degree(gStr1));

        // now remove stuff
        assertTrue(intGraph.deleteEdge(wInt3.source(), wInt3.end()));
        assertTrue(strGraph.deleteEdge(wStr3.source(), wStr3.end()));
        i--;
        // check that degree decremented
        assertEquals(i, intGraph.degree(gInt1));
        assertEquals(i, strGraph.degree(gStr1));


        assertEquals(intGraph.degree(gInt1), gInt1.getNeighbors().size());
        assertEquals(strGraph.degree(gStr1), gStr1.getNeighbors().size());
    }
//--------------------  areIncident() --------------------
// this is covered by the edge class

//--------------------  allEdges() --------------------
// not sure how we would even check this

//--------------------  allVertices() --------------------
// same problem


//--------------------  depthFirst() --------------------
    /** Checkstyle. */
    @Test
    public void depthFirst() {
        GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
        double weight = (double) 1;

        WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
        WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

        assertTrue(intGraph.addEdge(wInt));
        assertTrue(strGraph.addEdge(wStr));

        GVertex<Integer> gInt3 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr3 = new GVertex<>("three", strGraph.nextID());
        GVertex<Integer> gInt4 = new GVertex<>((Integer) 4, intGraph.nextID());
        GVertex<String> gStr4 = new GVertex<>("four", strGraph.nextID());


        WEdge<Integer> wInt2 = new WEdge<>(gInt1, gInt3, weight);
        WEdge<String> wStr2 = new WEdge<>(gStr1, gStr3, weight);

        assertTrue(intGraph.addEdge(wInt2));
        assertTrue(strGraph.addEdge(wStr2));

        WEdge<Integer> wInt3 = new WEdge<>(gInt1, gInt4, weight);
        WEdge<String> wStr3 = new WEdge<>(gStr1, gStr4, weight);

        assertTrue(intGraph.addEdge(wInt3));
        assertTrue(strGraph.addEdge(wStr3));

        GVertex<Integer> gInt5 = new GVertex<>((Integer) 5, intGraph.nextID());
        GVertex<String> gStr5 = new GVertex<>("six", strGraph.nextID());
        GVertex<Integer> gInt6 = new GVertex<>((Integer) 6, intGraph.nextID());
        GVertex<String> gStr6 = new GVertex<>("five", strGraph.nextID());

        WEdge<Integer> wInt4 = new WEdge<>(gInt2, gInt5, weight);
        WEdge<String> wStr4 = new WEdge<>(gStr2, gStr5, weight);


        WEdge<Integer> wInt5 = new WEdge<>(gInt3, gInt6, weight);
        WEdge<String> wStr5 = new WEdge<>(gStr3, gStr6, weight);

        //add cycle to graph
        WEdge<Integer> wInt6 = new WEdge<>(gInt1, gInt6, weight);
        WEdge<String> wStr6 = new WEdge<>(gStr1, gStr6, weight);

        intGraph.addEdge(wInt4);
        strGraph.addEdge(wStr4);
        intGraph.addEdge(wInt5);
        strGraph.addEdge(wStr5);

        //cycle added
        intGraph.addEdge(wInt6);
        strGraph.addEdge(wStr6);

        LinkedList<GVertex<Integer>> intList = new LinkedList<>();
        LinkedList<GVertex<String>> strList = new LinkedList<>();

        intList.add(gInt1);
        strList.add(gStr1);

        intList.add(gInt2);
        strList.add(gStr2);

        intList.add(gInt3);
        strList.add(gStr3);

        intList.add(gInt4);
        strList.add(gStr4);

        intList.add(gInt5);
        strList.add(gStr5);

        intList.add(gInt6);
        strList.add(gStr6);

        //all vertices in graph are present, so all should be in depth
        //first search result
        for (GVertex<Integer> v : intGraph.depthFirst(gInt1)) {
            assertTrue(intList.contains(v));
        }
        for (GVertex<String> v : strGraph.depthFirst(gStr1)) {
            assertTrue(strList.contains(v));
        }



        //test search after edge deletion, 5 should not be present
        intGraph.deleteEdge(wInt4.source(), wInt4.end());
        strGraph.deleteEdge(wStr4.source(), wStr4.end());

        intList.remove(gInt5);
        strList.remove(gStr5);

        //all vertices in graph are present, so all should be in depth
        //first search result

        assertEquals(intList.size(), intGraph.depthFirst(gInt1).size());
        for (GVertex<Integer> v : intGraph.depthFirst(gInt1)) {
            assertTrue(intList.contains(v));
        }
        for (GVertex<String> v : strGraph.depthFirst(gStr1)) {
            assertTrue(strList.contains(v));
        }
        



    }

//--------------------  incidentEdges() --------------------
    /** Checkstyle. */
    @Test
    public void incidentEdges() {
        GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
        double weight = (double) 1;

        WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
        WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

        assertTrue(intGraph.addEdge(wInt));
        assertTrue(strGraph.addEdge(wStr));

        GVertex<Integer> gInt3 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr3 = new GVertex<>("three", strGraph.nextID());
        GVertex<Integer> gInt4 = new GVertex<>((Integer) 4, intGraph.nextID());
        GVertex<String> gStr4 = new GVertex<>("four", strGraph.nextID());


        WEdge<Integer> wInt2 = new WEdge<>(gInt1, gInt3, weight);
        WEdge<String> wStr2 = new WEdge<>(gStr1, gStr3, weight);

        assertTrue(intGraph.addEdge(wInt2));
        assertTrue(strGraph.addEdge(wStr2));

        WEdge<Integer> wInt3 = new WEdge<>(gInt1, gInt4, weight);
        WEdge<String> wStr3 = new WEdge<>(gStr1, gStr4, weight);

        assertTrue(intGraph.addEdge(wInt3));
        assertTrue(strGraph.addEdge(wStr3));

        GVertex<Integer> gInt5 = new GVertex<>((Integer) 5, intGraph.nextID());
        GVertex<String> gStr5 = new GVertex<>("six", strGraph.nextID());
        GVertex<Integer> gInt6 = new GVertex<>((Integer) 6, intGraph.nextID());
        GVertex<String> gStr6 = new GVertex<>("five", strGraph.nextID());

        WEdge<Integer> wInt4 = new WEdge<>(gInt2, gInt5, weight);
        WEdge<String> wStr4 = new WEdge<>(gStr2, gStr5, weight);


        WEdge<Integer> wInt5 = new WEdge<>(gInt3, gInt6, weight);
        WEdge<String> wStr5 = new WEdge<>(gStr3, gStr6, weight);

        //add cycle to graph
        WEdge<Integer> wInt6 = new WEdge<>(gInt1, gInt6, weight);
        WEdge<String> wStr6 = new WEdge<>(gStr1, gStr6, weight);

        intGraph.addEdge(wInt4);
        strGraph.addEdge(wStr4);
        intGraph.addEdge(wInt5);
        strGraph.addEdge(wStr5);

        //cycle added
        intGraph.addEdge(wInt6);
        strGraph.addEdge(wStr6);

        LinkedList<WEdge<Integer>> incident1 = new LinkedList<>();
        incident1.add(wInt);
        incident1.add(wInt2);
        incident1.add(wInt3);
        incident1.add(wInt6);

        for (WEdge<Integer> e : intGraph.incidentEdges(gInt1)) {
            assertTrue(incident1.contains(e));
        }


        LinkedList<WEdge<Integer>> incident6 = new LinkedList<>();
        incident1.add(wInt);
        incident1.add(wInt5);

        for (WEdge<Integer> e : intGraph.incidentEdges(gInt6)) {
            assertTrue(incident1.contains(e));
        }



        //test edges after remove
        intGraph.deleteEdge(wInt3.source(), wInt3.end());
        incident1.remove(gInt4);
        for (WEdge<Integer> e : intGraph.incidentEdges(gInt1)) {
            assertTrue(incident1.contains(e));
        }

    }


//--------------------  kruskals() --------------------
    /** Checkstyle. */
    @Test
    public void testEmptyKruskals() {
        System.out.println("\nEmpty");
        // make mst on empty graph
        mstInt = intGraph.kruskals();
        // check that mst is empty
        assertTrue(mstInt.isEmpty());
        // repeat for string
        mstStr = strGraph.kruskals();
        assertTrue(mstStr.isEmpty());
    }

    /** Checkstyle. */
    @Test
    public void testSingleEdgeKruskals() {
        System.out.println("\nSingleEdge");
        // add one edge to graph
        double weight = 0.5;
        GVertex<Integer> g0 = intVerts.get(0);
        GVertex<Integer> g1 = intVerts.get(1);
        WEdge<Integer> wInt = new WEdge<>(g0, g1, weight);
        intGraph.addEdge(wInt);
        // check that adds were successful
        assertTrue(intGraph.hasVertex(g0));
        assertTrue(intGraph.hasVertex(g1));
        // check that there's an edge between g0 and g1
        assertTrue(intGraph.areAdjacent(g0, g1));
        // try Kruskal's now that everything worked
        mstInt = intGraph.kruskals();
        // check that desired edge is contained
        assertTrue(mstInt.contains(wInt));
        System.out.println(mstInt.toString());
        // check that mstInt has one edge
        assertEquals(mstInt.size(), 1); // line that fails
        // check that mstInt has the edge you want
        //WEdge<Integer> myEdge = mstInt.get(0);
        //assertEquals(myEdge,wInt);

    }

    /** Checkstyle. */
    @Test
    public void testMinimumEdgesChosen() {
        System.out.println("\nMinEdgesChosen");
        // weights for edges
        double w1 = 0.95;
        double w2 = 0.8;
        double w3 = 0.4;
        // make 3 vertices
        GVertex<Integer> g0 = intVerts.get(0);
        GVertex<Integer> g1 = intVerts.get(1);
        GVertex<Integer> g2 = intVerts.get(2);
        // make 3 edges of dif weight, add to graph
        WEdge<Integer> e1 = new WEdge<>(g0, g1, w1); 
        WEdge<Integer> e2 = new WEdge<>(g1, g2, w2);
        WEdge<Integer> e3 = new WEdge<>(g2, g0, w3);
        intGraph.addEdge(e1); // should not be in Kruskal's
        intGraph.addEdge(e2);
        intGraph.addEdge(e3);
        // get Kruskal's
        mstInt = intGraph.kruskals();
        // check that 2 edges of least weight are returned
        assertTrue(mstInt.contains(e2));
        assertTrue(mstInt.contains(e3));
        // check that e1 is excluded
        assertFalse(mstInt.contains(e1)); 
        
    }

    /** Checkstyle. */
    @Test
    public void testSimpleForest() {
        System.out.println("\nSimpleForest");
        // weights for edges
        double w1 = 0.95;
        double w2 = 0.8;
        double w3 = 0.4;
        // make 5 vertices
        GVertex<Integer> g0 = intVerts.get(0);
        GVertex<Integer> g1 = intVerts.get(1);
        GVertex<Integer> g2 = intVerts.get(2);
        GVertex<Integer> g3 = intVerts.get(3);
        GVertex<Integer> g4 = intVerts.get(4);
        // make 4 edges
        WEdge<Integer> e01 = new WEdge<>(g0, g1, w1); // should be excluded
        WEdge<Integer> e23 = new WEdge<>(g2, g3, w2); 
        WEdge<Integer> e04 = new WEdge<>(g0, g4, w3);
        WEdge<Integer> e41 = new WEdge<>(g4, g1, w3);
        // add to graph
        intGraph.addEdge(e01);
        intGraph.addEdge(e23);
        intGraph.addEdge(e04);
        intGraph.addEdge(e41);
        // get Kruskal's
        System.out.println("At Kruskals");
        mstInt = intGraph.kruskals();
        System.out.println("Done w/ Kruskals");

        // check that e23, e04, and e41 included
        assertTrue(mstInt.contains(e23));
        assertTrue(mstInt.contains(e04));
        assertTrue(mstInt.contains(e41));
        // check that e01 is excluded
        assertFalse(mstInt.contains(e01));
    }
}
