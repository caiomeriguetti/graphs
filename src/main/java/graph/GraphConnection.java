package graph;

public class GraphConnection {
    
    public GraphConnection(Graph g1, Graph g2, Vertex v1, Vertex v2, Edge e) {
        this.setG1(g1);
        this.setG2(g2);
        this.setV1(v1);
        this.setV2(v2);
        this.setEdge(e);
    }
    
    public Graph getG1() {
        return g1;
    }
    public void setG1(Graph g1) {
        this.g1 = g1;
    }
    public Graph getG2() {
        return g2;
    }
    public void setG2(Graph g2) {
        this.g2 = g2;
    }
    public Vertex getV1() {
        return v1;
    }
    public void setV1(Vertex v1) {
        this.v1 = v1;
    }
    public Vertex getV2() {
        return v2;
    }
    public void setV2(Vertex v2) {
        this.v2 = v2;
    }
    public Edge getEdge() {
        return edge;
    }
    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    private Graph g1, g2;
    private Vertex v1, v2;
    private Edge edge;
    
}
