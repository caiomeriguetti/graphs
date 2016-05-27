package graph;

public class Vertex {
    private Object name;
    private Object value;
    
    public Vertex() {
    }
    
    public Vertex(Object name) {
    	this.name = name;
    }
    
    public Object getName() {
		return name;
	}

	public void setName(Object name) {
		this.name = name;
	}

	public void setValue(Object v) {
    	this.value = v;
    }
    
    public Object getValue() {
    	return value;
    }

    public String toString() {
        return this.name.toString()+((value !=null)?value :"");
    }
}
