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

    @BeforeClass
    public static void init() {
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

    @Test
    public void testEmptyKruskals() {
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
    	// add one edge to graph
    	double weight = (double) 0.5;
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
    	// check that mstInt has one edge
    	assertEquals(mstInt.size(),1);
    	// check that mstInt has the edge you want
    	WEdge<Integer> myEdge = mstInt.get(0);
    	assertEquals(myEdge,wInt);

    }
}