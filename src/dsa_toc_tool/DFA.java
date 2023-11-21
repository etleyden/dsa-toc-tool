package dsa_toc_tool;

public class DFA extends Graph {
	public DFA(String filepath) {
		
	}
	public DFA() {}

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