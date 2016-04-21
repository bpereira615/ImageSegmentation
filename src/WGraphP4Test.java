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
    }

    @Test
    public void numVertsRemove() {
    	//check after removing vertex
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
    }

    @Test
    public void nextIDRemove() {
    	//check ID after removing vertex
    }



//--------------------  addVertex() --------------------
    @Test
    public void addVertexNew() {
    	assertTrue(intGraph.addVertex((Integer) 0));
    	assertTrue(strGraph.addVertex("zro"));
    }
}
