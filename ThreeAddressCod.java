import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class ThreeAddressCode {
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
        String expression = "x = a * b / ( c - d )";
        List<String> postfix = infixToPostfix(expression);
        List<Quadruple> quads = new ArrayList<>();
        List<Triple> triples = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        List<String> results = new ArrayList<>();
        int tempCount = 0;

        for (String token : postfix) {
            if (Character.isLetter(token.charAt(0))) {
                stack.push(token);
            } else {
                String arg2 = stack.pop();
                String arg1 = stack.pop();
                String result = "t" + tempCount++;
                quads.add(new Quadruple(token, arg1, arg2, result));

                // Replace args with indices for triples
                String tArg1 = results.contains(arg1) ? "[" + results.indexOf(arg1) + "]" : arg1;
                String tArg2 = results.contains(arg2) ? "[" + results.indexOf(arg2) + "]" : arg2;

                triples.add(new Triple(token, tArg1, tArg2));
                results.add(result);
                stack.push(result);
            }
        }

        // Final assignment to x
        String finalResult = stack.pop();
        quads.add(new Quadruple("=", finalResult, "", "x"));
        triples.add(new Triple("=", results.contains(finalResult) ? "[" + results.indexOf(finalResult) + "]" : finalResult, ""));

        // Print results
        System.out.println("Three Address Code:");
        for (Quadruple q : quads) {
            if (q.op.equals("="))
                System.out.println(q.result + " = " + q.arg1);
            else
                System.out.println(q.result + " = " + q.arg1 + " " + q.op + " " + q.arg2);
        }

        System.out.println("\nQuadruples:");
        System.out.printf("%-10s%-10s%-10s%-10s\n", "Op", "Arg1", "Arg2", "Result");
        for (Quadruple q : quads) {
            System.out.printf("%-10s%-10s%-10s%-10s\n", q.op, q.arg1, q.arg2, q.result);
        }

        System.out.println("\nTriples:");
        System.out.printf("%-10s%-10s%-10s\n", "Op", "Arg1", "Arg2");
        int index = 0;
        for (Triple t : triples) {
            System.out.printf("[%d] %-10s%-10s%-10s\n", index++, t.op, t.arg1, t.arg2);
        }
    }

    // Converts infix expression to postfix
    static List<String> infixToPostfix(String expr) {
        String[] tokens = expr.split(" ");
        List<String> postfix = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        Map<String, Integer> precedence = Map.of("+", 1, "-", 1, "*", 2, "/", 2);

        for (String token : tokens) {
            if (token.matches("[a-zA-Z]")) {
                postfix.add(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.peek().equals("(")) {
                    postfix.add(stack.pop());
                }
                stack.pop();
            } else if (precedence.containsKey(token)) {
                while (!stack.isEmpty() && precedence.getOrDefault(stack.peek(), 0) >= precedence.get(token)) {
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
}
