package graph;

public class Edge {
    
    private Vertex v1;
    private Vertex v2;
    private Object value;

    public Edge(Vertex v1, Vertex v2) {
        this(v1, v2, 1.0);
    }

    public Edge(Vertex v1, Vertex v2, Object value) {
        this.v1 = v1;
        this.v2 = v2;
        this.value = value;
    }

    public Vertex getV1() {
        return v1;
    }

    public void setV1(Vertex v1) {
        this.v1 = v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public void setV2(Vertex v2) {
        this.v2 = v2;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    public Vertex getEnd(Vertex startVertex) throws Exception {
    	if (startVertex == v1) {
    		return v2;
    	}
    	
    	if (startVertex == v2) {
    		return v1;
    	}
    	
    	throw new Exception("Vertex not in this edge");
    }
    
    public String toString() {
    	return "(" + v1 + ", " + v2 + ")";
    }
}
