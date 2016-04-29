import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.LinkedList;


public class P4C {


    static int [] Diff(ArrayList<GVertex<Pixel>> list) {
        // initialize maxes and mins
        int maxR = 0; // less than expected max
        int maxG = 0;
        int maxB = 0;
        int minR = 1000; // larger than expected min
        int minG = 1000;
        int minB = 1000;
        // initialize differences array
        int[] diffs = new int[3];
        for (GVertex<Pixel> vert : list) {
            Pixel pix = vert.getData();
            // check if any are new maxes
            if (pix.r() > maxR){
                maxR = pix.r();
            }
            if (pix.g() > maxG) {
                maxG = pix.g();
            }
            if (pix.b() > maxB) {
                maxB = pix.b();
            }
            // check if any new mins
           if (pix.r() < minR) {
                minR = pix.r();
           }
           if (pix.g() < minG) {
                minG = pix.g();
           }
           if (pix.b() < minB) {
                minB = pix.b();
           }
        }
        // find differences between maxes and mins
        diffs[0] = maxR - minR;
        diffs[1] = maxB - minB;
        diffs[2] = maxG - minG;
        
        return diffs;
    }

    /** Convert an image to a graph of Pixels with edges between
     *  north, south, east and west neighboring pixels.
     *  @param image the image to convert
     *  @param pd the distance object for pixels
     *  @return the graph that was created
     */
    static WGraph<Pixel> imageToGraph(BufferedImage image, PixelDistance pd) {

        WGraphP4<Pixel> graph = new WGraphP4<Pixel>();

        

        int y = image.getHeight();
        int x = image.getWidth();
        ArrayList<ArrayList<GVertex<Pixel>>> grid = new ArrayList<ArrayList<GVertex<Pixel>>>(); 

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



        System.out.println("vertices: " + graph.numVerts() + "\tedges: " + graph.numEdges());
    



        //add edges
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                GVertex<Pixel> curr = grid.get(i).get(j);

                //edge cases 
                if (i == 0 && j == 0) {
                    GVertex<Pixel> lower = grid.get(i+1).get(j);
                    GVertex<Pixel> right = grid.get(i).get(j+1);

                    graph.addEdge(curr, lower, pd.distance(curr.data(),lower.data()));
                    graph.addEdge(curr, right, pd.distance(curr.data(),right.data()));
                } else if (i == y-1 && j == x-1) {
                    GVertex<Pixel> upper = grid.get(i-1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j-1);
    
                    graph.addEdge(curr, upper, pd.distance(curr.data(),upper.data()));
                    graph.addEdge(curr, left, pd.distance(curr.data(),left.data()));
                } else if (i == 0 && j == x-1) {
                    GVertex<Pixel> lower = grid.get(i+1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j-1);

                    graph.addEdge(curr, lower, pd.distance(curr.data(),lower.data()));
                    graph.addEdge(curr, left, pd.distance(curr.data(),left.data()));
                } else if (i == y-1 && j == 0) {
                    GVertex<Pixel> upper = grid.get(i-1).get(j);
                    GVertex<Pixel> right = grid.get(i).get(j+1);

                    graph.addEdge(curr, upper, pd.distance(curr.data(),upper.data()));
                    graph.addEdge(curr, right, pd.distance(curr.data(),right.data()));
                } else if (i == 0) {
                    

                    GVertex<Pixel> left = grid.get(i).get(j-1);
                    GVertex<Pixel> right = grid.get(i).get(j+1);
                    GVertex<Pixel> lower = grid.get(i+1).get(j);

                    graph.addEdge(curr, left, pd.distance(curr.data(),left.data()));
                    graph.addEdge(curr, right, pd.distance(curr.data(),right.data()));
                    graph.addEdge(curr, lower, pd.distance(curr.data(),lower.data()));
                } else if (j == 0) {
                    GVertex<Pixel> upper = grid.get(i-1).get(j);
                    GVertex<Pixel> right = grid.get(i).get(j+1);
                    GVertex<Pixel> lower = grid.get(i+1).get(j);

                    graph.addEdge(curr, upper, pd.distance(curr.data(),upper.data()));
                    graph.addEdge(curr, right, pd.distance(curr.data(),right.data()));
                    graph.addEdge(curr, lower, pd.distance(curr.data(),lower.data()));
                } else if (i == y-1) {
                    GVertex<Pixel> upper = grid.get(i-1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j-1);
                    GVertex<Pixel> right = grid.get(i).get(j+1);

                    graph.addEdge(curr, upper, pd.distance(curr.data(),upper.data()));
                    graph.addEdge(curr, left, pd.distance(curr.data(),left.data()));
                    graph.addEdge(curr, right, pd.distance(curr.data(),right.data()));
                } else if (j == x-1) {
                    GVertex<Pixel> upper = grid.get(i-1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j-1);
                    GVertex<Pixel> lower = grid.get(i+1).get(j);

                    graph.addEdge(curr, upper, pd.distance(curr.data(),upper.data()));
                    graph.addEdge(curr, left, pd.distance(curr.data(),left.data()));
                    graph.addEdge(curr, lower, pd.distance(curr.data(),lower.data()));
                } else {
                    //general case

                    GVertex<Pixel> upper = grid.get(i-1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j-1);
                    GVertex<Pixel> right = grid.get(i).get(j+1);
                    GVertex<Pixel> lower = grid.get(i+1).get(j);

                    graph.addEdge(curr, upper, pd.distance(curr.data(),upper.data()));
                    graph.addEdge(curr, left, pd.distance(curr.data(),left.data()));
                    graph.addEdge(curr, right, pd.distance(curr.data(),right.data()));
                    graph.addEdge(curr, lower, pd.distance(curr.data(),lower.data()));

                }
            }
        }
    
        System.out.println("vertices: " + graph.numVerts() + "\tedges: " + graph.numEdges());

        System.out.println(graph.depthFirst(graph.kruskals().get(0).source()).size());

        //TODO: Distance<Pixel> confusion as second parameter
        return graph;
    }


    /** Return a list of edges in a minimum spanning forest by
     *  implementing Kruskal's algorithm using fast union/finds.
     *  @param g the graph to segment
     *  @param kvalue the value to use for k in the merge test
     *  @return a list of the edges in the minimum spanning forest
     */

    static List<WEdge<Pixel>> segmenter(WGraph<Pixel> g, double kvalue) {
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
        Partition P = new Partition(g.allVertices().size());
        // create a priority queue
        // TODO: implement Ryan's PQHeap
        PriorityQueue<WEdge<Pixel>> Q = new PriorityQueue<>(g.numEdges());
        // fill priority heap with edges
        for (WEdge<Pixel> e : g.allEdges()) {
            Q.add(e);
        }
        // initialize list of lists to track vertex partitions
        ArrayList<ArrayList<GVertex<Pixel>>> megaList = new ArrayList<>();
        // details: need mega list of lists<vertex<pixel>>
        //TODO: how to quickly find which list contains u1/v1?
        
        // while PQ not empty
        while (!Q.isEmpty()) {
            WEdge<Pixel> currE = Q.poll();
            GVertex<Pixel> u = currE.source();
            GVertex<Pixel> v = currE.end();
            // get clouds that u and v are in
            int u1 = P.find(u.id());
            int v1 = P.find(v.id());
            // if u1 = v1, done
            // else, find vertices with given IDs
            // TODO: can we make this quicker? quadratic time
            if (u1 != v1) {
                // iterate through mega-lists 
                int megaIndexU = -1;
                int megaIndexV = -1;
                i = 0; // initialize counter
                for (ArrayList<GVertex<Pixel>> list : megaList) {
                    // iterate through sublist
                    for (GVertex<Pixel> elem : list) {
                        // if elem is u1, report
                        if (elem.id() == u1) {
                            megaIndexU = i;
                            list.add(u);
                        }
                        // if elem is v1, report
                        if (elem.id() == v1) {
                            megaIndexV = i;
                            list.add(v);
                        }
                    }
                    i++;
                }
                // decide where to put u and v
                // if u1 not found, new list
                if (megaIndexU == -1) {
                    ArrayList<GVertex<Pixel>> newList = new ArrayList<>();
                    newList.add(u);
                    megaList.add(i, newList);
                    megaIndexU = i;
                    i++; // increment counter
                }
                // if v1 not found, new list
                if (megaIndexV == -1) {
                    ArrayList<GVertex<Pixel>> newList = new ArrayList<>();
                    newList.add(v);
                    megaList.add(i, newList);
                    megaIndexV = i;
                    i++;
                }
                // now you know which lists contain u and v
                ArrayList<GVertex<Pixel>> listU = megaList.get(megaIndexU);
                ArrayList<GVertex<Pixel>> listV = megaList.get(megaIndexV);
                // create a union of lists U and V to check
                ArrayList<GVertex<Pixel>> listUV = new ArrayList<>();
                listUV.addAll(listU);
                listUV.addAll(listV);
                // get differences for each list
                int[] diffUV = Diff(listUV);
                int[] diffU = Diff(listU);
                int[] diffV = Diff(listV);
                // decide if lists pass joining conditions
                boolean[] join = new boolean[3]; // initializes false
                for (i=0; i<3; i++) {
                    // if pass joining conditions
                    if (diffUV[i] <= Math.min(diffU[i], diffV[i]) + 
                        (kvalue/(listU.size() + listV.size()))) {
                        // add edge to spanning tree
                        mst.add(currE);
                        // union partitions
                        P.union(u.id(), v.id());
                        // union lists
                        // TODO: add smaller list to longer list?
                        megaList.remove(listU);
                        megaList.remove(listV);
                        megaList.add(listUV);
                    }
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


    public static void writeImage(List<GVertex<Pixel>> pixels, BufferedImage image, String filename, int num) {

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
            //TODO: proper file naming
            File f = new File(filename + num + ".png");
            ImageIO.write(image, "png", f);
        } catch(Exception e) {
            System.out.println("IMAGE WRITE");
        }
        // You'll need to do that for each connected component,
        // writing each one to a different file, clearing the
        // image buffer first
    }

    public static void main(String[] args) {

        

        try {
          // the line that reads the image file
            File file = new File(args[0]);
            BufferedImage image = ImageIO.read(file);
            WGraph<Pixel> g = imageToGraph(image, new PixelDistance());
            
            //List<WEdge<Pixel>> res = segmenter(g, Double.parseDouble(args[1]));
            //TODO: try with 
            List<WEdge<Pixel>> res = g.kruskals();


            System.out.print("result =  " + res.size() + "\n");
            System.out.print("NSegments =  "
                             + (g.numVerts() - res.size()) + "\n");


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
            

            System.out.println(subgraph.numVerts() + " " + subgraph.numEdges());

            //System.out.println(subgraph.depthFirst(subgraph.kruskals().get(0).source()).size());






            


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
                    writeImage(vertexList,  image, file.getName(), i);
                    i++;
                }

                if (!w.source().isVisited()) {
                    vertexList = subgraph.depthFirstSegmenter(w.source());
                    writeImage(vertexList,  image, file.getName(), i);
                    i++;
                }
            }



        } catch (IOException e) {
            System.out.print("Missing File!\n");

            // log the exception
            // re-throw if desired
        }

    }

}
