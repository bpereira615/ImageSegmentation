/********************************************************************
 * Lydia Carroll, Benjamin Hoertnagl-Pereira, Ryan Walter
 * JHED: lcarro12, bhoertn1, rwalte25
 * lcarro12 @jhu.edu, bhoertn1@jhu.edu, rwalte25@jhu.edu
 *
 * 600.226.01 | CS226 Data Structures
 * Project 4 - Image Segmentation
 *******************************************************************/

/**
 * Tests for GVertex
 */

// junit asserts
import static org.junit.Assert.assertEquals;



import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

// junit classes

import org.junit.BeforeClass;
import org.junit.Test;
/** Contains JUnit tests for GVertex. */
public class GVertexTest {

    /** Integer Vertex to test on. */
    static Vertex vint;
    /** Integer GVertex to test on. */
    static GVertex<Integer> gvint;
    /** String Vertex to test on. */
    static Vertex vstr;
    /** String GVertex to test on. */
    static GVertex<String> gvstr;
    /** Empty Vertex to test on. */
    static Vertex vnull;
    /** Empty GVertex to test on. */
    static GVertex<Integer> gvnull;
    /** Integer to put in vertices. */
    static Integer myInt = 5;
    /** String to put in vertices. */
    static String myStr = "hey";
    /** Integers to be ID values. */
    static Integer[] ids = {0, 1, 2};

    /** Run before every class. */
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

    /** Make sure vertices ID correctly. */
    @Test
    public void testID() {
        // check integer vertices
        assertEquals(vint.id(), gvint.id());
        // check string vertices
        assertEquals(vstr.id(), gvstr.id());
    }
    
    /** ToString should be the same as Vertex class. */
    @Test
    public void testToString() {
        // check integer vertices
        assertEquals(vint.toString(), gvint.toString());
        // check string vertices
        assertEquals(vstr.toString(), gvstr.toString());
    }
    
    /** The equals function should be the same as Vertex. */
    @Test
    public void testEquals() {
        // curr has same id but dif data as gvint
        GVertex<Integer> i1 = new GVertex(0, ids[0]);
        GVertex<String> s1 = new GVertex("yo", ids[1]);
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

    /** Hash code should mirror Vertex. */
    @Test
    public void testHashCode() {
        // check ids equal
        assertEquals(gvint.id(), vint.id());
        assertEquals(gvstr.id(), vstr.id());
        // check hashcodes equal
        assertEquals(gvint.hashCode(), vint.hashCode());
        assertEquals(gvstr.hashCode(), vstr.hashCode());
    }
    
    /** CompareTo should be the same as Vertex. */
    @Test
    public void testCompareTo() {
        // create other vertices to check
        GVertex<Integer> i1 = new GVertex(0, ids[2]); // greater
        GVertex<String> s1 = new GVertex("yo", ids[2]); // greater
        Vertex i2 = new Vertex(0, ids[2]); // greater
        Vertex s2 = new Vertex("yo", ids[2]); // greater
        // make sure comparisons work
        assertSame(gvint.compareTo(i1), vint.compareTo(i2));
        assertSame(gvstr.compareTo(s1), vstr.compareTo(s2));
    }
    // compareTo

}