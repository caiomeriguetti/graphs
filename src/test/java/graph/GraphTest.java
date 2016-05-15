package graph;

import org.junit.Assert;
import org.junit.Test;

public class GraphTest {
	
	@Test
	public void testLoadFile() throws Exception {
		Graph graph = new Graph();
		graph.loadFromFile("/home/caiomeriguetti/teste.txt");
		
		Assert.assertEquals(graph.getVertices().get(3).toString(), "4");
		Assert.assertEquals(graph.getVertices().get(0).toString(), "1");
		
		Assert.assertEquals(graph.getEdges().get(3).toString(), "(4, 5)");
	}
	
	@Test
	public void testGraph() throws Exception {
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
        
        Assert.assertEquals("(A, B)", graph1.getEdge(a, b).toString());
        Assert.assertEquals("[(A, B), (B, D), (B, E)]", graph1.getEdges(b).toString());
        Assert.assertEquals("[A, F, G]", graph1.getNeighbors(c).toString());
	}

}
