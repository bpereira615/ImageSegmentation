/**
 * Lydia Carroll, JHED: lcarro12
 * Ben Periera, JHED: bhoertn1
 * Ryan Walter, rwalte25
 *
 * Data Structures 
 * EN.600.226(01) 
 * Project 4, Part C
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Performs image segmentation.
 * Run from command line with image name and desire kvalue.
 * We have found kvalue = 100 to be very effective.
 */
public class P4C {
    /** Number of primary colors, three. */
    static final int THREE = 3;

    /** Optimal number for pixel ratio. */
    static final int THOU = 1000;

    /** Empty constructor for checkstyle. */
    public P4C() {
        // don't know how to get rid of this error
    }

    /** 
     * Return the maximum different in red, green and blue values.
     * @param  info stores max and min info for each color
     * @return      array of ints [r g b]
     */
    static int[] diff(StoreInfo info) {
        int[] diffs = new int[THREE];
        // find differences between maxes and mins
        diffs[0] = info.getMaxR() - info.getMinR();
        diffs[1] = info.getMaxG() - info.getMinG();
        diffs[2] = info.getMaxB() - info.getMinB();
        
        return diffs;
    }

    /** Convert an image to a graph of Pixels with edges between
     *  north, south, east and west neighboring pixels.
     *  @param image the image to convert
     *  @param pd the distance object for pixels
     *  @return the graph that was created
     */
    static WGraph<Pixel> imageToGraph(BufferedImage image, Distance<Pixel> pd) {

        WGraphP4<Pixel> graph = new WGraphP4<Pixel>();

        

        int y = image.getHeight();
        int x = image.getWidth();
        ArrayList<ArrayList<GVertex<Pixel>>> grid = 
            new ArrayList<ArrayList<GVertex<Pixel>>>(); 

        int rgb;
        //make gird of pixels
        for (int i = 0; i < y; i++) {
            ArrayList<GVertex<Pixel>> row = new ArrayList<GVertex<Pixel>>();
            for (int j = 0; j < x; j++) {

                rgb = image.getRGB(j, i);
                Pixel pix = new Pixel(j, i, rgb);
                GVertex<Pixel> ver = new GVertex<>(pix, graph.nextID());
                row.add(ver);
            }
            grid.add(row);
        }


        //add edges
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                GVertex<Pixel> curr = grid.get(i).get(j);

                //edge cases 
                if (i == 0 && j == 0) {
                    GVertex<Pixel> lower = grid.get(i + 1).get(j);
                    GVertex<Pixel> right = grid.get(i).get(j + 1);

                    graph.addEdge(curr, lower, 
                        pd.distance(curr.data(), lower.data()));
                    graph.addEdge(curr, right, 
                        pd.distance(curr.data(), right.data()));
                } else if (i == y - 1 && j == x - 1) {
                    GVertex<Pixel> upper = grid.get(i - 1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j - 1);
                    graph.addEdge(curr, upper, 
                        pd.distance(curr.data(), upper.data()));
                    graph.addEdge(curr, left, 
                        pd.distance(curr.data(), left.data()));
                } else if (i == 0 && j == x - 1) {
                    GVertex<Pixel> lower = grid.get(i + 1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j - 1);
                    graph.addEdge(curr, lower, 
                        pd.distance(curr.data(), lower.data()));
                    graph.addEdge(curr, left, 
                        pd.distance(curr.data(), left.data()));
                } else if (i == y - 1 && j == 0) {
                    GVertex<Pixel> upper = grid.get(i - 1).get(j);
                    GVertex<Pixel> right = grid.get(i).get(j + 1);
                    graph.addEdge(curr, upper, 
                        pd.distance(curr.data(), upper.data()));
                    graph.addEdge(curr, right, 
                        pd.distance(curr.data(), right.data()));
                } else if (i == 0) {
                    GVertex<Pixel> left = grid.get(i).get(j - 1);
                    GVertex<Pixel> right = grid.get(i).get(j + 1);
                    GVertex<Pixel> lower = grid.get(i + 1).get(j);
                    graph.addEdge(curr, left, 
                        pd.distance(curr.data(), left.data()));
                    graph.addEdge(curr, right, 
                        pd.distance(curr.data(), right.data()));
                    graph.addEdge(curr, lower, 
                        pd.distance(curr.data(), lower.data()));
                } else if (j == 0) {
                    GVertex<Pixel> upper = grid.get(i - 1).get(j);
                    GVertex<Pixel> right = grid.get(i).get(j + 1);
                    GVertex<Pixel> lower = grid.get(i + 1).get(j);
                    graph.addEdge(curr, upper, 
                        pd.distance(curr.data(), upper.data()));
                    graph.addEdge(curr, right, 
                        pd.distance(curr.data(), right.data()));
                    graph.addEdge(curr, lower, 
                        pd.distance(curr.data(), lower.data()));
                } else if (i == y - 1) {
                    GVertex<Pixel> upper = grid.get(i - 1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j - 1);
                    GVertex<Pixel> right = grid.get(i).get(j + 1);
                    graph.addEdge(curr, upper, 
                        pd.distance(curr.data(), upper.data()));
                    graph.addEdge(curr, left, 
                        pd.distance(curr.data(), left.data()));
                    graph.addEdge(curr, right, 
                        pd.distance(curr.data(), right.data()));
                } else if (j == x - 1) {
                    GVertex<Pixel> upper = grid.get(i - 1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j - 1);
                    GVertex<Pixel> lower = grid.get(i + 1).get(j);
                    graph.addEdge(curr, upper, 
                        pd.distance(curr.data(), upper.data()));
                    graph.addEdge(curr, left, 
                        pd.distance(curr.data(), left.data()));
                    graph.addEdge(curr, lower, 
                        pd.distance(curr.data(), lower.data()));
                } else {
                    //general case

                    GVertex<Pixel> upper = grid.get(i - 1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j - 1);
                    GVertex<Pixel> right = grid.get(i).get(j + 1);
                    GVertex<Pixel> lower = grid.get(i + 1).get(j);

                    graph.addEdge(curr, upper, 
                        pd.distance(curr.data(), upper.data()));
                    graph.addEdge(curr, left, 
                        pd.distance(curr.data(), left.data()));
                    graph.addEdge(curr, right, 
                        pd.distance(curr.data(), right.data()));
                    graph.addEdge(curr, lower, 
                        pd.distance(curr.data(), lower.data()));

                }
            }
        }
    
    

        


        return graph;
    }


    /** Return a list of edges in a minimum spanning forest by
     *  implementing Kruskal's algorithm using fast union/finds.
     *  @param g the graph to segment
     *  @param kvalue the value to use for k in the merge test
     *  @return a list of the edges in the minimum spanning forest
     */

    static List<WEdge<Pixel>> segmenter(WGraph<Pixel> g, double kvalue) {
        System.out.println("Running segmenter...");
        // create list of output edges == minimum spanning tree
        ArrayList<WEdge<Pixel>> mst = new ArrayList<WEdge<Pixel>>();
        // empty graph case
        if (g.numVerts() == 0) {
            return mst;
        }
        // unconnected graph case
        if (g.numEdges() == 0) {
            return mst;
        }
        // renumber all vertices in graph
        int i = 0;
        for (GVertex<Pixel> curr : g.allVertices()) {
            curr.setId(i);
            i++;
        }
        // create a partition to track unions
        Partition p = new Partition(g.allVertices().size());
        // create a priority queue
        
        //PriorityQueue<WEdge<Pixel>> Q = new PriorityQueue<>(g.numEdges());
        PQHeap<WEdge<Pixel>> q = new PQHeap<WEdge<Pixel>>();
        // fill priority heap with edges
        for (WEdge<Pixel> e : g.allEdges()) {
            q.insert(e);
        }
        // initialize list of lists to track vertex partitions
        ArrayList<StoreInfo> megaList = new ArrayList<>(g.numVerts());
        for (i = 0; i < g.numVerts(); i++) {
            megaList.add(new StoreInfo());
        }

        
        // while PQ not empty
        while (!q.isEmpty()) {
            //System.out.println(Q.size());
            WEdge<Pixel> currE = q.remove();
            GVertex<Pixel> u = currE.source();
            GVertex<Pixel> v = currE.end();
            // get clouds that u and v are in
            int u1 = p.find(u.id());
            //System.out.println("u1: " + u1);
            int v1 = p.find(v.id());
            // if u1 = v1, done
            // else, find vertices with given IDs
            // constant time
            if (u1 != v1) {
                // updata u's in megaList
                StoreInfo currU = megaList.get(u1);
                currU.setMaxR(Math.max(currU.getMaxR(), u.getData().r()));
                currU.setMaxG(Math.max(currU.getMaxG(), u.getData().g()));
                currU.setMaxB(Math.max(currU.getMaxB(), u.getData().b()));
                currU.setMinR(Math.min(currU.getMinR(), u.getData().r()));
                currU.setMinG(Math.min(currU.getMinG(), u.getData().g()));
                currU.setMinB(Math.min(currU.getMinB(), u.getData().b()));
                currU.addVert();
                // update v's
                StoreInfo currV = megaList.get(v1);
                currV.setMaxR(Math.max(currV.getMaxR(), v.getData().r()));
                currV.setMaxG(Math.max(currV.getMaxG(), v.getData().g()));
                currV.setMaxB(Math.max(currV.getMaxB(), v.getData().b()));
                currV.setMinR(Math.min(currV.getMinR(), v.getData().r()));
                currV.setMinG(Math.min(currV.getMinG(), v.getData().g()));
                currV.setMinB(Math.min(currV.getMinB(), v.getData().b()));
                currV.addVert();

                // create a union of lists U and V to check
                StoreInfo uv = new StoreInfo();
                uv.setMaxR(Math.max(currU.getMaxR(), currV.getMaxR()));
                uv.setMaxG(Math.max(currU.getMaxG(), currV.getMaxG()));
                uv.setMaxB(Math.max(currU.getMaxB(), currV.getMaxB()));
                uv.setMinR(Math.min(currU.getMinR(), currV.getMinR()));
                uv.setMinG(Math.min(currU.getMinG(), currV.getMinG()));
                uv.setMinB(Math.min(currU.getMinB(), currV.getMinB()));
        
                // get differences for each list
                int[] diffUV = diff(uv);
                int[] diffU = diff(currU);
                int[] diffV = diff(currV);

                // decide if lists pass joining conditions
                boolean[] join = new boolean[THREE]; // initializes false
                for (i = 0; i < THREE; i++) {
                    // if pass joining conditions
                    if (diffUV[i] <= Math.min(diffU[i], diffV[i]) 
                        + (kvalue / (currU.getVerts() + currV.getVerts()))) {
                        join[i] = true;
                    }
                }
                 // add edge to spanning tree
                if (join[0] && join[1] && join[2]) {
                    mst.add(currE);
                    // union partitions
                    p.union(u.id(), v.id());
                    megaList.set(u1, uv);
                    megaList.set(v1, uv);
                }
            }
        }
            // check if u,v in same partition. if so, done. if not, cont DONE
            // find(u) --> u1, find(v) --> v1 DONE
            // find lists with u1, v1 DONE
            // check joining criteria (formula from Selinski)
            // if pass joining criteria, union, add edge to mst
            
        return mst;
    }
    /**
     * Creates an output image.
     * @param pixels   Vertices found by depth-first search.
     * @param image    File to write into
     * @param filename Name of output file
     * @param num      Tracks sequential output number
     */
    public static void writeImage(List<GVertex<Pixel>> pixels, 
        BufferedImage image, String filename, int num) {

        final int gray = 0x0DCDCDC;

        // make a background image to put a segment into
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                image.setRGB(j, i, gray);
            }
        }



        for (GVertex<Pixel> p : pixels) {
            Pixel d = p.data();
            image.setRGB(d.row(), d.col(), d.value());
        }

        try {
            File f = new File(filename + num + ".png");
            ImageIO.write(image, "png", f);
        } catch (Exception e) {
            System.out.println("IMAGE WRITE");
        }
        // You'll need to do that for each connected component,
        // writing each one to a different file, clearing the
        // image buffer first
    }
    /**
     * Segment an image by color.
     * Works for rounded edges!
     * @param args command line, file name and kvalue for Kruskals's.
     */
    public static void main(String[] args) {


        try {
          // the line that reads the image file
            File file = new File(args[0]);
            BufferedImage image = ImageIO.read(file);
            WGraph<Pixel> g = imageToGraph(image, new PixelDistance());
            
            List<WEdge<Pixel>> res = segmenter(g, Double.parseDouble(args[1]));
            System.out.println("Segmenter complete, generating image...");


            // After you have a spanning tree connected component x, 
            // you can generate an output image like this:



            WGraphP4<Pixel> subgraph = new WGraphP4<Pixel>();

            List<WEdge<Pixel>> reset = new LinkedList<WEdge<Pixel>>();
            for (WEdge<Pixel> w : res) {
                w.end().reset();
                w.source().reset();
            }

            for (WEdge<Pixel> w : res) {
                //reset connectedness of vertices
                subgraph.addEdge(w);
            }


            //depth first portion
            //reset the visited flags for all vertices
            for (GVertex<Pixel> ver : subgraph.allVertices()) {
                ver.clearVisited();
            }

            int i = 0;
            for (WEdge<Pixel> w : res)  {
                List<GVertex<Pixel>> vertexList;

                if (!w.end().isVisited()) {
                    vertexList = subgraph.depthFirstSegmenter(w.end());
                    if (vertexList.size() >= subgraph.numVerts() / THOU) {
                        writeImage(vertexList,  image, file.getName(), i);
                        i++;
                    }
                    
                }

                if (!w.source().isVisited()) {
                    vertexList = subgraph.depthFirstSegmenter(w.source());
                    if (vertexList.size() >= subgraph.numVerts() / THOU) {
                        writeImage(vertexList,  image, file.getName(), i);
                        i++;
                    }
                }
            }



        } catch (IOException e) {
            System.out.print("Missing File!\n");

            // log the exception
            // re-throw if desired
        }

    }

}
