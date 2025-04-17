import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class pass1 {
    public static void main(String[] args) {
        List<String> inputLines = Arrays.asList(
            "ABC START",
            "MACRO",
            "ADD &ARG1, &ARG2",
            "L 1, &ARG1",
            "A 1, &ARG2",
            "MEND",
            "MACRO",
            "SUB &ARG3, &ARG4",
            "L 1, &ARG3",
            "S 1, &ARG4",
            "MEND",
            "ADD DATA1, DATA2",
            "SUB DATA1, DATA2",
            "DATA1 DC F'9'",
            "DATA2 DC F'5'",
            "END"
        );

        List<List<String>> mdt = new ArrayList<>();
        Map<String, Integer> mnt = new LinkedHashMap<>();
        List<String> ala = new ArrayList<>();

        boolean isMacroDef = false;
        for (String line : inputLines) {
            List<String> tokens = Arrays.asList(line.trim().split("\\s+"));

            if (tokens.get(0).equals("MACRO")) {
                isMacroDef = true;
                continue;
            }

            if (isMacroDef) {
                mdt.add(new ArrayList<>(tokens));
                if (tokens.get(0).equals("MEND")) {
                    isMacroDef = false;
                }
            }
        }

        // MDT indices
        List<Integer> mdtIndices = new ArrayList<>();
        mdtIndices.add(0);
        for (int i = 0; i < mdt.size(); i++) {
            if (mdt.get(i).contains("MEND") && i + 1 < mdt.size()) {
                mdtIndices.add(i + 1);
            }
        }

        // Macro names
        List<String> macroNames = new ArrayList<>();
        for (int idx : mdtIndices) {
            macroNames.add(mdt.get(idx).get(0));
        }

        // Fill MNT
        for (int i = 0; i < macroNames.size(); i++) {
            mnt.put(macroNames.get(i), mdtIndices.get(i));
        }

        // Fill ALA
        for (int idx : mdtIndices) {
            for (int i = 1; i < mdt.get(idx).size(); i++) {
                String arg = mdt.get(idx).get(i).replace(",", "").replace("=", "");
                if (!ala.contains(arg)) {
                    ala.add(arg);
                }
            }
        }

        // Replace arguments in MDT
        for (int i = 0; i < mdt.size(); i++) {
            if (!mdtIndices.contains(i)) {
                List<String> line = mdt.get(i);
                for (int j = 0; j < line.size(); j++) {
                    String word = line.get(j).replace(",", "");
                    if (ala.contains(word)) {
                        int idx = ala.indexOf(word) + 1;
                        line.set(j, "#" + idx);
                    }
                }
            }
        }

        // MDT strings
        List<String> mdtStrings = new ArrayList<>();
        for (List<String> line : mdt) {
            mdtStrings.add(String.join(" ", line));
        }

        // Print Outputs
        System.out.println("\nPASS 1:");
        
        System.out.println("\nMNT:");
        System.out.printf("%-10s %-15s %-10s\n", "Index", "Macro Name", "MDT Index");
        int count = 1;
        for (Map.Entry<String, Integer> entry : mnt.entrySet()) {
            System.out.printf("%-10d %-15s %-10d\n", count++, entry.getKey(), entry.getValue() + 1);
        }

        System.out.println("\nALA:");
        System.out.printf("%-10s %-10s\n", "Index", "Argument");
        for (int i = 0; i < ala.size(); i++) {
            System.out.printf("%-10d %-10s\n", i + 1, ala.get(i));
        }

        System.out.println("\nMDT:");
        System.out.printf("%-10s %-30s\n", "Index", "Macro Definition");
        for (int i = 0; i < mdtStrings.size(); i++) {
            System.out.printf("%-10d %-30s\n", i + 1, mdtStrings.get(i));
        }

        System.out.println("\nMNTC: " + (mnt.size() + 1) + ", MDTC: " + (mdt.size() + 1));
    }
}
