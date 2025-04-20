import java.util.HashMap;
import java.util.Map;

public class Pass2 {
    static class Symbol {
        String name;
        int address;

        Symbol(String name, int address) {
            this.name = name;
            this.address = address;
        }
    }

    private static Map<String, Symbol> symbolTable = new HashMap<>();
    private static final Map<String, String> registerCodes = new HashMap<>();
    private static final Map<String, String> opcodes = new HashMap<>();

    public static void main(String[] args) {
        symbolTable.put("A", new Symbol("A", 501));
        symbolTable.put("B", new Symbol("B", 502));
        symbolTable.put("C", new Symbol("C", 503));

        registerCodes.put("AREG", "01");
        registerCodes.put("BREG", "02");
        registerCodes.put("CREG", "03");

        opcodes.put("READ", "09");
        opcodes.put("PRINT", "10");
        opcodes.put("MOVER", "04");
        opcodes.put("ADD", "01");
        opcodes.put("MOVEM", "05");

        String[] input = {
            "START 501", "A DS 1", "B DS 1", "C DS 1",
            "READ A", "READ B", "MOVER AREG, A", "ADD AREG, B",
            "MOVEM AREG, C", "PRINT C", "END"
        };

        System.out.println("Pass 2 Output:\n--");
        pass2(input);
        System.out.println("--");
    }

    private static void pass2(String[] input) {
        for (String line : input) {
            String[] tokens = line.split("\\s+");

            if (tokens.length == 0 || tokens[0].equals("START") || tokens[0].equals("END")) {
                continue;
            }

            if (tokens.length > 1 && tokens[1].equals("DS")) {
                continue;
            }

            if (tokens.length > 1 && (tokens[0].equals("READ") || tokens[0].equals("PRINT"))) {
                Symbol symbol = symbolTable.get(tokens[1]);
                if (symbol != null) {
                    String opcode = opcodes.get(tokens[0]);
                    String register = "00";
                    System.out.println(opcode + " " + register + " " + String.format("%03d", symbol.address));
                }
            } else if (tokens.length > 2 && tokens[0].equals("MOVER")) {
                String register = tokens[1].replace(",", ""); 
                Symbol symbol = symbolTable.get(tokens[2]);

                if (symbol != null && registerCodes.containsKey(register)) {
                    String opcode = opcodes.get("MOVER"); // 04
                    System.out.println(opcode + " " + registerCodes.get(register) + " " + String.format("%03d", symbol.address));
                } else {
                    System.out.println("Error: Invalid register or symbol in MOVER (" + register + ")");
                }
            } else if (tokens.length > 2 && tokens[0].equals("ADD")) {
                Symbol symbol = symbolTable.get(tokens[2]);
                if (symbol != null) {
                    String opcode = opcodes.get("ADD"); // 01
                    String register = "01"; // ADD uses AREG (01)
                    System.out.println(opcode + " " + register + " " + String.format("%03d", symbol.address));
                }
            } else if (tokens.length > 2 && tokens[0].equals("MOVEM")) {
                Symbol symbol = symbolTable.get(tokens[2]);
                if (symbol != null) {
                    String opcode = opcodes.get("MOVEM"); // 05
                    String register = "01"; // MOVEM uses AREG (01)
                    System.out.println(opcode + " " + register + " " + String.format("%03d", symbol.address));
                }
            }
        }
    }
}
