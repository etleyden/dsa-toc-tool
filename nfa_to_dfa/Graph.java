package nfa_to_dfa;

import java.util.ArrayList;

/**
 * While intended to be a parent class for the DFA class, the NFA class, and others, 
 * the Graph class can be utilized in many applications. Utilizes {@link Node}, {@link GraphEdge}
 * 
 * @see GraphEdge
 * @see Node
 * @see DFA
 * @see NFA
 * @author Ethan Leyden 
 */
public class Graph {
    private ArrayList<ArrayList<GraphEdge>> adj; //adjacency matrix with edges
    private ArrayList<Node> nodes; //node labels, where their index represents the index in the adj matrix
    private int num_nodes = 0;
    /**
     * Creates a new Graph object with no nodes or (obviously) edges between nodes.
     */
    public Graph() {
        this.adj = new ArrayList<>();
        this.nodes = new ArrayList<>();
    }
    /**
     * Add a new node to the graph. The node automatically gets assigned an int id. 
     */
    public void addNode(String node) {
        Node newNode = new Node(node, num_nodes);
        num_nodes++;
        nodes.add(newNode);
        adj.add(new ArrayList<>());
        for(int i = 0; i < adj.size(); i++) {
            ArrayList<Transition> row = adj.get(i);
            while(row.size() <= num_nodes) {
                adj.add(new GraphEdge()); // make sure every row has enough edges to make a complete matrix
            }
        }
    }
    /**
     * Make sure a passed adjacency list is eligible to be for this graph
     */
    private boolean validateAdjSize(ArrayList<String> adj) {
        if(adj.size() == num_nodes) {
            for(int i = 0; i < num_nodes; i++) {
                if(adj.get(i).size() != num_nodes){ return false;}
            }
        }
        return true;
    }
}
