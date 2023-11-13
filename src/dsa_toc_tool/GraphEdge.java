package dsa_toc_tool;

/**
 * This is an interface to cover all transitions in the various automota which may appear in this library.
 * It should be extended (SOLID) for more specific use cases.
 */
public class GraphEdge {
	protected boolean isNull = true;
	public enum Type {
		WEIGHTED, LABELLED
	}
	private boolean isLabel, isWeight;
	private int weight;
	private String label;
	public GraphEdge() {}
	public GraphEdge(int weight) {
		this.weight = weight;
		this.isLabel = false;
		this.isWeight = true;
	}
	public GraphEdge(String label) {
		this.label = label;
		this.isLabel = true;
		this.isWeight = false;
	}

	/**
	 * Sets the weight of the GraphEdge
	 * @param weight
	 * @return True if successful, False if the edge is a labelled edge and not a weighted one.
	 */
	public boolean setWeight(int weight) {
		if(this.isLabel) return false;
		if(isNull) isNull = false;
		this.weight = weight;
		return true;
	}
	public Integer getWeight() {
		if (this.isWeight) {
			return weight;
		} else {
			return null;
		}
	}
	/**
	 * Sets the label of the GraphEdge
	 * @param label a string label
	 * @return true if successful, false if the edge is a weighted edge (and not a labelled edge)
	 */
	public boolean setLabel(String label) {
		if(!this.isLabel) return false;
		if(isNull) isNull = false;
		this.label = label;
		return true;
	}
	public String getLabel() {
		if(!this.isLabel) return null;
		return this.label;
	}
	public boolean isLabel() {
		return this.isLabel;
	}
	public boolean isWeight() {
		return this.isWeight;
	}
}