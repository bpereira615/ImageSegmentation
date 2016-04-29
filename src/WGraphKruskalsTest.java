/********************************************************************
 * Lydia Carroll, Benjamin Hoertnagl-Pereira, Ryan Walter
 * JHED: lcarro12, bhoertn1, rwalte25
 * lcarro12 @jhu.edu, bhoertn1@jhu.edu, rwalte25@jhu.edu
 *
 * 600.226.01 | CS226 Data Structures
 * Project 4 - Image Segmentation
 *******************************************************************/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WGraphKruskalsTest {
	static WGraphP4<Integer> intGraph;
    static WGraphP4<String> strGraph;
    static List<WEdge<Integer>> mstInt;
    static List<WEdge<String>> mstStr;
    static Integer[] iray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //size = 11
    static String[] sray = {"zro", "one", "two", "tre", "for", "fyv", "six", "svn", "ate", "nyn", "ten"};
    static ArrayList<GVertex<Integer>> intVerts;
    static ArrayList<GVertex<String>> strVerts;

    @Before
    public void init() {
    	//fresh graphs for each test
		intGraph = new WGraphP4<>();
    	strGraph = new WGraphP4<>();
    	// fresh mst for each test
    	mstInt = new ArrayList<WEdge<Integer>>();
    	mstStr = new ArrayList<WEdge<String>>();
    	// make arrays of vertices
    	intVerts = new ArrayList<GVertex<Integer>>();
    	strVerts = new ArrayList<GVertex<String>>();
    	for (Integer i : iray) {
    		GVertex<Integer> vInt = new GVertex<Integer>(i, i);
    		GVertex<String> vStr = new GVertex<String>(sray[i],i);
    		intVerts.add(vInt);
    		strVerts.add(vStr);
    	}
    }

    // @Before
    // public static void setup() {
    //     mstInt.clear();
    //     mstStr.clear();
    // }
    	

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
    	assertTrue(intGraph.areAdjacent(g0,g1));
    	// try Kruskal's now that everything worked
    	mstInt = intGraph.kruskals();
        // check that desired edge is contained
        assertTrue(mstInt.contains(wInt));
        System.out.println(mstInt.toString());
    	// check that mstInt has one edge
    	assertEquals(mstInt.size(),1); // line that fails
    	// check that mstInt has the edge you want
    	//WEdge<Integer> myEdge = mstInt.get(0);
    	//assertEquals(myEdge,wInt);

    }

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
        assertTrue(mstInt.contains(e41)); // line currently fails
        // check that e01 is excluded
        assertFalse(mstInt.contains(e01));
    }
}