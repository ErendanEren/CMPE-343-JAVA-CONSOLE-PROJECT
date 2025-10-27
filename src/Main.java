import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


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
                    System.out.println("\n Invalid selection! Please choose a number between 1 and 5.\n");
                    break;
                }
            }
        }


    // ===========================================
    //           OPTION A - PRIMARY SCHOOL
    // ===========================================

    // Option A Submenu

    // Shows submenu for Option A
    private static void subMenuOptionA(Scanner input) {
        int choice = 0;
        do {
            System.out.println("\n=== OPTION A: PRIMARY SCHOOL ===");
            System.out.println("1-) Age and Zodiac Sign Detection");
            System.out.println("2-) Reverse the Words (Recursive)");
            System.out.println("3-) Return to Main Menu");
            System.out.print("Your choice: ");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine(); // clear buffer
                switch (choice) {
                    case 1:
                        System.out.println("\n Running Task A1...");
                        ageAndZodiacSignDetection();
                        break;
                    case 2:
                        System.out.println("\n Running Task A2...");
                        reverseTheWords();
                        break;
                    case 3:
                        System.out.println("Returning to Main Menu...\n");
                        break;
                    default:
                        System.out.println("Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("❌ Invalid input. Please enter a number.");
                input.nextLine();
            }

        } while (choice != 3);
    }

    // Option A Task 1: Age and Zodiac Sign Detection Main Code
    private static void ageAndZodiacSignDetection() {}

    // Option A Task 2: Reverse the Words Main Code
    private static void reverseTheWords() {
        System.out.println("\n--- Task 2: Reverse the Words ---");
        System.out.println("Please enter the text you want to reverse:");

        String originalText = scanner.nextLine();

        System.out.println("\n--- Orijinal Metin ---");
        System.out.println(originalText);

        // Özyinelemeli metodu çağırarak metni ters çeviriyoruz.
        String reversedText = reverseSentenceRecursively(originalText.trim());

        System.out.println("\n--- Ters Çevrilmiş Metin ---");
        System.out.println(reversedText);

        System.out.println("\nPress ENTER to return to the Primary School menu...");
        scanner.nextLine();
    }

    // ReverseWords sınıfında kullanılan fonskiyon 1
    private static String reverseSentenceRecursively(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return "";
        }

        String trimmedSentence = sentence.trim();
        if (trimmedSentence.isEmpty()) {
            return "";
        }

        String firstWord;
        String restOfSentence;

        int firstSpaceIndex = trimmedSentence.indexOf(' ');
        if (firstSpaceIndex == -1) {
            firstWord = trimmedSentence;
            restOfSentence = "";
        } else {
            firstWord = trimmedSentence.substring(0, firstSpaceIndex);
            restOfSentence = trimmedSentence.substring(firstSpaceIndex + 1);
        }

        String reversedWord = reverseSingleWord(firstWord);
        String processedRest = reverseSentenceRecursively(restOfSentence);

        if (processedRest.isEmpty()) {
            return reversedWord;
        } else {
            return reversedWord + " " + processedRest;
        }
    }

    // ReverseWords sınıfında kullanılan fonksiyon 2
    private static String reverseSingleWord(String word) {
        if (word == null) return null;

        StringBuilder letters = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) {
                letters.append(c);
            }
        }

        if (letters.length() < 2) {
            return word;
        }

        letters.reverse();

        StringBuilder result = new StringBuilder();
        int letterIndex = 0;
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(letters.charAt(letterIndex));
                letterIndex++;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }


    // ===========================================
    public static void CalculatePrimeNumbers()
    {
        int n;

        do {
            System.out.print("Please enter an integer n (n >= 12): ");
            // Global (statik) scanner nesnesini kullanıyoruz.
            if (scanner.hasNextInt())
            {
                n = scanner.nextInt();
                // nextInt() sonrası satır sonu karakterini tüketmek için
                scanner.nextLine();

                if (n < 12)
                {
                    System.out.println("Input should be larger/equal to 12. Please try again.");
                }
            }
            else
            {
                System.out.println("Invalid input. Please enter an integer.");
                // Hatalı girdiyi tarayıcıdan (buffer'dan) temizle
                scanner.next();
                // next() sonrası satır sonu karakterini tüketmek için
                scanner.nextLine();
                n = 0;
            }
        }
        while(n<12);

        SieveOfEratosthenes(n);
        SieveOfSundaram(n);
        SieveOfAtkin(n);
    }

    // Sieve of Eratosthenes metodu
    public static void SieveOfEratosthenes(int n)
    {
        long startTime = System.nanoTime();
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for(int j=2; j*j<=n; j++)
        {
            if(isPrime[j])
            {
                for(int i = j*j; i <= n; i += j)
                {
                    isPrime[i]=false;
                }
            }
        }

        ArrayList<Integer> primeNumbers = new ArrayList<>();
        for(int i =2; i<= n; i++)
        {
            if(isPrime[i])
            {
                primeNumbers.add(i);
            }
        }
        long endTime = System.nanoTime();
        double duration = (endTime - startTime)/1_000_000_000.0;
        int size = primeNumbers.size();

        System.out.println("1. Sieve of Eratosthenes");
        // n>=12 olduğu için size>=5 garanti, direkt erişim güvenli
        System.out.println("First 3 primes: " + primeNumbers.get(0) +  ", " + primeNumbers.get(1)+  ", " + primeNumbers.get(2));
        System.out.println("Last 2 primes: " + primeNumbers.get(size -2) +  ", " + primeNumbers.get(size-1));
        System.out.printf("Execution time(s): %.9f\n", duration);
        System.out.println(" ");
    }

    // Sieve of Sundaram metodu
    public static void SieveOfSundaram(int n)
    {
        long startTime = System.nanoTime();
        int k = (n-1)/2;

        boolean[] isNotPrime = new boolean[k+1];
        for (int i= 1; i<= k; i++)
        {
            for (int j = i; (i+j+2*i*j) <= k; j++)
            {
                isNotPrime[i+j+2*i* j] = true;
            }
        }
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        if(n>=2)
        {
            primeNumbers.add(2);
        }
        for(int i=1; i<=k; i++)
        {
            if(!isNotPrime[i])
            {
                int prime = 2*i+1;
                if(prime <= n)
                {
                    primeNumbers.add(prime);
                }
            }
        }
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000_000.0;
        int size = primeNumbers.size();

        System.out.println("2: Sieve of Sundaram");
        System.out.println("First 3 primes: " + primeNumbers.get(0) + ", " + primeNumbers.get(1) + ", " + primeNumbers.get(2));
        System.out.println("Last 2 primes: " + primeNumbers.get(size - 2) + ", " + primeNumbers.get(size - 1));
        System.out.printf("Execution time(s): %.9f\n", duration);
        System.out.println(" ");
    }

    // Sieve of Atkin metodu
    public static void SieveOfAtkin(int n)
    {
        long startTime = System.nanoTime();
        boolean[] sieve = new boolean[n+1];

        // n>=12 olduğu için 2 ve 3 kontrolü mantıklı
        if(n>=2)
        {
            sieve[2] = true;
        }
        if(n>=3)
        {
            sieve[3] = true;
        }
        int limit = (int) Math.sqrt(n);

        // 1. Aşama: Olası asalları işaretle
        for (int x = 1; x <= limit; x++)
        {
            for (int y = 1; y <= limit; y++)
            {
                int num;

                // 4x^2 + y^2
                num = (4 * x * x) + (y * y);
                if (num <= n && (num % 12 == 1 || num % 12 == 5)) {
                    sieve[num] = !sieve[num];
                }

                // 3x^2 + y^2
                num = (3 * x * x) + (y * y);
                if (num <= n && (num % 12 == 7)) {
                    sieve[num] = !sieve[num];
                }

                // 3x^2 - y^2 (x > y ise)
                if (x > y) {
                    num = (3 * x * x) - (y * y);
                    if (num <= n && (num % 12 == 11)) {
                        sieve[num] = !sieve[num];
                    }
                }
            }
        }

        // 2. Aşama: Karelerin katlarını ele (Compositelere bak)
        for (int r = 5; r * r <= n; r++)
        {
            if (sieve[r])
            {
                for (int i = r * r; i <= n; i += r * r)
                {
                    sieve[i] = false;
                }
            }
        }

        // 3. Aşama: Sonuçları topla
        ArrayList<Integer> primeNumbers= new ArrayList<>();
        for (int i = 2; i <= n; i++)
        {
            if (sieve[i])
            {
                primeNumbers.add(i);
            }
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000_000.0;
        int size = primeNumbers.size();

        System.out.println("3. Sieve of Atkin");
        System.out.println("First 3 primes: " + primeNumbers.get(0) + ", " + primeNumbers.get(1) + ", " + primeNumbers.get(2));
        System.out.println("Last 2 primes: " + primeNumbers.get(size - 2) + ", " + primeNumbers.get(size - 1));
        System.out.printf("Execution time(s): %.9f\n", duration);
        System.out.println(" ");
    }
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
        if (operator == '+' || operator == '-') return 1; // Assigns a precedence level to operators.
        if (operator == 'x' || operator == ':') return 2;
        return 0;  //Other characters return 0.

    }

    private static boolean isOperator ( char c){
        return c == '+' || c == '-' || c == 'x' || c == ':';
    } // It checks the valid operators.

    private static boolean isDigit ( char c){
        return c >= '0' && c <= '9';
    }

    // --- Check Validation ---
    public static boolean isValidExpression (String expression){
        if (expression == null || expression.trim().isEmpty()) return false;

        // Convert commas to periods and remove spaces
        StringBuilder ns = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == ' ') {
                continue;
            }
            else if (c == ',') {
                ns.append('.'); // Insert a period instead of a comma

            } else {
                ns.append(c); //Add all other characters
            }
        }
        String trimmed= ns.toString();
        // Checks invalid characters
        for (int i = 0; i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);


            if (!(isDigit(c) || isOperator(c) || c == '(' || c == ')' || c == '.')) {
                return false;
            }
        }

        // Parentheses control
        int balance = 0;
        for (char c : trimmed.toCharArray()) {
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false;
        }
        if (balance != 0) return false;


        if (trimmed.length() > 0) {
            char firstChar = trimmed.charAt(0);


            if (firstChar == '+' || firstChar == 'x' || firstChar == ':') {
                return false;
            }

            // The end of the expression cannot be an operator
            char lastChar = trimmed.charAt(trimmed.length() - 1);
            if (isOperator(lastChar)) {
                return false;
            }
        }

        //  Operator and Operand order (Side by side operators in parentheses).
        for (int i = 0; i < trimmed.length() - 1; i++) {
            char current = trimmed.charAt(i);
            char next = trimmed.charAt(i + 1);


            if (isOperator(current) && isOperator(next)) {


                if (next != '-') {
                    // ++, +x, +:, -+, -x, -: will be error.
                    return false;
                }

                if (i > 0 && isDigit(trimmed.charAt(i - 1)) && next == '-') {
                    return false;
                }

            }

            //  Number and parentheses control

            if (isDigit(current) && next == '(') return false;
            if (current == ')' && isDigit(next)) return false;

            //  Parentheses and operator control

            if (current == '(' && isOperator(next) && next != '-') {

                return false;
            }


            if (isOperator(current) && next == ')') return false;


            if (current == '.') {
                if (i == trimmed.length() - 1 || !isDigit(next)) return false;
                if (i > 0 && !isDigit(trimmed.charAt(i - 1))) return false;


                int count = 0;
                for (int j = 0; j <= i; j++) {
                    if (isOperator(trimmed.charAt(j)) || trimmed.charAt(j) == '(') {
                        count = 0;
                    }
                    if (trimmed.charAt(j) == '.') {
                        count++;
                    }
                    if (count > 1) return false;
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


            String resultStr = String.format("%.2f", innerResult);

            int endIndex = resultStr.length() - 1; //Removing unnecessary zeros from the last part.

            //Skip all trailing zeros

            while (endIndex > 0 && resultStr.charAt(endIndex) == '0') {
                endIndex--;
            }

            //Truncate the string by length.
            resultStr = resultStr.substring(0, endIndex + 1);
            //If the string ends with a dot, remove it.
            if (resultStr.endsWith(".")) {
                resultStr = resultStr.substring(0, resultStr.length() - 1);
            }

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


            String resultStr = String.format("%.2f", result);
            int endIndex = resultStr.length() - 1;

            // It will skip the zeros at the end.
            while (endIndex > 0 && resultStr.charAt(endIndex) == '0') {
                endIndex--;
            }
            resultStr = resultStr.substring(0, endIndex + 1);
            if (resultStr.endsWith(".")) {
                resultStr = resultStr.substring(0, resultStr.length() - 1);
            }


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

                    // Prepares the expression for evaluation by converting commas to periods.
                    char[] tempChars = input.toCharArray();
                    int newLength = 0;
                    for (int i = 0; i < input.length(); i++) {
                        char c = input.charAt(i);
                        if (c == ' ') {
                            continue;
                        }
                        else if (c == ',') {
                            tempChars[newLength] = '.';
                            newLength++;
                        }
                        else {
                            tempChars[newLength] = c;
                            newLength++;
                        }
                    }
                    String processedInput = new String(tempChars,0, newLength);

                    double finalResult = evaluateAndPrintSteps(processedInput);
                    System.out.printf("Final result = %.2f%n", finalResult);
                    System.out.println("\n--- END(ENTER `EXIT TO RETURN MAIN MENU`  ---\n");
                } catch (IllegalArgumentException e) {
                    System.out.println("retry to enter valid expression.`Exit`for return menu (" + e.getMessage() + ")");
                } catch (Exception e) {
                    System.out.println("retry to enter valid expression. (Unexpected error) `Exit`for return menu");
                }
            } else {
                System.out.println("retry to enter valid expression.`Exit`for return menu");
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
