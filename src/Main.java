import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class Main
{
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
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
                    subMenuOptionC(); // Option C Menu Connection
                    break;

                case "D":
                    System.out.println("\n-> Option D - University menu is opening...");
                    connectFourGame(); // Connect Four Menu Connection
                    break;

                case "E":
                    break;
<<<<<<< HEAD
                        default:
                    System.out.println("\n Invalid selection! Please choose a number between 1 and 5.\n");// Programdan çıkış yapılacak, do-while dışına düşecek
                    break;
=======


                default:
                    System.out.println("\n Invalid selection! Please choose a number between 1 and 5.\n");
                    break;
                }
>>>>>>> e028092a5bbd21cc09b57f0611172ebb7bc4296d
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
                        ageAndZodiacSignDetection(input);
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
                System.out.println(" Invalid input. Please enter a number.");
                input.nextLine();
            }

        } while (choice != 3);
    }

    // Option A Task 1: Age and Zodiac Sign Detection Main Code

    private static int getCurrentDay() {
        return LocalDate.now().getDayOfMonth();
    }
    private static int getCurrentMonth() {
        return LocalDate.now().getMonthValue();
    }
    private static int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    private static void ageAndZodiacSignDetection(Scanner input) {
        int day = 0;
        int month = 0;
        int year = 0;
        int isValidDateCheck = 0;


        do {
            year = getYear(input);
            month = getMonth(input);
            day = getDay(input, month, year);

            isValidDateCheck = checkIsValidDate(year, month, day);

            if (isValidDateCheck == 0) {
                System.out.println("\n❌ Invalid date. You cannot be born in the future. Please re-enter your date of birth.\n");
            }

        } while (isValidDateCheck == 0);

        System.out.println("\n Birth Date: " + day + "/" + month + "/" + year);
        calculateZodiac(day, month);
        calculateAge(day, month, year);

        System.out.println("\n--- Task A1 Finished ---");
        subMenuOptionA(input);
    }

    // --- Input Methods with Validation ---

    private static int getYear(Scanner input) {
        int currentYear = getCurrentYear();
        int year = -1;

        do {
            System.out.print("Enter your birth year (1900 to " + currentYear + "): ");

            if (input.hasNextInt()) {
                year = input.nextInt();
                input.nextLine();

                if (year < 1900 || year > currentYear) {
                    System.out.println("Invalid year. Please enter a year between 1900 and " + currentYear + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a whole number for the year.");
                input.nextLine();
            }

        } while (year < 1900 || year > currentYear);

        return year;
    }

    private static int getMonth(Scanner input) {
        int month = 0;

        do {
            System.out.print("Enter the birth month number (1 for Jan, 12 for Dec): ");

            if (input.hasNextInt()){
                month = input.nextInt();
                input.nextLine();

                if (month < 1 || month > 12){
                    System.out.println(" Invalid month. Please enter a number between 1 and 12.");
                }
            } else {
                System.out.println(" Invalid input. Please enter a whole number for the month.");
                input.nextLine();
            }

        } while(month < 1 || month > 12);

        String[] monthNames = {"", "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        System.out.println("Selected month is " + monthNames[month] + ".");

        return month;
    }

    private static int getDay(Scanner input, int month, int year) {
        int maxDay = daysInMonth(month, year);
        int day = 0;

        do {
            System.out.print("Enter the day of your birthday (1-" + maxDay + "): ");

            if (input.hasNextInt()) {
                day = input.nextInt();
                input.nextLine();

                if (day < 1 || day > maxDay) {
                    System.out.println(" Invalid day. Please enter a number between 1 and " + maxDay + " for " + monthToName(month) + ".");
                }
            } else {
                System.out.println(" Invalid input. Please enter a whole number for the day.");
                input.nextLine();
            }
        } while (day < 1 || day > maxDay);

        return day;
    }

    // Helper to get maximum days in a month (implements leap year logic)
    private static int daysInMonth(int month, int year) {
        int maxDay; // maxDay değişkenini tanımla ve sadece switch içinde kullan

        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                maxDay = 31;
                break;
            case 4: case 6: case 9: case 11:
                maxDay = 30;
                break;
            case 2:
                // Sıçrama yılı kontrolü: if-else yapısı kullanıldı.
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    maxDay = 29;
                } else {
                    maxDay = 28;
                }
                break;
            default:
                maxDay = 0; // Bu durum, month input kontrolü sayesinde oluşmamalı
        }
        return maxDay;
    }

    // Helper to convert month number to name
    private static String monthToName(int month) {
        String[] monthNames = {"", "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return month >= 1 && month <= 12 ? monthNames[month] : "Invalid Month";
    }

    private static int checkIsValidDate(int year, int month, int day) {
        int currentYear = getCurrentYear();
        int currentMonth = getCurrentMonth();
        int currentDay = getCurrentDay();

        if (year > currentYear) {
            return 0;
        }

        if (year == currentYear && month > currentMonth) {
            return 0;
        }

        if (year == currentYear && month == currentMonth && day > currentDay) {
            return 0;
        }

        return 1;
    }

    // --- Calculation Methods ---

    // Calculate age in years, months, and days
    private static void calculateAge(int day, int month, int year) {
        int currentYear = getCurrentYear();
        int currentMonth = getCurrentMonth();
        int currentDay = getCurrentDay();

        int yearDiff = currentYear - year;
        int monthDiff = currentMonth - month;
        int dayDiff = currentDay - day;

        // 1. Adjust Day Difference
        if (dayDiff < 0) {
            monthDiff--;

            int previousMonth = currentMonth - 1;
            int daysInPreviousMonth;

            if (previousMonth == 0) {
                previousMonth = 12;
                daysInPreviousMonth = daysInMonth(previousMonth, currentYear - 1);
            } else {
                daysInPreviousMonth = daysInMonth(previousMonth, currentYear);
            }

            dayDiff += daysInPreviousMonth;
        }

        // 2. Adjust Month Difference
        if (monthDiff < 0) {
            yearDiff--;
            monthDiff += 12;
        }

        System.out.println("Your age is: " + yearDiff + " years, " + monthDiff + " months, and " + dayDiff + " days old.");
    }

    // Calculate zodiac sign based on day and month
    // İstenen: Switch içinde if-else yapısı kullanıldı
    private static void calculateZodiac(int day, int month) {
        String sign = "";

        switch (month) {
            case 1: // Ocak (Jan)
                if (day <= 19) {
                    sign = "Capricorn ♑";
                } else {
                    sign = "Aquarius ♒";
                }
                break;
            case 2: // Şubat (Feb)
                if (day <= 18) {
                    sign = "Aquarius ♒";
                } else {
                    sign = "Pisces ♓";
                }
                break;
            case 3: // Mart (Mar)
                if (day <= 20) {
                    sign = "Pisces ♓";
                } else {
                    sign = "Aries ♈";
                }
                break;
            case 4: // Nisan (Apr)
                if (day <= 20) {
                    sign = "Aries ♈";
                } else {
                    sign = "Taurus ♉";
                }
                break;
            case 5: // Mayıs (May)
                if (day <= 20) {
                    sign = "Taurus ♉";
                } else {
                    sign = "Gemini ♊";
                }
                break;
            case 6: // Haziran (Jun)
                if (day <= 20) {
                    sign = "Gemini ♊";
                } else {
                    sign = "Cancer ♋";
                }
                break;
            case 7: // Temmuz (Jul)
                if (day <= 22) {
                    sign = "Cancer ♋";
                } else {
                    sign = "Leo ♌";
                }
                break;
            case 8: // Ağustos (Aug)
                if (day <= 22) {
                    sign = "Leo ♌";
                } else {
                    sign = "Virgo ♍";
                }
                break;
            case 9: // Eylül (Sep)
                if (day <= 22) {
                    sign = "Virgo ♍";
                } else {
                    sign = "Libra ♎";
                }
                break;
            case 10: // Ekim (Oct)
                if (day <= 22) {
                    sign = "Libra ♎";
                } else {
                    sign = "Scorpio ♏";
                }
                break;
            case 11: // Kasım (Nov)
                if (day <= 22) {
                    sign = "Scorpio ♏";
                } else {
                    sign = "Sagittarius ♐";
                }
                break;
            case 12: // Aralık (Dec)
                if (day <= 21) {
                    sign = "Sagittarius ♐";
                } else {
                    sign = "Capricorn ♑";
                }
                break;
        }

        System.out.println("Your zodiac sign is: " + sign);
    }


    // Option A Task 2: Reverse the Words Main Code
    private static void reverseTheWords() {
        System.out.println("\n--- Task 2: Reverse the Words ---");
        System.out.println("Please enter the text you want to reverse:");

        String originalText = scanner.nextLine();

        System.out.println("\n--- Original text ---");
        System.out.println(originalText);

        String reversedText = reverseSentenceRecursively(originalText.trim());

        System.out.println("\n--- Reversed text ---");
        System.out.println(reversedText);

        System.out.println("\nPress ENTER to return to the Primary School menu...");
        scanner.nextLine();
    }

    private static String reverseSentenceRecursively(String sentence) {
        if (sentence == null || sentence.isEmpty()) { // Base case
            return "";
        }

        String trimmedSentence = sentence.trim();
        if (trimmedSentence.isEmpty()) {
            return "";
        }

        String firstWord;
        String restOfSentence;

        int firstSpaceIndex = trimmedSentence.indexOf(' '); // Divide the sentences into two parts: first word and the rest of the sentence
        if (firstSpaceIndex == -1) { // No words left
            firstWord = trimmedSentence;
            restOfSentence = "";
        } else {
            firstWord = trimmedSentence.substring(0, firstSpaceIndex);
            restOfSentence = trimmedSentence.substring(firstSpaceIndex + 1);
        }

        String reversedWord = reverseSingleWord(firstWord);
        String processedRest = reverseSentenceRecursively(restOfSentence);

        if (processedRest.isEmpty()) { // Merge results
            return reversedWord;
        } else {
            return reversedWord + " " + processedRest;
        }
    }

    private static String reverseSingleWord(String word) {
        if (word == null) return null;

        StringBuilder letters = new StringBuilder();
        for (char c : word.toCharArray()) { // Sort out the characters in the words
            if (Character.isLetter(c)) {
                letters.append(c);
            }
        }

        if (letters.length() < 2) { // returns the word with only one character
            return word;
        }

        letters.reverse();

        // When re-forming the word, put the non-letter characters in their original places
        StringBuilder result = new StringBuilder();
        int letterIndex = 0;
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(letters.charAt(letterIndex));
                letterIndex++;
            } else { // If the character is not a letter , insert it as is
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
            if (scanner.hasNextInt())
            {
                n = scanner.nextInt();
                scanner.nextLine(); // buffer temizle

                if (n < 12)
                {
                    System.out.println("Input should be larger/equal to 12. Please try again.");
                }
            }
            else
            {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
                scanner.nextLine(); // buffer temizle
                n = 0;
            }
        }
        while(n<12);

        SieveOfEratosthenes(n);
        SieveOfSundaram(n);
        SieveOfAtkin(n);

        System.out.println("\nPress ENTER to return to the Secondary School menu...");
        scanner.nextLine();
    }

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
        System.out.println("First 3 primes: " + primeNumbers.get(0) +  ", " + primeNumbers.get(1)+  ", " + primeNumbers.get(2));
        System.out.println("Last 2 primes: " + primeNumbers.get(size -2) +  ", " + primeNumbers.get(size-1));
        System.out.printf("Execution time(s): %.9f\n", duration);
        System.out.println(" ");
    }

    public static void SieveOfSundaram(int n)
    {
        long startTime = System.nanoTime();
        int k = (n-1)/2;

        boolean[] isNotPrime = new boolean[k+1];
        for (int i = 1; i <= k; i++)
        {
            for (int j = i; (i + j + 2 * i * j) <= k; j++)
            {
                isNotPrime[i + j + 2 * i * j] = true;
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

    public static void SieveOfAtkin(int n)
    {
        long startTime = System.nanoTime();
        boolean[] sieve = new boolean[n+1];

        if(n>=2)
        {
            sieve[2] = true;
        }
        if(n>=3)
        {
            sieve[3] = true;
        }
        int limit = (int) Math.sqrt(n);

        for (int x = 1; x <= limit; x++)
        {
            for (int y = 1; y <= limit; y++)
            {
                int num;

                num = (4 * x * x) + (y * y);
                if (num <= n && (num % 12 == 1 || num % 12 == 5)) {
                    sieve[num] = !sieve[num];
                }

                num = (3 * x * x) + (y * y);
                if (num <= n && (num % 12 == 7)) {
                    sieve[num] = !sieve[num];
                }

                if (x > y) {
                    num = (3 * x * x) - (y * y);
                    if (num <= n && (num % 12 == 11)) {
                        sieve[num] = !sieve[num];
                    }
                }
            }
        }

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
<<<<<<< HEAD
=======

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
>>>>>>> e028092a5bbd21cc09b57f0611172ebb7bc4296d

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

<<<<<<< HEAD
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
=======
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
>>>>>>> e028092a5bbd21cc09b57f0611172ebb7bc4296d
        }
    }





// ===========================================
//             OPTION C - HIGH SCHOOL
// ===========================================

// Option C Submenu
private static void subMenuOptionC () {
}

// Option C Task 1: Array Statistics Main Code
private static void arrayStatisticsTask() {
    int size = 0;

    // Determining size of the array
    do {
        System.out.print("Enter the size of the array: ");
        if (scanner.hasNextInt()) {
            size = scanner.nextInt();
            if (size <= 0) {
                System.out.println("Array size must be a positive integer. Please try again.");
            }
        } else {
            System.out.println("Invalid input. Please enter an integer for the size.");
            scanner.next();
            size = 0;
        }
    } while (size <= 0);
    scanner.nextLine();

    double[] array = new double[size];

    // Determining the elements of the array
    System.out.println("\n--- Enter array elements with double values ---");
    for (int i = 0; i < size; i++) {
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Enter element " + (i + 1) + "/" + size + ": ");
            if (scanner.hasNextDouble()) {
                array[i] = scanner.nextDouble();
                isValid = true;
            } else {
                System.out.println("Invalid input. Please enter a double value (e.g., 3,14 or 3.14 depending on your locale).");
                scanner.next();
            }
        }
    }
    scanner.nextLine(); // Clean up the remaining newline

    System.out.println("\n--- Results for Array: " + Arrays.toString(array) + " ---");

    double median = calculateMedian(array);
    double arithmeticMean = calculateArithmeticMean(array);
    double geometricMean = calculateGeometricMean(array);
    double harmonicMean = calculateHarmonicMean(array);

    // Print the results with format (2 digits after the decimal point)
    System.out.printf("Median: %.2f\n", median);
    System.out.printf("Arithmetic Mean: %.2f\n", arithmeticMean);
    System.out.printf("Geometric Mean: %.2f\n", geometricMean);
    System.out.printf("Harmonic Mean: %.2f\n", harmonicMean);

    System.out.println("\nPress ENTER to return to the High School menu...");
    scanner.nextLine();
}

    public static double calculateMedian(double[] array) {
        // We create a copy and sort it to avoid damaging the original array
        double[] sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);
        int n = sortedArray.length;

        if (n % 2 != 0) { // If the array size is odd the middle element is the median
            return sortedArray[n / 2];
        } else { // If the array size is even The average of the middle two elements is the median
            int mid1 = n / 2 - 1;
            int mid2 = n / 2;
            return (sortedArray[mid1] + sortedArray[mid2]) / 2.0;
        }
    }

    public static double calculateArithmeticMean(double[] array) {
        double sum = 0;
        for (double num : array) {
            sum += num;
        }
        return sum / array.length;
    }

    public static double calculateGeometricMean(double[] array) {
        double product = 1.0;
        for (double num : array) {
            product *= num;
        }
        return Math.pow(product, 1.0 / array.length);
    }

    public static double calculateHarmonicMean(double[] array) {
        // Call the recursive method to calculate the denominator (1/x1 + 1/x2 ...).
        double sumOfReciprocals = recursiveSumReciprocals(array, array.length);
        if (sumOfReciprocals == 0) {
            return 0.0;
        }
        return array.length / sumOfReciprocals;
    }

    private static double recursiveSumReciprocals(double[] array, int n) {
        if (n == 0) { // Base case
            return 0;
        }
        // Recursive Step: Reverse the last element of the array (1/x)
        // and call yourself again for the remaining part of the array (n-1).
        return (1.0 / array[n - 1]) + recursiveSumReciprocals(array, n - 1);
    }

