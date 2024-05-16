import java.util.*;

public class Kruskals {

  static class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
      this.src = src;
      this.dest = dest;
      this.weight = weight;
    }
  }

  public static void main(String[] args) {
    List<Edge> graph = new ArrayList<>(Arrays.asList(
        new Edge(0, 1, 1),
        new Edge(0, 2, 2),
        new Edge(1, 2, 1),
        new Edge(1, 3, 1),
        new Edge(2, 3, 2)));

    List<Edge> mst = kruskalsAlgorithm(graph, 4);

    System.out.println("Minimum Spanning Tree Edges:");
    int totalWeight = 0;
    for (Edge edge : mst) {
      System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
      totalWeight += edge.weight;
    }
    System.out.println("Total weight of the MST: " + totalWeight);
  }

  public static List<Edge> kruskalsAlgorithm(List<Edge> graph, int numNodes) {
    List<Edge> mst = new ArrayList<>();
    Collections.sort(graph, Comparator.comparingInt(edge -> edge.weight));
    int[] parent = new int[numNodes];
    for (int i = 0; i < numNodes; i++) {
      parent[i] = i;
    }
    int edgeCount = 0;
    int index = 0;
    while (edgeCount < numNodes - 1 && index < graph.size()) {
      Edge edge = graph.get(index++);
      int srcParent = find(parent, edge.src);
      int destParent = find(parent, edge.dest);
      if (srcParent != destParent) {
        mst.add(edge);
        union(parent, srcParent, destParent);
        edgeCount++;
      }
    }
    return mst;
  }

  private static int find(int[] parent, int node) {
    if (parent[node] != node) {
      parent[node] = find(parent, parent[node]);
    }
    return parent[node];
  }

  private static void union(int[] parent, int x, int y) {
    int xRoot = find(parent, x);
    int yRoot = find(parent, y);
    parent[yRoot] = xRoot;
  }
}

/*
 * Kruskal's Algorithm for Minimum Spanning Tree (MST)
 * 
 * Input: Graph G = (V, E) with vertices V and edges E, where each edge has a
 * weight/cost
 * Output: Minimum Spanning Tree (MST) of G
 * 
 * 1. Sort all the edges in non-decreasing order of their weights.
 * 2. Initialize an empty list to store the edges of the MST: MST = []
 * 3. Initialize an array to keep track of the parent of each node in the graph:
 * parent[]
 * - Initially, each node is its own parent.
 * 4. Initialize a variable to count the number of edges added to the MST:
 * edgeCount = 0
 * 5. For each edge (u, v) in the sorted list of edges:
 * a. Find the parent of node u and node v.
 * b. If the parent of u is not the same as the parent of v (i.e., adding this
 * edge does not create a cycle):
 * - Add edge (u, v) to the MST.
 * - Merge the components containing u and v by updating their parent pointers.
 * - Increment edgeCount by 1.
 * c. If edgeCount equals the number of nodes minus 1 (i.e., MST is complete),
 * break the loop.
 * 6. Return MST
 */
