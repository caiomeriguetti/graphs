package graph;

import java.io.File;
import java.nio.file.Files;
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
    
    public void loadFromFile(String path) throws Exception {
    	File toLoad = new File(path);
    	List<String> lines = Files.readAllLines(toLoad.toPath());
    	
    	int vertexNumber = Integer.parseInt(lines.get(0));
    	int i;
    	for (i = 1; i <= vertexNumber; i ++) {
    		this.addVertex(new Vertex(Integer.toString(i)));
    	}
    	
    	for (i = 2; i <= lines.size(); i++) {
    		String line = lines.get(i-1);
    		String[] vertexIndices = line.split(" ");
    		
    		int v1Index = Integer.parseInt(vertexIndices[0]) - 1;
    		int v2Index = Integer.parseInt(vertexIndices[1]) - 1;
    		
    		Edge e = new Edge(vertices.get(v1Index), vertices.get(v2Index));
    		
    		this.addEdge(e);
    	}
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
    
    public List<Edge> getEdges() {
        return this.edges;
    } 
    
    public List<Vertex> getNeighbors(Vertex v) throws Exception {
    	List<Edge> edges = this.edgesByVertex.get(v);
    	List<Vertex> neighbors = new ArrayList<Vertex>();
    	for (Edge e: edges) {
    		neighbors.add(e.getEnd(v));
    	}
    	
    	return neighbors;
    }
    
    public Edge getEdge(Vertex v1, Vertex v2) {
    	
    	List<Edge> edgesV1 = this.edgesByVertex.get(v1);
    	
    	for (Edge e: edgesV1) {
    		if ((e.getV1() == v1 && e.getV2() == v2) || (e.getV1() == v2 && e.getV2() == v1)) {
    			return e;
    		}
    	}
    	
    	return null;
    }
    
    public List<Edge> getEdges(Vertex v) {
        return this.edgesByVertex.get(v);
    }
}
