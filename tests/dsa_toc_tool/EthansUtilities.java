import dsa_toc_tool.*;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.nio.charset.StandardCharsets;

// This is my personal Java utilities class for various methods
// Used in the tests --> NOT in production
public class EthansUtilities {
    public static String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    /**
     * Generates a random alphanumeric string with a given length
     * 
     * @param length length of the string to be generated, no more, no less.
     * @return the random string
     */
    public static String randomString(int length) {
        String s = "";
        for (int i = 0; i <= length; i++) {
            s += EthansUtilities.AlphaNumericString.charAt(
                    (int) Math.floor(Math.random() * EthansUtilities.AlphaNumericString.length()));
        }
        return s;
    }
}
