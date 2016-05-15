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

}
