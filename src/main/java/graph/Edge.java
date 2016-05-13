package graph;

public class Edge {
    
    private Vertex v1;
    private Vertex v2;
    private double value;

    public Edge(Vertex v1, Vertex v2) {
        this(v1, v2, 1);
    }

    public Edge(Vertex v1, Vertex v2, double value) {
        this.v1 = v1;
        this.v2 = v2;
        this.value = value;
    }
}
