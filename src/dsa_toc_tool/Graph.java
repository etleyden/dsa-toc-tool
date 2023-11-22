package dsa_toc_tool;

import java.util.*;
//TODO: Adjust class to support undirected graphs as well
//TODO: Design graph to either use String labels OR int ids, but not both

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
    private boolean isDirected;
    private ArrayList<ArrayList<GraphEdge>> adj; // adjacency matrix with edges
    private ArrayList<Node> nodes; // node labels, where their index represents the index in the adj matrix
    private GraphEdge.Type edgeType;
    private int num_nodes = 0;
    /**
     * A HashMap that provides a quick lookup of labels to get the id.
     * !! Labels should have a one-to-one relationship with ids. !!
     */
    private HashMap<String, Integer> ids; // Look up a label, get an id
    /**
     * A HashMap that provides a quick lookup of ids to get the index in the
     * adjacency matrix
     * !! ids should have a one-to-one relationship with indexes !!
     */
    private HashMap<Integer, Integer> idx; // Look up an id, get an index in the adjacency matrix.

    /**
     * Creates a new weighted-edge Graph object with no nodes or (obviously) edges
     * between nodes.
     */
    public Graph() {
        this(GraphEdge.Type.WEIGHTED, false);
    }

    /**
     * Creates an empty Graph with specified edge type.
     * 
     * @param edgeType GraphEdge.Type is an enum that will define edges as weighted
     *                 or labelled
     */
    public Graph(GraphEdge.Type edgeType, boolean isDirected) {
        this.edgeType = edgeType;
        this.isDirected = isDirected;
        this.adj = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.idx = new HashMap<>();
        this.ids = new HashMap<>();
    }

    // ---------- NODE ------------
    /**
     * Add a new node to the graph. The integer id automatically assigned to the
     * label will be the number of nodes in
     * the graph, with collisions handled via quadratic probing
     * 
     * @param label the label assigned to this node, it should be unique.
     * @return the id assigned to this node
     */
    public int addNode(String label) {
        if (this.nodeExists(label))
            return -1;
        // assign an id automatically
        int offset = 0;
        int id = num_nodes;
        do {
            id = num_nodes + (int) Math.pow(offset++, 2);
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
     * @return the label automatically assigned to the string
     */
    public String addNode(int id) {
        if (idx.containsKey(id))
            return null;
        int i = 0;
        String label;
        // assign a label automatically
        do {
            label = Integer.toHexString(id + (int) Math.pow(i++, 2)).toUpperCase();
        } while (ids.containsKey(label));
        addNode(label, id, true);
        ids.put(label, id);
        idx.put(id, num_nodes - 1);
        return label;
    }

    /**
     * If no label is provided, the label is ignored, so it should be sufficient
     * to provide the empty string ("") as an argument. Automatically handles the
     * incrementing of num_nodes
     */
    private void addNode(String label, int id, boolean hasLabel) {
        Node newNode = (hasLabel) ? new Node(label, id) : new Node(id);
        nodes.add(newNode);
        num_nodes++;
        adj.add(new ArrayList<>());
        for (int i = 0; i < adj.size(); i++) {
            ArrayList<GraphEdge> row = adj.get(i);
            while (row.size() <= num_nodes) {
                row.add(new GraphEdge()); // make sure every row has enough edges to make a complete matrix
            }
        }
    }

    /**
     * Retrieves the label for the Node with a matching id.
     * 
     * @param id the id of the node whose label we want to find
     */
    public String getNodeLabel(int id) {
        for (Map.Entry<String, Integer> entry : ids.entrySet()) {
            if (id == entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Retrieves the id for the Node with a matching label.
     * 
     * @param label the label for the node who's id we want to find
     */
    public int getNodeId(String label) {
        return ids.get(label);
    }

    /**
     * Check if a node exists, given its label
     * 
     * @param label
     * @return T/F whether the node exists
     */
    public boolean nodeExists(String label) {
        return ids.containsKey(label);
    }

    /**
     * Check if a node exists, given its id
     * 
     * @param id
     * @return T/F whether the node exists
     */
    public boolean nodeExists(int id) {
        return idx.containsKey(id);
    }

    // --------- EDGES -----------
    // TODO: Implement Edge direction
    /**
     * Sets an edge weight between two nodes, given their ids.
     * 
     * @param u_id   the id of the node the edge is from
     * @param v_id   the id of the node the edge is to
     * @param weight the new weight of the edge
     * @return T/F the success of the operation
     */
    public boolean setEdge(int u_id, int v_id, int weight) {
        int u_idx = idx.get(u_id);
        int v_idx = idx.get(v_id);
        if (!isDirected)
            adj.get(v_idx).get(u_idx).setWeight(weight);
        return adj.get(u_idx).get(v_idx).setWeight(weight);
    }

    /**
     * Sets an edge weight between two nodes, given their labels
     * 
     * @param u_label the label of the node the edge is from
     * @param v_label the label of the node the edge is to
     * @param weight  the new weight of the edge
     * @return T/F the success of the operation
     */
    public boolean setEdge(String u_label, String v_label, int weight) {
        int u_id = ids.get(u_label);
        int v_id = ids.get(v_label);
        return setEdge(u_id, v_id, weight);
    }

    /**
     * Sets the edge label between two nodes
     * 
     * @param u_id  the node the edge is coming from
     * @param v_id  the node the edge is going to
     * @param label the label of the edge.
     * @return T/F the success of the operation
     */
    public boolean setEdge(int u_id, int v_id, String label) {
        int u_idx = idx.get(u_id);
        int v_idx = idx.get(v_id);
        if (!isDirected)
            adj.get(v_idx).get(u_idx).setLabel(label);
        return adj.get(u_idx).get(v_idx).setLabel(label);
    }

    /**
     * Sets the label of the edge between two nodes
     * 
     * @param u_label the node the edge is from
     * @param v_label the node the edge is to
     * @param label   the new label
     * @return T/F the success of the operation
     */
    public boolean setEdge(String u_label, String v_label, String label) {
        int u_id = ids.get(u_label);
        int v_id = ids.get(v_label);
        return setEdge(u_id, v_id, label);
    }

    public GraphEdge getEdge(int u_id, int v_id) {
        int u_idx = idx.get(u_id);
        int v_idx = idx.get(v_id);
        return adj.get(v_idx).get(u_idx);
    }

    public GraphEdge getEdge(String u_label, String v_label) {
        int u_id = ids.get(u_label);
        int v_id = ids.get(v_label);
        return getEdge(u_id, v_id);
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        ListIterator<Node> node_iterator = this.nodes.listIterator();
        Iterator<ArrayList<GraphEdge>> adj_iterator = this.adj.iterator();
        out.append("Nodes,");
        // Add the node tuples
        while (node_iterator.hasNext()) {
            Node current_node = node_iterator.next();
            out.append(String.format("(%d, %s)", current_node.getId(), current_node.getLabel()));
            if (node_iterator.hasNext())
                out.append(",");
        }
        out.append("\n");

        // move node iterator back to the beginning
        while (node_iterator.hasPrevious())
            node_iterator.previous();
        while (adj_iterator.hasNext()) {
            Node current_node = node_iterator.next();
            out.append(String.format("(%d %s)", current_node.getId(), current_node.getLabel()));
            Iterator<GraphEdge> row_iterator = adj_iterator.next().iterator();
            while (row_iterator.hasNext()) {
                GraphEdge edge = row_iterator.next();

                if (edge.getType() == GraphEdge.Type.NULL) {
                    out.append("null");
                } else if (edge.getType() == GraphEdge.Type.LABELLED) {
                    out.append(edge.getLabel());
                } else {
                    out.append(edge.getWeight());
                }

                if (row_iterator.hasNext())
                    out.append(",");
            }
            out.append("\n");
        }
        return out.toString();
    }
}
