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
    public class ReverseWords {
        public static void main(String[] args) {
            // Proje dosyasında verilen örnek metin
            String originalText = "Çılgın köylü, 3. günün öğlesinde \"İğde ağacının gölgesine oturalım mı?\" diye sordu. " +
                    "Şaşkın Üzeyir, 7 tane üzüm yedi, sonra gülümseyip \"Şimdi dönüyoruz!\" dedi. " +
                    "Öykü ise, 12:45'teki trene yetişmek için hızlıca yürümeye başladı!";

            System.out.println("--- Orijinal Metin ---");
            System.out.println(originalText);

            // Özyinelemeli metodu çağırarak metni ters çeviriyoruz.
            String reversedText = reverseSentenceRecursively(originalText.trim());

            System.out.println("\n--- Ters Çevrilmiş Metin ---");
            System.out.println(reversedText);
        }

        /**
         * Cümleyi kelimelere ayırarak özyinelemeli bir şekilde işler.
         * Cümlenin ilk kelimesini alır, onu işler ve geri kalanı için kendini tekrar çağırır.
         */
        public static String reverseSentenceRecursively(String sentence) {
            // Temel Durum (Base Case): Cümle boşaldığında recursion durur.
            if (sentence.isEmpty()) {
                return "";
            }

            String firstWord;
            String restOfSentence;

            // Cümleyi ilk kelime ve cümlenin geri kalanı olarak ikiye ayırır.
            int firstSpaceIndex = sentence.indexOf(' ');
            if (firstSpaceIndex == -1) { // Cümlede başka kelime kalmamışsa
                firstWord = sentence;
                restOfSentence = "";
            } else {
                firstWord = sentence.substring(0, firstSpaceIndex);
                restOfSentence = sentence.substring(firstSpaceIndex + 1);
            }

            // İlk kelimeyi özel kurallara göre ters çevirir[cite: 33].
            String reversedWord = reverseSingleWord(firstWord);

            // Cümlenin geri kalanını işlemek için metodu tekrar çağırır[cite: 34].
            String processedRest = reverseSentenceRecursively(restOfSentence);

            // Sonuçları birleştirir. Eğer cümlenin sonuysa araya boşluk koymaz.
            if (processedRest.isEmpty()) {
                return reversedWord;
            } else {
                return reversedWord + " " + processedRest;
            }
        }

        /**
         * Tek bir kelimeyi proje kurallarına göre ters çevirir.
         * Kural: Sadece 2 veya daha fazla harf içeren kelimeler ters çevrilir[cite: 33].
         * Kural: Noktalama işaretleri ve rakamlar yerlerinde kalır[cite: 33].
         */
        public static String reverseSingleWord(String word) {
            StringBuilder letters = new StringBuilder();
            // Kelimenin içindeki sadece harfleri (Türkçe karakterler dahil) ayıkla.
            for (char c : word.toCharArray()) {
                if (Character.isLetter(c)) {
                    letters.append(c);
                }
            }

            // Eğer kelimede 2'den az harf varsa, hiçbir şey yapmadan kelimeyi geri döndür[cite: 33].
            if (letters.length() < 2) {
                return word;
            }

            // Harfleri ters çevir.
            letters.reverse();

            // Kelimeyi yeniden oluştururken harf olmayan karakterleri orijinal yerlerine koy.
            StringBuilder result = new StringBuilder();
            int letterIndex = 0;
            for (char c : word.toCharArray()) {
                if (Character.isLetter(c)) {
                    // Bu pozisyona ters çevrilmiş harf listesinden sıradaki harfi ekle.
                    result.append(letters.charAt(letterIndex));
                    letterIndex++;
                } else {
                    // Eğer karakter harf değilse (rakam, noktalama vb.), olduğu gibi ekle[cite: 33].
                    result.append(c);
                }
            }
            return result.toString();
        }
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
}