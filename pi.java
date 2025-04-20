import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ThreeAddressGenerator {

    static class Quadruple {
        String op, arg1, arg2, result;
        Quadruple(String op, String arg1, String arg2, String result) {
            this.op = op; this.arg1 = arg1; this.arg2 = arg2; this.result = result;
        }
    }

    static class Triple {
        String op, arg1, arg2;
        Triple(String op, String arg1, String arg2) {
            this.op = op; this.arg1 = arg1; this.arg2 = arg2;
        }
    }

    // Operator precedence
    static int precedence(String op) {
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> 0;
        };
    }

    // Convert infix to postfix using Shunting-yard algorithm
    static List<String> infixToPostfix(String expr) {
        Stack<String> stack = new Stack<>();
        List<String> postfix = new ArrayList<>();
        String[] tokens = expr.split(" ");

        for (String token : tokens) {
            if (Character.isLetterOrDigit(token.charAt(0)) || token.equals("pi")) {
                postfix.add(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.peek().equals("(")) {
                    postfix.add(stack.pop());
                }
                stack.pop(); // remove "("
            } else { // operator
                while (!stack.isEmpty() && precedence(token) <= precedence(stack.peek())) {
                    postfix.add(stack.pop());
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }
        return postfix;
    }

    public static void main(String[] args) {
        String assign = "x = a * pi * 180 + b * pi * 2";

        String[] parts = assign.split("=");
        String lhs = parts[0].trim();
        String rhs = parts[1].replaceAll(";", "").trim();

        List<String> postfix = infixToPostfix(rhs);
        Stack<String> stack = new Stack<>();
        List<Quadruple> quads = new ArrayList<>();
        List<Triple> triples = new ArrayList<>();
        List<String> results = new ArrayList<>();

        int tempCount = 0;
        for (String token : postfix) {
            if (Character.isLetterOrDigit(token.charAt(0)) || token.equals("pi")) {
                stack.push(token);
            } else {
                String arg2 = stack.pop();
                String arg1 = stack.pop();
                String result = "t" + tempCount++;
                quads.add(new Quadruple(token, arg1, arg2, result));
                stack.push(result);

                // Replace temp in triples with index
                String tArg1 = results.contains(arg1) ? "[" + results.indexOf(arg1) + "]" : arg1;
                String tArg2 = results.contains(arg2) ? "[" + results.indexOf(arg2) + "]" : arg2;
                triples.add(new Triple(token, tArg1, tArg2));
                results.add(result);
            }
        }

        // Final assignment: x = tN
        String finalTemp = stack.pop();
        quads.add(new Quadruple("=", finalTemp, "", lhs));
        triples.add(new Triple("=", results.contains(finalTemp) ? "[" + results.indexOf(finalTemp) + "]" : finalTemp, ""));
        
        // Print TAC
        System.out.println("Three Address Code:");
        for (Quadruple q : quads) {
            if (q.op.equals("=")) {
                System.out.println(q.result + " = " + q.arg1);
            } else {
                System.out.println(q.result + " = " + q.arg1 + " " + q.op + " " + q.arg2);
            }
        }

        // Print Quadruples
        System.out.println("\nQuadruples:");
        System.out.printf("%-10s%-10s%-10s%-10s\n", "Op", "Arg1", "Arg2", "Result");
        for (Quadruple q : quads) {
            System.out.printf("%-10s%-10s%-10s%-10s\n", q.op, q.arg1, q.arg2, q.result);
        }

        // Print Triples
        System.out.println("\nTriples:");
        System.out.printf("%-5s%-10s%-10s%-10s\n", "Index", "Op", "Arg1", "Arg2");
        for (int i = 0; i < triples.size(); i++) {
            Triple t = triples.get(i);
            System.out.printf("%-5d%-10s%-10s%-10s\n", i, t.op, t.arg1, t.arg2);
        }
    }
}
