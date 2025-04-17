import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LexicalAnalyzer {
    static List<String> verbs = Arrays.asList("hate", "like");
    static List<String> keywords = Arrays.asList("if", "then");
    static String actionKeyword = "they";
    static int nounCount = 1;

    static void analyze(String input) {
        input = input.toLowerCase().replace("$", "").trim();
        String[] words = input.split("[\\s\\.]");

        List<String> tokens = new ArrayList<>();

        for (String word : words) {
            if (word.isEmpty()) continue;

            if (keywords.contains(word)) {
                tokens.add("(k)");
            } else if (verbs.contains(word)) {
                tokens.add("(v)");
            } else if (word.equals(actionKeyword)) {
                tokens.add("(a)");
            } else if (word.matches("[a-z]+")) {
                tokens.add("(n," + nounCount + ")");
                nounCount++;
            } else {
                tokens.add("(unk)"); // Unknown token
            }
        }

        // Check for terminating symbol
        if (input.endsWith(".")) {
            tokens.add("(op)");
        }

        // Output
        System.out.println("Tokens: " + String.join(" ", tokens));
        System.out.println("Total Tokens: " + tokens.size());
    }

    public static void main(String[] args) {
        String input = "If dogs hate cats then they chase. $";
        analyze(input);
    }
}
