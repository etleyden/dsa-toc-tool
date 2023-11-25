package dsa_toc_tool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DFATest {

    @Test
    void parsefile() {
        DFA d = new DFA("example_json/dfa1.json");
    }

    @Test
    void hasTransition() {
    }

    @Test
    void doesAccept() {
    }

    @Test
    void convertToNFA() {
    }

    @Test
    void convertToRegularExpression() {
    }
}