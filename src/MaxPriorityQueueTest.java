/********************************************************************
 * Benjamin Hoertnagl-Pereira & Ryan Walter
 * JHED: bhoertn1 & rwalte25
 * bhoertn1@jhu.edu & rwalte25@jhu.edu
 *
 * 600.226.02 | CS226 Data Structures
 * Assignment 4 - Maximum Priority Queue JUnit Testing
 *
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
import java.util.Collections;
/** JUnit tests for the MaxPriorityQueue interface.
 */
public class MaxPriorityQueueTest {

    PQHeap<Integer> intFull, intEmpty;
    PQHeap<String> strFull, strEmpty;
    static Integer[] iray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //size = 11
    static String[] sray = {"zro", "one", "two", "tre", "for", "fyv", "six", "svn", "ate", "nyn", "ten"};
    static ArrayList<String> svals;
    static ArrayList<Integer> ivals;

    @BeforeClass
    public static void init() {
        //initial values if needed before each test
        svals = new ArrayList<String>(); //creation of string collection
        for (String val: sray) {
            svals.add(val);
        }
        ivals = new ArrayList<Integer>(); //collection of integer collection
        for (Integer val: iray) {
            ivals.add(val);
        }

    }

    // re-initialize to original values before each test
    // assumes MaxPQ init works - if no tests work, MaxPQ init is likely why.
    @Before
    public void setup() {
        //initial creation of objects, once for each run of program
        intFull = new PQHeap<Integer>();
        intFull.init(ivals); //0 - 10
        strFull = new PQHeap<String>();
        strFull.init(svals); //"zro" - "ten"  

        intEmpty = new PQHeap<Integer>();
        strEmpty = new PQHeap<String>();
    }

//--------------------  size() --------------------
    @Test
    public void sizeNew() {
        assertEquals(0, intEmpty.size());
        assertEquals(0, strEmpty.size());
    }

    @Test
    public void sizeCleared() {
        //NOTE: depends on correct clear function
        intFull.clear();
        assertEquals(0, intFull.size());
        strFull.clear();
        assertEquals(0, strFull.size());

    }

    @Test
    public void sizeInsertNew() {
        //new value inserted, size should increment
        intEmpty.insert((Integer) 0);
        assertEquals(1, intEmpty.size());
        for (int i = 1; i <= 5; i++ ) {
            intEmpty.insert(2*i);
            //System.out.println(intEmpty.toString() + "\n");
        }
        assertEquals(6, intEmpty.size());
        intEmpty.insert(1);
        //System.out.println(intEmpty.toString() + "\n");

        strEmpty.insert("zro");
        assertEquals(1, strEmpty.size());
        for (int i = 1; i <= 5; i++ ) {
            strEmpty.insert(sray[i]);
        }
        assertEquals(6, strEmpty.size());
    }

    @Test
    public void sizeInsertContained() {
        //value to be inserted already present, size still increments
        intEmpty.insert(0);
        assertEquals(1, intEmpty.size());
        intEmpty.insert(0);
        assertEquals(2, intEmpty.size());

        strEmpty.insert("zro");
        assertEquals(1, strEmpty.size());
        strEmpty.insert("zro");
        assertEquals(2, strEmpty.size());
    }

    @Test
    public void sizeRemoveContained() {
        //remove a value in heap, size should decrement
        assertEquals(11, intFull.size());
        assertEquals (0, (int) intFull.remove());
        assertEquals(10, intFull.size());

        intEmpty.insert(0);
        assertEquals(1, intEmpty.size());
        assertEquals(0, (int) intEmpty.remove());
        assertEquals(0, intEmpty.size());

        assertEquals(11, strFull.size());
        assertEquals ("ate", strFull.remove());
        assertEquals(10, strFull.size());

        strEmpty.insert("zro");
        assertEquals(1, strEmpty.size());
        assertEquals("zro", strEmpty.remove());
        assertEquals(0, strEmpty.size());

    }

    @Test
    public void sizeRemoveEmpty() {
        try {
            intEmpty.remove(); 
        } catch (Exception e){
            //will throw exception, ignore and check that size unchanged
        }
        assertEquals(0, intEmpty.size());

        try {
            strEmpty.remove(); 
        } catch (Exception e){
            //will throw exception, ignore and check that size unchanged
        }
        assertEquals(0, strEmpty.size());
    }

//-------------------------------------------------


//--------------------  isEmpty() --------------------
    //NOTE: isEmpty is entirely dependent on size(), if size() tests are passing,
    // then it can be assumed that isEmpty() will be working correctly

