package dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
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
    
    public DijkstraPathFinder() {
    	
    }
    
    public DijkstraPathFinder(Graph graph, Vertex origin) throws Exception {
        calculateMinimumPaths(graph, origin);
    }

    public Path getPath(Vertex destination) {
    	Path path = new Path();
    	Vertex current = destination;
    	List<Vertex> pathList = new ArrayList<Vertex>();
    	List<Edge> edgeList = new ArrayList<Edge>();
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
		
		List<Vertex> queue = new ArrayList<Vertex>();
		List<Vertex> checkeds = new ArrayList<Vertex>();

		for (Vertex v: this.graph.getVertices()) {
		    this.distancesFromOrigin.put(v, Double.POSITIVE_INFINITY);
		    this.flags.put(v, NO_PATHS_FOUND);
		    this.parents.put(v, null);
		    queue.add(v);
		}
		
		this.distancesFromOrigin.put(origin, 0.0);

		while (queue.size() > 0) {
			Vertex current = queue.get(0);
			for (Vertex v: queue) {
				if (distancesFromOrigin.get(v) < distancesFromOrigin.get(current)) {
					current = v;
				}
			}
			
			queue.remove(current);
			checkeds.add(current);

			for(Edge e: this.graph.getEdges(current)) {
	        	Vertex end = e.getEnd(current);
	        	
	        	if(!checkeds.contains(current)) {
	        		queue.add(end);
	        	}
	        	
	        	double currentPathSize = this.distancesFromOrigin.get(current) + e.getValue();
	        	
	        	if (this.flags.get(end) == NO_PATHS_FOUND) {
	        		this.flags.put(end, PATH_FOUND);
	        		this.distancesFromOrigin.put(end, currentPathSize);
	        		this.parents.put(end, current);
	        	} else if (this.flags.get(end) == PATH_FOUND && currentPathSize < this.distancesFromOrigin.get(end)) {
	        		this.distancesFromOrigin.put(end, currentPathSize);
	        		this.parents.put(end, current);
	        	}
	        }
		}
    }
}
