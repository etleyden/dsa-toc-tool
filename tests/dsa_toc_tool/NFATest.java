import dsa_toc_tool.NFA;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class NFATest {
    public static void main(String[] args) {
        NFA test = new NFA("example_json\\nfa1.json");
    }

    @Test
    public void test_hasTransition() {
        NFA nfa = new NFA("example_json/nfa1.json");
        assertTrue(nfa.hasTransition(1, "1", 1));
        assertTrue(nfa.hasTransition(1, "1", 2));
        assertTrue(nfa.hasTransition(2, "", 3));
        assertFalse(nfa.hasTransition(0, "", 4));
        assertFalse(nfa.hasTransition(4, "", 4));
        assertFalse(nfa.hasTransition(2, "1", 3));
    }

    /*
     * public void doesAccept() {
     * NFA nfa = new NFA("example_json/nfa1.json");
     * assertTrue(nfa.doesAccept("1111010101"));
     * assertFalse(nfa.doesAccept(""));
     * assertTrue(nfa.doesAccept("11"));
     * assertFalse(nfa.doesAccept("1"));
     * assertFalse(nfa.doesAccept("111000"));
     * assertTrue(nfa.doesAccept("111010"));
     * }
     */
}
