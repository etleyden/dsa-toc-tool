package nfa_to_dfa;

import java.util.ArrayList;

public class Graph {
    private ArrayList<ArrayList<String>> adj; //adjacency matrix with transition labels
    private ArrayList<String> nodes; //node labels, where their index represents the index in the adj matrix
    /**
     * @param nodes the list of nodes w/ string names, where the location in the string array corresponds to the location on the adjacency matrix
     * @param adj the adjacency matrix to initialize the graph with
     */
    public Graph(ArrayList<String> nodes, ArrayList<ArrayList<String>> adj) {
        this.nodes = nodes;
        this.adj = adj;
    }
    /**
     * 
     * @param nodes the list of nodes w/ string names which correspond to their location in the adjacency matrix
     */
    public Graph(ArrayList<String> nodes) {
        this.nodes = nodes;
    }
    public void addNode(String node) {
        nodes.add(node);
    }
}