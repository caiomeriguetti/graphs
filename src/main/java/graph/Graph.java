package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;
    private Map<Vertex, List<Vertex>> neighborhood;
    
    public Graph() {
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        neighborhood = new HashMap<Vertex, List<Vertex>>();
    }
    
    public void addVertex(Vertex v) {
        vertices.add(v);
    }
    
    public void addEdge(Edge e) {
        edges.add(e);
        
        if (!neighborhood.keySet().contains(e.getV1())) {
            neighborhood.put(e.getV1(), new ArrayList<Vertex>());
        }
        
        if (!neighborhood.keySet().contains(e.getV2())) {
            neighborhood.put(e.getV2(), new ArrayList<Vertex>());
        }
        
        neighborhood.get(e.getV1()).add(e.getV2());
        neighborhood.get(e.getV2()).add(e.getV1());
    }
    
    public List<Vertex> getVertices() {
        return this.vertices;
    }
    
    public List<Vertex> getNeighbors(Vertex v) {
        return this.neighborhood.get(v);
    }
}
