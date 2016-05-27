package graph;

import java.util.LinkedList;
import java.util.List;

public class Tree extends Graph {
	private TreeVertex root;
	public TreeVertex getRoot() {
		return root;
	}
	
	public List<Edge> getChildEdges(TreeVertex v) throws Exception {
		List<Edge> allEdges = this.getEdges(v);
		List<Edge> childEdges = new LinkedList<Edge>();
		for (Edge e: allEdges) {
			if (e.getEnd(v) == v.getParent()) {
				continue;
			}
			childEdges.add(e);
		}
		return childEdges;
	}
	public void setRoot(TreeVertex root) {
		this.root = root;
	}
	public Tree() {
    	this("");
    }
    public Tree(String name) {
        super(name);
    }
}
