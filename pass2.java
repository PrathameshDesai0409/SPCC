import java.util.HashMap;
import java.util.Map;

public class pass2 {
    public static void main(String[] args) {
        // Symbol Table
        Map<Integer, Integer> symbolTable = new HashMap<>();
        symbolTable.put(0, 501); // A
        symbolTable.put(1, 502); // B
        symbolTable.put(2, 503); // C

        // Intermediate Code (address, instruction)
        String[][] IC = {
            {"501", "(AD,01)", "(C,501)"},
            {"501", "(DL,0)", "(C,1)"},
            {"502", "(DL,0)", "(C,1)"},
            {"503", "(DL,0)", "(C,1)"},
            {"504", "(IS,09)", "(S,0)"},
            {"505", "(IS,09)", "(S,1)"},
            {"506", "(IS,04)", "(RG,01)", "(S,0)"},
            {"507", "(IS,01)", "(RG,01)", "(S,1)"},
            {"508", "(IS,05)", "(RG,01)", "(S,2)"},
            {"509", "(IS,10)", "(S,2)"},
            {"510", "(AD,02)"}
        };

        System.out.println("PASS 2 OUTPUT (Machine Code):");
        System.out.printf("%-10s %-15s\n", "Address", "Machine Code");

        for (String[] line : IC) {
            String address = line[0];
            String opcode = "", reg = "0", mem = "000";

            for (String token : line) {
                if (token.startsWith("(IS")) {
                    opcode = token.substring(4, 6); // e.g., 09 for READ
                } else if (token.startsWith("(RG")) {
                    reg = token.substring(4, 6);    // e.g., 01 for AREG
                } else if (token.startsWith("(S")) {
                    int symIndex = Integer.parseInt(token.substring(3, token.length() - 1));
                    mem = String.valueOf(symbolTable.get(symIndex));
                } else if (token.startsWith("(C")) {
                    mem = token.substring(3, token.length() - 1); // Constant
                }
            }

            if (opcode.length() > 0) {
                System.out.printf("%-10s %s %s %s\n", address, opcode, reg, mem);
            }
        }
    }
}
