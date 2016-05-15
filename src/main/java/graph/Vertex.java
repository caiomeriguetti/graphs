package graph;

public class Vertex {
    private String name;
    private Object value;
    public Vertex(String name) {
    	this.name = name;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(Object v) {
    	this.value = v;
    }
    
    public Object getValue() {
    	return value;
    }

    public String toString() {
        return this.name+((value !=null)?value :"");
    }
}
