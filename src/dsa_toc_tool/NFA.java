package dsa_toc_tool;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

public class NFA extends Graph {

    /**
     * Takes the filepath to a .json description of the NFA, and generates an instance of this class.
     * @param filepath the relative path to the json file.
     */
    public NFA(String filepath) {
        super(); // added temporarily to test out build settings
        try {
            File text_file = new File(filepath);
            Scanner reader = new Scanner(text_file);

            while(reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("NOT FOUND");
            e.printStackTrace();
        }
    }
    public NFA() {

    }

    /**
     * Verifies a transition between two states and a given input
     * @param startState the start state
     * @param input the input character which triggers the transition
     * @param endState the end state
     * @return true if the transition exists, false if not
     */
    public boolean hasTransition(int startState, char input, int endState) {
        return false;
    }

    /**
     * Accepts a string, runs the NFA, and returns whether or not it is in the language.
     * @param str the string to be accepted
     * @return the result.
     */
    public boolean doesAccept(String str) {
        return false;
    }


    /**
     * Converts this instance of an NFA to a DFA.
     * @return a DFA instance
     */
    public DFA convertToDFA() {
        return new DFA();
    }

    /**
     * Converts this instance of an NFA to a Regular Expression
     * @return
     */
    public RegularExpression convertToRegularExpression() {
        return new RegularExpression("");
    }
}
