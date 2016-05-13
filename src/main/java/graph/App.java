package graph;

public class App {
    public static void main(String[] args) {
        Graph graph = new Graph();
        
        Vertex a = new Vertex();
        Vertex b = new Vertex();
        Vertex c = new Vertex();
        Vertex d = new Vertex();
        Vertex e = new Vertex();
        Vertex f = new Vertex();
        Vertex g = new Vertex();
        
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        graph.addVertex(g);
        
        graph.addEdge(new Edge(a ,b));
        graph.addEdge(new Edge(a ,g));
        graph.addEdge(new Edge(a ,c));
        
        graph.addEdge(new Edge(b ,c));
        graph.addEdge(new Edge(b ,d));
        graph.addEdge(new Edge(b ,f));
        
        graph.addEdge(new Edge(c ,d));
        graph.addEdge(new Edge(c ,g));
        
        graph.addEdge(new Edge(d ,e));
        graph.addEdge(new Edge(d ,g));
        
        graph.addEdge(new Edge(e ,f));
        graph.addEdge(new Edge(e ,g));
    }
}
