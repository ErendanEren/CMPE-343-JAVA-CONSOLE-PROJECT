import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showWelcomeScreen();
        mainMenuLoop();
        System.out.println("Program terminated.");
    }

    // WELCOME SCREEN FOR PROJECT

    public static void showWelcomeScreen() {
        System.out.println("\n\n" +
                "█       █▄     ▄████████  ▄█        ▄████████  ▄██████▄    ▄▄▄▄███▄▄▄▄      ▄████████ \n" +
                "███     ███   ███    ███ ███       ███    ███ ███    ███ ▄██▀▀▀███▀▀▀██▄   ███    ███ \n" +
                "███     ███   ███    █▀  ███       ███    █▀  ███    ███ ███   ███   ███   ███    █▀  \n" +
                "███     ███  ▄███▄▄▄     ███       ███        ███    ███ ███   ███   ███  ▄███▄▄▄     \n" +
                "███     ███ ▀▀███▀▀▀     ███       ███        ███    ███ ███   ███   ███ ▀▀███▀▀▀     \n" +
                "███     ███   ███    █▄  ███       ███    █▄  ███    ███ ███   ███   ███   ███    █▄  \n" +
                "███ ▄█▄ ███   ███    ███ ███▌    ▄ ███    ███ ███    ███ ███   ███   ███   ███    ███ \n" +
                " ▀███▀███▀    ██████████ █████▄▄██ ████████▀   ▀██████▀   ▀█   ███   █▀    ██████████ \n" +
                "                         ▀                                                           \n");

        System.out.println("WELCOME TO THE JAVA CONSOLE PROJECT!");
        System.out.println("Team Members: Eren, Selçuk, Arda and Zafer");
        System.out.println("Press ENTER to continue to Project...");
        scanner.nextLine();
    }

    // MAIN MENU
    public static void mainMenuLoop() {
        while (true) {
            System.out.println("\nMAIN MENU");
            System.out.println("1-) Primary School [A]");
            System.out.println("2-) Secondary School [B]");
            System.out.println("3-) High School [C]");
            System.out.println("4-) University [D]");
            System.out.println("5-) Exit [E]");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().toUpperCase();

            // Checking for input
            switch (choice) {
                case "A":
                    System.out.println("\n-> Option A - Primary School menu is opening...");
                    subMenuOptionA(scanner);
                    break;

                case "B":
                    System.out.println("\n-> Option B Selected- Secondary School menu is opening...");
                    subMenuOptionB();
                    break;

                case "C":
                    System.out.println("\n-> Option C - High School menu is opening...");
                    subMenuOptionC();
                    break;

                case "D":
                    System.out.println("\n-> Option D - University menu is opening...");
                    connectFourGame(); // Connect Four menu burada olabilir
                    break;

                case "E":
                    // Programdan çıkış yapılacak, do-while dışına düşecek
                    break;

                default:
                    System.out.println("\n❌ Invalid selection! Please choose a number between 1 and 5.\n");
                    break;
            }
            }
        }


    // ===========================================
    //           OPTION A - PRIMARY SCHOOL
    // ===========================================

    // Option A Submenu

    // Shows submenu for Option A
    private static void subMenuOptionA(Scanner input) {}

    // Option A Task 1: Age and Zodiac Sign Detection Main Code
    private static void ageAndZodiacSignDetection() {}

    // Option A Task 2: Reverse the Words Main Code
    private static void reverseTheWords() {}


    // ===========================================
    //         OPTION B - SECONDARY SCHOOL
    // ===========================================

    // Option B Submenu
    private static void subMenuOptionB() {
        int choice = 0;
        do {
            System.out.println("\n=== OPTION B: SECONDARY SCHOOL ===");
            System.out.println("1-) Prime Number Generator ");
            System.out.println("2-) Step-by-step Expression Evaluation");
            System.out.println("3-) Return to Main Menu");
            System.out.print("Your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("\n Running Task B1 ");

                        break;
                    case 2:
                        System.out.println("\n Running Task B2");
                        expressioncontroller();
                        break;
                    case 3:
                        System.out.println("Project Returning to Main Menu...\n");
                        break;
                    default:
                        System.out.println("❌ Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("❌ Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        } while (choice != 3);
    }
            // --- Character Functions and Priority ---
            private static int takePriority ( char operator){
                if (operator == '+' || operator == '-') return 1;
                if (operator == 'x' || operator == ':') return 2;
                return 0;
            }

            private static boolean isOperator ( char c){
                return c == '+' || c == '-' || c == 'x' || c == ':';
            }

            private static boolean isDigit ( char c){
                return c >= '0' && c <= '9';
            }

            // --- Check Validation ---
            public static boolean isValidExpression (String expression){
                if (expression == null || expression.trim().isEmpty()) return false;

                String trimmed = expression.replaceAll(" ", "").replace(',', '.');

                for (int i = 0; i < trimmed.length(); i++) {
                    char c = trimmed.charAt(i);

                    if (!(isDigit(c) || isOperator(c) || c == '(' || c == ')')) {
                        return false;
                    }
                }

                int balance = 0;
                for (char c : trimmed.toCharArray()) {
                    if (c == '(') balance++;
                    else if (c == ')') balance--;
                    if (balance < 0) return false;
                }
                if (balance != 0) return false;

                for (int i = 0; i < trimmed.length() - 1; i++) {
                    if (isOperator(trimmed.charAt(i)) && isOperator(trimmed.charAt(i + 1))) {
                        if (trimmed.charAt(i) != '-' || trimmed.charAt(i + 1) != '-') {

                        } else if (i != 0 && trimmed.charAt(i - 1) != '(') {
                            return false;
                        }
                    }
                }

                return true;
            }



    // --- Evaluation ---

    public static double evaluateAndPrintSteps (String expression){
        System.out.println(expression);
        return recursiveSolve(expression);
    }

    private static double recursiveSolve (String expression){

        //Step-by-step printing logic is implemented in this method.

        // Basic Condition Check: Is the expression just a number?
        // If it's just a number, the calculation is complete, return it immediately.
        try {
            return Double.parseDouble(expression);
        } catch (NumberFormatException ignored) {

        }

        // Parentheses Handling: The logic for finding the DEEPEST parentheses is preserved.
        int balance = 0;
        int OpenParent = -1;
        int CloseParent = -1;
        int nestingLevel = 0;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                balance++;
                if (balance > nestingLevel) {
                    nestingLevel = balance;
                    OpenParent = i;
                }
            } else if (c == ')') {
                if (balance == nestingLevel) {
                    CloseParent = i;

                    break;
                }
                balance--;
            }
        }

        if (OpenParent != -1 && CloseParent != -1 && CloseParent > OpenParent) {
            String innerExpression = expression.substring(OpenParent + 1, CloseParent);


            double innerResult = recursiveSolve(innerExpression);


            String resultStr = String.format("%f", innerResult).replaceAll("(\\.\\d*?)0+$", "$1").replaceAll("\\.$", "");


            String newExpression = expression.substring(0, OpenParent) +
                    resultStr +
                    expression.substring(CloseParent + 1);

            System.out.println("= " + newExpression);

            return recursiveSolve(newExpression);
        }


        int splitIndex = -1;


        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (isOperator(c) && takePriority(c) == 1) {

                if (c == '-' && (i == 0 || expression.charAt(i - 1) == '(' || isOperator(expression.charAt(i - 1)))) {

                    continue;
                }
                splitIndex = i;
                break;
            }
        }

        // If addition/subtraction is not found, search for multiplication/division (Rightmost)
        if (splitIndex == -1) {
            for (int i = expression.length() - 1; i >= 0; i--) {
                char c = expression.charAt(i);
                if (isOperator(c) && takePriority(c) == 2) {
                    splitIndex = i;
                    break;
                }
            }
        }


        //  Calculation
        if (splitIndex != -1) {
            char operator = expression.charAt(splitIndex);
            String leftPart = expression.substring(0, splitIndex);
            String rightPart = expression.substring(splitIndex + 1);


            double leftValue = recursiveSolve(leftPart);
            double rightValue = recursiveSolve(rightPart);

            double result = 0;
            if (operator == '+') result = leftValue + rightValue;
            else if (operator == '-') result = leftValue - rightValue;
            else if (operator == 'x') result = leftValue * rightValue;
            else if (operator == ':') {
                if (rightValue == 0) throw new IllegalArgumentException("Error for division by zero");
                result = leftValue / rightValue;
            }


            String resultStr = String.format("%.10f", result)
                    .replaceAll("(\\.\\d*?)0+$", "$1")
                    .replaceAll("\\.$", "");




            if (expression.equals(leftPart + operator + rightPart)) {

                System.out.println("= " + resultStr);
                return recursiveSolve(resultStr);
            }


            String newExpression = resultStr;

            // To show step
            System.out.println("= " + newExpression);

            // Recursion to solve new expression.
            return recursiveSolve(newExpression);
        }

        //  Final check

        try {
            return Double.parseDouble(expression);
        } catch (NumberFormatException e) {

            throw new IllegalArgumentException("Invalid expression; " + expression);
        }
    }

            // Main method and user login (main method in expressioncontroller)
            public static void expressioncontroller () {

                String input;

                System.out.println("Expression Evaluation Program: Step by Step (Type 'exit' to exit)");

                while (true) {
                    System.out.print("Enter the expression: ");
                    input = scanner.nextLine().trim();

                    // Exit command control
                    if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("3")) {
                        System.out.println("Returning to Secondary School menu...");
                        break;
                    }

                    if (isValidExpression(input)) {
                        try {
                            System.out.println("\n-- Solution with step by step --\n");

                            // Virgülleri noktaya çevirerek ifadeyi evaluate etmek için hazırlayın.
                            String processedInput = input.replaceAll(" ", "").replace(',', '.');
                            double finalResult = evaluateAndPrintSteps(processedInput);

                            System.out.printf("Final result = %.2f%n", finalResult);
                            System.out.println("\n--- END  ---\n");
                        } catch (IllegalArgumentException e) {
                            System.out.println("retry to enter valid expression. (" + e.getMessage() + ")");
                        } catch (Exception e) {
                            System.out.println("retry to enter valid expression. (Unexpected error)");
                        }
                    } else {
                        System.out.println("retry to enter valid expression.");
                    }
                }
            }




        // ===========================================
        //             OPTION C - HIGH SCHOOL
        // ===========================================

        // Option C Submenu
        private static void subMenuOptionC () {
        }

        // Option C Task 1: Array Statistics Main Code
        private static void arrayStatisticsTask () {
        }

        // Option C Task C2: Distance Between Two Arrays Main Code
        private static void arrayDistanceTask () {
        }

        // ===========================================
        //             OPTION D - UNIVERSITY
        // ===========================================

        // Connect Four Game Main Code
        private static void connectFourGame () {
        }
    }