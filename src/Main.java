import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class Main
{
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        runMatrixRain(80, 24, 3000, 30);
        showWelcomeScreen();
        mainMenuLoop();
        System.out.println("Program terminated.");
    }

    // === MATRIX RAIN CONSTANTS ===
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_CLEAR = "\u001B[2J";
    static final String ANSI_HOME  = "\u001B[H";
    static final String ANSI_HIDE_CURSOR = "\u001B[?25l";
    static final String ANSI_SHOW_CURSOR = "\u001B[?25h";

    static final String ANSI_GREEN_DIM    = "\u001B[32m";
    static final String ANSI_GREEN        = "\u001B[92m";
    static final String ANSI_GREEN_BRIGHT = "\u001B[97m"; // tepedeki parlayan harf

    static final char[] MATRIX_CHARS = (
            "01" +
                    "アイウエオカキクケコサシスセソタチツテト" +
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                    "abcdefghijklmnopqrstuvwxyz" +
                    "0123456789"
    ).toCharArray();

    static void runMatrixRain(int width, int height, int durationMs, int fps) {
        width = Math.max(width, 20);
        height = Math.max(height, 10);
        fps = Math.max(fps, 10);
        long frameTime = 1000L / fps;

        int[] y = new int[width];
        int[] speed = new int[width];
        java.util.Random rnd = new java.util.Random();

        for (int c = 0; c < width; c++) {
            y[c] = rnd.nextInt(height);
            speed[c] = 1 + rnd.nextInt(3); // 1..3
        }

        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(width * (height + 2));

        System.out.print(ANSI_HIDE_CURSOR);
        try {
            while (System.currentTimeMillis() - start < durationMs) {
                long t0 = System.currentTimeMillis();

                sb.setLength(0);
                sb.append(ANSI_HOME).append(ANSI_CLEAR);

                char[][] screen = new char[height][width];
                for (int r = 0; r < height; r++) {
                    java.util.Arrays.fill(screen[r], ' ');

                }

                for (int c = 0; c < width; c++) {
                    int head = y[c];
                    screen[head % height][c] = MATRIX_CHARS[rnd.nextInt(MATRIX_CHARS.length)];
                    int tail1 = (head - 1 + height) % height;
                    int tail2 = (head - 2 + height) % height;
                    if (screen[tail1][c] == ' ') screen[tail1][c] = MATRIX_CHARS[rnd.nextInt(MATRIX_CHARS.length)];
                    if (screen[tail2][c] == ' ') screen[tail2][c] = MATRIX_CHARS[rnd.nextInt(MATRIX_CHARS.length)];

                    y[c] = (y[c] + speed[c]) % height;
                    if (rnd.nextDouble() < 0.02) speed[c] = 1 + rnd.nextInt(3);
                }

                for (int r = 0; r < height; r++) {
                    for (int c = 0; c < width; c++) {
                        char ch = screen[r][c];
                        if (ch == ' ') {
                            sb.append(' ');
                        } else {
                            boolean isHead = (r == 0 && screen[height-1][c] != ' ')
                                    || (r > 0 && screen[r-1][c] == ' ');
                            if (isHead) {
                                sb.append(ANSI_GREEN_BRIGHT).append(ch).append(ANSI_RESET);
                            } else if (r % 3 == 0) {
                                sb.append(ANSI_GREEN).append(ch).append(ANSI_RESET);
                            } else {
                                sb.append(ANSI_GREEN_DIM).append(ch).append(ANSI_RESET);
                            }
                        }
                    }
                    sb.append('\n');
                }

                System.out.print(sb.toString());

                long dt = System.currentTimeMillis() - t0;
                if (dt < frameTime) {
                    try { Thread.sleep(frameTime - dt); } catch (InterruptedException ignored) {}
                }
            }
        } finally {
            System.out.print(ANSI_HOME + ANSI_CLEAR + ANSI_SHOW_CURSOR);
            System.out.flush();
        }
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
                case "1":
                    System.out.println("\n-> Option A - Primary School menu is opening...");
                    subMenuOptionA(scanner);
                    break;

                case "B":
                case "2":
                    System.out.println("\n-> Option B Selected- Secondary School menu is opening...");
                    subMenuOptionB();
                    break;

                case "C":
                case "3":
                    System.out.println("\n-> Option C - High School menu is opening...");
                    subMenuOptionC();
                    break;

                case "D":
                case "4":
                    System.out.println("\n-> Option D - University menu is opening...");
                    connectFourGame();
                    break;

                case "E":
                case "5":
                    return; // FIX: return instead of break to exit while loop
                default:
                    System.out.println("\n Invalid selection! Please choose a number between 1 and 5.\n");
                    break;
            }
        }
    }

    // ===========================================
    //           OPTION A - PRIMARY SCHOOL
    // ===========================================

    /*
        Option A Task 1 -  Age and Zodiac Sign Detection
    */

    /**
     * Shows the submenu for Option A (Primary School).
     * Lets the user choose between:
     * 1) Age and Zodiac Sign Detection
     * 2) Reverse the Words (Recursive)
     * 3) Return to Main Menu
     * Loops until the user selects option 3.
     *
     * @author Eren Çakır Bircan
     * @param input Scanner used for user input.
     */
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
                input.nextLine();
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

    /**
     * Gets today's day of the month.
     * @return Current day (1–31)
     */
    private static int getCurrentDay() {
        return LocalDate.now().getDayOfMonth();
    }

    /**
     * Gets today's month number.
     * @return Current month (1–12)
     */
    private static int getCurrentMonth() {
        return LocalDate.now().getMonthValue();
    }

    /**
     * Gets the current year.
     * @return Current year (0-2025)
     */
    private static int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    /**
     * Gets the user's birth date and shows related information.
     * It asks for year, month, and day inputs until a valid date is entered.
     * Then prints the birth date, zodiac sign, and age.
     * Lastly it returns to the Primary School submenu.
     *
     * @author Eren Çakır Bircan
     * @param input Scanner used for user input.
     */
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

    /**
     * Prompts the user to enter a valid year month even if user enter wrong number.
     * @author Eren Çakır Bircan
     * @param input Scanner object for user input.
     * @return A valid year number or enter -44444444 for exit condition.
     */
    private static int getYear(Scanner input) {
        int currentYear = getCurrentYear();
        int year = -1;

        do {
            System.out.print("Enter your birth year (0 to " + currentYear + "): ");

            if (input.hasNextInt()) {
                year = input.nextInt();
                input.nextLine();

                if (year < 0 || year > currentYear) {
                    System.out.println("Invalid year. Please enter a year between 0 and " + currentYear + ".");
                }

            } else {
                System.out.println("Invalid input. Please enter a whole number for the year.");
                input.nextLine(); // hatalı girişi temizler
            }

        } while (year < 0 || year > currentYear);

        return year;
    }

    /**
     * Prompts the user to enter a valid birth month even if user enter wrong number.
     * @author Eren Çakır Bircan
     * @param input Scanner object for user input.
     * @return A valid month number.
     */

    private static int getMonth(Scanner input) {
        int month = 0;

        do {
            System.out.print("Enter the birth month number (1 for Jan, 12 for Dec): ");

            if (input.hasNextInt()) {
                month = input.nextInt();
                input.nextLine();

                if (month < 1 || month > 12) {
                    System.out.println("Invalid month. Please enter a number between 1 and 12.");
                }

            } else {
                System.out.println("Invalid input. Please enter a whole number for the month.");
                input.nextLine(); // hatalı girişi temizler
            }

        } while (month < 1 || month > 12);

        String[] monthNames = {"", "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        System.out.println("Selected month is " + monthNames[month] + ".");

        return month;
    }

    /**
     * Prompts the user to enter a valid birth day even if user try to enter wrong number.
     * @author Eren Çakır Bircan
     * @param input Scanner object for user input.
     * @return A valid day number or enter -44444444 for exit condition.
     */
    private static int getDay(Scanner input, int month, int year) {
        int maxDay = daysInMonth(month, year);
        int day = 0;

        do {
            System.out.print("Enter the day of your birthday (1-" + maxDay + "): ");

            if (input.hasNextInt()) {
                day = input.nextInt();
                input.nextLine();

                if (day < 1 || day > maxDay) {
                    System.out.println("Invalid day. Please enter a number between 1 and "
                            + maxDay + " for " + monthToName(month) + ".");
                }

            } else {
                System.out.println("Invalid input. Please enter a whole number for the day.");
                input.nextLine(); // hatalı girdiyi temizler
            }

        } while (day < 1 || day > maxDay);

        return day;
    }

    /**
     * Calculates the number of days in a given month for a specific year for dates.
     * Takes into account leap years for February (29 days in leap years and 28 for other years).
     * Returns 31 days for January, March, May, July, August, October, December.
     * Returns 30 days for April, June, September, November.
     *
     * @author Eren Çakır Bircan
     * @param month The month number (1-12).
     * @param year The year to check for leap year calculation.
     * @return The number of days in the specified month, or 0 if month is invalid.
     */
    private static int daysInMonth(int month, int year) {
        int maxDay;

        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                maxDay = 31;
                break;
            case 4: case 6: case 9: case 11:
                maxDay = 30;
                break;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    maxDay = 29;
                } else {
                    maxDay = 28;
                }
                break;
            default:
                maxDay = 0;
        }
        return maxDay;
    }

    /**
     * Converts and match month numbers to their corresponding month name in English.
     * Returns the full name of the month
     *
     * @author Eren Çakır Bircan
     * @param month The month number (1-12).
     * @return The name of the month as a String, or "Invalid Month" if the number is out of range.
     */
    private static String monthToName(int month) {
        String[] monthNames = {"", "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return month >= 1 && month <= 12 ? monthNames[month] : "Invalid Month";
    }

    /**
     * Checks if a date is valid and not in the future.
     * Compares the given date with today's date.
     *
     * @author Eren Çakır Bircan
     * @param year The year to check.
     * @param month The month to check (1-12).
     * @param day The day to check.
     * @return 1 if the date is valid and not in the future, 0 if the date is in the future.
     */
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

    /**
     * Calculates and prints the exact age based on a birth date taken from the user.
     * Shows the age in years, months, and days.
     * Adjusts for negative day and month differences correctly.
     *
     * @author Eren Çakır Bircan
     * @param day The birth day.
     * @param month The birth month (1-12).
     * @param year The birth year.
     */
    private static void calculateAge(int day, int month, int year) {
        int currentYear = getCurrentYear();
        int currentMonth = getCurrentMonth();
        int currentDay = getCurrentDay();

        int yearDiff = currentYear - year;
        int monthDiff = currentMonth - month;
        int dayDiff = currentDay - day;

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

    /**
     * Determines and prints the zodiac sign for a given birth day and month for given input by user.
     * Prints an ASCII-art banner for the sign and then.
     * Note: This method only checks the month range (1–12).
     * If the month is outside 1–12, it prints "Invalid month."
     *
     * @author Eren Çakır Bircan
     * @param day   The day of the month
     * @param month The month number (1–12).
     */
    private static void calculateZodiac(int day, int month) {
        String sign = "";

        if (month == 1) {
            if (day <= 19) {
                sign = "Capricorn";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%#=+#%%%%%%%%%*=--+#%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%:....-%%%%%:.......:%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%#-....+%+....:=:...=%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%.........#%%*...=%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%:.. ...%%%%#...-%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%:. ..*%%%%#...:%%#=--=#%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*....@%%%%%.............:#%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#...:%%%%%%......+%%%%*:..=%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=.:*%%%%%%=.. .#%%%%%%%:..#%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%*.. -%%%%%%%%+..#%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%#... .#%%%%%%%...%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%#+:.......=#%%#=...#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%:..........:%%*........=%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%-.......-+%%%%%%%%%#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            } else {
                sign = "Aquarius";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%+*%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%=.....:*%%%%%%%%%%=.....=%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%+...........:*%%%%=...........=%%%%%%%%%%%%\n" +
                        "%%%%%%%%*:.....:*%%+.............-#%#-.....:+%%%%%%%%%\n" +
                        "%%%%%%%-....:*%%%%%%%%=.......:#%%%%%%%#-.....#%%%%%%%\n" +
                        "%%%%%%%#--*%%%%#-.-#%%%%%=::*%%%%*--*%%%%%%+-+%%%%%%%%\n" +
                        "%%%%%%%%%%%%#-.......-*%%%%%%%#-......:+%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%-......:......:*%%-............:*%%%%%%%%%%%\n" +
                        "%%%%%%%*......-#%%%%-...........-%%%%#:.....:%%%%%%%%%\n" +
                        "%%%%%%#:...-#%%%%%%%%%#-.....-#%%%%%%%%%*-....%%%%%%%%\n" +
                        "%%%%%%%%##%%%%%%%%%%%%%%%#*#%%%%%%%%%%%%%%%%#%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            }
        } else if (month == 2) {
            if (day <= 18) {
                sign = "Aquarius";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%+*%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%=.....:*%%%%%%%%%%=.....=%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%+...........:*%%%%=...........=%%%%%%%%%%%%\n" +
                        "%%%%%%%%*:.....:*%%+.............-#%#-.....:+%%%%%%%%%\n" +
                        "%%%%%%%-....:*%%%%%%%%=.......:#%%%%%%%#-.....#%%%%%%%\n" +
                        "%%%%%%%#--*%%%%#-.-#%%%%%=::*%%%%*--*%%%%%%+-+%%%%%%%%\n" +
                        "%%%%%%%%%%%%#-.......-*%%%%%%%#-......:+%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%-......:......:*%%-............:*%%%%%%%%%%%\n" +
                        "%%%%%%%*......-#%%%%-...........-%%%%#:.....:%%%%%%%%%\n" +
                        "%%%%%%#:...-#%%%%%%%%%#-.....-#%%%%%%%%%*-....%%%%%%%%\n" +
                        "%%%%%%%%##%%%%%%%%%%%%%%%#*#%%%%%%%%%%%%%%%%#%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            } else {
                sign = "Pisces";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%#..+%%%%%%%%%%%%#:.+%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%*...:%%%%%%%%%%-...=%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%*...:%%%%%%%%+...+%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%:...=%%%%%%*...-%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%....#%%%%%:...#%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%-...=%%%%*...:%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%++=...:++**-...-+*%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%:............. ....:%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%*==....-:::....-+*%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%=...=%%%%*....%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%....#%%%%%:...*%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%-...=%%%%%%*....#%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%+...-%%%%%%%%*...-%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+...-%%%%%%%%%%#...:%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%#--*%%%%%%%%%%%%%--*%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            }
        } else if (month == 3) {
            if (day <= 20) {
                sign = "Pisces";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%#..+%%%%%%%%%%%%#:.+%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%*...:%%%%%%%%%%-...=%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%*...:%%%%%%%%+...+%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%:...=%%%%%%*...-%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%....#%%%%%:...#%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%-...=%%%%*...:%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%++=...:++**-...-+*%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%:............. ....:%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%*==....-:::....-+*%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%=...=%%%%*....%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%....#%%%%%:...*%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%-...=%%%%%%*....#%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%+...-%%%%%%%%*...-%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+...-%%%%%%%%%%#...:%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%#--*%%%%%%%%%%%%%--*%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            } else {
                sign = "Aries";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%*=:....-#%%%%%%%%%+-:..:-+%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%=....:....=%%%%%%*:...::....+%%%%%%%%%%%\n" +
                        "%%%%%%%%%%*:..*%%%#-..-#%%%+...+%%%%=..:*%%%%%%%%%%\n" +
                        "%%%%%%%%%%=..:#%%%%%=..-#%*:..*%%%%%#..:*%%%%%%%%%%\n" +
                        "%%%%%%%%%%+...#%%%%%#:..=*-..=%%%%%%=..:#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%-..-%%%%%%*...:..:*%%%%%#...=%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%+=+%%%%%%%=.....+%%%%%%#-:+%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%*.. ..*%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%*.. .-#%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%#-. .+%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%+...+%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%+...*%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%*:.=%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            }
        } else if (month == 4) {
            if (day <= 20) {
                sign = "Aries";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%*=:....-#%%%%%%%%%+-:..:-+%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%=....:....=%%%%%%*:...::....+%%%%%%%%%%%\n" +
                        "%%%%%%%%%%*:..*%%%#-..-#%%%+...+%%%%=..:*%%%%%%%%%%\n" +
                        "%%%%%%%%%%=..:#%%%%%=..-#%*:..*%%%%%#..:*%%%%%%%%%%\n" +
                        "%%%%%%%%%%+...#%%%%%#:..=*-..=%%%%%%=..:#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%-..-%%%%%%*...:..:*%%%%%#...=%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%+=+%%%%%%%=.....+%%%%%%#-:+%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%*.. ..*%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%*.. .-#%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%#-. .+%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%+...+%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%+...*%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%*:.=%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            } else {
                sign = "Taurus";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%#:...:=#%%%%%%%%%%%%%%%%%%#+-::-*%%%%%%%%%%\n" +
                        "%%%%%%%%=........=%%%%%%%%%%%%%%-........#%%%%%%%%%\n" +
                        "%%%%%%%%%#*+-......#%%%%%%%%%%+.....:=*#%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%#:....-%%%%%%%#:....*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%=......---:.....=%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%.............#%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%#....:+###+-....+%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%#...-%%%%%%%%%+...+%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%-..=%%%%%%%%%%%+...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%:..#%%%%%%%%%%%%:..*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%:..#%%%%%%%%%%%%...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%+..:%%%%%%%%%%%=..-%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%=...+%%%%%%%#...:%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%:....:-:.....*%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+-:....:+#%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            }
        } else if (month == 5) {
            if (day <= 20) {
                sign = "Taurus";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%#:...:=#%%%%%%%%%%%%%%%%%%#+-::-*%%%%%%%%%%\n" +
                        "%%%%%%%%=........=%%%%%%%%%%%%%%-........#%%%%%%%%%\n" +
                        "%%%%%%%%%#*+-......#%%%%%%%%%%+.....:=*#%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%#:....-%%%%%%%#:....*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%=......---:.....=%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%.............#%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%#....:+###+-....+%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%#...-%%%%%%%%%+...+%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%-..=%%%%%%%%%%%+...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%:..#%%%%%%%%%%%%:..*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%:..#%%%%%%%%%%%%...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%+..:%%%%%%%%%%%=..-%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%=...+%%%%%%%#...:%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%:....:-:.....*%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+-:....:+#%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            } else {
                sign = "Gemini";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%=.:-*%%%%%%%%%%%%#=::+%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%*:....:-=+***+=-....:*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%#=:............-+#%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+..=%%###=..=%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+..=%%%%%+..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+..+%%%%%+..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+..+%%%%%+..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+.:+%%%%%+..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+.:+%%%%%+..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+.:*%%%%%+..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%=..=*++++-..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*-..............:=*%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%*:...:-=*#%%##*=-....-#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%#-:=#%%%%%%%%%%%%%*-.-#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            }
        } else if (month == 6) {
            if (day <= 20) {
                sign = "Gemini";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%=.:-*%%%%%%%%%%%%#=::+%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%*:....:-=+***+=-....:*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%#=:............-+#%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+..=%%###=..=%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+..=%%%%%+..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+..+%%%%%+..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+..+%%%%%+..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+.:+%%%%%+..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+.:+%%%%%+..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%+.:*%%%%%+..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%=..=*++++-..+%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*-..............:=*%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%*:...:-=*#%%##*=-....-#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%#-:=#%%%%%%%%%%%%%*-.-#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            } else {
                sign = "Cancer";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%#*-::........-*%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%#:....................:#%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%*.........+%%%%%%##*=:.....*%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%+..:#%%%#...=%%%%%%%%%%%%+:-#%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%:..%%%%%%%...%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%:..#%%%%%#...%%%%%%*-::-+%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%#...-#%#-...*%%%%:........:%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%-.......-%%%%#...=%%%%+...#%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#*#%%%%%%%=..-%%%%%%=..+%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%+..:%%%%%%-..+%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%:..:*%%%%%%%%%%%-...=##+...=%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%*........-=++****:........#%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%*-.................:+%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%#*+==+++*##%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            }
        } else if (month == 7) {
            if (day <= 22) {
                sign = "Cancer";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%#*-::........-*%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%#:....................:#%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%*.........+%%%%%%##*=:.....*%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%+..:#%%%#...=%%%%%%%%%%%%+:-#%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%:..%%%%%%%...%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%:..#%%%%%#...%%%%%%*-::-+%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%#...-#%#-...*%%%%:........:%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%-.......-%%%%#...=%%%%+...#%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#*#%%%%%%%=..-%%%%%%=..+%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%+..:%%%%%%-..+%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%:..:*%%%%%%%%%%%-...=##+...=%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%*........-=++****:........#%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%*-.................:+%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%#*+==+++*##%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            } else {
                sign = "Leo";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%#-.........=%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%#:.............=%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%:....-#%%%%=.. .-%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%-...:%%%%%%%%*....*%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#....+%%%%%%%%%:...-%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#....*%%%%%%%%%:...-%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#....*%%%%%%%%#....+%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#....+%%%%%%%%=....#%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%#=:.......=%%%%%%%*....=%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%#:..........:#%%%%%%:...:%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%#:..-#%%%%+...=%%%%%+....#%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%=..-%%%%%%%#...#%%%#....*%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%:..+%%%%%%%%:..*%%%=...-%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%=...#%%%%%%*...#%%%....#%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%:...:+**=:...*%%%=...:%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%*.........=%%%%%-...:%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%*+==*#%%%%%%%=....#%%%%=..:%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%....:#%#-...:%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%*...........#%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%=.......+%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            }
        } else if (month == 8) {
            if (day <= 22) {
                sign = "Leo";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%#-.........=%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%#:.............=%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%:....-#%%%%=.. .-%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%-...:%%%%%%%%*....*%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#....+%%%%%%%%%:...-%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#....*%%%%%%%%%:...-%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#....*%%%%%%%%#....+%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#....+%%%%%%%%=....#%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%#=:.......=%%%%%%%*....=%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%#:..........:#%%%%%%:...:%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%#:..-#%%%%+...=%%%%%+....#%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%=..-%%%%%%%#...#%%%#....*%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%:..+%%%%%%%%:..*%%%=...-%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%=...#%%%%%%*...#%%%....#%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%:...:+**=:...*%%%=...:%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%*.........=%%%%%-...:%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%*+==*#%%%%%%%=....#%%%%=..:%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%....:#%#-...:%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%*...........#%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%=.......+%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            } else {
                sign = "Virgo";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%#+-::-*%*-...=#%*:..:=#%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%#........................:%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%....*%*.. ..*%=.....=*:...:%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%...+%%%=...*%%%-...*%%%....#*+*#%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%+...#%%%=...#%%%:.........:#%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%+...#%%%=...#%%%: ...=##+...#%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%+...#%%%=...#%%%: .:%%%%%*..+%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*...#%%%=...#%%%:..=%%%%%=..+%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*...#%%%=...#%%%: .+%%%%#...#%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*...#%%%= ..#%%%...+%%%%...*%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*...#%%%=...#%%%...+%%#...*%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*...#%%%=...#%%%...+%+...*%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#...#%%%=...#%%%...=-...%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#...%%%%+...%%%%......=%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%+:......#%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%#...........:%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%-..-*%%%=...%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            }
        } else if (month == 9) {
            if (day <= 22) {
                sign = "Virgo";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%#+-::-*%*-...=#%*:..:=#%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%#........................:%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%....*%*.. ..*%=.....=*:...:%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%...+%%%=...*%%%-...*%%%....#*+*#%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%+...#%%%=...#%%%:.........:#%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%+...#%%%=...#%%%: ...=##+...#%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%+...#%%%=...#%%%: .:%%%%%*..+%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*...#%%%=...#%%%:..=%%%%%=..+%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*...#%%%=...#%%%: .+%%%%#...#%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*...#%%%= ..#%%%...+%%%%...*%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*...#%%%=...#%%%...+%%#...*%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*...#%%%=...#%%%...+%+...*%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#...#%%%=...#%%%...=-...%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#...%%%%+...%%%%......=%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%+:......#%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%#...........:%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%-..-*%%%=...%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            } else {
                sign = "Libra";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%#+=+#%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%*.........:#%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%:....-=-:....+%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%:...%%%%%%%:...*%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%*...+%%%%%%%%...=%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%*...-%%%%%%%#...=%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%-...........=%%%%%*...........-%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%#............-%%%%%+...........:%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%#**************************###%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%#................................@%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%*++++++++++===================#%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            }
        } else if (month == 10) {
            if (day <= 22) {
                sign = "Libra";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%#+=+#%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%*.........:#%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%:....-=-:....+%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%:...%%%%%%%:...*%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%*...+%%%%%%%%...=%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%*...-%%%%%%%#...=%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%-...........=%%%%%*...........-%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%#............-%%%%%+...........:%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%#**************************###%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%#................................@%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%*++++++++++===================#%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            } else {
                sign = "Scorpio";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%=.....--.....:+=.....=%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%=........................=%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%#...+%%%....*%%+....#%%:...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%=:=%%%%-...%%%#...:%%%+...*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=...%%%#...:@%%=. .*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=...%%%#...:%%%=...+%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=...%%%#...-%%%=...*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=...%%%#...-%%%=...*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=...%%%#...-%%%=...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%-...%%%#...=%%%=...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%-...%%%#...=%%%+...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%-...%%%#...=%%%*...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=..:%%%#...+%%%#...=%%+..=%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%-..........*%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%-.........%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#:....#%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%+..+%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            }
        } else if (month == 11) {
            if (day <= 22) {
                sign = "Scorpio";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%=.....--.....:+=.....=%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%=........................=%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%#...+%%%....*%%+....#%%:...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%=:=%%%%-...%%%#...:%%%+...*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=...%%%#...:@%%=. .*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=...%%%#...:%%%=...+%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=...%%%#...-%%%=...*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=...%%%#...-%%%=...*%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=...%%%#...-%%%=...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%-...%%%#...=%%%=...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%-...%%%#...=%%%+...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%-...%%%#...=%%%*...#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=..:%%%#...+%%%#...=%%+..=%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%-..........*%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%-.........%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#:....#%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%+..+%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"); // <-- Scorpio ASCII buraya
            } else {
                sign = "Sagittarius";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#############%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%#..............:%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%-..  .....   ..#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%+..... ..#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%+..........#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%*......+#....#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%*......=%%#...:#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%#-..*%%*......=%%%%#...:%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%*.....:.....-#%%%%%%=::*%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%-.......-#%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%=.......=%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%+.....:....:%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*.....=%%%*:.+%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%*.....=%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%:....-%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%-..-%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            }
        } else if (month == 12) {
            if (day <= 21) {
                sign = "Sagittarius";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#############%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%#..............:%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%-..  .....   ..#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%+..... ..#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%+..........#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%*......+#....#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%*......=%%#...:#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%#-..*%%*......=%%%%#...:%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%*.....:.....-#%%%%%%=::*%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%-.......-#%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%=.......=%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%+.....:....:%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*.....=%%%*:.+%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%*.....=%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%:....-%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%-..-%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            } else {
                sign = "Capricorn";
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%#=+#%%%%%%%%%*=--+#%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%:....-%%%%%:.......:%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%#-....+%+....:=:...=%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%.........#%%*...=%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%:.. ...%%%%#...-%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%:. ..*%%%%#...:%%#=--=#%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%*....@%%%%%.............:#%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%#...:%%%%%%......+%%%%*:..=%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%=.:*%%%%%%=.. .#%%%%%%%:..#%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%*.. -%%%%%%%%+..#%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%#... .#%%%%%%%...%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%#+:.......=#%%#=...#%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%:..........:%%*........=%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%-.......-+%%%%%%%%%#%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                        "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            }
        } else {
            System.out.println("Invalid month.");
        }

        System.out.println("Your Zodiac sign is: " + sign);
    }


    // Option A Task 2: Reverse the Words Main Code
    /*
    * Prompts the user to enter a text and then displays the reversed version of that text.
    * @author Zafer Mert Serinken
    */
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

    /*
     * Reverses each word within a given sentence individually.
     * @author Zafer Mert Serinken
     * @param sentence The input string containing one or more words to be processed.
     * @return A new String where the letters of each word have been reversed.
     */
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

    /*
     * Reverses the letter components of a single word, while leaving any
     * non-letter characters in their original positions.
     * @author Zafer Mert Serinken
     * @param word The single word string to be processed.
     * @return A new String with only the letter parts reversed.
     */
    private static String reverseSingleWord(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }

        StringBuilder reversedWordBuilder = new StringBuilder();
        StringBuilder currentPart = new StringBuilder();

        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) {
                currentPart.append(c);
            } else {
                reversedWordBuilder.append(currentPart.reverse());
                currentPart.setLength(0);
                reversedWordBuilder.append(c);
            }
        }
        reversedWordBuilder.append(currentPart.reverse());

        return reversedWordBuilder.toString();
    }


    // ===========================================
    /*
     * Function takes an integer that equal/larger than 12 from the user.
     * Calculates primes to n by using Sieve of Eratosthenes, Sieve of Sundaram, and Sieve of Atkin algorithms.
     * Shows the execution time to compare.
     * @author Selçuk Aloba
     * return no return because it is a void function.
     */
    public static void CalculatePrimeNumbers()
    {
        int n;
        // safe upper limit close to overflow
        final int MAX_PRACTICAL_LIMIT = 2_000_000_000;

        do {
            System.out.print("Please enter an integer n (n >= 12): ");
            if (scanner.hasNextInt())
            {
                n = scanner.nextInt();
                scanner.nextLine();

                if (n < 12)
                {
                    System.out.println("Input should be larger/equal to 12. Please try again.");
                }
                else if (n > MAX_PRACTICAL_LIMIT)
                {
                    System.out.println("Input value is too large and impractical to compute.");
                    System.out.println("Please enter a value smaller than " + MAX_PRACTICAL_LIMIT);
                    n = 0;
                }
            }
            else
            {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
                scanner.nextLine();
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

    /*
     * Sieve of Eratosthenes algorithm finds all prime numbers up to a given limit n.
     * It iteratively marks multiples of each prime number starting from 2 as not prime
     * It prints the first 3 and last 2 primes along with execution time.
     * @param n The upper limit for prime number generation.
     * @return There is no return because it is a void function
     * @author Selçuk Aloba
     */
    public static void SieveOfEratosthenes(int n)
    {
        long startTime = System.nanoTime();
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for(int j=2; (long)j * j <= n; j++)
        {
            if(isPrime[j])
            {
                long start = (long)j * j;
                for(long i = start; i <= n; i += j)
                {
                    isPrime[(int)i]=false;
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

    /*
     * Sieve of Sundaram algorithm finds all prime numbers up to a given limit n
     * It works by eliminating numbers 'k' of the form i + j + 2ij
     * Numbers 'i' that are not eliminated are used to find primes using the formula 2*i + 1. (2 is added manually)
     * It prints the first 3 and last 2 primes along with execution time
     * @param n is the upper limit for prime number generation
     * @return There is no return because it is a void function
     * @author Selçuk Aloba
     */
    public static void SieveOfSundaram(int n)
    {
        long startTime = System.nanoTime();
        int k = (n-1)/2;

        boolean[] isNotPrime = new boolean[k+1];

        for (int i = 1; i <= Math.sqrt(k); i++)
        {
            int j = i;
            while (true)
            {
                long indexLong = (long)i + j + 2 * (long)i * j;

                if (indexLong > k)
                {
                    break;
                }
                isNotPrime[(int)indexLong] = true;

                j++;
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

    /*
     * Sieve of Atkin algorithm finds all prime numbers up to a given limit n
     * It is s an optimization that uses quadratic formulas (based on modulo 12 properties)
     * to mark prime candidates, and then eliminates multiples of prime squares
     * It prints the first 3 and last 2 primes along with execution time.
     * @param n is the upper limit for prime number generation.
     * @return There is no return because it is a void function
     * @author Selçuk Aloba
     */
    public static void SieveOfAtkin(int n)
    {
        long startTime = System.nanoTime();
        boolean[] sieve = new boolean[n+1];

        if(n>=2) sieve[2] = true;
        if(n>=3) sieve[3] = true;

        int limit = (int) Math.sqrt(n);

        for (int x = 1; x <= limit; x++)
        {
            for (int y = 1; y <= limit; y++)
            {
                long num;

                num = (4 * (long)x * x) + ((long)y * y);
                if (num <= n && (num % 12 == 1 || num % 12 == 5)) {
                    sieve[(int)num] = !sieve[(int)num];
                }

                num = (3 * (long)x * x) + ((long)y * y);
                if (num <= n && (num % 12 == 7)) {
                    sieve[(int)num] = !sieve[(int)num];
                }

                if (x > y) {
                    num = (3 * (long)x * x) - ((long)y * y);
                    if (num <= n && (num % 12 == 11)) {
                        sieve[(int)num] = !sieve[(int)num];
                    }
                }
            }
        }

        for (int r = 5; (long)r * r <= n; r++)
        {
            if (sieve[r])
            {
                long increment = (long)r * r;
                for (long i = increment; i <= n; i += increment)
                {
                    sieve[(int)i] = false;
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

    /**
     * Shows the submenu for Option B (Secondary School).
     * Lets the user choose between:
     * 1) Prime Number Generator
     * 2) Step-by-step Expression Evaluation
     * 3) Return to Main Menu
     * Keeps looping until the user selects option 3.
     *
     * @author Eren Çakır Bircan
     */
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
                        System.out.println("❌ Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("❌ Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        } while (choice != 3);
    }


    // Option B Task 2 ===========================================

     /* Assigns order to operators according to their mathematical operation priorities.
      * @param operator
      * @author Arda Dülger
      * return 0 has no priority, 1 has low priority, 2 has the most priority.
      */
    private static int takePriority(char operator){
        if (operator == '+' || operator == '-') return 1; // Assigns a precedence level to operators.
        if (operator == 'x' || operator == ':') return 2;
        return 0;  //Other characters return 0.
    }

    /*
     * Checks the 'c' if it's an operator.
     * @param c
     * @author Arda Dülger
     * @return true if the c is an operator '+,-,x,:' otherwise false.
     */
    private static boolean isOperator(char c){
        return c == '+' || c == '-' || c == 'x' || c == ':';
    } // It checks the valid operators.

    /*
     * Checks the 'c' if it's a digits.
     * @param c
     * @author Arda Dülger
     * @return ture if c is a digit, otherwise false.
     */
    private static boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }

    // --- Check Validation ---

    /*
     * Checks if a mathematical expression follows rules such as spaces, invalid characters,
     * incorrect use of parentheses, incorrect operator/operand order, and incorrect decimal number format.
     * @param expression A user-entered mathematical expression string to be checked for validity.
     * @author Arda Dülger
     * @return boolean returns true if the expression is valid; false if it is invalid, has bad, or incomplete syntax.
     */
    public static boolean isValidExpression(String expression) {
        if (expression == null || expression.trim().isEmpty()) return false;

        String trimmed = expression.trim();
        if (trimmed.isEmpty()) return false;

        for (int i = 0; i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);

            if (c == ' ') {
                int prevNonSpace = i - 1;
                while (prevNonSpace >= 0 && trimmed.charAt(prevNonSpace) == ' ') {
                    prevNonSpace--;
                }

                int nextNonSpace = i + 1;
                while (nextNonSpace < trimmed.length() && trimmed.charAt(nextNonSpace) == ' ') {
                    nextNonSpace++;
                }

                if (prevNonSpace >= 0 && nextNonSpace < trimmed.length() &&
                        isDigit(trimmed.charAt(prevNonSpace)) && isDigit(trimmed.charAt(nextNonSpace)))
                {
                    return false;
                }
            } else if (!(isDigit(c) || isOperator(c) || c == '(' || c == ')')) {

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

        for (int i = 0; i < trimmed.length() - 1; i++) {
            char current = trimmed.charAt(i);
            char next = trimmed.charAt(i + 1);

            if (current == ' ' || next == ' ') continue;

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
        }
        return true;
    }

    // --- Evaluation ---

    /*
     * It prints the original expression as is,
     * then passes the expression to the recursiveSolve method to start the actual calculation and returns its result.
     * @author Arda Dülger
     * @param expression The mathematical expression that needs to be solved.
     * @return The final calculated result of the expression.
     */
    public static int evaluateAndPrintSteps (String expression){
        System.out.println(expression);
        return recursiveSolve(expression);
    }

    /*
     * The goal is to reduce the given string expression to a single numerical value by applying the rules of
     * mathematical order of operations (first parentheses, then multiplication/division, last addition/subtraction).
     * @author Arda Dülger
     * @param expression A string of mathematical expressions to be solved.This string can be the expression initially entered by the user,
     * or it can be an intermediate expression solved and simplified in a previous step.
     * return numeric value,If the expression reduces entirely to a number, it returns that number (terminates the recursion).
     * @return recursivesolve() Sends the simplified expression back to the recursiveSolve method and returns the result of this new call.
     */
    private static int recursiveSolve (String expression){
        try {
            return Integer.parseInt(expression);
        } catch (NumberFormatException ignored) {}

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
            int innerResult = recursiveSolve(innerExpression);
            String resultStr = String.valueOf(innerResult);
            int endIndex = resultStr.length() - 1;

            while (endIndex > 0 && resultStr.charAt(endIndex) == '0') {
                endIndex--;
            }

            resultStr = resultStr.substring(0, endIndex + 1);
            //If the string ends with a dot, remove it.
            if (resultStr.endsWith(".")) {
                resultStr = resultStr.substring(0, resultStr.length() - 1);
            }

            String newExpression = expression.substring(0, OpenParent) + resultStr + expression.substring(CloseParent + 1);

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

        if (splitIndex != -1) {
            char operator = expression.charAt(splitIndex);
            String leftPart = expression.substring(0, splitIndex);
            String rightPart = expression.substring(splitIndex + 1);

            int leftValue = recursiveSolve(leftPart);
            int rightValue = recursiveSolve(rightPart);

            int result = 0;
            if (operator == '+') result = leftValue + rightValue;
            else if (operator == '-') result = leftValue - rightValue;
            else if (operator == 'x') result = leftValue * rightValue;
            else if (operator == ':') {
                if (rightValue == 0) throw new IllegalArgumentException("Error for division by zero");
                result = leftValue / rightValue;
            }

            String resultStr = String.valueOf(result);
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
            return Integer.parseInt(expression);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid expression; " + expression);
        }
    }

    // Main method and user login (main method in expressioncontroller)

    /*
     * Its basic function is to continuously receive mathematical expressions from the user,
     * verify them, calculate them and print the results to the screen.
     * @author Arda Dülger
     * @return The function doesn't return any value to the caller.
     */
    public static void expressioncontroller () {

        String input;
        Locale.setDefault(Locale.US);
        System.out.println("Expression Evaluation Program: Step by Step (Press ENTER to return to the Secondary School Menu...)");

        while (true) {
            System.out.print("Enter the expression: ");

            input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Returning to Secondary School menu...");
                break;
            }

            if (isValidExpression(input)) {
                try {
                    System.out.println("\n-- Solution with step by step --\n");

                    int finalResult = evaluateAndPrintSteps(input.trim());
                    System.out.printf("Final result = "+ finalResult);
                    System.out.println("\n--- END ---");

                    System.out.println("\n Press ENTER for return to return to the Secondary School Menu...");

                    scanner.nextLine();

                    break;

                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid expression. (" + e.getMessage() + ")");
                } catch (Exception e) {
                    System.out.println("Invalid expression.");
                }
            } else {
                System.out.println("Invalid expression.");
            }
        }
    }


    // ===========================================
    //             OPTION C - HIGH SCHOOL
    // ===========================================

    /**
     * Shows the submenu for Option C (High School).
     * Lets the user choose between:
     * 1) Array Statistics
     * 2) Distance Between Two Arrays
     * 3) Return to Main Menu
     * Repeats until the user selects option 3.
     *
     * @author Eren Çakır Bircan
     */
    private static void subMenuOptionC() {
        int choice = 0;
        do {
            System.out.println("\n=== OPTION C: HIGH SCHOOL ===");
            System.out.println("1-) Array Statistics");
            System.out.println("2-) Distance Between Two Arrays");
            System.out.println("3-) Return to Main Menu");
            System.out.print("Your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
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
                System.out.println("❌ Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        } while (choice != 3);
    }

    // Option C Task 1: Array Statistics Main Code
    /*
     * Prompts the user to define an array, validates the size and elements,
     * calculates various statistical measures (Median, Arithmetic, Geometric, and Harmonic Means),
     * and prints the formatted results to the console.
     * @author Zafer Mert Serinken and Eren Çakır Bircan
     */
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

    /*
     * Calculates the statistical median of a given array of double values
     * @author Zafer Mert Serinken
     * @param array The array of double values used for calculating the median.
     * @return The calculated median value as a double.
     */
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

    /*
     * Calculates the Mean of the elements in a given array.
     * @author Zafer Mert Serinken and Eren Çakır Bircan
     * @param array Values of the array used for calculating the mean.
     * @return The calculated arithmetic mean as a double.
     */
    public static double calculateArithmeticMean(double[] array) {
        double sum = 0;
        for (double num : array) {
            sum += num;
        }
        return sum / array.length;
    }

    /*
     * Calculates the Geometric Mean ean of the elements in a given array.
     * @author Zafer Mert Serinken and Eren Çakır Bircan
     * @param array Values of the array used for calculating the geometric mean.
     * @return The calculated geometric mean as a double.
     */
    public static double calculateGeometricMean(double[] array) {
        double product = 1.0;
        for (double num : array) {
            product *= num;
        }
        return Math.pow(product, 1.0 / array.length);
    }

    /*
     * Calculates the Harmonic Mean of the elements in a given array.
     * @author Zafer Mert Serinken and Eren Çakır Bircan
     * @param array Values of the array used for calculating the harmonic mean.
     * @return The calculated harmonic mean as a double.
     */
    public static double calculateHarmonicMean(double[] array) {
        // Call the recursive method to calculate the denominator (1/x1 + 1/x2 ...).
        double sumOfReciprocals = recursiveSumReciprocals(array, array.length);
        if (sumOfReciprocals == 0) {
            return 0.0;
        }
        return array.length / sumOfReciprocals;
    }

    /*
     * Recursively calculates the sum of the reciprocals (1/x) for all elements in the specified portion of the array.
     * @author Zafer Mert Serinken and Eren Çakır Bircan
     * @param array The array containing the double values.
     * @param n The number of elements to process, starting from the end of the array. This is the stopping condition for the recursion.
     * @return The calculated sum of the reciprocals of the first 'n' elements.
     */
    private static double recursiveSumReciprocals(double[] array, int n) {
        if (n == 0) { // Base case
            return 0;
        }
        // Recursive Step: Reverse the last element of the array (1/x)
        // and call yourself again for the remaining part of the array (n-1).
        return (1.0 / array[n - 1]) + recursiveSumReciprocals(array, n - 1);
    }

    // Option C Task C2: Distance Between Two Arrays Main Code

    /*
     * Function allows the user to input two integer arrays of equal size (elements should be in the interval 0–9)
     * It calculates Manhattan Distance, Euclidean Distance, and Cosine Similarity
     * It gives user input, performs calculations, and displays formatted results.
     * @return There is no return because it is a void function.
     * @author Selçuk Aloba & Arda Dülger
     */
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
            } while (!isValid );
        }

        scanner.nextLine();

        System.out.println("\nCalculating distances for:");
        System.out.println("Array 1: " + Arrays.toString(array1));
        System.out.println("Array 2: " + Arrays.toString(array2));

        double manhattan = calculateManhattanDistance(array1, array2);
        System.out.printf("Manhattan Distance: %.2f\n", manhattan);

        double euclidean = calculateEuclideanDistance(array1, array2);
        System.out.printf("Euclidean Distance: %.2f\n", euclidean);

        double cosine = calculateCosineSimilarity(array1, array2);
        System.out.printf("Cosine Similarity: %.2f\n", cosine);

        System.out.println("\nPress ENTER to return to the High School menu...");
        scanner.nextLine();
    }

    /*
     * Calculates the Manhattan Distance between two arrays.
     * Formula: |a1 - b1| + |a2 - b2| + ... + |an - bn|
     * @param a First integer array.
     * @param b Second integer array.
     * @return The total Manhattan Distance as a double value.
     * @author Selçuk Aloba and Arda Dülger
     */
    public static double calculateManhattanDistance(int[] a, int[] b)
    {
        double sum = 0; //total distance
        for (int i = 0; i < a.length; i++)
        {
            sum += Math.abs(a[i] - b[i]);
        }
        return sum;
    }

    /*
     * Calculates the Euclidean Distance between two arrays.
     * Formula: √((a1 - b1)² + (a2 - b2)² + ... + (an - bn)²)
     * @param a First integer array.
     * @param b Second integer array.
     * @return The Euclidean Distance as a double value.
     * @author Selçuk Aloba and Arda Dülger
     */
    public static double calculateEuclideanDistance(int[] a, int[] b)
    {
        double sumOfSquares = 0;
        for (int i = 0; i < a.length; i++) {
            sumOfSquares += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sumOfSquares);
    }

    /*
     * It calculates the Cosine Similarity between two arrays.
     * Formula: (A · B) / (|A| × |B|)
     * It shows how similar the two arrays are in direction (not magnitude).
     * @param a first integer array
     * @param b second integer array
     * @return The Cosine Similarity value in range [0, 1]. Returns 0 if a or b is a zero vector.
     * @author Selçuk Aloba and Arda Dülger
     */
    public static double calculateCosineSimilarity(int[] a, int[] b) {
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


    static char[][] C4_BOARD;
    static int C4_ROWS, C4_COLS;
    static char C4_CURRENT;
    static boolean C4_VS_AI = false;

    static String C4_P1_NAME, C4_P2_NAME;

    static final String C4_RESET = "\u001B[0m";
    static final String C4_RED = "\u001B[31m";
    static final String C4_BLUE = "\u001B[34m";

    static final int C4_CELL_W = 3;
    static final String C4_H = "━";
    static final String C4_V = "┃";
    static final String C4_EMPTY = " ";
    static final String C4_DISK = "●";

    static String C4_COLOR_X = C4_RED;
    static String C4_COLOR_O = C4_BLUE;

    /**
     * Main menu loop for Connect Four Game.
     * Lets the user choose PvP, vs AI, or return to main menu.
     *
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static void connectFourGame() {
        while (true) {
            System.out.println("\n--- CONNECT FOUR ---");
            System.out.println("1-) Play with a Friend");
            System.out.println("2-) Play with AI");
            System.out.println("3-) Return to Main Menu");
            System.out.print("Choose: ");
            String ch = scanner.nextLine().trim();

            if (ch.equals("1")) {
                C4_VS_AI = false;
                if (c4SetupPVP()) c4RunLoop();
            } else if (ch.equals("2")) {
                C4_VS_AI = true;
                if (c4SetupAI()) c4RunLoop();
            } else if (ch.equals("3")) {
                return;
            } else {
                System.out.println("❌ Invalid choice.");
            }
        }
    }

    /**
     * Sets up a two-player (player vs player) game.
     * Reads player names, selects board size, initializes the board, and sets ● to start.
     *
     * @return true if setup completed; false if the user went back.
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */

    private static boolean c4SetupPVP() {
        System.out.print("Enter name for Player 1 (Red ●): ");
        C4_P1_NAME = scanner.nextLine().trim();
        if (C4_P1_NAME.isEmpty()) C4_P1_NAME = "Player 1";

        System.out.print("Enter name for Player 2 (Blue ●): ");
        C4_P2_NAME = scanner.nextLine().trim();
        if (C4_P2_NAME.isEmpty()) C4_P2_NAME = "Player 2";

        C4_COLOR_X = C4_RED;
        C4_COLOR_O = C4_BLUE;

        if (!c4ChooseSizeAndInit()) return false;
        C4_CURRENT = 'X';
        System.out.println("\nGame setup complete. " + C4_P1_NAME + " starts!");
        return true;
    }

    /**
     * Sets up a human vs AI game.
     * Asks for player name, let the user pick a color and selects board size.
     * Then initializes the board, assigns the AI side, and sets the starting player.
     *
     * @return true if setup completed, false if the user went back.
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static boolean c4SetupAI() {
        System.out.print("Enter your name: ");
        C4_P1_NAME = scanner.nextLine().trim();
        if (C4_P1_NAME.isEmpty()) C4_P1_NAME = "You";

        C4_P2_NAME = "Computer";

        char humanSide = c4AskColorAndAssign();

        if (!c4ChooseSizeAndInit()) return false;

        C4_CURRENT = 'X';
        if (humanSide == 'X') {
            System.out.println(C4_P1_NAME + " starts!");
        } else {
            System.out.println(C4_P2_NAME + " starts!");
        }

        c4AI_setSide(humanSide == 'X' ? 'O' : 'X');
        return true;
    }

    /**
     * Asks the human to choose a color (red/blue) and maps it to the side ●.
     * Also sets the display colors for both sides.
     * Uses red and blue bullet symbols (●) instead of X and O during display.
     *
     * @return 'X' if the human plays X; 'O' if the human plays O .
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static char c4AskColorAndAssign() {
        while (true) {
            System.out.print("Choose your color [red/blue]: ");
            String s = scanner.nextLine().trim().toLowerCase();

            if (s.equals("red") || s.equals("R") || s.equals("RED")||s.equals("Red")) {
                C4_COLOR_X = C4_RED;
                C4_COLOR_O = C4_BLUE;
                System.out.println("You are " + C4_RED + "RED ●" + C4_RESET + " (X). Computer is " + C4_BLUE + "BLUE ●" + C4_RESET + " (O).");
                return 'X';
            } else if (s.equals("blue") || s.equals("b")||s.equals("BLUE")||s.equals("Blue")) {
                C4_COLOR_X = C4_RED;
                C4_COLOR_O = C4_BLUE;
                System.out.println("You are " + C4_BLUE + "BLUE ●" + C4_RESET + " (O). Computer is " + C4_RED + "RED ●" + C4_RESET + " (X).");
                return 'O';
            } else {

                System.out.println("❌ Invalid enter. Please enter 'red' or 'blue'.");
            }
        }
    }

    /**
     * Lets the user choose one of the board sizes and initializes the board array for game.
     *
     * @return true if a size was chosen and the board was initialized, false to go back.
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static boolean c4ChooseSizeAndInit() {
        while (true) {
            System.out.println("\nSelect Board Size:");
            System.out.println("1-) 5x4");
            System.out.println("2-) 6x5");
            System.out.println("3-) 7x6");
            System.out.println("4-) Back");
            System.out.print("Choose: ");
            String ch = scanner.nextLine().trim();

            if (ch.equals("1")) {
                C4_ROWS = 5;
                C4_COLS = 4;
                break;
            } else if (ch.equals("2")) {
                C4_ROWS = 6;
                C4_COLS = 5;
                break;
            } else if (ch.equals("3")) {
                C4_ROWS = 7;
                C4_COLS = 6;
                break;
            } else if (ch.equals("4")) {
                return false;
            } else {
                System.out.println("❌ Invalid input.");
            }
        }

        C4_BOARD = new char[C4_ROWS][C4_COLS];
        for (int r = 0; r < C4_ROWS; r++) Arrays.fill(C4_BOARD[r], ' ');
        return true;
    }

    /**
     * Main game loop for a single match.
     * Alternates turns, gets a valid move (or AI move), drops a disk, checks win and tie condition,
     * prints the final result, and waits for input ENTER to return to the menu.
     *
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static void c4RunLoop() {
        boolean win = false;
        int turn = 1, maxTurns = C4_ROWS * C4_COLS;

        while (!win && turn <= maxTurns) {
            c4PrintBoard();

            int col;
            if (C4_VS_AI && C4_CURRENT == c4AI_getSide()) {
                System.out.println(C4_P2_NAME + " is thinking...");
                col = c4AI_bestMove(C4_BOARD, C4_ROWS, C4_COLS, 3);
                if (col == -1) break;
            } else {
                col = c4GetValidMove();
            }

            c4Drop(col);
            win = c4CheckWin(C4_CURRENT);

            if (!win) {
                C4_CURRENT = (C4_CURRENT == 'X') ? 'O' : 'X';
                turn++;
            }
        }

        c4PrintBoard();
        if (win) {
            String name = (!C4_VS_AI)
                    ? (C4_CURRENT == 'X' ? C4_P1_NAME : C4_P2_NAME)
                    : (C4_CURRENT == c4AI_getSide() ? C4_P2_NAME : C4_P1_NAME);
            String color = (C4_CURRENT == 'X') ? C4_COLOR_X : C4_COLOR_O;
            System.out.println("\n!!! " + color + name + " (" + C4_CURRENT + ") WON! " + C4_RESET + "!!!");
        } else {
            System.out.println("\n!!! TIE GAME! (Board is full) !!!");
        }

        System.out.println("Press ENTER to return to the Connect Four menu...");
        scanner.nextLine();
    }

    /**
     * Gets the current player's chosen column and provides it's valid.
     * prevents from putting fisk to full or out-of-range columns before returning a valid index.
     *
     * @return zero-based column index for the next move
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static int c4GetValidMove() {
        while (true) {
            String name = (C4_CURRENT == 'X') ? C4_P1_NAME : C4_P2_NAME;
            String color = (C4_CURRENT == 'X') ? C4_COLOR_X : C4_COLOR_O;
            System.out.print(color + name + " (" + C4_CURRENT + ")" + C4_RESET +
                    ", choose column (1-" + C4_COLS + "): ");

            if (scanner.hasNextInt()) {
                int col1 = scanner.nextInt();
                scanner.nextLine();
                int col = col1 - 1;
                if (col < 0 || col >= C4_COLS) {
                    System.out.println("❌ Invalid column number. Please enter between 1 and " + C4_COLS + ".");
                } else if (C4_BOARD[0][col] != ' ') {
                    System.out.println("❌ Column " + col1 + " is full. Try another.");
                } else {
                    return col;
                }
            } else {
                System.out.println("❌ Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Drops the current player's disk into the given column at the lowest empty row based on users input.
     *
     * @param col zero-based column index
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static void c4Drop(int col) {
        for (int r = C4_ROWS - 1; r >= 0; r--) {
            if (C4_BOARD[r][col] == ' ') {
                C4_BOARD[r][col] = C4_CURRENT;
                return;
            }
        }
    }


    /**
     * Renders the current board state to the console using box-drawing characters
     * and colored bullet symbols (●) instead of X and O to represent players.
     *
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static void c4PrintBoard() {
        System.out.println();
        System.out.print("  ");
        for (int c = 0; c < C4_COLS; c++) {
            int pad = (C4_CELL_W - String.valueOf(c + 1).length()) / 2;
            System.out.print(" ".repeat(pad) + (c + 1) + " ".repeat(C4_CELL_W - pad - 1) + " ");
        }
        System.out.println("\n");

        System.out.print("┏");
        for (int c = 0; c < C4_COLS; c++) {
            System.out.print(C4_H.repeat(C4_CELL_W));
            if (c < C4_COLS - 1) System.out.print("┳");
        }
        System.out.println("┓");

        for (int r = 0; r < C4_ROWS; r++) {
            System.out.print(C4_V);
            for (int c = 0; c < C4_COLS; c++) {
                char p = C4_BOARD[r][c];
                String cellStr;
                if (p == 'X') cellStr = C4_COLOR_X + C4_DISK + C4_RESET;
                else if (p == 'O') cellStr = C4_COLOR_O + C4_DISK + C4_RESET;
                else cellStr = C4_EMPTY;

                int left = (C4_CELL_W - 1) / 2;
                int right = C4_CELL_W - 1 - left;
                System.out.print(" ".repeat(left) + cellStr + " ".repeat(right));
                System.out.print(C4_V);
            }
            System.out.println();

            if (r < C4_ROWS - 1) {
                System.out.print("┣");
                for (int c = 0; c < C4_COLS; c++) {
                    System.out.print(C4_H.repeat(C4_CELL_W));
                    if (c < C4_COLS - 1) System.out.print("╋");
                }
                System.out.println("┫");
            }
        }

        System.out.print("┗");
        for (int c = 0; c < C4_COLS; c++) {
            System.out.print(C4_H.repeat(C4_CELL_W));
            if (c < C4_COLS - 1) System.out.print("┻");
        }
        System.out.println("┛\n");
    }
    /**
     * Checks if the player has four in a row horizontally, vertically, or diagonally.
     * Uses red and blue bullet symbols (●) instead of X and O during display.
     *
     * @param p the player symbol ('X' or 'O')
     * @return true if p has a winning line; false otherwise
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static boolean c4CheckWin(char p) {
        // Horizontal
        for (int r = 0; r < C4_ROWS; r++)
            for (int c = 0; c <= C4_COLS - 4; c++)
                if (C4_BOARD[r][c] == p && C4_BOARD[r][c + 1] == p && C4_BOARD[r][c + 2] == p && C4_BOARD[r][c + 3] == p)
                    return true;
        // Vertical
        for (int r = 0; r <= C4_ROWS - 4; r++)
            for (int c = 0; c < C4_COLS; c++)
                if (C4_BOARD[r][c] == p && C4_BOARD[r + 1][c] == p && C4_BOARD[r + 2][c] == p && C4_BOARD[r + 3][c] == p)
                    return true;
        // Diagonal ↗
        for (int r = 3; r < C4_ROWS; r++)
            for (int c = 0; c <= C4_COLS - 4; c++)
                if (C4_BOARD[r][c] == p && C4_BOARD[r - 1][c + 1] == p && C4_BOARD[r - 2][c + 2] == p && C4_BOARD[r - 3][c + 3] == p)
                    return true;
        // Diagonal ↘
        for (int r = 0; r <= C4_ROWS - 4; r++)
            for (int c = 0; c <= C4_COLS - 4; c++)
                if (C4_BOARD[r][c] == p && C4_BOARD[r + 1][c + 1] == p && C4_BOARD[r + 2][c + 2] == p && C4_BOARD[r + 3][c + 3] == p)
                    return true;
        return false;
    }

    static char C4_AI_SIDE = 'O';

    private static void c4AI_setSide(char side) { C4_AI_SIDE = side; }
    private static char c4AI_getSide() { return C4_AI_SIDE; }


    /**
     * Picks the best column for the AI using a depth-limited minimax search.
     * Breaks ties randomly among equally good moves.
     *
     * @param b     board array
     * @param rows  number of rows
     * @param cols  number of columns
     * @param depth search depth (plies)
     * @return zero-based column index for the AI move; 0 if no legal move found
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static int c4AI_bestMove(char[][] b, int rows, int cols, int depth) {
        java.util.List<Integer> bestColumns = new java.util.ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        for (int c = 0; c < cols; c++) {
            if (b[0][c] != ' ') continue; //

            int r = c4AI_FindNextRow(b, rows, c);
            if (r == -1) continue;

            b[r][c] = C4_AI_SIDE;

            int score = c4AI_minimax(b, rows, cols, depth - 1, false);

            b[r][c] = ' ';

            if (score > bestScore) {
                bestScore = score;
                bestColumns.clear();
                bestColumns.add(c);
            } else if (score == bestScore) {
                bestColumns.add(c);
            }
        }

        if (bestColumns.isEmpty()) return 0;
        return bestColumns.get(new java.util.Random().nextInt(bestColumns.size()));
    }

    /**
     * Minimax search with simple terminal evaluation:
     * +1000 for AI win (minus depth), −1000 for opponent win (plus depth), 0 at depth limit or full board.
     *
     * @param b     board array
     * @param rows  number of rows
     * @param cols  number of columns
     * @param depth remaining depth
     * @param isMax true if maximizing (AI turn); false if minimizing (opponent turn)
     * @return score from the current position for the AI
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static int c4AI_minimax(char[][] b, int rows, int cols, int depth, boolean isMax) {
        char opponent = (C4_AI_SIDE == 'X') ? 'O' : 'X';

        if (c4AI_Won(b, rows, cols, C4_AI_SIDE)) return 1000 - depth;
        if (c4AI_Won(b, rows, cols, opponent)) return -1000 + depth;

        if (depth == 0 || c4AI_isFull(b, cols)) return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int c = 0; c < cols; c++) {
                if (b[0][c] != ' ') continue;
                int r = c4AI_FindNextRow(b, rows, c);
                if (r == -1) continue;

                b[r][c] = C4_AI_SIDE;
                int score = c4AI_minimax(b, rows, cols, depth - 1, false);
                b[r][c] = ' ';

                best = Math.max(best, score);
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int c = 0; c < cols; c++) {
                if (b[0][c] != ' ') continue;
                int r = c4AI_FindNextRow(b, rows, c);
                if (r == -1) continue;

                b[r][c] = opponent;
                int score = c4AI_minimax(b, rows, cols, depth - 1, true);
                b[r][c] = ' ';

                best = Math.min(best, score);
            }
            return best;
        }
    }

    /**
     * Checks if player p has a connect-four on a given board.
     * Uses red and blue bullet symbols (●) instead of X and O during display.
     *
     * @param b    board array
     * @param rows number of rows
     * @param cols number of columns
     * @param p    player symbol ('X' or 'O')
     * @return true if p has won; false otherwise
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static boolean c4AI_Won(char[][] b, int rows, int cols, char p) {
        // Horizontical
        for (int r = 0; r < rows; r++)
            for (int c = 0; c <= cols - 4; c++)
                if (b[r][c]==p && b[r][c+1]==p && b[r][c+2]==p && b[r][c+3]==p)
                    return true;

        // Vertical
        for (int r = 0; r <= rows - 4; r++)
            for (int c = 0; c < cols; c++)
                if (b[r][c]==p && b[r+1][c]==p && b[r+2][c]==p && b[r+3][c]==p)
                    return true;

        // Diagonal ↗
        for (int r = 3; r < rows; r++)
            for (int c = 0; c <= cols - 4; c++)
                if (b[r][c]==p && b[r-1][c+1]==p && b[r-2][c+2]==p && b[r-3][c+3]==p)
                    return true;

        // Diagonal ↘
        for (int r = 0; r <= rows - 4; r++)
            for (int c = 0; c <= cols - 4; c++)
                if (b[r][c]==p && b[r+1][c+1]==p && b[r+2][c+2]==p && b[r+3][c+3]==p)
                    return true;

        return false;
    }

    /**
     * Finds the next free row in a column (the drop position).
     *
     * @param b    board array
     * @param rows number of rows
     * @param col  zero-based column index
     * @return the row index to place a disk, or -1 if the column is full
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static int c4AI_FindNextRow(char[][] b, int rows, int col) {
        for (int r = rows - 1; r >= 0; r--) {
            if (b[r][col] == ' ') return r;
        }
        return -1;
    }

    /**
     * Checks if the board has no free cells in the top row (i.e., no legal moves).
     *
     * @param b    board array
     * @param cols number of columns
     * @return true if the board is full; false otherwise
     * @author Eren Çakır Bircan, Zafer Mert Serinken, Arda Dülger, Selçuk Aloba
     */
    private static boolean c4AI_isFull(char[][] b, int cols) {
        for (int c = 0; c < cols; c++) {
            if (b[0][c] == ' ') return false;
        }
        return true;
    }
}