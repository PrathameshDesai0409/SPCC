import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class pass1 {
    static class Symbol {
        String name;
        int address;
        int index; 

        Symbol(String name, int address, int index) {
            this.name = name;
            this.address = address;
            this.index = index;
        }
    }

    private static Map<String, Symbol> symbolTable = new LinkedHashMap<>();
    private static List<String> pass1Output = new ArrayList<>();
    private static int locCounter = 501;

    public static void main(String[] args) {
        String[] input = {
            "START 501", "A DS 1", "B DS 1", "C DS 1", 
            "READ A", "READ B", "MOVER AREG, A", "ADD AREG, B", 
            "MOVEM AREG, C", "PRINT C", "END"
        };

        pass1(input);
        System.out.println("Pass 1 Output:");
        pass1Output.forEach(System.out::println);

        System.out.println("\nSymbol Table:");
        symbolTable.forEach((key, value) -> 
            System.out.println(key + " -> Address: " + value.address + ", Index: " + value.index)
        );
    }

    private static void pass1(String[] input) {
        int symbolIndex = 0;  // Tracks (S,n)
        for (String line : input) {
            String[] s = line.split("\\s+");
            if (s.length == 0) continue;
            if (s[0].equals("START") && s.length > 1) {
                locCounter = Integer.parseInt(s[1]);
                pass1Output.add("");
            } 
            else if (s.length > 1 && s[1].equals("DS")) { 
                String label = s[0];
                symbolTable.put(label, new Symbol(label, locCounter, symbolIndex));
                pass1Output.add("(S," + symbolIndex + ") (DL,01) (C,1)");
                locCounter++;
                symbolIndex++;
            } 
            else if (s[0].equals("READ") && s.length > 1) { 
                Symbol sym = symbolTable.get(s[1]);
                if (sym != null) {
                    pass1Output.add("(AD,09) (S," + sym.index + ")");
                }
            } 
            else if (s[0].equals("PRINT") && s.length > 1) { 
                Symbol sym = symbolTable.get(s[1]);
                if (sym != null) {
                    pass1Output.add("(AD,10) (S," + sym.index + ")");
                }
            } 
            else if ((s[0].equals("MOVER") || s[0].equals("ADD") || s[0].equals("MOVEM")) && s.length > 2) { 
                Symbol sym = symbolTable.get(s[2]);
                if (sym != null) {
                    String opcode = (s[0].equals("MOVER")) ? "(IS,04)" :
                                    (s[0].equals("ADD")) ? "(IS,01)" :
                                    "(IS,05)"; // MOVEM
                    pass1Output.add(opcode + " (Rg,01) (S," + sym.index + ")");
                }
            } 
            else if (s[0].equals("END")) { 
                pass1Output.add("(AD,02)");
            }
        }
    }
}