    @Test
    public void isEmptyNew() {
        assertTrue(intEmpty.isEmpty());
        assertTrue(strEmpty.isEmpty());
    }

    @Test
    public void isEmptyInsert() {
        intEmpty.insert(0);
        assertFalse(intEmpty.isEmpty());

        strEmpty.insert("zro");
        assertFalse(strEmpty.isEmpty());        
    }

    @Test
    public void isEmptyFullRemove() {
        for (int i = 11; i > 0; i--) { //called 11 times
            assertEquals(i, intFull.size());
            assertEquals(11 - i, (int) intFull.remove());
            assertEquals(i - 1, intFull.size());
            //System.out.println(intFull.toString() + "\n");
        }     
        assertTrue(intFull.isEmpty());

        // for (int i = 11; i > 0; i--) { //called 11 times
        //     assertEquals(sray[i-11], strFull.remove());
        //     assertEquals(i, strFull.size());
        // }     
        // assertTrue(strFull.isEmpty());       
    }

    @Test
    public void isEmptyClear() {
        intFull.clear();
        assertTrue(intFull.isEmpty());

        strFull.clear();
        assertTrue(strFull.isEmpty());
    }

//----------------------------------------------------


//--------------------  clear() --------------------

    @Test
    public void clearNew() {
        //could be edge case
        assertEquals(0, intEmpty.size());
        assertTrue(intEmpty.isEmpty());
        intEmpty.clear();
        assertEquals(0, intEmpty.size());
        assertTrue(intEmpty.isEmpty());

        assertEquals(0, strEmpty.size());
        assertTrue(strEmpty.isEmpty());
        strEmpty.clear();
        assertEquals(0, strEmpty.size());
        assertTrue(strEmpty.isEmpty());
    }

    @Test
    public void clearGeneral() {
        intFull.clear();
        strFull.clear();

        assertEquals(intEmpty.size(), intFull.size());
        assertEquals(strEmpty.size(), strFull.size());
    }

//--------------------------------------------------


//-------------------- () --------------------

    @Test (expected=QueueEmptyException.class)
        public void peekNewInt() {
        intEmpty.peek();
    }

    @Test (expected=QueueEmptyException.class)
        public void peekNewStr() {
        strEmpty.peek();
    }

    @Test (expected=QueueEmptyException.class)
        public void peekClearInt() {
        intFull.clear();
        intFull.peek();
    }

    @Test (expected=QueueEmptyException.class)
        public void peekClearStr() {
        strFull.clear();
        strFull.peek();
    }

    @Test
    public void peekInsert() { //assumes Min Heap
        intEmpty.insert((Integer) 0);
        assertEquals(0, (int) intEmpty.peek());
        assertEquals(1, intEmpty.size());

        assertEquals(0, (int) intFull.peek());
        assertEquals(11, intFull.size());

        intFull.insert((Integer) 20);
        assertEquals(0, (int) intFull.peek());
        assertEquals(12, intFull.size());

        intFull.insert((Integer) 15);
        assertEquals(0, (int) intFull.peek());
        assertEquals(13, intFull.size());

        intFull.insert((Integer) 15);
        assertEquals(0, (int) intFull.peek());
        assertEquals(14, intFull.size());        

        strEmpty.insert("zro");
        assertEquals("zro", strEmpty.peek());
        assertEquals(1, strEmpty.size());

        assertEquals("ate", strFull.peek()); 
        assertEquals(11, strFull.size());

        strFull.insert("zz");
        assertEquals("ate", strFull.peek());
        assertEquals(12, strFull.size());

        strFull.insert("aaz");
        assertEquals("aaz", strFull.peek());
        assertEquals(13, strFull.size());

    }

    @Test
    public void peekRemove() {
        assertEquals(0, (int) intFull.peek());
        assertEquals(0, (int) intFull.remove());
        assertEquals(1, (int) intFull.peek());
        assertEquals(10, intFull.size()); 

        assertEquals("ate", strFull.peek());
        assertEquals("ate", strFull.remove());
        assertEquals("for", strFull.peek());
        assertEquals(10, strFull.size());
    }

//---------------------------------------------------





//--------------------  remove() --------------------

    @Test (expected=QueueEmptyException.class)
    public void removeNewInt() {
    intEmpty.remove();
    }

    @Test (expected=QueueEmptyException.class)
    public void removeNewStr() {
    strEmpty.remove();
    }

    @Test (expected=QueueEmptyException.class)
    public void removeClearInt() {
    intFull.clear();
    intFull.remove();
    }

    @Test (expected=QueueEmptyException.class)
    public void removeClearStr() {
    strFull.clear();
    strFull.remove();
    }

