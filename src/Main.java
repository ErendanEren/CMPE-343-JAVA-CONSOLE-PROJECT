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