package nfa_to_dfa;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * While intended to be a parent class for the DFA class, the NFA class, and
 * others,
 * the Graph class can be utilized in many applications. Utilizes {@link Node},
 * {@link GraphEdge}
 * 
 * @see GraphEdge
 * @see Node
 * @see DFA
 * @see NFA
 * @author Ethan Leyden
 */
public class Graph {
    private ArrayList<ArrayList<GraphEdge>> adj; // adjacency matrix with edges
    private ArrayList<Node> nodes; // node labels, where their index represents the index in the adj matrix
    private int num_nodes = 0;
    private HashMap<String, Integer> ids; // Look up a label, get an id
    private HashMap<Integer, Integer> idx; // Look up an id, get an index in the adjacency matrix.

    /**
     * Creates a new Graph object with no nodes or (obviously) edges between nodes.
     */
    public Graph() {
        this.adj = new ArrayList<>();
        this.nodes = new ArrayList<>();
        HashMap<String, Integer> labels = new HashMap();
        HashMap<Integer, String> ids = new HashMap();
    }

    // ---------- NODE ------------
    /**
     * Add a new node to the graph. The node automatically gets assigned an int id,
     * with collisions handled
     * via quadratic probing
     * 
     * @param node the label assigned to this node, it should be unique.
     * @return the id assigned to this node
     */
    public int addNode(String label) {
        if (ids.containsKey(label))
            return -1;
        // assign an id automatically
        int offset = 0;
        int id = num_nodes;
        do {
            id = num_nodes + (int) (Math.pow(offset++, 2));
        } while (idx.containsKey(id));
        addNode(label, num_nodes, true);
        ids.put(label, id);
        idx.put(id, num_nodes - 1);
        return id;
    }

    /**
     * Add a new node to the graph. The node will be assigned a hexadecimal label
     * based on its id,
     * with collisions being resolved with quadratic probing.
     * You cannot assign two nodes with the same integer id.
     * 
     * @param id the unique id assigned to this node. Cannot be -1, as this denotes
     *           failure in another version of this method.
     */
    public void addNode(int id) {
        int i = 0;
        String label;
        // assign a label automatically
        do {
            label = Integer.toHexString((int) (id + Math.pow(i++, 2)));
        } while (ids.containsKey(label));
        addNode(label, id, true);
        ids.put(label, id);
        idx.put(id, num_nodes - 1);
    }

    /*
     * This is not a javadoc comment, since this method is not public facing.
     * If no label is provided, the label is ignored, so it should be sufficient
     * to provide the empty string ("") as an argument.
     */
    private void addNode(String label, int id, boolean hasLabel) {
        Node newNode = (hasLabel) ? new Node(label, id) : new Node(id);
        nodes.add(newNode);
        num_nodes++;
        adj.add(new ArrayList<>());
        for (int i = 0; i < adj.size(); i++) {
            ArrayList<Transition> row = adj.get(i);
            while (row.size() <= num_nodes) {
                adj.add(new GraphEdge()); // make sure every row has enough edges to make a complete matrix
            }
        }
    }

    /**
     * Retrieves the label for the Node with a matching id.
     * 
     * @param id the id of the node who's label we want to find
     */
    public String getNodeLabel(int id) {
        return ""; // dummy value
    }

    /**
     * Retrieves the id for the Node with a matching label.
     * 
     * @param label the label for the node who's id we want to find
     */
    public int getNodeId(String label) {
        return 0;
    }

    // --------- EDGES -----------
    /**
     * Adds an edge weight between two nodes, given their ids
     */
    public void addEdge(int u_id, int v_id, int weight) {
        int u_idx = idx.get(u_id);
        int v_idx = idx.get(v_id);
        adj.get(u_idx).get(v_idx).setWeight(weight);
    }

    /**
     * Adds an edge weight between two nodes with the given string labels.
     */
    public void addEdge(String u_label, String v_label, int weight) {
        int u_id = ids.get(u_label);
        int v_id = ids.get(v_label);
        addEdge(u_id, v_id, weight);
    }

    /**
     * Make sure a passed adjacency list is eligible to be for this graph
     */
    public String toString() {
        return "";
    }
}
