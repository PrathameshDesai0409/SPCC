import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class LexicalAnalyzer {
    private static final String[] KEYWORDS = {"If", "then"};
    private static final String[] VERBS = {"hate", "like"};
    private static final String[] ACTIONS = {"they"};
    
    private static final Map<String, Integer> st = new LinkedHashMap<>();
    private static int symbolIndex = 1;
    private static int tokenCount = 0;
    public static void main (String[] args){
        String input = "If dogs hate cats then they chase. If cats like milk then they drink.";
        System.out.println("Input: "+ input);
        System.out.println("\nOutput: ");
        analyze(input);

        System.out.println("\nSymbol Table: ");
        for (Map.Entry<String, Integer> entry: st.entrySet()){
            System.out.println(entry.getKey()+ "->[" + entry.getValue()+"]");
        }
        System.out.println("\nTotal number of tokens: " + tokenCount);

    }
    private static void analyze(String input){
        String[] words = input.split("\\s+");
        for (String word : words){
            String token = classifyToken(word.replace(".", ""));
            if (!token.isEmpty()){
                System.out.println((token + " "));
                tokenCount++;
            }
        }
    }
    
    private static String classifyToken(String word){
        if (Arrays.asList(KEYWORDS).contains(word)){
            return "(k)";
        }else if (Arrays.asList(VERBS).contains(word)){
            return "(v)";
        } else if (Arrays.asList(ACTIONS).contains(word)){
            return "(a)";
        } else {
            if (!st.containsKey(word)){
                st.put(word, symbolIndex++);
            } 
            return "(n," + st.get(word) + ")";
        }
    }
}
