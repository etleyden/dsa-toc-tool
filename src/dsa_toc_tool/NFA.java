package dsa_toc_tool;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

public class NFA {
    private Graph transitions;
    private int num_states;
    private int start_num;
    private JSONArray input_alphabet;
    private int start_state;
    private JSONArray accept_states;

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
        this.input_alphabet = new JSONArray();
        this.start_state = -1;
        this.accept_states = new JSONArray();
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
            this.num_states = (int) (long) o.get("num_states");
            this.start_num = (int) (long) o.get("start_numbering");
            this.input_alphabet = (JSONArray) o.get("input_alphabet");
            this.start_state = (int) (long) o.get("start_state");
            this.accept_states = (JSONArray) o.get("accept_states");
            JSONArray trans = (JSONArray) o.get("transitions");
            for (int i = start_num; i <= num_states; i++) {
                transitions.addNode(i);
            }
            for (int i = 0; i < trans.size(); i++) {
                JSONArray transition = (JSONArray) trans.get(i);
                int curr_state = i + 1;
                for (int j = 0; j < transition.size(); j++) {
                    JSONArray pair = (JSONArray) transition.get(j);
                    // Grabs input of Edge and destination from JSON pair
                    String input = (String) pair.get(0);
                    int next_state = (int) (long) pair.get(1);
                    if ((input.equals(""))) {
                        transitions.setEdge(curr_state, next_state, input);
                    } else {
                        transitions.setEdge(curr_state, next_state, Integer.parseInt(input));
                    }
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
                return n.getLabel().equals(input);
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
