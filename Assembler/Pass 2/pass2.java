
import java.io.*;

public class pass2 {
    public static void main(String[] args) throws Exception {
        // Open input, symbol table, and literal table files for reading
        BufferedReader inputReader = new BufferedReader(new FileReader("output.txt"));
        BufferedReader symReader = new BufferedReader(new FileReader("symtab.txt"));
        BufferedReader litReader = new BufferedReader(new FileReader("littab.txt"));

        // Open output file for writing
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter("output2.txt"));

        String inputLine;
        String symLine = null;
        String litLine = null;

        // Process each line in the input file
        while ((inputLine = inputReader.readLine()) != null) {
            // Split the line into tokens using ")("
            String[] tokens = inputLine.split("\\)\\(");
            StringBuilder outputLine = new StringBuilder();

            // Process each token
            for (String token : tokens) {
                // Remove parentheses from the token
                token = token.replaceAll("[\\(\\)]", "");

                // Process symbol tokens
                if (token.startsWith("S,")) {
                    // Read the corresponding line from the symbol table
                    if (symLine == null) {
                        symLine = symReader.readLine();
                    }
                    if (symLine != null) {
                        String[] symTokens = symLine.split("\t");
                        if (symTokens.length > 1) {
                            // Get the address from the symbol table
                            int address = Integer.parseInt(symTokens[1]);
                            // Remove "S," prefix and replace with formatted machine code
                            token = token.replace("S,", "");
                            token = getFormattedMachineCode(token, address);
                        }
                    }
                }
                // Process literal tokens
                else if (token.startsWith("L,")) {
                    // Read the corresponding line from the literal table
                    if (litLine == null) {
                        litLine = litReader.readLine();
                    }
                    if (litLine != null) {
                        String[] litTokens = litLine.split("\t");
                        if (litTokens.length > 1) {
                            // Get the address from the literal table
                            int address = Integer.parseInt(litTokens[1]);
                            // Remove "L," prefix and replace with formatted machine code
                            token = token.replace("L,", "");
                            token = getFormattedMachineCode(token, address);
                        }
                    }
                }

                // Append the token to the output line
                outputLine.append(token.replace(",", "")).append(" ");
            }

            // Remove any remaining letters and write the formatted line to the output file
            outputLine = new StringBuilder(outputLine.toString().replaceAll("[A-Za-z]", ""));
            outputWriter.write(outputLine.toString().trim());
            outputWriter.newLine();
        }

        // Close all file readers and writers
        inputReader.close();
        symReader.close();
        litReader.close();
        outputWriter.close();
    }

    // Format machine code with register numbers and address
    private static String getFormattedMachineCode(String instruction, int address) {
        String[] parts = instruction.split(",");
        String opcode = parts[0];
        String[] operands = parts.length > 1 ? parts[1].split(" ") : new String[0];

        String registerNumbers = "";
        for (String operand : operands) {
            // Convert operand to register number
            int regIndex = Integer.parseInt(operand);
            registerNumbers += getRegisterNumber(regIndex);
        }

        // Combine opcode, register numbers, and address
        return opcode + " " + registerNumbers + " " + address;
    }

    // Get register number based on index
    private static String getRegisterNumber(int index) {
        String[] regNumbers = {"01", "02", "03", "04"};
        if (index >= 0 && index < regNumbers.length) {
            return regNumbers[index];
        }
        return "";
    }
}


