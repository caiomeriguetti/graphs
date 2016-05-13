package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;
    private Map<Vertex, List<Vertex>> nerighborhood;
    
    public Graph() {
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        nerighborhood = new HashMap<Vertex, List<Vertex>>();
    }
    
    public void addVertex(Vertex v) {
        vertices.add(v);
    }
    
    public void addEdge(Edge e) {
        edges.add(e);
    }
    
    public List<Vertex> getVertices() {
        return this.vertices;
    }
}
