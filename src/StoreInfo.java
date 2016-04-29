public class StoreInfo {

    /** Starting vertex of an edge. */
    private int maxR;
    private int maxG;
    private int maxB;

    private int minR;
    private int minG;
    private int minB;

    private int numVerts;

    public StoreInfo() {
        this.maxR = -1;
        this.minR = 1000;

        this.maxG = -1;
        this.minG = 1000;

        this.maxB = -1;
        this.minB = 1000;

        this.numVerts = 0;
    }

    public StoreInfo(int upR, int downR, int upG, int downG, int upB, int downB) {
        this.maxR = upR;
        this.minR = downR;

        this.maxG = upG;
        this.minG = downG;

        this.maxB = upB;
        this.minB = downB;

        this.numVerts = 1;
    }

    public void setMaxR(int r) {
        this.maxR = r;
    }

    public void setMinR(int r) {
        this.minR = r;
    }

    public void setMaxG(int g) {
        this.maxG = g;
    }

    public void setMinG(int g) {
        this.minG = g;
    }

    public void setMaxB(int b) {
        this.maxB = b;
    }

    public void setMinB(int b) {
        this.minB = b;
    }

    public int getMaxR() {
        return this.maxR;
    }

    public int getMinR() {
        return this.minR;
    }

    public int getMaxG() {
        return this.maxG;
    }

    public int getMinG() {
        return this.minG;
    }

    public int getMaxB() {
        return this.maxB;
    }

    public int getMinB() {
        return this.minB;
    }

    public void addVert() {
        this.numVerts++;
    }

    public int getVerts() {
        return this.numVerts;
    }
}