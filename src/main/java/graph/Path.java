package graph;

import java.util.LinkedList;
import java.util.List;

public class Path {
	private List<Vertex> vertices;
	private List<Edge> edges;
	
	public Path() {
	    vertices = new LinkedList<>();
	    edges = new LinkedList<>();
	}
	
	public void addVertex(Vertex v) {
        vertices.add(v);
    }
	
	public void addEdge(Edge e) {
        edges.add(e);
    }
	
	public double getCost() {
	    double s = 0.0;
	    for (Edge e: edges) {
	        s += e.getValue();
	    }
	    
	    return s;
	}
	
	public List<Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
}
