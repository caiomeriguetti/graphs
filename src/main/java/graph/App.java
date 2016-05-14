package graph;

public class App {
    public static void main(String[] args) throws Exception {
        Graph graph = new Graph();
        
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        graph.addVertex(g);
        
        graph.addEdge(new Edge(a, b, 1.0));
        graph.addEdge(new Edge(a, g, 20.0));
        graph.addEdge(new Edge(a, c, 20.0));
        
        graph.addEdge(new Edge(b, c, 1.0));
        graph.addEdge(new Edge(b, d, 20.0));
        graph.addEdge(new Edge(b, f, 20.0));
        
        graph.addEdge(new Edge(c, d, 1.0));
        graph.addEdge(new Edge(c, g, 1.0));
        
        graph.addEdge(new Edge(d, e, 1.0));
        graph.addEdge(new Edge(d, g, 20.0));
        
        graph.addEdge(new Edge(e, f, 20.0));
        graph.addEdge(new Edge(e, g, 1.0));
        
        DijkstraPathFinder dijkstra = new DijkstraPathFinder(graph, a);
        System.out.println(dijkstra.getPath(e));
    }
}
