package graph;

public class TreeVertex extends Vertex {
    private TreeVertex parent;
    public TreeVertex() {
    	super();
    }
    public TreeVertex(Object name) {
    	super(name);
    }
    
    public TreeVertex getParent() {
		return parent;
	}

	public void setParent(TreeVertex parent) {
		this.parent = parent;
	}
}
