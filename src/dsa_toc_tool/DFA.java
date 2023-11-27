package dsa_toc_tool;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DFA {

	private int num_states;
	private int start_num; //which index the states start with (usually 0 or 1)
	private String input_alphabet;
	private int start_state;
	private int[] accept_states;
	private Graph transitions;
	public DFA(String filepath) {
		parsefile(filepath);
	}
	public DFA() {}


	public void parsefile(String filepath) {
		try {
			JSONParser p = new JSONParser();
			JSONObject o = (JSONObject) p.parse(new FileReader(filepath));
			String type_declaration = (String) o.get("dsa_type");
			if(!(type_declaration.equals("dfa"))) {
				throw new Exception("Field \"dsa_type\" is missing or incorrect");
			}

			this.num_states = (int)(long) o.get("num_states");
			this.start_num = (int)(long) o.get("start_numbering");
			JSONArray input_alphabet_field = (JSONArray) o.get("input_alphabet");
			this.start_state = (int)(long) o.get("start_state");
			JSONArray accept_states_field = (JSONArray) o.get("accept_states");
			JSONArray transitions_field = (JSONArray) o.get("transitions");
			// flatten input alphabet into a single string
			this.input_alphabet = "";
			for(int i = 0; i < input_alphabet_field.size(); i++)
				this.input_alphabet += input_alphabet_field.get(i).toString();

			// flatten accept states into an array
			this.accept_states = new int[accept_states_field.size()];
			for(int i = 0; i < accept_states_field.size(); i++)
				this.accept_states[i] = (int)(long) accept_states_field.get(i);

			//Parse transitions into a graph; [from][to] = input character to make transition
			//Initialize nodes:
			this.transitions = new Graph(GraphEdge.Type.LABELLED, true);
			for(int i = start_num; i < this.num_states + start_num; i++)
				this.transitions.addNode(i);

			for(int u = 0; u < transitions_field.size(); u++) {
				JSONArray transitions_for_node = (JSONArray) transitions_field.get(u);
				for(int v = 0; v < transitions_for_node.size(); v++) {
					String input = (String) transitions_for_node.get(v);
					transitions.setEdge(u + start_num, v + start_num, input);
				}
			}


		} catch (Exception e) {
			System.out.println("There was an error while opening the json file: ");
			e.printStackTrace();
		}

	}

	public boolean hasTransition(int startState, char input, int endState) {
		return false;
	}

	public boolean doesAccept(String str) {
		return false;
	}

	public NFA convertToNFA() {
		return new NFA();
	}
	public RegularExpression convertToRegularExpression() {
		return new RegularExpression("");
	}
}