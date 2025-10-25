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
    private static void reverseTheWords() {}


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
    private static void arrayStatisticsTask() {}

    // Option C Task C2: Distance Between Two Arrays Main Code
    private static void arrayDistanceTask() {}

    // ===========================================
    //             OPTION D - UNIVERSITY
    // ===========================================

    // Connect Four Game Main Code
    private static void connectFourGame() {}
    //sa
}