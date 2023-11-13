package dsa_toc_tool;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    /*
     * TODO: Generate some edge cases
     * Tests adding a node with a string label
     * Tests looking up the node with its ID, and with its Label
     */
    @Test
    void addNode() {
        // Test a graph with String labels
        Graph g = new Graph();
        int a_id = g.addNode("A");
        int z_id = g.addNode("Z");
        assertTrue(g.nodeExists("A"));
        assertTrue(g.nodeExists("Z"));
        assertFalse(g.nodeExists("Hi Mom!"));
        assertFalse(g.nodeExists("0"));
        assertTrue(g.nodeExists(a_id));
        assertTrue(g.nodeExists(z_id));
        for (int i = 0; i < 5; i++) {
            int test_id;
            do {
                test_id = (int) Math.floor(Math.random() * 100);
            } while (test_id == a_id && test_id == z_id);
            assertFalse(g.nodeExists(test_id));
        }
        // Test a graph that utilizes int IDs
        g = new Graph();
        int id_a = (int) Math.floor(Math.random() * 100);
        int id_b = (int) Math.floor(Math.random() * 100);
        String label_a = g.addNode(id_a);
        String label_b = g.addNode(id_b);
        assertTrue(g.nodeExists(id_a));
        assertTrue(g.nodeExists(id_b));
        assertTrue(g.nodeExists(label_a));
        assertTrue(g.nodeExists(label_b));
        for (int i = 0; i < 5; i++) {
            String fake_label;
            do {
                fake_label = EthansUtilities.randomString(5);
            } while(Objects.equals(fake_label, label_a) && Objects.equals(fake_label, label_b));
            assertFalse(g.nodeExists(fake_label));
        }

    }

    @Test
    void getNodeLabel() {
        Graph g = new Graph(GraphEdge.Type.WEIGHTED);
        g.addNode("A"); //the id should be assigned 0 automatically
        g.addNode("Z"); //the id should be assigned 1 automatically
        g.addNode("B");
        assertEquals("A", g.getNodeLabel(0));
        assertEquals("Z", g.getNodeLabel(1));
        assertEquals("B", g.getNodeLabel(2));
        // Testing how the graph class handles collisions between a pre-existing label and its id
        g.addNode(10);
        assertEquals("E", g.getNodeLabel(10));
    }

    @Test
    void getNodeId() {
        Graph g = new Graph();
        g.addNode(255);
        g.addNode(55);
        g.addNode("2");
        //TODO: Adding a node with a label "5" and then adding a node with an integer value 5 is possible
        //This is potentially confusing and can lead to errors. This should be prevented in source
        //and tested for.

        // since a node with id 2 should already exist, this should return null
        assertTrue(Objects.isNull(g.addNode(2)));
        // 255 will be assigned FF, so "FF" cannot be added as a label.
        assertEquals(-1, g.addNode("FF"));
        assertEquals(255, g.getNodeId("FF"));
        assertEquals(55, g.getNodeId("37"));
    }

    @Test
    void setEdge() {
        //There are four setEdge() methods
        //setting weight with id, setting weight with label, setting label with id, and setting label with label
        Graph g = new Graph();
        //Setting weight with id
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.setEdge(3, 1, 2);
        g.setEdge(3, 2, 6);
        g.setEdge(2, 1, 9);
        g.setEdge(1, 0, 5);
        g.setEdge(2, 0, 8);

    }

}