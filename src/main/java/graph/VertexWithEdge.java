package graph;

public class VertexWithEdge {
    
    public VertexWithEdge(Vertex v) {
        setVertex(v);
    }
    
    public VertexWithEdge(Vertex v, Edge e) {
        this.setVertex(v);
        this.setEdge(e);
    }
    
    private Vertex vertex;
    private Edge edge;
    public Vertex getVertex() {
        return vertex;
    }
    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }
    public Edge getEdge() {
        return edge;
    }
    public void setEdge(Edge edge) {
        this.edge = edge;
    }
    
    public String toString() {
        return "(" + vertex + " => " + edge + ")";
    }
}
