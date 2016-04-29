/** Implementation of tree-based set partitions with fast union/find.
 *  Adapted from Shaffer/OpenDSA text.
 */
public class Partition {

    /** The size of the partition.  */
    private int size;

    /** The array holding the parents for each node.  */
    private int[] parent;

    /** The array holding the weights (size) of the tree for each node. */
    private int[] weight;

    /** Create a partition of singleton sets of the given size.
     *  @param num the starting size of the partition
     */
    public Partition(int num) {
        this.size = num;
        this.parent = new int[num];
        this.weight = new int[num];
        for (int i = 0; i < this.size; i++) {
            this.parent[i] = -1;
            this.weight[i] = 1;
        }
    }

    /** Weighted union of the sets containing two nodes, if different.
     *  @param a the first node
     *  @param b the second node
     */
    void union(int a, int b) {
        int root1 = this.find(a);     // Find root of node a
        int root2 = this.find(b);     // Find root of node b
        if (root1 != root2) {    // Merge with weighted union
            if (this.weight[root2] > this.weight[root1]) {
                this.parent[root1] = root2;
                this.weight[root2] += this.weight[root1];
            } else {
                this.parent[root2] = root1;
                this.weight[root1] += this.weight[root2];
            }
        }
    }

    /** Find the (root of the) set containing a node, with path compression.
     *  @param curr the node to find
     *  @return the root node
     */
    int find(int curr) {
        if (this.parent[curr] == -1) {
            return curr; // At root
        }
        this.parent[curr] = this.find(this.parent[curr]);
        return this.parent[curr];
    }
}
