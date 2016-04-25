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

import java.util.LinkedList;
import java.util.List;

/** JUnit tests for the MaxPriorityQueue interface.
 */
public class WGraphP4Test {

    static WGraphP4<Integer> intGraph;
    static WGraphP4<String> strGraph;
    static Integer[] iray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //size = 11
    static String[] sray = {"zro", "one", "two", "tre", "for", "fyv", "six", "svn", "ate", "nyn", "ten"};
    static GVertex<String> s1;
    static GVertex<String> s2;
    static GVertex<String> s3;
    static GVertex<Integer> i1;
    static GVertex<Integer> i2;
    static GVertex<Integer> i3;

    @BeforeClass
    public static void init() {
    	
    }


    @Before
    public void setup() {
    	//fresh graphes for each test
		intGraph = new WGraphP4<>();
    	strGraph = new WGraphP4<>();
        // string vertices
        s1 = new GVertex<>("a",strGraph.nextID());
        s2 = new GVertex<>("b", strGraph.nextID());
        s3 = new GVertex<>("c", strGraph.nextID());
    }






//--------------------  numEdges() --------------------
    @Test
    public void numEdgesEmpty() {
        assertEquals(0, intGraph.numEdges());
        assertEquals(0, strGraph.numEdges());
    }

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

    @Test
    public void numEdgesAddRemove() {
        int edges = strGraph.numEdges();
        // add something to graph
        strGraph.addEdge(s1,s2,0.8);
        edges++;
        // check that add was successful
        assertEquals(strGraph.numEdges(),edges); // # edges
        assertTrue(strGraph.hasVertex(s1)); // has s1
        assertTrue(strGraph.hasVertex(s2)); // has s2
        // now remove and check for decrement
    	strGraph.deleteEdge(s1,s2);
        edges--;
        assertEquals(strGraph.numEdges(), edges);
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
    public void numVertsEmpty() {
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

    	GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());

    	assertTrue(intGraph.addVertex(gInt2));
    	assertTrue(strGraph.addVertex(gStr2));

    	//vertices with the same ID as other vertices already contained cannot be added
    	assertFalse(intGraph.addVertex(gInt));
    	assertFalse(strGraph.addVertex(gStr));
    }




//--------------------  addEdge(WEdge e) --------------------
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


//--------------------  addEdge(GVertex<VT> v, GVertex<VT> u, double weight) --------------------

public void addEdgeFromVertsDuplicate() {
    // should be able to add vertices
    double weight = 1.0;
    strGraph.addEdge(s1,s2,weight);
    // check that vertices are included
    assertTrue(strGraph.hasVertex(s1));
    assertTrue(strGraph.hasVertex(s2));
    // check that edge is connecting them
    assertTrue(strGraph.areAdjacent(s1,s2));
    // try to add edge again, should fail
    assertFalse(strGraph.addEdge(s1,s2,weight));
}


//--------------------  deleteEdge() --------------------
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

        //edge will have been deleted, should be false
        assertFalse(intGraph.deleteEdge(gInt1, gInt2));
        assertFalse(strGraph.deleteEdge(gStr1, gStr2));
	}


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
	@Test
    public void neighborsEmptyList(){
        strGraph.addVertex(s1);
        strGraph.addVertex(s2);
        LinkedList<GVertex<String>> nbs = new LinkedList<GVertex<String>>();
        assertEquals(nbs, strGraph.neighbors(s1));
        assertTrue(strGraph.hasVertex(s1));
        assertEquals(nbs, strGraph.neighbors(s2));
        assertTrue(strGraph.hasVertex(s2));
    }
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

	@Test
	public void degreeNew() {
		GVertex<Integer> gInt = new GVertex<>((Integer) 1, 1);
        GVertex<String> gStr = new GVertex<>("one", 1);

        intGraph.addVertex(gInt);
        strGraph.addVertex(gStr);

        assertEquals(0, intGraph.degree(gInt));
        assertEquals(0, strGraph.degree(gStr));
	}

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
    }
//--------------------  areIncident() --------------------
// this is covered by the edge class

//--------------------  allEdges() --------------------
// not sure how we would even check this

//--------------------  allVertices() --------------------
// same problem


//--------------------  depthFirst() --------------------

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

		intGraph.addEdge(wInt4);
		strGraph.addEdge(wStr4);
		intGraph.addEdge(wInt5);
		strGraph.addEdge(wStr5);

		System.out.println(intGraph.depthFirst(gInt1));
	}

//--------------------  incidentEdges() --------------------



//--------------------  kruskals() --------------------






}
