***
    Lydia Carroll, Benjamin Hoertnagl-Pereira, Ryan Walter
    JHED: lcarro12, bhoertn1, rwalte25
    lcarro12 @jhu.edu, bhoertn1@jhu.edu, rwalte25@jhu.edu
    600.226.01 | CS226 Data Structures
    Project 4 - Image Segmentation


    Files:
    Distance.java 
    GVertex.java 
    P4C.java
    Partition.java
    Pixel.java
    PixelDistance.java
    PQHeap.java 
    QueueEmptyException.java
    StoreInfo.java 
    WEdge.java 
    WGraphP4.java 
    WGraphP4Test.java

    *--------------------------------------------------------------------------------*

    Command line needed to run program:
    java P4C [pic_file_name].png [k_value]

    Picture files passed to program should be of type png. Kvalue is a tuning variable and is chosen by user—-results will vary depending on K. Usually K ~100 works. Test4 picture as input (blue background, orange circles/ ovals) works beautifully at K=100.

    *--------------------------------------------------------------------------------*
    Discussion

    Our original graph implementation was fairly easy to integrate into Part C. Because we stored a list of neighboring vertices and incident edges in each vertex, it was easy to go from the list of edges returned by segementer() to a graph, then from a graph to a depth-first search. Our greatest challenge for this part was writing efficiently to reduce run time. We ultimately were able to progress from quadratic time to near-constant time by changing from storing lists of vertices in segmenter() to only storing the minimum, maximum and size of each partition. After this change our solution was slowest at the depth-first search, which we were not sure how to make faster. We tested k-values from 20 to 1000 and were happiest with a k-value of 100. It worked particularly well for the given input file test4.png and produced nicely rounded edges. Our function runs in under 7 seconds for the largest test file that we were given so we are happy with our time complexity.

***

# Overview: From Images to Graphs and Back Again

In this project, we have seen how to transform a problem into a graph representation, solve it as a graph, and then transform it back again. The starting point for this project is work by Pedro Felzenszwalb and Dan Huttenlocher on provable algorithms for image segmentation: http://cs.brown.edu/~pff/papers/seg-ijcv.pdf. The idea in this paper is to reduce image segmentation to the problem of producing a minimal spanning forest.

This paper has been used in many forms, including, for example, AR Tag recognition: https://april.eecs.umich.edu/pdfs/olson2011tags.pdf

This project solves 3 problems:

* Flesh out a graph implementation starting with an existing solution we will provide.
* Implement a minimally weighted spanning tree algorithm using Kruskal's Algorithm.
* Adapt this algorithm to image segmentation as we describe below and apply the segmentation algorithm to real images.

## Part A: Graph Implementation

