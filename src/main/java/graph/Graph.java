package graph;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
    protected List<Vertex> vertices;
    protected List<Edge> edges;
    protected Map<Vertex, List<Edge>> edgesByVertex;
    private String name;
    
    public Graph() {
    	this("");
    }
    public Graph(String name) {
        vertices = new LinkedList<Vertex>();
        edges = new LinkedList<Edge>();
        edgesByVertex = new HashMap<Vertex, List<Edge>>();
        this.name = name;
    }
    
    public boolean isLeaf(Vertex v) {
    	return getEdges(v).size() == 1;
    }
    
    public boolean isValidPath(Path path) {
    	List<Vertex> pathVertices = path.getVertices();
    	
    	for (int i = 1; i < pathVertices.size(); i++) {
    		Vertex v1 = pathVertices.get(i-1);
    		Vertex v2 = pathVertices.get(i);
    		Edge e = getEdge(v1, v2);
    		
    		if (e == null) {
    			return false;
    		}
    	}
    	
    	return true;
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
        	edgesByVertex.put(e.getV1(), new LinkedList<Edge>());
        }
        
        if (!edgesByVertex.keySet().contains(e.getV2())) {
        	edgesByVertex.put(e.getV2(), new LinkedList<Edge>());
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
    	List<Vertex> neighbors = new LinkedList<Vertex>();
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
    
    public String toString () {
		return name;
    }
}
