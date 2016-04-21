/**
 * Tests for GVertex
 */

// junit asserts
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
// junit classes
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GVertexTest {
	// vertices to test on
	static Vertex vint;
	static GVertex<Integer> gvint;
	static Vertex vstr;
	static GVertex<String> gvstr;
	static Vertex vnull;
	static GVertex<Integer> gvnull;
	// things to put in vertices
	static Integer myInt = 5;
	static String myStr = "hey";
	static Integer[] ids = {0,1,2};

	@BeforeClass
	public static void init() {
		// integer vertices should be identical
		vint = new Vertex(myInt, ids[0]);
		gvint = new GVertex(myInt, ids[0]);
		// string vertices should be identical
		vstr = new Vertex(myStr, ids[1]);
		gvstr = new GVertex(myStr, ids[1]);
		// create weird null vertices to test???
	}

	@Test
	public void testID() {
		// check integer vertices
		assertEquals(vint.id(), gvint.id());
		// check string vertices
		assertEquals(vstr.id(), gvstr.id());
	}
	
	@Test
	public void TestToString() {
		// check integer vertices
		assertEquals(vint.toString(), gvint.toString());
		// check string vertices
		assertEquals(vstr.toString(), gvstr.toString());
	}
	
	@Test
	public void testEquals(){
		// curr has same id but dif data as gvint
		GVertex<Integer> i1 = new GVertex(0,ids[0]);
		GVertex<String> s1 = new GVertex("yo",ids[1]);
		// check that i1 and gvint evaluate as equal
		assertTrue(i1.equals(gvint));
		assertTrue(s1.equals(gvstr));
		// check that i1 and gvint have same id
		assertEquals(i1.id(), gvint.id());
		assertEquals(s1.id(), gvstr.id());
		// check that gvint and vint are not equal
		assertFalse(gvint.equals(vint));
		assertFalse(gvstr.equals(vstr));
	}

	@Test
	public void testHashCode() {
		// check ids equal
		assertEquals(gvint.id(), vint.id());
		assertEquals(gvstr.id(), vstr.id());
		// check hashcodes equal
		assertEquals(gvint.hashCode(), vint.hashCode());
		assertEquals(gvstr.hashCode(), vstr.hashCode());
	}
	
	@Test
	public void testCompareTo() {
		// create other vertices to check
		GVertex<Integer> i1 = new GVertex(0,ids[2]); // greater
		GVertex<String> s1 = new GVertex("yo",ids[2]); // greater
		Vertex i2 = new Vertex(0, ids[2]); // greater
		Vertex s2 = new Vertex("yo", ids[2]); // greater
		// make sure comparisons work
		assertSame(gvint.compareTo(i1), vint.compareTo(i2));
		assertSame(gvstr.compareTo(s1), vstr.compareTo(s2));
	}
	// compareTo

}