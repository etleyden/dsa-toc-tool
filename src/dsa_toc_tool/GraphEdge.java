package dsa_toc_tool;

/**
 * This is an interface to cover all transitions in the various automota which
 * may appear in this library.
 * It should be extended (SOLID) for more specific use cases.
 */
public class GraphEdge {

	public enum Type {
		NULL, WEIGHTED, LABELLED
	}

	private Type type;
	private int weight;
	private String label;

	public GraphEdge() {
		this.type = Type.NULL;
	}

	public GraphEdge(int weight) {
		this.weight = weight;
		this.type = Type.WEIGHTED;
	}

	public GraphEdge(String label) {
		this.label = label;
		this.type = Type.LABELLED;
	}

	/**
	 * Sets the weight of the GraphEdge
	 * @param weight
	 * @return True if successful, False if the edge is a labelled edge and not a
	 *         weighted one.
	 */
	public boolean setWeight(int weight) {
		if(type == Type.LABELLED)
			return false;
		if(type == Type.NULL)
			type = Type.WEIGHTED;
		this.weight = weight;
		return true;
	}

	public Integer getWeight() {
		return (type == Type.WEIGHTED) ? weight : null;
	}

	/**
	 * Sets the label of the GraphEdge
	 * 
	 * @param label a string label
	 * @return true if successful, false if the edge is a weighted edge (and not a
	 *         labelled edge)
	 */
	public boolean setLabel(String label) {
		if(type == Type.WEIGHTED)
			return false;
		if(type == Type.NULL)
			type = Type.LABELLED;
		this.label = label;
		return true;
	}

	public String getLabel() {
		return (type == Type.LABELLED) ? label : null;
	}

	public GraphEdge.Type getType() {
		return type;
	}
}