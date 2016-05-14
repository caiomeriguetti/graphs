package graph;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DijkstraPathFinderTest {
	protected DijkstraPathFinder pathFinder;
	
	@Before
	public void init() {
		pathFinder = new DijkstraPathFinder();
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
        
		pathFinder.initialize(graph1, a);
		List<Vertex> path = pathFinder.getPath(e);
		
		assertEquals(5, path.size());
		assertEquals(a, path.get(0));
		assertEquals(b, path.get(1));
		assertEquals(c, path.get(2));
		assertEquals(g, path.get(3));
		assertEquals(e, path.get(4));
		
	}

}