Extend vertices to be able to store information using Generics. Currently they can store information, but the class uses Object. Call the new generic class file GVertex.java. All the methods in the existing Vertex.java class must be present in the generic version.
Extend the implementation to support weighted graphs by including a weight value (real number) on each edge. This requires upating the Edge class so that it is generic and includes weights. (You might also want to simplify by making it undirected only.) Call the new class file WEdge.java. Make this class implement the Comparable interface, comparing edges by their weights. (This can be useful when testing methods that return lists of edges - by sorting the lists it's easier to compare if the results are correct in the case of unique edge weights.) Also include a method "public double weight()" that returns the weight of an edge. Lastly, include edge weights in the WEdge toString. For example, (3, 5, 23.13) would be the string for an edge between vertices with ids 3 and 5 and edge weight 23.13.
Modify the graph implementation to use adjacency or edge incidence lists instead of an adjacency matrix. An adjacency list typically stores for each vertex a list of neighboring vertices. However, in the case of weighted graphs you would need to store the neighbors paired with the edge weights, or you could simply store the list of edges incident to each vertex. Think about what representation will give you the fastest running times and still be space efficient. (Note the text has an implementation which you can adapt.)
We have supplied a interface specification for the weighted graph class, WGraph.java. You must use this interface and name your implementation class file WGraphP4.java. Note that the interface includes two methods not in the adjacency matrix version. The first is a method to return all the edges incident to a particular vertex. The second is a method for Kruskal's algorithm to compute a minimum spanning tree/forest. You'll implement this in the next part.


In any case, you must create a WGraphTest.java JUnit file that fully tests the generic weighted version, your WGraphP4 implementation. Be sure to include a test for the new incidentEdges methods. You will also need to add tests for the kruskals minimum spanning tree method in Part B.

Discuss your process for implementing this part of the assignment, including which team members worked on which classes and tests, and why you chose that particular approach in your README file for this assignment.

## Part B: Minimally Weighted Spanning Trees

Using your graph representation, implement Kruskal's algorithm to find the minimum spanning tree or forest of a graph (depending on if the graph is connected or not, respectively). Note that this algorithm makes use of a priority queue of edges based on their weights as keys, and fast disjoint set union.

You must write a general purpose heap based PriorityQueue implementation that is instantiated with a Comparator for the objects. (Sorry, but since we gave you the Partition, you can't use the Java API for the priority queue.) We have provided an interface PriorityQueue.java that includes instructions for your implementation to have two constructors: one that relies on the "natural" ordering of the Comparable objects in the queue, and the other that takes a Comparator object which will then be used to compare values for a more general purpose approach. Your implementation must be heap based, and we recommend putting the root of the heap (the "best" element) in index 1 in the underlying ranked array representation, "wasting" the 0 position to simplify the parent and child operations. Your init implementation must be an O(N) bottom up build. Name your implementation class file PQHeap.java.

Your spanning tree method should be a member of your WGraphP4 implementation and return a List of WEdges, as specified by the "kruskals" method in the provided WGraph interface. In the case of a connected graph, the edges will form one tree. But for a disconnected graph, they will represent a forest of trees (connected components) instead.

You should implement all of the tests necessary to make sure your Kruskal's algorithm implementation works correctly. Include these in the WGraphTest JUnit file from part A. Also include an updated PQHeapTest.java JUnit file for your general purpose PQ implementation.

## Part C: Images as Graphs

You will now need to create a graph representation of an image. This involves the following processes:

Create a graph node representation for a pixel. To do this, create a class named Pixel to store pixel locations and values. Locations are row and column indices in an image, with [0,0] as the top left corner. The value will always be an integer, although you may find it useful to create methods to pull out specific bytes to get different components, e.g. color values. This class will be the base type for the generic parameter of your GVertex class when storing a pixel in a graph node.

For the purposes of this assignment, the neighborhood of a pixel are its right, left, up and down neighbors. So your graph should, in general, have vertices of degree 4 with the exception of those on the image edges which will be of degree 3, and the corners (4 of them) of degree 2.

You should now create a variation on Kruskal's algorithm that includes an additional condition on merges (partition unions) -- namely it checks the condition:

> Diff(A u B) <= Min(Diff(A), Diff(B)) + K/(|A| + |B|)

and only allows the merge if this condition is true. Diff(A) is a tuple which represents the difference between the minimum and maximum of each component (byte) of all the pixel values in the graph component A, |A| means the size (number of vertices) in A, and K is a constant explained below.

Note that K is a "tuning parameter." You should now write a new version of Kruskal called Segmenter. Segmenter is identical to Kruskal except that when you get to the point of merging two spanning components, if the condition is not satisfied, you do not do the merge. The result of this new algorithm will be a spanning forest represented as a list of edges. Each connected component of the forest will be a portion of the original image that has a consistent appearance.

You'll need to now find the connected components of the spanning forest, and create a collection of the pixels that comprise it. 

Apply your algorithm to our test images and write out the results, one connected component per file. Your main program should accept the input file to segment as a command line argument. 

Once you read the image and find the connected components, you should zero out all the pixels in an image of the same size as the input image (it could be the BufferedImage you started with), and then write back the values from all of the pixels from a single connected component. Now, you should write that image to a file. The files should be numbered sequentially up to the number of components you found. Use the input file name as a base. So, if you had three components and the input file was RGBImage.png, the output files would be called "RGBImage1.png", "RGBImage2.png" and "RGBImage3.png".

You will apply this to (EDIT - only one) RGB images. In this case, each pixel stores a red, green, and blue value as the first, second, and third bytes of the integer. Color values in each band range therefore from 0 to 255. The difference function has to keep track of the color range for each band, and the "Diff" condition above has to be true for all three bands.


***
    *--------------------------------------------------------------------------------*
    Part A

    Summary : Our greatest challenge for Part A was understanding the Java syntax to implement generics. Once this was understood we proceeded rapidly.

    GVertex.java - Lydia 
    There were early problems with generics and implementing comparable which were quickly resolved. Each instance of GVertex holds a holds a list of neighboring vertices as well as a list of incident edges. 

    GVertexTest.java - Ben and Lydia 
    It tests GVertex by comparing the results of its methods to the results of the Vertex methods.

    WEdge.java - Ryan and Ben
    Early problems with generics were resolved as Part B was written.

    WGraphP4.java - Ben, everything but Kruskal’s
    The given graph implementation using an adjacency matrix was modified to work with the adjacency lists present in GVertex. Adjacency lists were chosen because we thought they would be easiest to implement down the line despite the additional upfront bookkeeping. The graph was implemented to best support insertion operations of vertices and edges at the expense of making removal operations on these objects more expenseive. This design choice was made because our appication did not utilize removal operations, so we wanted to make sure the core operations used were as fast as possible.

    *--------------------------------------------------------------------------------*
    Part B

    PQHeap.java - Ryan
    This priority queue implementation uses the Priority Queue interface given in the assignment. Creating the Comparator constructor and default constructor was initially difficult and somewhat unclear. The PQHeap is represented via an ArrayList for a bottom-up construction. 

    PQHeapTest.java - Ryan
    Extensively tests the written PQHeap implementation. 

    WGraphP4.java - Lydia, Kruskal’s
    This algorithm returns a spanning forest of edges for an input graph. It was not difficult to implement thanks to the excellent slides given in class. 

    WGraphP4Test.java - Ben and Lydia
    We extensively tested our graph implementation before moving on to Part C. We first wrote our own tests then looked at the tests given by Prof. Selinski and added them.

    *--------------------------------------------------------------------------------*
    Part C

    Distance.java - N/A
    This interface was given for the color difference between pixels in the starter code.

    P4C.java
    - Lydia, segmenter, diff
    - Ben, readImage, writeImage, and depth-first search
    - Ryan, readImage, writeImage, and depth-first search
    Initially we were confused because the depth-first search of the segmented output was only showing 7 edges, but then we realized we were accidentally resetting all edges every call in the graph construction loop. Once we fixed this the main function ran and worked but was exceptionally slow. To speed it up, we switched from storing lists of vertices in segmenter to only storing the minimum, maximum and size of each partition. We then improved our output images by excluding any subtrees of less than 50 vertices. This eliminated the results of over-segmentation.

    Partition.java - N/A
    This class tracks which vertices are in the same cloud. It was given in the starter code.

    Pixel.java - Ben
    This class stores the color, row and column values of a pixel in an image.

    PixelDistance.java - Ben
    This implements the given Distance interface to find the color difference between pixels.

    QueueEmptyException.java - Ryan
    This exception is thrown by PQHeap if remove() is attempted when it is empty.

    StoreInfo.java - Ryan and Lydia
    This class holds the minimum, maximum, and number of vertices in a given partition. It is used to calculate the difference between partitions used in segmenter.

***
