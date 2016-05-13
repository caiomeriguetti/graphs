package graph;

import java.util.HashMap;
import java.util.Map;

public class DijkstraData {
    private Graph graph;
    private Map<Vertex, Integer> flags;
    private Map<Vertex, Double> distancesFromOrigin;
    private Map<Vertex, Vertex> parents;

    public void initialize(Graph graph) {
        this.graph = graph;
        this.flags = new HashMap<Vertex, Integer>();
        this.distancesFromOrigin = new HashMap<Vertex, Double>();
        this.parents = new HashMap<Vertex, Vertex>();

        for (Vertex v: this.graph.getVertices()) {
            this.distancesFromOrigin.put(v, Double.POSITIVE_INFINITY);
            this.flags.put(v, 0);
            this.parents.put(v, null);
        }
    }
    
    public Path calculateMinimumPaths(Vertex origin) {
        return null;
    }
}
