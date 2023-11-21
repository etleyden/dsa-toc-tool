package dsa_toc_tool;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

public class NFA extends Graph {

    public NFA(String path) {
        super(); // added temporarily to test out build settings
        try {
            File text_file = new File(path);
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

}
