package dsa_toc_tool;

import java.util.ArrayList;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

public class NFA {
    private Graph transitions;
    private int num_states;
    private int start_num;
    private ArrayList<String> input_alphabet;
    private String start_state;
    private ArrayList<String> accept_states;

    /**
     * Takes the filepath to a .json description of the NFA, and generates an
     * instance of this class.
     * 
     * @param filepath the relative path to the json file.
     */
    public NFA(String filepath) {
        this();
        parseFile(filepath);
    }

    /**
     * Default construct for NFA that requires no .json description
     */
    public NFA() {
        this.transitions = new Graph();
        this.num_states = 0;
        this.start_num = 0;
        this.input_alphabet = new ArrayList<>();
        this.start_state = "";
        this.accept_states = new ArrayList<>();
    }

    /**
     * Parse .json description into NFA object
     * Adds nodes to NFA's graph object according to states
     * Adds edges to nodes of NFA's graph object
     * 
     * @param filepath path of JSON file
     */
    public void parseFile(String filepath) {
        try {
            JSONParser p = new JSONParser();
            JSONObject o = (JSONObject) p.parse(new FileReader(filepath));
            String dsa_type = (String) o.get("dsa_type");
            if (!(dsa_type.equals("nfa"))) {
                throw new Exception();
            }
            // Parse JSON into NFA variables
            this.num_states = (int) (long) o.get("num_states");
            this.start_num = (int) (long) o.get("start_numbering");
            this.start_state = (String) o.get("start_state");
            JSONArray accept_field = (JSONArray) o.get("accept_states");
            JSONArray input_field = (JSONArray) o.get("input_alphabet");
            JSONArray transitions_field = (JSONArray) o.get("transitions");
            // Convert JSONArray of accept states into ArrayList
            for (int i = 0; i < accept_field.size(); i++) {
                String c = (String) accept_field.get(i);
                this.accept_states.add(c);
            }
            // Convert JSONArray of input alphabet into ArrayList
            for (int i = 0; i < input_field.size(); i++) {
                String c = (String) input_field.get(i);
                this.input_alphabet.add(c);
            }
            for (int i = start_num; i <= num_states; i++) {
                // Adds nodes to graph
                transitions.addNode(Integer.toString(i));
            }
            for (int i = 0; i < transitions_field.size(); i++) {
                JSONArray transition = (JSONArray) transitions_field.get(i);
                int curr_state = i + start_num;
                for (int j = 0; j < transition.size(); j++) {
                    JSONArray pair = (JSONArray) transition.get(j);
                    // Grabs input of Edge and destination from JSON pair
                    String input = (String) pair.get(0);
                    int next_state = (int) (long) pair.get(1);
                    GraphEdge n = transitions.getEdge(curr_state, next_state);
                    // setEdge with label
                    String label = (n.getType() == GraphEdge.Type.NULL) ? "" : n.getLabel();
                    transitions.setEdge(curr_state, next_state, label + input);
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    /**
     * Verifies a transition between two states and a given input
     * 
     * @param startState the start state
     * @param input      the input character which triggers the transition
     * @param endState   the end state
     * @return true if the transition exists, false if not
     */
    public boolean hasTransition(int startState, String input, int endState) {
        try {
            GraphEdge n = transitions.getEdge(startState, endState);
            GraphEdge.Type type = n.getType();
            if (type == GraphEdge.Type.LABELLED) {
                return n.getLabel().contains(input);
            } else if (type == GraphEdge.Type.WEIGHTED) {
                return n.getWeight() == Integer.parseInt(input);
            } else if (type == GraphEdge.Type.NULL) {
                return input.equals("");
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    /**
     * Accepts a string, runs the NFA, and returns whether or not it is in the
     * language.
     * 
     * @param str the string to be accepted
     * @return the result.
     */
    public boolean doesAccept(String str) {

        return false;
    }

    /**
     * Converts this instance of an NFA to a DFA.
     * 
     * @return a DFA instance
     */
    public DFA convertToDFA() {
        return new DFA();
    }

    /**
     * Converts this instance of an NFA to a Regular Expression
     * 
     * @return
     */
    public RegularExpression convertToRegularExpression() {
        return new RegularExpression("");
    }
}
