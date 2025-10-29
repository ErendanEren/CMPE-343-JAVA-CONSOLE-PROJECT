import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class Main
{
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        runMatrixRain(80, 24, 5000, 30);
        showWelcomeScreen();
        mainMenuLoop();
        System.out.println("Program terminated.");
    }

    // === MATRIX RAIN SABİTLERİ ===
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
                    // Programdan çıkış yapılacak, do-while dışına düşecek
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
                        ageAndZodiacSignDetection(input);
                        break;
                    case 2:
                        System.out.println("\n Running Task A2...");
                        reverseTheWords(

                        );
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
static char[][] C4_BOARD;
    static int C4_ROWS, C4_COLS;
    static char C4_CURRENT;
    static boolean C4_VS_AI = false;

    /** Player names */
    static String C4_P1_NAME, C4_P2_NAME;

    /** ANSI colors */
    static final String C4_RESET = "\u001B[0m";
    static final String C4_RED   = "\u001B[31m";
    static final String C4_BLUE  = "\u001B[34m";

    /** Rendering config */
    static final int    C4_CELL_W = 3;
    static final String C4_H = "━";
    static final String C4_V = "┃";
    static final String C4_EMPTY = " ";
    static final String C4_DISK  = "●";

    /** Visual color mapping */
    static String C4_COLOR_X = C4_RED;
    static String C4_COLOR_O = C4_BLUE;

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

    private static boolean c4SetupPVP() {
        System.out.print("Enter name for Player 1 (X, red ●): ");
        C4_P1_NAME = scanner.nextLine().trim();
        if (C4_P1_NAME.isEmpty()) C4_P1_NAME = "Player 1";

        System.out.print("Enter name for Player 2 (O, blue ●): ");
        C4_P2_NAME = scanner.nextLine().trim();
        if (C4_P2_NAME.isEmpty()) C4_P2_NAME = "Player 2";

        C4_COLOR_X = C4_RED;
        C4_COLOR_O = C4_BLUE;

        if (!c4ChooseSizeAndInit()) return false;
        C4_CURRENT = 'X';
        System.out.println("\nGame setup complete. " + C4_P1_NAME + " starts!");
        return true;
    }

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

    private static char c4AskColorAndAssign() {
        while (true) {
            System.out.print("Choose your color [kirmizi/mavi]: ");
            String s = scanner.nextLine().trim().toLowerCase();

            if (s.equals("kirmizi") || s.equals("kırmızı") || s.equals("k")) {
                C4_COLOR_X = C4_RED;
                C4_COLOR_O = C4_BLUE;
                System.out.println("You are " + C4_RED + "RED ●" + C4_RESET + " (X). Computer is " + C4_BLUE + "BLUE ●" + C4_RESET + " (O).");
                return 'X';
            } else if (s.equals("mavi") || s.equals("m")) {
                C4_COLOR_X = C4_RED;
                C4_COLOR_O = C4_BLUE;
                System.out.println("You are " + C4_BLUE + "BLUE ●" + C4_RESET + " (O). Computer is " + C4_RED + "RED ●" + C4_RESET + " (X).");
                return 'O';
            } else {
                System.out.println("❌ Geçersiz giriş. Lütfen 'kirmizi' veya 'mavi' yaz.");
            }
        }
    }

    private static boolean c4ChooseSizeAndInit() {
        while (true) {
            System.out.println("\nSelect Board Size:");
            System.out.println("1-) 5x4");
            System.out.println("2-) 6x5");
            System.out.println("3-) 7x6");
            System.out.println("4-) Back");
            System.out.print("Choose: ");
            String ch = scanner.nextLine().trim();

            if (ch.equals("1")) { C4_ROWS = 5; C4_COLS = 4; break; }
            else if (ch.equals("2")) { C4_ROWS = 6; C4_COLS = 5; break; }
            else if (ch.equals("3")) { C4_ROWS = 7; C4_COLS = 6; break; }
            else if (ch.equals("4")) { return false; }
            else { System.out.println("❌ Invalid input."); }
        }

        C4_BOARD = new char[C4_ROWS][C4_COLS];
        for (int r = 0; r < C4_ROWS; r++) Arrays.fill(C4_BOARD[r], ' ');
        return true;
    }

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

    private static void c4Drop(int col) {
        for (int r = C4_ROWS - 1; r >= 0; r--) {
            if (C4_BOARD[r][col] == ' ') {
                C4_BOARD[r][col] = C4_CURRENT;
                return;
            }
        }
    }

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

    private static boolean c4CheckWin(char p) {
        // Horizontal
        for (int r = 0; r < C4_ROWS; r++)
            for (int c = 0; c <= C4_COLS - 4; c++)
                if (C4_BOARD[r][c]==p && C4_BOARD[r][c+1]==p && C4_BOARD[r][c+2]==p && C4_BOARD[r][c+3]==p)
                    return true;
        // Vertical
        for (int r = 0; r <= C4_ROWS - 4; r++)
            for (int c = 0; c < C4_COLS; c++)
                if (C4_BOARD[r][c]==p && C4_BOARD[r+1][c]==p && C4_BOARD[r+2][c]==p && C4_BOARD[r+3][c]==p)
                    return true;
        // Diagonal ↗
        for (int r = 3; r < C4_ROWS; r++)
            for (int c = 0; c <= C4_COLS - 4; c++)
                if (C4_BOARD[r][c]==p && C4_BOARD[r-1][c+1]==p && C4_BOARD[r-2][c+2]==p && C4_BOARD[r-3][c+3]==p)
                    return true;
        // Diagonal ↘
        for (int r = 0; r <= C4_ROWS - 4; r++)
            for (int c = 0; c <= C4_COLS - 4; c++)
                if (C4_BOARD[r][c]==p && C4_BOARD[r+1][c+1]==p && C4_BOARD[r+2][c+2]==p && C4_BOARD[r+3][c+3]==p)
                    return true;
        return false;
    }

    /** AI side holder */
    static char C4_AI_SIDE = 'O';

    private static void c4AI_setSide(char side) { C4_AI_SIDE = side; }
    private static char c4AI_getSide() { return C4_AI_SIDE; }

    private static int c4AI_bestMove(char[][] b, int rows, int cols, int depth) {
        java.util.List<Integer> valid = c4AI_validCols(b, cols);
        if (valid.isEmpty()) return -1;

        // 1) Immediate win
        for (int c : valid) if (c4AI_makes4(b, rows, c, C4_AI_SIDE)) return c;

        // 2) Immediate block
        char opp = (C4_AI_SIDE == 'X') ? 'O' : 'X';
        for (int c : valid) if (c4AI_makes4(b, rows, c, opp)) return c;

        // 3) Minimax light
        int bestCol = valid.get(new java.util.Random().nextInt(valid.size()));
        int bestScore = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE, beta = Integer.MAX_VALUE;

        for (int c : valid) {
            int r = c4AI_nextRow(b, rows, c);
            if (r == -1) continue; // FIX: Skip if column is full

            b[r][c] = C4_AI_SIDE;
            int score = c4AI_minimax(b, rows, cols, depth - 1, alpha, beta, false, opp);
            b[r][c] = ' ';

            if (score > bestScore) {
                bestScore = score;
                bestCol = c;
            }
            alpha = Math.max(alpha, bestScore);
            if (alpha >= beta) break;
        }
        return bestCol;
    }

    private static int c4AI_minimax(char[][] b, int rows, int cols, int depth, int alpha, int beta, boolean max, char opp) {
        if (depth == 0 || c4AI_terminal(b, rows, cols)) {
            return c4AI_eval(b, rows, cols, C4_AI_SIDE);
        }

        java.util.List<Integer> valid = c4AI_validCols(b, cols);
        if (valid.isEmpty()) return c4AI_eval(b, rows, cols, C4_AI_SIDE);

        if (max) {
            int val = Integer.MIN_VALUE;
            for (int c : valid) {
                int r = c4AI_nextRow(b, rows, c);
                if (r == -1) continue; // FIX: Skip if column is full

                b[r][c] = C4_AI_SIDE;
                val = Math.max(val, c4AI_minimax(b, rows, cols, depth - 1, alpha, beta, false, opp));
                b[r][c] = ' ';
                alpha = Math.max(alpha, val);
                if (alpha >= beta) break;
            }
            return val;
        } else {
            int val = Integer.MAX_VALUE;
            for (int c : valid) {
                int r = c4AI_nextRow(b, rows, c);
                if (r == -1) continue; // FIX: Skip if column is full

                b[r][c] = opp;
                val = Math.min(val, c4AI_minimax(b, rows, cols, depth - 1, alpha, beta, true, opp));
                b[r][c] = ' ';
                beta = Math.min(beta, val);
                if (alpha >= beta) break;
            }
            return val;
        }
    }

    private static int c4AI_eval(char[][] b, int rows, int cols, char me) {
        char you = (me == 'X') ? 'O' : 'X';
        if (c4AI_four(b, rows, cols, me))  return 100000;
        if (c4AI_four(b, rows, cols, you)) return -100000;

        int score = 0;
        int center = cols / 2;
        for (int r = 0; r < rows; r++) {
            if (b[r][center] == me) score += 5;
        }

        score += c4AI_windowSum(b, rows, cols, me);
        score -= c4AI_windowSum(b, rows, cols, you) / 2;
        return score;
    }

    private static int c4AI_windowSum(char[][] b, int rows, int cols, char p) {
        int s = 0;
        // Horizontal
        for (int r = 0; r < rows; r++)
            for (int c = 0; c <= cols - 4; c++)
                s += c4AI_scoreWindow(new char[]{b[r][c], b[r][c+1], b[r][c+2], b[r][c+3]}, p);
        // Vertical
        for (int r = 0; r <= rows - 4; r++)
            for (int c = 0; c < cols; c++)
                s += c4AI_scoreWindow(new char[]{b[r][c], b[r+1][c], b[r+2][c], b[r+3][c]}, p);
        // Diagonal ↗
        for (int r = 3; r < rows; r++)
            for (int c = 0; c <= cols - 4; c++)
                s += c4AI_scoreWindow(new char[]{b[r][c], b[r-1][c+1], b[r-2][c+2], b[r-3][c+3]}, p);
        // Diagonal ↘
        for (int r = 0; r <= rows - 4; r++)
            for (int c = 0; c <= cols - 4; c++)
                s += c4AI_scoreWindow(new char[]{b[r][c], b[r+1][c+1], b[r+2][c+2], b[r+3][c+3]}, p);
        return s;
    }

    private static int c4AI_scoreWindow(char[] w, char p) {
        char q = (p == 'X') ? 'O' : 'X';
        int pc=0, qc=0, emp=0;
        for (char ch : w) {
            if (ch == p) pc++;
            else if (ch == q) qc++;
            else if (ch == ' ') emp++;
        }
        if (pc == 4) return 10000;
        if (pc == 3 && emp == 1) return 50;
        if (pc == 2 && emp == 2) return 10;
        if (qc == 3 && emp == 1) return -40;
        return 0;
    }

    private static boolean c4AI_terminal(char[][] b, int rows, int cols) {
        return c4AI_four(b, rows, cols, 'X') ||
                c4AI_four(b, rows, cols, 'O') ||
                c4AI_validCols(b, cols).isEmpty();
    }

    private static java.util.List<Integer> c4AI_validCols(char[][] b, int cols) {
        java.util.List<Integer> res = new java.util.ArrayList<>();
        for (int c = 0; c < cols; c++) {
            if (b[0][c] == ' ') res.add(c);
        }
        return res;
    }

    private static int c4AI_nextRow(char[][] b, int rows, int c) {
        for (int r = rows - 1; r >= 0; r--) {
            if (b[r][c] == ' ') return r;
        }
        return -1;
    }

    private static boolean c4AI_makes4(char[][] b, int rows, int c, char p) {
        int r = c4AI_nextRow(b, rows, c);
        if (r == -1) return false;

        b[r][c] = p;
        boolean win = c4AI_four(b, rows, C4_COLS, p);
        b[r][c] = ' ';
        return win;
    }

    private static boolean c4AI_four(char[][] b, int rows, int cols, char p) {
        // Horizontal
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
}