    @Test
    public void removeDouble() {
        intEmpty.insert(2);
        assertEquals(2, (int) intEmpty.peek());
        assertEquals(1, intEmpty.size());
        intEmpty.insert(1);
        assertEquals(1, (int) intEmpty.peek());
        assertEquals(2, intEmpty.size());
        intEmpty.insert(2); 
        assertEquals(1, (int) intEmpty.peek());
        assertEquals(3, intEmpty.size());

        assertEquals(1, (int) intEmpty.remove());
        assertEquals(2, intEmpty.size());

        assertEquals(2, (int) intEmpty.remove());
        assertEquals(1, intEmpty.size());

        assertEquals(2, (int) intEmpty.remove());
        assertEquals(0, intEmpty.size());
    }

    @Test
    public void removeGeneral() {
        intEmpty.insert((Integer) 0);
        assertEquals(0, (int) intEmpty.remove());
        assertEquals(0, intEmpty.size());

        assertEquals(11, intFull.size());
        assertEquals(0, (int) intFull.remove());
        assertEquals(10, intFull.size());

        intFull.insert((Integer) 20);
        assertEquals(11, intFull.size());
        assertEquals(1, (int) intFull.remove());

        intFull.insert((Integer) 2);
        assertEquals(2, (int) intFull.remove());
        assertEquals(2, (int) intFull.remove());

        strEmpty.insert("zro");
        assertEquals("zro", strEmpty.remove());
        assertEquals(0, strEmpty.size());

        assertEquals(11, strFull.size());
        assertEquals("ate", strFull.remove());
        assertEquals(10, strFull.size());

        strFull.insert("aax");
        assertEquals(11, strFull.size());
        assertEquals("aax", strFull.remove());

        strFull.insert("llx");
        assertEquals("for", strFull.remove());
        assertEquals("fyv", strFull.remove());
    }

//------------------------------------------------------


//--------------------  insert() --------------------

    @Test
    public void insertNew() {
        intEmpty.insert((Integer) 0);
        assertEquals(0, (int) intEmpty.peek());
        assertEquals(1, intEmpty.size());

        strEmpty.insert("zro");
        assertEquals("zro", strEmpty.peek());
        assertEquals(1, strEmpty.size());
    }

    @Test
    public void insertClear() {
        intEmpty.clear();
        intEmpty.insert((Integer) 0);
        assertEquals(0, (int) intEmpty.peek());
        assertEquals(1, intEmpty.size());

        strEmpty.clear();
        strEmpty.insert("zro");
        assertEquals("zro", strEmpty.peek());
        assertEquals(1, strEmpty.size());
    }

    @Test
    public void insertDouble() {
        intEmpty.insert(2);
        assertEquals(2, (int) intEmpty.peek());
        assertEquals(1, intEmpty.size());
        intEmpty.insert(1);
        assertEquals(1, (int) intEmpty.peek());
        assertEquals(2, intEmpty.size());
        intEmpty.insert(2); 
        assertEquals(1, (int) intEmpty.peek());
        assertEquals(3, intEmpty.size());
    }

    @Test
    public void insertBubbleRoot() {
        for (int i = 4; i < 15; i++) {
            intEmpty.insert(i * 2);
        }
        intEmpty.insert(2);
        assertEquals(2, (int) intEmpty.peek());
        intEmpty.insert(1);
        assertEquals(1, (int) intEmpty.peek());
        assertEquals(1, (int) intEmpty.remove());
    }

//---------------------------------------------------


//--------------------  init() --------------------

    @Test
    public void emptyInit() {
        assertEquals(11, intFull.size());
        ArrayList<Integer> ivalsEmpty = new ArrayList<Integer>();
        intFull.init(ivalsEmpty);
        assertEquals(0, intFull.size());
        assertTrue(intFull.isEmpty());

        assertEquals(11, strFull.size());
        ArrayList<String> svalsEmpty = new ArrayList<String>();
        strFull.init(svalsEmpty);
        assertEquals(0, strFull.size());
        assertTrue(strFull.isEmpty());
    }

    @Test
    public void reverseInit() {
        Collections.reverse(ivals);
        intEmpty.init(ivals);
        System.out.println(intEmpty.toString());
        for (int i = 11; i > 0; i--) { //called 11 times
            assertEquals(i, intEmpty.size());
            assertEquals(11 - i, (int) intEmpty.remove());
            assertEquals(i - 1, intEmpty.size());
            System.out.println(intEmpty.toString() + "\n");
        }     
        assertTrue(intEmpty.isEmpty());
    }
    //non-empty init basically tested throughout above tests.

//-------------------------------------------------





}
