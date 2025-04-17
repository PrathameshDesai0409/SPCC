public class ThreeAddressCodeGenerator {

    static int tempVarCount = 1;

    // Method to generate temporary variable names like t1, t2, etc.
    public static String newTemp() {
        return "t" + (tempVarCount++);
    }

    public static void main(String[] args) {
        String expr = "x = (a + b) * (c - d)";
        System.out.println("Input Expression: " + expr + "\n");

        // Simulate parsing and TAC generation manually

        String t1 = newTemp();
        System.out.println(t1 + " = a + b");

        String t2 = newTemp();
        System.out.println(t2 + " = c - d");

        String t3 = newTemp();
        System.out.println(t3 + " = " + t1 + " * " + t2);

        System.out.println("x = " + t3);
    }
}
