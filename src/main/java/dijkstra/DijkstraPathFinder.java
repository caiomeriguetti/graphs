package dijkstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import graph.Edge;
import graph.Graph;
import graph.Path;
import graph.Vertex;

public class DijkstraPathFinder {
    private Graph graph;
    private Vertex origin;
    private Map<Vertex, Integer> flags;
    private Map<Vertex, Double> distancesFromOrigin;
    private Map<Vertex, Vertex> parents;
    
    public static int NO_PATHS_FOUND = 0;
    public static int PATH_FOUND = 1;
    public static int MINIMUM_PATH_FOUND = 2;
    
    public DijkstraPathFinder() {
    	
    }
    
    public DijkstraPathFinder(Graph graph, Vertex origin) throws Exception {
        calculateMinimumPaths(graph, origin);
    }

    public Path getPath(Vertex destination) {
    	Path path = new Path();
    	Vertex current = destination;
    	List<Vertex> pathList = new LinkedList<Vertex>();
    	List<Edge> edgeList = new LinkedList<Edge>();
    	pathList.add(0, destination);
    	while (current != origin) {
    		Vertex pathVertex = this.parents.get(current);
    		pathList.add(0, pathVertex);
    		if (pathList.size() > 1) {
    			Edge pathEdge = graph.getEdge(pathList.get(0), pathList.get(1));
    			List<Edge> a = graph.getEdges(pathList.get(0));
        		edgeList.add(0, pathEdge);
    		}
    		current = pathVertex;
    	}
    	path.setVertices(pathList);
    	path.setEdges(edgeList);
    	
    	return path;
    }
    
    public void calculateMinimumPaths(Graph graph, Vertex origin) throws Exception {
    	this.origin = origin;
    	this.graph = graph;
		this.flags = new HashMap<Vertex, Integer>();
		this.distancesFromOrigin = new HashMap<Vertex, Double>();
		this.parents = new HashMap<Vertex, Vertex>();

		for (Vertex v: this.graph.getVertices()) {
		    this.distancesFromOrigin.put(v, Double.POSITIVE_INFINITY);
		    this.flags.put(v, NO_PATHS_FOUND);
		    this.parents.put(v, null);
		}
		
		this.distancesFromOrigin.put(origin, 0.0);
		
		Vertex current = origin;

		while (current != null) {
			flags.put(current, MINIMUM_PATH_FOUND);
			
			for(Edge e: graph.getEdges(current)) {
	        	Vertex end = e.getEnd(current);
	        	
	        	if (flags.get(end) == MINIMUM_PATH_FOUND) {
	        		continue;
	        	}
	        	
	        	double currentPathSize = this.distancesFromOrigin.get(current) + e.getValue();
	        	
	        	if (flags.get(end) == NO_PATHS_FOUND) {
	        		flags.put(end, PATH_FOUND);
	        		distancesFromOrigin.put(end, currentPathSize);
	        		parents.put(end, current);
	        	} else if (flags.get(end) == PATH_FOUND && currentPathSize < distancesFromOrigin.get(end)) {
	        		distancesFromOrigin.put(end, currentPathSize);
	        		parents.put(end, current);
	        	}
	        }
			
			current = null;
			
			for (Vertex v: distancesFromOrigin.keySet()) {
				if (flags.get(v) == MINIMUM_PATH_FOUND) {
					continue;
				}
				
				if (current == null) {
					current = v;
				} else {
					if (distancesFromOrigin.get(v) < distancesFromOrigin.get(current)) {
						current = v;
					}
				}
			}
		}
    }
}