// Option C Task C2: Distance Between Two Arrays Main Code
private static void arrayDistanceTask()
{
    int size = 0;

    do {
        System.out.print("Enter the size for the arrays (e.g., 5): ");
        if (scanner.hasNextInt()) {
            size = scanner.nextInt();
            if (size <= 0)
            {
                System.out.println("Dimension must be a positive number. Please try again.");
            }
        }
        else
        {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next();
            size = 0;
        }
    } while (size <= 0);

    scanner.nextLine();

    int[] array1 = new int[size];
    int[] array2 = new int[size];

    System.out.println("\n--- Enter elements for the first array in [0,9] ---");
    for (int i = 0; i < size; i++) {
        int element;
        boolean isValid;

        do {
            System.out.print("Enter element at index " + i + " (must be 0-9): ");
            if (scanner.hasNextInt()) {
                element = scanner.nextInt();
                if (element >= 0 && element <= 9)
                {
                    array1[i] = element;
                    isValid = true;
                }
                else
                {
                    System.out.println("Invalid entry. Number must be between 0 and 9.");
                    isValid = false;
                }
            }
            else
            {
                System.out.println("Invalid entry. Please enter an integer.");
                scanner.next();
                element = -1;
                isValid = false;
            }
        } while (!isValid);
    }

    scanner.nextLine();

    System.out.println("\n--- Enter elements for the second array ---");
    for (int i = 0; i < size; i++) {
        int element;
        boolean isValid;

        do {
            System.out.print("Enter element at index " + i + " (must be 0-9): ");
            if (scanner.hasNextInt()) {
                element = scanner.nextInt();
                if (element >= 0 && element <= 9)
                {
                    array2[i] = element;
                    isValid = true;
                }
                else
                {
                    System.out.println("Invalid entry. Number must be between 0 and 9.");
                    isValid = false;
                }
            }
            else
            {
                System.out.println("Invalid entry. Please enter an integer.");
                scanner.next();
                element = -1;
                isValid = false;
            }
        } while (!isValid);
    }

    scanner.nextLine();

    System.out.println("\nCalculating distances for:");
    System.out.println("Array 1: " + Arrays.toString(array1));
    System.out.println("Array 2: " + Arrays.toString(array2));

    double manhattan = calculateManhattanDistance(array1, array2);
    System.out.printf("Manhattan Distance: %.2\n", manhattan);

    double euclidean = calculateEuclideanDistance(array1, array2);
    System.out.printf("Euclidean Distance: %.2f\n", euclidean);

    double cosine = calculateCosineSimilarity(array1, array2);
    System.out.printf("Cosine Similarity: %.2f\n", cosine);

    System.out.println("\nPress ENTER to return to the High School menu...");
    scanner.nextLine();
}

    public static double calculateManhattanDistance(int[] a, int[] b)
    {
        //|a1-b1|+|a2-b2|+...|an-bn|
        double sum = 0; //total distance
        for (int i = 0; i < a.length; i++)
        {
            sum += Math.abs(a[i] - b[i]);
        }
        return sum;
    }

    public static double calculateEuclideanDistance(int[] a, int[] b)
    {
        //in root, (a1-b2)^2 + (a2-b2)^2 +...(an-bn)^2
        double sumOfSquares = 0;
        for (int i = 0; i < a.length; i++) {
            sumOfSquares += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sumOfSquares);
    }

    public static double calculateCosineSimilarity(int[] a, int[] b) {
        //similarity(A,B) = (A.B)/|A|.|B|
        double Product = 0.0;
        double valA = 0.0;
        double valB = 0.0;

        for (int i = 0; i < a.length; i++) {
            Product += a[i] * b[i];
            valA += Math.pow(a[i], 2);
            valB += Math.pow(b[i], 2);
        }

        valA = Math.sqrt(valA);
        valB = Math.sqrt(valB);

        if (valA == 0.0 || valB == 0.0) {
            return 0.0;
        }
        return Product / (valA * valB);
    }

// ===========================================
//             OPTION D - UNIVERSITY
// ===========================================

// Connect Four Game Main Code
private static void connectFourGame () { }

}
