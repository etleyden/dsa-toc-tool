package dsa_toc_tool;

/**
 * This class is to represents nodes contained within a graph (or any of its subclasses) 
 * A node can have either a label (String) or an id (int) or both. 
 * Once created, a node cannot be modified, but you can retrieve it's properties with getters. 
 */
public class Node {
		private String label;
		private int id;
		private Node() {
			
		}
		/**
		 * Create a Node with only a string label
		 */
		public Node(String label) {
			this.label = label;
		}
		/**
		 * Create a Node with only an int id. 
		 */
		public Node(int id) {
			this.id = id;
		}
		/**
		 * Create a Node with both a string label and an int id
		 */
		public Node(String label, int id) {
			this.label = label;
			this.id = id;
		}
		public int getId() {return id;}
		public String getLabel() {return label;}
}