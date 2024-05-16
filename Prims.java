import java.util.*;

public class Prims {

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

    List<Edge> mst = primsAlgorithm(graph, 4);

    System.out.println("Minimum Spanning Tree Edges:");
    int totalWeight = 0;
    for (Edge edge : mst) {
      System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
      totalWeight += edge.weight;
    }
    System.out.println("Total weight of the MST: " + totalWeight);
  }

  public static List<Edge> primsAlgorithm(List<Edge> graph, int numNodes) {
    List<Edge> mst = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();
    PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));

    visited.add(0);
    for (Edge edge : graph) {
      if (edge.src == 0) {
        minHeap.offer(edge);
      }
    }

    while (visited.size() < numNodes && !minHeap.isEmpty()) {
      Edge minEdge = minHeap.poll();

      if (!visited.contains(minEdge.dest)) {
        mst.add(minEdge);
        visited.add(minEdge.dest);
        for (Edge edge : graph) {
          if (edge.src == minEdge.dest && !visited.contains(edge.dest)) {
            minHeap.offer(edge);
          }
        }
      }
    }
    return mst;
  }
}

/*
 * Prim's Algorithm for Minimum Spanning Tree (MST)
 * 
 * Input: Graph G = (V, E) with vertices V and edges E, where each edge has a
 * weight/cost
 * Output: Minimum Spanning Tree (MST) of G
 * 
 * 1. Initialize an empty list to store the edges of the MST: MST = []
 * 2. Initialize an empty set to keep track of visited nodes: visited = {}
 * 3. Initialize a priority queue (min-heap) to store edges based on their
 * weights: minHeap = PriorityQueue()
 * 4. Choose an arbitrary starting node s and mark it as visited: visited.add(s)
 * 5. Add all edges incident to s to the priority queue: For each edge (s, v) in
 * E, add v to minHeap
 * 6. While the MST is not complete (visited.size() < numNodes) and minHeap is
 * not empty:
 * a. Extract the minimum-weight edge (u, v) from minHeap
 * b. If v is not visited:
 * - Add edge (u, v) to MST
 * - Mark v as visited: visited.add(v)
 * - Add all edges incident to v that lead to unvisited nodes to minHeap
 * (For each edge (v, w) in E, if w is not visited, add w to minHeap)
 * 7. Return MST
 */