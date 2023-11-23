package dsa_toc_tool;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.lang.reflect.Array;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

public class NFA extends Graph {
    private int num_states;
    private int start_num;
    private JSONArray input_alphabet;
    private int start_state;
    private JSONArray accept_states;
    private JSONArray transitions;

    /**
     * Takes the filepath to a .json description of the NFA, and generates an
     * instance of this class.
     * 
     * @param filepath the relative path to the json file.
     */
    public NFA(String filepath) {
        super(); // added temporarily to test out build settings
        parseFile(filepath);
    }

    public NFA() {

    }

    /**
     * Parse JSON file into NFA
     * 
     * @param filePath path of JSON file
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
