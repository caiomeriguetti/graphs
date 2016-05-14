package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class DijkstraPathFinder {
    private Graph graph;
    private Vertex origin;
    private Map<Vertex, Integer> flags;
    private Map<Vertex, Double> distancesFromOrigin;
    private Map<Vertex, Vertex> parents;
    
    public static int NO_PATHS_FOUND = 0;
    public static int PATH_FOUND = 1;
    public static int MINIMUM_PATH_FOUND = 2;

    public DijkstraPathFinder(Graph graph, Vertex origin) throws Exception {
        this.graph = graph;
        this.origin = origin;
        this.calculateMinimumPaths(origin);
    }
    
    public List<Vertex> getPath(Vertex destination) {
    	Vertex current = destination;
    	List<Vertex> path = new ArrayList<Vertex>();
    	path.add(0, destination);
    	while (current != origin) {
    		Vertex pathVertex = this.parents.get(current);
    		path.add(0, pathVertex);
    		current = pathVertex;
    	}
    	return path;
    }
    
    private void calculateMinimumPaths(Vertex origin) throws Exception {
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
			double lastPathSize = Double.POSITIVE_INFINITY;
			Vertex lowest = null;
			for(Edge e: this.graph.getEdges(current)) {
	        	Vertex end = e.getEnd(current);
	        	
	        	if (this.flags.get(end) == MINIMUM_PATH_FOUND) {
	        		continue;
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
	        	
	        	if (currentPathSize < lastPathSize) {
	        		lowest = end;
	        	}
	        	
	        	lastPathSize = currentPathSize;
	        }
			
			this.flags.put(current, MINIMUM_PATH_FOUND);
			
			current = lowest;
		}
    }
}
