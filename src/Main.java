import java.util.Arrays;
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
            switch (userChoice) {
                case 1:
                    System.out.println("\n-> Option A - Primary School menu is opening...");
                    subMenuOptionA(input);
                    break;

                case 2:
                    System.out.println("\n-> Option B Selected- Secondary School menu is opening...");
                    subMenuOptionB(input);
                    break;

                case 3:
                    System.out.println("\n-> Option C - High School menu is opening...");
                    subMenuOptionC(input);
                    break;

                case 4:
                    System.out.println("\n-> Option D - University menu is opening...");
                    subMenuOptionD(input); // Connect Four menu burada olabilir
                    break;

                case 5:
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
    //         OPTION B - SECONDARY SCHOOL
    // ===========================================

    // Option B Submenu
    private static void subMenuOptionB() {}


    // ===========================================
    //             OPTION C - HIGH SCHOOL
    // ===========================================

    // Option C Submenu
    private static void subMenuOptionC() {}

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
    private static void arrayDistanceTask() {}

    // ===========================================
    //             OPTION D - UNIVERSITY
    // ===========================================

    // Connect Four Game Main Code
    private static void connectFourGame() {}
}