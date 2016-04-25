/********************************************************************
 * Ryan Walter
 * JHED: rwalte25
 * rwalte25@jhu.edu
 *
 * 600.226.02 | CS226 Data Structures
 * Project 4 - Priority Queue Heap
 *
 *******************************************************************/

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Priority heap: root is "best" as determined by comparator.
 * Final version: passes all tests.
 */
public class PQHeap<T extends Comparable<? super T>> 
            implements PriorityQueue<T> {

    /** Comparator used--changed by passing argument to constructor. */
    private Comparator<T> comp;
    /** List representation of the heap. */
    private ArrayList<T> list;
    /** Keeps track of current index in ArrayList. */
    private int location;
    
    public PQHeap() {
        this.clear();
        this.comp = new DefaultComparator<T>(); //---------------------------------------------------------------------------------------------------
    }

    public PQHeap(Comparator<T> c){
        this.clear();
        this.comp = c;
    }

    /** 
     * Insert a value. Duplicate values <b>do</b> end up in the
     *  queue, so inserting X three times means it has to be removed
     *  three times before it's gone again.
     *  @param t Value to add.
     */
    public void insert(T t) {
        int index = this.location;
        int newLoc;
        this.list.add(t);
        while (index > 1) { //while not at root
            if (index % 2 == 0) { //even
                newLoc = index / 2;
            } else { //odd
                newLoc = (index - 1) / 2;
            }
            // if (newLoc < 1) { //have reached root
            //     this.location++;
            //     return;
            // }
            if (this.comp.compare(this.list.get(index), 
                this.list.get(newLoc)) < 0) {
                //T temp = this.list.get(index);
                T temp = this.list.set(index, this.list.get(newLoc));
                this.list.set(newLoc, temp);
                index = newLoc;
            // even if in middle of tree, parent < child is good so stop
            } else { 
                this.location++;
                return;
            }
        }
        this.location++;
    }
    
    /** 
     * Remove "best" value. This value is the "best" value in the
     *  queue as determined by the comparator for the queue.
     *  @return the value that was removed
     *  @throws QueueEmptyException If queue is empty.
     */
    public T remove() throws QueueEmptyException{ //
        if (this.location == 1) {
            throw new QueueEmptyException();
        }
        T root = this.list.get(1); //get root value
        this.location--; //decrement location
        if (this.location > 1) { //if not the only node, need to check stuff
            T temp = this.list.remove(this.location); //end value
            this.list.set(1, temp); //overwrite root with saved last node val

            int index = 1;
            T childVal;
            int newIndex;
            T left;
            T right;

            //while there is a left child, not @ end of branch
            while (index * 2 <= this.location - 1){ 
                T currentVal = this.list.get(index);
                int leftIndex = index * 2;
                int rightIndex = index * 2 + 1;

                // need to determine if current index has 1 or 2 children
                if (rightIndex > this.location - 1) { //only left child
                    newIndex = leftIndex;
                    childVal = this.list.get(newIndex);
                } else { //both present, need to choose which to switch
                    left = this.list.get(leftIndex); 
                    right = this.list.get(rightIndex);
                    if (this.comp.compare(left, right) <= 0) { // left is smaller or equal
                        childVal = left;
                        newIndex = leftIndex;
                    }
                    else { // right is smaller
                        childVal = right;
                        newIndex = rightIndex;
                    }
                }

                // compare previously determined "best child" w/ current and switch
                if (this.comp.compare(currentVal, childVal) > 0) { //current > child
                    this.list.set(index, childVal); //switch current to child val
                    this.list.set(newIndex, currentVal); //push current val to child
                    index = newIndex;
                } else {
                    return root;
                }
            }
        }
        return root;
    }
    
    /** 
     * Get the "best" value. This value is the "best" value in the
     *  queue as determined by the comparator for the queue.  [Note,
     *  "best" is at the root in a heap-based implementation.]
     *  @return best value in the queue.
     *  @throws QueueEmptyException If queue is empty.
     */
    public T peek() throws QueueEmptyException{
        if (this.location == 1) {
            throw new QueueEmptyException();
        }
        return this.list.get(1);
    }
    
    /** 
     * No elements?
     *  @return True if queue is empty, false otherwise.
     */
    public boolean isEmpty(){
        return this.location == 1;
    }

    /** 
     * Get the number of elements in the queue.
     *  @return the numbers
     */
    public int size(){
        return this.location - 1;
    }

    /** 
     * Dump the contents of the priority queue.
     */
    public void clear(){
        this.list = new ArrayList<T>();
        this.list.add(null); //fills 1st slot, never accessed
        this.location = 1;
    }

    /** 
     * Initialize a priority queue from a container of values.
     * Assumes PQHeap already exists--will clear if values present already.
     *  @param values the collection of starting values
     */
    public void init(Collection<T> values){
        if (this.location != 1) { //if values present, clear/ create new array
            this.clear();
        }
        for (T t: values) {
            this.insert(t);
        }
    }

    public String toString(){
        String output = "";
        int i = 0;
        for(T t: this.list){
            if (i != 0) { //first will be null, don't print
                output += t + "|" + i + "; ";
            }
            i++;
        }
        return output;
    }

    /**
     * Default Comparator to use if comparator not passed as optional argument
     */
    private static class DefaultComparator<T extends Comparable<? super T>> 
                                                implements Comparator<T> {    
        /**
         * Uses ordering determined by the class being compared.
         * @param  t1 1st object being compared
         * @param  t2 2nd object being compared
         * @return    Comparison output as determined by class.
         */
        public int compare(T t1, T t2) {
            return t1.compareTo(t2);
        }
    }
}