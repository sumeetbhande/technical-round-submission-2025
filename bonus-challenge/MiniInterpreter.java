import java.util.HashMap;
import java.util.Map;

/**
 * A simple interpreter that can handle:
 * - let statements to create variables
 * - if statements to check conditions and print messages
 */
public class MiniInterpreter {

    // This map keeps track of variables and their numbers
    private Map<String, Integer> variables = new HashMap<>();

    /**
     * This runs one line of code.
     * It checks if it's a let or if statement and runs the right thing.
     */
    public void evaluate(String line) {
        line = line.trim();
        if (line.startsWith("let ")) {
            evaluateLet(line);
        } else if (line.startsWith("if ")) {
            evaluateIf(line);
        } else {
            System.out.println("I don't know how to run this: " + line);
        }
    }

    // Handle let statements like: let x = 5;
    private void evaluateLet(String line) {
        // Take off 'let ' and the semicolon at the end
        line = line.substring(4).trim();
        if (!line.endsWith(";")) {
            System.out.println("Oops, missing ';' in let statement");
            return;
        }
        line = line.substring(0, line.length() - 1).trim();

        // Split at '=' to get variable name and value
        String[] parts = line.split("=");
        if (parts.length != 2) {
            System.out.println("Let statement looks wrong");
            return;
        }
        String varName = parts[0].trim();
        String valueStr = parts[1].trim();

        try {
            int value = Integer.parseInt(valueStr);
            variables.put(varName, value);  // Save the variable
        } catch (NumberFormatException e) {
            System.out.println("Can't use this value in let: " + valueStr);
        }
    }

    // Handle if statements like: if (x > 3) { print("yes"); }
    private void evaluateIf(String line) {
        // Find the condition inside parentheses
        int condStart = line.indexOf('(');
        int condEnd = line.indexOf(')');
        if (condStart == -1 || condEnd == -1 || condEnd < condStart) {
            System.out.println("If statement condition is wrong");
            return;
        }
        String condition = line.substring(condStart + 1, condEnd).trim();

        // Find the code inside curly braces
        int blockStart = line.indexOf('{', condEnd);
        int blockEnd = line.indexOf('}', blockStart);
        if (blockStart == -1 || blockEnd == -1 || blockEnd < blockStart) {
            System.out.println("If statement block is wrong");
            return;
        }
        String block = line.substring(blockStart + 1, blockEnd).trim();

        // Check the condition and run the block if true
        if (evaluateCondition(condition)) {
            executeBlock(block);
        }
    }

    // Check if the condition is true or false
    private boolean evaluateCondition(String condition) {
        // We support these operators
        String[] operators = {">=", "<=", "==", "!=", ">", "<"};
        String op = null;
        for (String operator : operators) {
            if (condition.contains(operator)) {
                op = operator;
                break;
            }
        }
        if (op == null) {
            System.out.println("Condition missing operator or not supported: " + condition);
            return false;
        }

        String[] parts = condition.split(java.util.regex.Pattern.quote(op));
        if (parts.length != 2) {
            System.out.println("Condition looks wrong: " + condition);
            return false;
        }

        int left = getValue(parts[0].trim());
        int right = getValue(parts[1].trim());

        // Compare based on operator
        switch (op) {
            case ">": return left > right;
            case "<": return left < right;
            case ">=": return left >= right;
            case "<=": return left <= right;
            case "==": return left == right;
            case "!=": return left != right;
            default: return false;
        }
    }

    // Get number from variable or just parse the number
    private int getValue(String token) {
        if (variables.containsKey(token)) {
            return variables.get(token);
        }
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            System.out.println("Don't know this variable or number: " + token);
            return 0;
        }
    }

    // Run the code inside the if block (only print supported)
    private void executeBlock(String block) {
        block = block.trim();
        if (block.startsWith("print(") && block.endsWith(");")) {
            // Get the text inside print(...)
            int start = block.indexOf('(');
            int end = block.lastIndexOf(')');
            String content = block.substring(start + 1, end).trim();
            // Remove quotes if there
            if ((content.startsWith("\"") && content.endsWith("\"")) ||
                (content.startsWith("'") && content.endsWith("'"))) {
                content = content.substring(1, content.length() - 1);
            }
            System.out.println(content);
        } else {
            System.out.println("I can't run this inside if: " + block);
        }
    }

    // Some quick tests to show how it works
    public static void main(String[] args) {
        MiniInterpreter interpreter = new MiniInterpreter();

        System.out.println("Test 1: let statement");
        interpreter.evaluate("let x = 10;");
        System.out.println("x = " + interpreter.variables.get("x"));  // Should print 10

        System.out.println("\nTest 2: if with true condition");
        interpreter.evaluate("if (x > 5) { print(\"x is bigger than 5\"); }");

        System.out.println("\nTest 3: if with false condition");
        interpreter.evaluate("if (x < 5) { print(\"x is smaller than 5\"); }");

        System.out.println("\nTest 4: let and if with variable");
        interpreter.evaluate("let y = 3;");
        interpreter.evaluate("if (y == 3) { print(\"y is 3\"); }");

        System.out.println("\nTest 5: bad let statement");
        interpreter.evaluate("let z = abc;");

        System.out.println("\nTest 6: bad if statement");
        interpreter.evaluate("if (x <> 5) { print(\"bad operator\"); }");
    }
}
