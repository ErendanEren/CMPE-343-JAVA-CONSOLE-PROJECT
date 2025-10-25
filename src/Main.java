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
                    System.out.println("\n Option B Selected- Secondary School menu is opening...");
                    subMenuOptionB(input);
                    break;

                case 3:
                    System.out.println("\n Option C - High School menu is opening...");
                    subMenuOptionC(input);
                    break;

                case 4:
                    System.out.println("\n Option D - University menu is opening...");
                    subMenuOptionD(input);
                    break;
                case 5:
                    break
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

            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("\n Running Task B1 ");
                        CalculatePrimeNumbers();
                        break;
                    case 2:
                        System.out.println("\n Running Task B2");
                        expressioncontroller();
                        break;
                    case 3:
                        System.out.println("Project Returning to Main Menu...\n");
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


    // ===========================================
    //             OPTION C - HIGH SCHOOL
    // ===========================================

    // Option C Submenu
    private static void subMenuOptionC() {
        int choice = 0;
        do {
            System.out.println("\n=== OPTION C: HIGH SCHOOL ===");
            System.out.println("1-) Statistical Information about an Array");
            System.out.println("2-) Distance Between Two Arrays");
            System.out.println("3-) Return to Main Menu");
            System.out.print("Your choice: ");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("\n Running Task C1...");
                        arrayStatisticsTask();
                        break;
                    case 2:
                        System.out.println("\n Running Task C2...");
                        arrayDistanceTask();
                        break;
                    case 3:
                        System.out.println("Returning to Main Menu...\n");
                        break;
                    default:
                        System.out.println("Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }

        } while (choice != 3);
    }

    // Option C Task 1: Array Statistics Main Code
    private static void arrayStatisticsTask() {}

    // Option C Task C2: Distance Between Two Arrays Main Code
    private static void arrayDistanceTask() {}


    // ===========================================
    //             OPTION D - UNIVERSITY
    // ===========================================

    // Connect Four Game Main Code
    private static void connectFourGame() {}
}