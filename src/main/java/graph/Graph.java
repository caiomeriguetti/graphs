package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;
    private Map<Vertex, List<Edge>> edgesByVertex;
    
    public Graph() {
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        edgesByVertex = new HashMap<Vertex, List<Edge>>();
    }
    
    public void addVertex(Vertex v) {
        vertices.add(v);
    }
    
    public void addEdge(Edge e) {
        edges.add(e);
        
        if (!edgesByVertex.keySet().contains(e.getV1())) {
        	edgesByVertex.put(e.getV1(), new ArrayList<Edge>());
        }
        
        if (!edgesByVertex.keySet().contains(e.getV2())) {
        	edgesByVertex.put(e.getV2(), new ArrayList<Edge>());
        }
        
        edgesByVertex.get(e.getV1()).add(e);
        edgesByVertex.get(e.getV2()).add(e);
    }
    
    public List<Vertex> getVertices() {
        return this.vertices;
    }
    
    public List<Edge> getEdges(Vertex v) {
        return this.edgesByVertex.get(v);
    }
}
