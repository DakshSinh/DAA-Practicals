import java.util.*;

// Class to represent a graph
class Graph {
  private int V; // No. of vertices
  private LinkedList<Integer> adj[]; // Adjacency List

  // Constructor
  @SuppressWarnings({ "rawtypes", "unchecked" })
  Graph(int v) {
    V = v;
    adj = new LinkedList[v];
    for (int i = 0; i < v; ++i)
      adj[i] = new LinkedList();
  }

  // Function to add an edge into the graph
  void addEdge(int v, int w) {
    adj[v].add(w);
  }

  // Depth First Search traversal from a given source to a destination
  void DFS(int source, int destination) {
    boolean visited[] = new boolean[V];
    ArrayList<Integer> route = new ArrayList<>();
    DFSUtil(source, destination, visited, route);
    printRoute(route);
  }

  // A recursive function to perform DFS traversal
  private boolean DFSUtil(int v, int destination, boolean visited[], ArrayList<Integer> route) {
    visited[v] = true;
    route.add(v);

    if (v == destination)
      return true;

    for (Integer i : adj[v]) {
      if (!visited[i]) {
        if (DFSUtil(i, destination, visited, route))
          return true;
      }
    }

    // If destination not reachable from the current vertex, remove it from route
    route.remove(route.size() - 1);
    return false;
  }

  // Breadth First Search traversal from a given source to a destination
  void BFS(int source, int destination) {
    boolean visited[] = new boolean[V];
    ArrayList<Integer> route = new ArrayList<>();
    LinkedList<Integer> queue = new LinkedList<>();

    visited[source] = true;
    queue.add(source);

    while (queue.size() != 0) {
      source = queue.poll();
      route.add(source);

      if (source == destination) {
        printRoute(route);
        return;
      }

      Iterator<Integer> i = adj[source].listIterator();
      while (i.hasNext()) {
        int n = i.next();
        if (!visited[n]) {
          visited[n] = true;
          queue.add(n);
        }
      }
    }
  }

  // Utility function to print the route
  private void printRoute(ArrayList<Integer> route) {
    System.out.print("Route: ");
    for (Integer node : route) {
      System.out.print(node + " ");
    }
    System.out.println();
  }
}

// Main class
public class GraphTraversal {
  public static void main(String args[]) {
    Graph graph = new Graph(6);

    // Adding edges to the graph
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 3);
    graph.addEdge(2, 3);
    graph.addEdge(2, 4);
    graph.addEdge(3, 5);
    graph.addEdge(4, 5);

    int source = 0;
    int destination = 5;

    System.out.println("DFS:");
    graph.DFS(source, destination);

    System.out.println("\nBFS:");
    graph.BFS(source, destination);
  }
}
