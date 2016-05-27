package graph;

import java.util.LinkedList;
import java.util.List;

public class SampleGraph extends Graph {
	private List<List<Vertex>> circles;
	public List<List<Vertex>> getCircles() {
		return circles;
	}
	public void setCircles(List<List<Vertex>> circles) {
		this.circles = circles;
	}
	
	public Vertex randomVertex(List<Vertex> list) {
		return list.get(new Double(Math.random()*list.size()).intValue());
	}
	public SampleGraph(int verticesNumber, int circlesNumber) {
		super();
		int i,j, k, verticeId;
		verticeId = 1;
		int verticesPerCircle = verticesNumber / circlesNumber;
		circles = new LinkedList<List<Vertex>>();
		for (i = 0; i < circlesNumber; i++) {
			List<Vertex> circleVertices = new LinkedList<>();
			Vertex prev = null;
			for (j = 0; j < verticesPerCircle; j++) {
				Vertex v = new Vertex(verticeId+"");
				
				if (prev != null) {
					addEdge(new Edge(v, prev, new Double(Math.random()*100)));
				}
				
				prev = v;
				circleVertices.add(v);
				addVertex(v);
				verticeId ++;
			}

			addEdge(new Edge(circleVertices.get(circleVertices.size() - 1), circleVertices.get(0)));
			circles.add(circleVertices);
		}
		
		for (i = 0; i < verticesPerCircle; i++) {
			Vertex prev = null;
			for (j = 0; j < circles.size(); j++) {
				List<Vertex> circle = circles.get(j);
				Vertex v = circle.get(i);
				if (prev != null) {
					addEdge(new Edge(v, prev, new Double(Math.random()*100)));
				}
				prev = v;
			}
		}
	}
}
