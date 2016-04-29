public class partitionData {

    /** Starting vertex of an edge. */
    public int maxR;
    public int maxG;
    public int maxB;

    public int minR;
    public int minG;
    public int minB;

    private int numVerts;

    public partitionData(int upR, int downR, int upG, int downG, int upB, int downB) {
        this.maxR = upR;
        this.minR = downR;

        this.maxG = upG;
        this.minG = downG;

        this.maxB = upB;
        this.minB = downB;

        this.numVerts = 1;
    }

    public void addVert() {
        this.numVerts++;
    }

    public int getVerts() {
        return this.numVerts;
    }
}