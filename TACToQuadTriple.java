import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TACToQuadTriple {
    static class Quadruple {
        String op, arg1, arg2, result;
        Quadruple(String op, String arg1, String arg2, String result) {
            this.op = op;
            this.arg1 = arg1;
            this.arg2 = arg2;
            this.result = result;
        }
    }

    static class Triple {
        String op, arg1, arg2;
        Triple(String op, String arg1, String arg2) {
            this.op = op;
            this.arg1 = arg1;
            this.arg2 = arg2;
        }
    }

    public static void main(String[] args) {
        // Given TAC as input
        String[] tac = {
            "f = c + d",
            "e = a - f",
            "g = b * e"
        };

        List<Quadruple> quads = new ArrayList<>();
        List<Triple> triples = new ArrayList<>();
        Map<String, Integer> resultIndex = new HashMap<>();

        int index = 0;
        for (String line : tac) {
            line = line.replace("=", " = ").replace("+", " + ")
                       .replace("-", " - ").replace("*", " * ")
                       .replace("/", " / ");
            String[] parts = line.trim().split("\\s+");
            String result = parts[0];
            String arg1 = parts[2];
            String op = parts[3];
            String arg2 = parts[4];

            // Save for Quadruples
            quads.add(new Quadruple(op, arg1, arg2, result));
            resultIndex.put(result, index);

            // Replace args with index if already seen (Triples)
            String tArg1 = resultIndex.containsKey(arg1) ? "[" + resultIndex.get(arg1) + "]" : arg1;
            String tArg2 = resultIndex.containsKey(arg2) ? "[" + resultIndex.get(arg2) + "]" : arg2;
            triples.add(new Triple(op, tArg1, tArg2));

            index++;
        }

        // Print Quadruples
        System.out.println("Quadruples:");
        System.out.printf("%-10s%-10s%-10s%-10s\n", "Op", "Arg1", "Arg2", "Result");
        for (Quadruple q : quads) {
            System.out.printf("%-10s%-10s%-10s%-10s\n", q.op, q.arg1, q.arg2, q.result);
        }

        // Print Triples
        System.out.println("\nTriples:");
        System.out.printf("%-10s%-10s%-10s\n", "Op", "Arg1", "Arg2");
        for (int i = 0; i < triples.size(); i++) {
            Triple t = triples.get(i);
            System.out.printf("[%d] %-10s%-10s%-10s\n", i, t.op, t.arg1, t.arg2);
        }
    }
}
