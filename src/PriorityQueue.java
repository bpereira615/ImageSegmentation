import java.util.Collection;

/** This is specifies a general Priority Queues of ordered values.

    Note that the interface does not include methods to obtain
    minimum or maximum explicitly, but of course we want to be
    able to create PQs of either sort.

    Your implementation must provide two constructors, one that
    uses the "natural" ordering of java.util.Comparable<T>, and
    another that accepts an explicit java.util.Comparator<T> argument.

    It's a *very* good idea to take the time and read the Java
    documentation for those interfaces *before* you start hacking!

    @param <T> Type of element values.
*/
public interface PriorityQueue<T extends Comparable<? super T>> {

    /** Insert a value. Duplicate values <b>do</b> end up in the
     *  queue, so inserting X three times means it has to be removed
     *  three times before it's gone again.
     *  @param t Value to add.
     */
    void insert(T t);
    
    /** Remove "best" value. This value is the "best" value in the
     *  queue as determined by the comparator for the queue.
     *  @throws QueueEmptyException If queue is empty.
     */
    void remove() throws QueueEmptyException;
    
    /** Get the "best" value. This value is the "best" value in the
     *  queue as determined by the comparator for the queue.  [Note,
     *  "best" is at the root in a heap-based implementation.]
     *  @return best value in the queue.
     *  @throws QueueEmptyException If queue is empty.
     */
    T peek() throws QueueEmptyException;
    
    /** No elements?
     *  @return True if queue is empty, false otherwise.
     */
    boolean isEmpty();

    /** Get the number of elements in the queue.
     *  @return the numbers
     */
    int size();

    /** Dump the contents of the priority queue.
     */
    void clear();

    /** Initialize a priority queue from a container of values.
     *  @param values the collection of starting values
     */
    void init(Collection<T> values);

}
