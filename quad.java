import java.util.ArrayList;

public class ExpressionConverter {
    static class Quadruple {
        String op, arg1, arg2, result;

        Quadruple(String op, String arg1, String arg2, String result) {
            this.op = op; this.arg1 = arg1; this.arg2 = arg2; this.result = result;
        }

        void print(int index) {
            System.out.printf("%-8d%-10s%-8s%-8s%-8s\n", index, op, arg1, arg2, result);
        }
    }

    static class Triple {
        String op, arg1, arg2;

        Triple(String op, String arg1, String arg2) {
            this.op = op; this.arg1 = arg1; this.arg2 = arg2;
        }

        void print(int index) {
            System.out.printf("%-8d%-10s%-8s%-8s\n", index, op, arg1, arg2);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> tac = new ArrayList<>();
        ArrayList<Quadruple> quadruples = new ArrayList<>();
        ArrayList<Triple> triples = new ArrayList<>();

        int tempCount = 1;

        // Step 1: t1 = b * c
        String t1 = "t" + tempCount++;
        tac.add(t1 + " = b * c");
        quadruples.add(new Quadruple("*", "b", "c", t1));
        triples.add(new Triple("*", "b", "c"));

        // Step 2: t2 = a + t1
        String t2 = "t" + tempCount++;
        tac.add(t2 + " = a + " + t1);
        quadruples.add(new Quadruple("+", "a", t1, t2));
        triples.add(new Triple("+", "a", "(0)"));

        // Step 3: t3 = t2 - d
        String t3 = "t" + tempCount++;
        tac.add(t3 + " = " + t2 + " - d");
        quadruples.add(new Quadruple("-", t2, "d", t3));
        triples.add(new Triple("-", "(1)", "d"));

        // Step 4: x = t3
        tac.add("x = " + t3);
        quadruples.add(new Quadruple("=", t3, "", "x"));
        triples.add(new Triple("=", "(2)", ""));

        // Print TAC
        System.out.println("=== Three Address Code (TAC) ===");
        for (String line : tac) System.out.println(line);

        // Print Quadruples
        System.out.println("\n=== Quadruples ===");
        System.out.printf("%-8s%-10s%-8s%-8s%-8s\n", "Index", "Op", "Arg1", "Arg2", "Result");
        for (int i = 0; i < quadruples.size(); i++)
            quadruples.get(i).print(i);

        // Print Triples
        System.out.println("\n=== Triples ===");
        System.out.printf("%-8s%-10s%-8s%-8s\n", "Index", "Op", "Arg1", "Arg2");
        for (int i = 0; i < triples.size(); i++)
            triples.get(i).print(i);
    }
}
