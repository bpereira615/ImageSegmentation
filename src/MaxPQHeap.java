/********************************************************************
 * Lydia Carroll
 * JHED: lcarro12
 * lcarro12@jhu.edu
 * 
 * Ryan Demo
 * JHED: rdemo1
 * rdemo1@jhu.edu
 *
 * 600.226.02 | CS226 Data Structures | Section 1
 * Assignment 4
 * 
 *******************************************************************/


import java.util.Collection;
import java.util.LinkedList;


/** Implementation for a generic maximum based priority queue. 
 *  @param <T> the generic type of the elements in the queue
 */
public class MaxPQHeap<T 
    extends Comparable<? super T>> implements MaxPriorityQueue<T> {

    private class HNode<T> {
        private HNode left;
        private HNode right;
        private T data;

        HNode(T t) {
            this.left = null;
            this.right = null;
            this.data = t;
        }

        /** Returns if element is on last row of heap. */
        public boolean isLeaf(){
            return(this.left == null || this.right == null);
        }
    }
    
    /** The number of elements in the heap. */
    private int size;

    /** The root of the heap. */
    private HNode root;
    
    /**
     * 
     */
    public MaxPQHeap() {
        this.clear(); 
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public T getMax() throws QueueEmptyException {
        if (this.isEmpty()) {
            throw new QueueEmptyException();
        } else {
            // are we sorting or nah?
        }
        
        
        
        return null;
    }

    @Override
    public T removeMax() throws QueueEmptyException {
        if (this.isEmpty()) {
            throw new QueueEmptyException();
        }
        
        return null;
    }

    @Override
    public void insert(T val) {
        // TODO Auto-generated method stub
        return this.insert(val, this.root);
        
    }

    public void insert(T val, HNode curr) {
        // TODO Auto-generated method stub
    }

    @Override
    public void init(Collection<T> values) {
        // TODO Auto-generated method stub
        
    }

}
