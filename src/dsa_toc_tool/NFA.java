package dsa_toc_tool;

import java.io.FileReader;
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.lang.reflect.Array;
>>>>>>> e2aa24e (Added NFA test cases, finished parseFile method, finished hasTransition method, added test libraries and JSON library for VSCode)
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

<<<<<<< HEAD
public class NFA {
    private Graph graph;
=======
public class NFA extends Graph {
>>>>>>> e2aa24e (Added NFA test cases, finished parseFile method, finished hasTransition method, added test libraries and JSON library for VSCode)
    private int num_states;
    private int start_num;
    private JSONArray input_alphabet;
    private int start_state;
    private JSONArray accept_states;
<<<<<<< HEAD
=======
    private JSONArray transitions;
>>>>>>> e2aa24e (Added NFA test cases, finished parseFile method, finished hasTransition method, added test libraries and JSON library for VSCode)

    /**
     * Takes the filepath to a .json description of the NFA, and generates an
     * instance of this class.
     * 
     * @param filepath the relative path to the json file.
     */
    public NFA(String filepath) {
<<<<<<< HEAD
        this();
        parseFile(filepath);
    }

    /**
     * Default construct for NFA that requires no .json description
     */
    public NFA() {
        this.graph = new Graph();
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
=======
        super(); // added temporarily to test out build settings
        parseFile(filepath);
    }

    public NFA() {

    }

    /**
     * Parse JSON file into NFA
     * 
     * @param filePath path of JSON file
>>>>>>> e2aa24e (Added NFA test cases, finished parseFile method, finished hasTransition method, added test libraries and JSON library for VSCode)
     */
    public void parseFile(String filepath) {
        try {
            JSONParser p = new JSONParser();
            JSONObject o = (JSONObject) p.parse(new FileReader(filepath));
            String dsa_type = (String) o.get("dsa_type");
            if (!(dsa_type.equals("nfa"))) {
                throw new Exception();
            }
<<<<<<< HEAD
            // Parse JSON into NFA variables
=======
>>>>>>> e2aa24e (Added NFA test cases, finished parseFile method, finished hasTransition method, added test libraries and JSON library for VSCode)
            this.num_states = (int) (long) o.get("num_states");
            this.start_num = (int) (long) o.get("start_numbering");
            this.input_alphabet = (JSONArray) o.get("input_alphabet");
            this.start_state = (int) (long) o.get("start_state");
            this.accept_states = (JSONArray) o.get("accept_states");
<<<<<<< HEAD
            JSONArray transitions = (JSONArray) o.get("transitions");
            for (int i = start_num; i <= num_states; i++) {
                // Adds nodes to graph
                graph.addNode(i);
            }
            for (int i = 0; i < transitions.size(); i++) {
                JSONArray transition = (JSONArray) transitions.get(i);
                int curr_state = i + start_num;
                for (int j = 0; j < transition.size(); j++) {
                    JSONArray pair = (JSONArray) transition.get(j);
                    // Grabs input of Edge and destination from JSON pair
                    String input = (String) pair.get(0);
                    int next_state = (int) (long) pair.get(1);
                    // Determines whether to setEdge with weight or label
                    if (input.matches("\\d+")) {
                        graph.setEdge(curr_state, next_state, Integer.parseInt(input));
                    } else {
                        graph.setEdge(curr_state, next_state, input);
=======
            this.transitions = (JSONArray) o.get("transitions");
            for (int i = start_num; i <= num_states; i++) {
                this.addNode(i);
                System.out.println("Node " + i + " " + nodeExists(i));
            }
            for (int i = 0; i < transitions.size(); i++) {
                JSONArray transition = (JSONArray) transitions.get(i);
                int curr_state = i + 1;
                for (int j = 0; j < transition.size(); j++) {
                    JSONArray pair = (JSONArray) transition.get(j);
                    String input = (String) pair.get(0);
                    int next_state = (int) (long) pair.get(1);
                    if ((input.equals(""))) {
                        setEdge(curr_state, next_state, input);
                    } else {
                        setEdge(curr_state, next_state, Integer.parseInt(input));
>>>>>>> e2aa24e (Added NFA test cases, finished parseFile method, finished hasTransition method, added test libraries and JSON library for VSCode)
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
<<<<<<< HEAD
=======

>>>>>>> e2aa24e (Added NFA test cases, finished parseFile method, finished hasTransition method, added test libraries and JSON library for VSCode)
    /**
     * Verifies a transition between two states and a given input
     * 
     * @param startState the start state
     * @param input      the input character which triggers the transition
     * @param endState   the end state
     * @return true if the transition exists, false if not
     */
    public boolean hasTransition(int startState, String input, int endState) {
<<<<<<< HEAD
        try {
            GraphEdge n = graph.getEdge(startState, endState);
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
=======
        for (int i = 0; i < transitions.size(); i++) {
            int curr_state = i + 1;
            if (curr_state == startState) {
                JSONArray transition = (JSONArray) transitions.get(i);
                for (int j = 0; j < transition.size(); j++) {
                    JSONArray pair = (JSONArray) transition.get(j);
                    String inp = (String) pair.get(0);
                    int next_state = (int) (long) pair.get(1);
                    if ((inp.equals(input)) && next_state == endState) {
                        return true;
                    }
                }
            }
>>>>>>> e2aa24e (Added NFA test cases, finished parseFile method, finished hasTransition method, added test libraries and JSON library for VSCode)
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
