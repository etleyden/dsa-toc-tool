package dsa_toc_tool;

/**
 * This is an interface to cover all transitions in the various automota which may appear in this library.
 * It should be extended (SOLID) for more specific use cases.
 */
public class GraphEdge {
	protected boolean isNull = true;
	private int weight; 
	public GraphEdge() {}
	public GraphEdge(int weight) {
		this.weight = weight;
	}
	public void setWeight(int weight) {
		if(isNull) isNull = false;
		this.weight = weight; 
	}
	public int getWeight() {
		return weight;
	}
}