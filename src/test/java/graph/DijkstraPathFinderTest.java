package graph;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dijkstra.DijkstraPathFinder;
import junit.framework.Assert;

public class DijkstraPathFinderTest {
	protected DijkstraPathFinder pathFinder;
	
	@Before
	public void init() {
		pathFinder = new DijkstraPathFinder();
	}


    @Test
    public void testSplit() throws Exception {
        SampleGraph graph = new SampleGraph(1000, 10);
        GraphSplit s = new GraphSplit(graph, 20);
        
        Graph region1 = s.getRegions().get(0);
        Graph region2 = s.getRegions().get(3);
        
        int randomIndex1 = new Double((region1.getVertices().size() - 1) * Math.random()).intValue();
        int randomIndex2 = new Double((region2.getVertices().size() - 1) * Math.random()).intValue();
        
        Vertex origin = region1.getVertices().get(randomIndex1);
        Vertex destination = s.getRegions().get(1).getVertices().get(randomIndex2);
        
        Path fastCalculationPath = s.getPath(origin, destination);
        
        DijkstraPathFinder regularPathFinder = new DijkstraPathFinder(graph, origin);
        Path slowCalculationPath = regularPathFinder.getPath(destination);
        
        System.out.println(fastCalculationPath.getVertices());
        System.out.println(fastCalculationPath.getCost());
        System.out.println(slowCalculationPath.getVertices());
        System.out.println(slowCalculationPath.getCost());
    }
	
    @Test
    public void testBigGraph() throws Exception {
        SampleGraph graph = new SampleGraph(10000, 100);
        long startTime = System.currentTimeMillis();
        pathFinder.calculateMinimumPaths(graph, graph.getVertices().get(0));
        Path path = pathFinder.getPath(graph.randomVertex(graph.getCircles().get(99)));
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println((totalTime/1000.0) + " - " + path.getVertices());
    }
	
	@Test
	public void testMinPathCalculation() throws Exception {
		Graph graph1 = new Graph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        
        graph1.addVertex(a);
        graph1.addVertex(b);
        graph1.addVertex(c);
        graph1.addVertex(d);
        graph1.addVertex(e);
        graph1.addVertex(f);
        graph1.addVertex(g);
        
        graph1.addEdge(new Edge(a, b, 1.0));
        graph1.addEdge(new Edge(a, g, 20.0));
        graph1.addEdge(new Edge(a, c, 20.0));
        graph1.addEdge(new Edge(b, c, 1.0));
        graph1.addEdge(new Edge(b, d, 20.0));
        graph1.addEdge(new Edge(b, f, 20.0));
        graph1.addEdge(new Edge(c, d, 20.0));
        graph1.addEdge(new Edge(c, g, 1.0));
        graph1.addEdge(new Edge(d, e, 1.0));
        graph1.addEdge(new Edge(d, g, 20.0));
        graph1.addEdge(new Edge(e, f, 20.0));
        graph1.addEdge(new Edge(e, g, 1.0));
        
		pathFinder.calculateMinimumPaths(graph1, a);
		Path path = pathFinder.getPath(e);
		
		assertEquals("[(A, B), (B, C), (C, G), (E, G)]", path.getEdges().toString());
		assertEquals("[A, B, C, G, E]", path.getVertices().toString());
	}
	
	@Test
	public void testMinPathCalculationBinaryTree() throws Exception {
		Graph graph1 = new Graph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        
        graph1.addVertex(a);
        graph1.addVertex(b);
        graph1.addVertex(c);
        graph1.addVertex(d);
        graph1.addVertex(e);
        graph1.addVertex(f);
        graph1.addVertex(g);
        
        graph1.addEdge(new Edge(a, b, 1.0));
        graph1.addEdge(new Edge(a, c, 1.0));
        graph1.addEdge(new Edge(b, d, 1.0));
        graph1.addEdge(new Edge(b, e, 1.0));
        graph1.addEdge(new Edge(c, f, 1.0));
        graph1.addEdge(new Edge(c, g, 1.0));
        
		pathFinder.calculateMinimumPaths(graph1, a);
		Path path = pathFinder.getPath(f);
		
		assertEquals("[A, C, F]", path.getVertices().toString());
		assertEquals("[(A, C), (C, F)]", path.getEdges().toString());
		
	}

}
