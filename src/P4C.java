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


public class P4C {


    static int [] Diff(ArrayList<GVertex<Pixel>> list) {
        
        return null;
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
        // how to quickly find which list contains u1/v1?
        
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
                // decide if those lists pass joining conditions
            }
        }
            // check if u,v in same partition. if so, done. if not, cont DONE
            // find(u) --> u1, find(v) --> v1 DONE
            // find lists with u1, v1 DONE
            // check joining criteria (formula from Selinski)
            // if pass joining criteria, union, add edge to mst
            
        return mst;
    }

    public static void main(String[] args) {

        final int gray = 0x202020;

        try {
          // the line that reads the image file

            BufferedImage image = ImageIO.read(new File(args[0]));
            WGraph<Pixel> g = imageToGraph(image, new PixelDistance());

            




            /*
            List<WEdge<Pixel>> res = segmenter(g, Double.parseDouble(args[1]));

            System.out.print("result =  " + res.size() + "\n");
            System.out.print("NSegments =  "
                             + (g.numVerts() - res.size()) + "\n");

            // make a background image to put a segment into
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    image.setRGB(j, i, gray);
                }
            }

            // After you have a spanning tree connected component x, 
            // you can generate an output image like this:
            List<GVertex<Pixel>> x = null;
            for (GVertex<Pixel> i: x)  {
                Pixel d = i.data();
                image.setRGB(d.col(), d.row(), d.value());
            }

            File f = new File("output.png");
            ImageIO.write(image, "png", f);

            // You'll need to do that for each connected component,
            // writing each one to a different file, clearing the
            // image buffer first
   */
        } catch (IOException e) {
            System.out.print("Missing File!\n");

            // log the exception
            // re-throw if desired
        }
     



    }

}
