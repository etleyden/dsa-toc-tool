package nfa_to_dfa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NFA extends Graph {

    public NFA(String path) {
        super(); // added temporarily to test out build settings
        try {
            File text_file = new File(path);
            Scanner reader = new Scanner(text_file);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("NOT FOUND");
            e.printStackTrace();
        }
    }

    public void addStates(String input) {
        String[] nodes = input.split(" ");
        for (String curr : nodes) {
            this.addNode(curr);
        }
    }

}
