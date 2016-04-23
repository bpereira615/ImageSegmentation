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

/** JUnit tests for the MaxPriorityQueue interface.
 */
public class WGraphP4Test {

    static WGraphP4<Integer> intGraph;
    static WGraphP4<String> strGraph;
    static Integer[] iray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //size = 11
    static String[] sray = {"zro", "one", "two", "tre", "for", "fyv", "six", "svn", "ate", "nyn", "ten"};

    @BeforeClass
    public static void init() {
    	
    }


    @Before
    public void setup() {
    	//fresh graphes for each test
		intGraph = new WGraphP4<>();
    	strGraph = new WGraphP4<>();
    }


//--------------------  numEdges() --------------------
    @Test
    public void numEdgesNew() {
        assertEquals(0, intGraph.numEdges());
        assertEquals(0, strGraph.numEdges());
    }

    @Test
    public void numEdgesAdd() {
    	//check after adding edge
    }

    @Test
    public void numEdgesRemove() {
    	//check after removing edge
    }


//--------------------  hasData() --------------------

    @Test
    public void hasVertexNew() {
    	GVertex<Integer> gInt = new GVertex<>((Integer) 1, 1);
        GVertex<String> gStr = new GVertex<>("one", 1);
    	assertFalse(intGraph.hasData((Integer) 1));
    	assertFalse(strGraph.hasData("one"));
    }

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
	@Test
    public void numVertsNew() {
        assertEquals(0, intGraph.numVerts());
        assertEquals(0, strGraph.numVerts());
    }

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

    @Test
    public void numVertsRemove() {
    	//check after removing vertex
    	
    	intGraph.addVertex((Integer) 0);
    	strGraph.addVertex("zro");
    	assertEquals(1, intGraph.numVerts());
        assertEquals(1, strGraph.numVerts());
        
    }





//--------------------  nextID() --------------------
	@Test
    public void nextIDNew() {
        assertEquals(0, intGraph.nextID());
        assertEquals(0, strGraph.nextID());
    }

    @Test
    public void nextIDAdd() {
	   	//check ID after adding vertex
	    GVertex<Integer> gInt = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr = new GVertex<>("one", strGraph.nextID());
        intGraph.addVertex(gInt);
        strGraph.addVertex(gStr);
        assertEquals(1, intGraph.nextID());
        assertEquals(1, strGraph.nextID());
    	}




//--------------------  addVertex(VT d) --------------------
    @Test
    public void addVertexNew() {
    	assertTrue(intGraph.addVertex((Integer) 0));
    	assertTrue(strGraph.addVertex("zro"));
    	assertTrue(intGraph.hasData((Integer) 0));
    	assertTrue(strGraph.hasData("zro"));

    }



//--------------------  addVertex(GVertex<VT> v) --------------------
    @Test
    public void addVertexObjectNew() {
    	GVertex<Integer> gInt = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr = new GVertex<>("one", strGraph.nextID());
        assertTrue(intGraph.addVertex(gInt));
        assertTrue(strGraph.addVertex(gStr));
        assertTrue(intGraph.hasData((Integer) 1));
    	assertTrue(strGraph.hasData("one"));

    }

    @Test
    public void addVertexObjectContained() {
    	GVertex<Integer> gInt = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr = new GVertex<>("one", strGraph.nextID());

    	assertTrue(intGraph.addVertex(gInt));
    	assertTrue(strGraph.addVertex(gStr));

    	//vertices with the same ID as other vertices already contained cannot be added
    	assertFalse(intGraph.addVertex(gInt));
    	assertFalse(strGraph.addVertex(gStr));
    }




//--------------------  addEdge(WEdge e) --------------------
	

/*
//--------------------  addEdge(GVertex<VT> v, GVertex<VT> u, double weight) --------------------


//--------------------  deleteEdge() --------------------

//--------------------  areAdjacent() --------------------


//--------------------  neighbors() --------------------


//--------------------  degree() --------------------


//--------------------  areIncident() --------------------


//--------------------  allEdges() --------------------


//--------------------  allVertices() --------------------



//--------------------  depthFirst() --------------------


//--------------------  incidentEdges() --------------------



//--------------------  kruskals() --------------------





*/
}
