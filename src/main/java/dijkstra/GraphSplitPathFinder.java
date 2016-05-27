package dijkstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import graph.Edge;
import graph.Graph;
import graph.Path;
import graph.Vertex;
import graph.VertexWithEdge;

public class GraphSplitPathFinder {
    Graph graph;
    private List<Vertex> alreadyInsideRegion = new LinkedList<>();
    private Vertex currentStart;
    private List<Graph> regions = new LinkedList<>();
    private HashMap<Graph, List<VertexWithEdge>> outsideConnections = new HashMap<>();
    private HashMap<VertexWithEdge, Graph> connectionToRegion = new HashMap<>();
    private int maxRegionSize;
    
    public GraphSplitPathFinder(Graph graph, int maxRegionSize) throws Exception {
        this.graph = graph;
        this.maxRegionSize = maxRegionSize;
        
        List<Vertex> list = this.graph.getVertices();
        currentStart = list.get(0);
        
        while (currentStart != null) {
            split(currentStart);
        }
        
        //mapping outside connections of each region to the destinationRegion
        int sum  = 0;
        for (Graph region: regions) {
        	sum += region.getVertices().size();
            List<VertexWithEdge> connections = outsideConnections.get(region);
            
            for (VertexWithEdge connection: connections) {
                Vertex end = connection.getEdge().getEnd(connection.getVertex());
                
                for (Graph destinationRegion: regions) {
                    if (destinationRegion != region && destinationRegion.getVertices().contains(end)) {
                        connectionToRegion.put(connection, destinationRegion);
                        break;
                    }
                }
            }
        }
//        System.out.println("Total: " + sum);
        
    }
    
    private VertexWithEdge getMinConnection(Graph region1, Graph region2) {
        
        List<VertexWithEdge> connections = outsideConnections.get(region1);
        double minCost = Double.POSITIVE_INFINITY;
        VertexWithEdge minConnection = null;
        for (VertexWithEdge connection: connections) {
            Graph destinationGraph = connectionToRegion.get(connection);
            double connectionCost = (double)connection.getEdge().getValue();
            if (destinationGraph == region2 && minCost > connectionCost) {
                minCost = connectionCost;
                minConnection = connection;
            }
        }
        
        return minConnection;
    }
    
    public Path getPath(Vertex origin, Vertex destination) throws Exception {
        Graph originRegion = getRegion(origin);
        Graph destinationRegion = getRegion(destination);

        VertexWithEdge minConnection = getMinConnection(originRegion, destinationRegion);
        
        if (minConnection != null) {
            DijkstraPathFinder pathFinder =  new DijkstraPathFinder(originRegion, origin);
            Path pathToConnection = pathFinder.getPath(minConnection.getVertex());
            
            DijkstraPathFinder pathFinder2 =  new DijkstraPathFinder(destinationRegion, minConnection.getEdge().getEnd(minConnection.getVertex()));
            Path pathToDestination = pathFinder2.getPath(destination);
            Path resultPath = new Path();
            
            for (int i=0; i < pathToConnection.getVertices().size(); i++) {
                resultPath.addVertex(pathToConnection.getVertices().get(i));
            }
            
            for (int i=0; i < pathToConnection.getEdges().size(); i++) {
                resultPath.addEdge(pathToConnection.getEdges().get(i));
            }
            
            resultPath.addEdge(minConnection.getEdge());
            
            for (int i=0; i < pathToDestination.getVertices().size(); i++) {
                resultPath.addVertex(pathToDestination.getVertices().get(i));
            }
            
            for (int i=0; i < pathToDestination.getEdges().size(); i++) {
                resultPath.addEdge(pathToDestination.getEdges().get(i));
            }
            
            return resultPath;
        } else {
        	throw new Exception("No direct neighbor regions");
        }

    }
    
    public Graph getRegion(Vertex v) {
        for(Graph region: regions) {
            if (region.getVertices().contains(v)) {
                return region;
            }
        }
        
        return null;
    }
    
    public List<Graph> getRegions() {
        return regions;
    }

    public void setRegions(List<Graph> regions) {
        this.regions = regions;
    }

    public void split(Vertex start) throws Exception {
        
        List<Vertex> vertices = new LinkedList<>();
        List<VertexWithEdge> queue = new LinkedList<>();
        
        queue.add(new VertexWithEdge(start));
        
        Graph region = new Graph("Region " + regions.size());
        
        while (queue.size() > 0) {
        	VertexWithEdge v = queue.remove(0);
        	
        	vertices.add(v.getVertex());
        	if (v.getEdge() != null) {
        		region.addEdge(v.getEdge());
        	}
            region.addVertex(v.getVertex());
            alreadyInsideRegion.add(v.getVertex());
            
            if (vertices.size() < maxRegionSize) {
	        	//visiting neighbors
	            List<Edge> neighborEdges = this.graph.getEdges(v.getVertex());
	            for (Edge e: neighborEdges) {
	                Vertex neighbor = e.getEnd(v.getVertex());
	                VertexWithEdge ve = new VertexWithEdge(neighbor, e);
	                if (!vertices.contains(neighbor) && !alreadyInsideRegion.contains(neighbor)) {
	                	queue.add(ve);
	                }
	            }
            }
        }

        regions.add(region);
        
        List<VertexWithEdge> connections = new LinkedList<>();
        
        for (Vertex v: vertices) {
            for (Edge e: this.graph.getEdges(v)) {
                Vertex neighbor = e.getEnd(v);
                if (!vertices.contains(neighbor)) {
                    connections.add(new VertexWithEdge(v, e));
                }
            }
        }
        
        outsideConnections.put(region, connections);
        
        currentStart = null;
        
        for (VertexWithEdge v: connections) {
            Vertex outsideVertex = v.getEdge().getEnd(v.getVertex());
            
            if (!this.alreadyInsideRegion.contains(outsideVertex)) {
                currentStart = outsideVertex;
                break;
            }
        }
        
//        System.out.println(region.getVertices() + " - " + region.getVertices().size() + " / " + this.graph.getVertices().size());
//        System.out.println(region.getEdges());
//        System.out.println(connections);
//        System.out.println(currentStart);
//        System.out.println("-----------------------");
    }
}
