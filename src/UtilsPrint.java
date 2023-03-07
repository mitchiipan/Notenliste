import java.util.List;

public final class UtilsPrint {

    private UtilsPrint (){
        // Soll nicht Instanziiert werden (hide Utility Konstruktor)
    }

    public static void printMenu(String headline, List<String> lines) {
        // todo exception, wenn headline-länge > maxspace
        int maxSpace = 88;

        printHeadline(headline, maxSpace);

        final int linesLeftSpace = 12;
        final int linesRightSpaceSubstring = 1;
        printLine("Optionen", 2, maxSpace - 10);

        for (String content : lines) {
            int linesLeftSpaceSubstring = linesLeftSpace;
            while (content.length() > 0) {
                String subContent =
                    content.substring(0, Math.min(content.length(), maxSpace - linesLeftSpaceSubstring - linesRightSpaceSubstring));
                printLine(subContent, linesLeftSpaceSubstring, maxSpace - subContent.length() - linesLeftSpaceSubstring);
                content = content.substring(subContent.length());
                linesLeftSpaceSubstring = 15;
            }
        }
        printLine("", maxSpace, 0);
        System.out.println("╚" + ("═".repeat(maxSpace)) + "╝");
    }

    public static void printHeadline(String headline, int maxSpace) {

        int lineLength = headline.length();
        int leftSpace = (maxSpace - lineLength) / 2;
        int rightSpace = maxSpace - leftSpace - lineLength;

        System.out.println("╔" + ("═".repeat(maxSpace)) + "╗");
        printLine(headline, leftSpace, rightSpace);
        System.out.println("╠" + ("═".repeat(maxSpace)) + "╣");
    }

    public static void printLine(String text, int leftSpace, int rightSpace) {
        System.out.print("║");
        System.out.print(" ".repeat(leftSpace) + text + " ".repeat(rightSpace));
        System.out.println("║");
    }

    public static void printStudentMenu(Student student) {
        String headline = student.getName();
        printMenu(headline, List.of("1. Schüler bearbeiten", "2. Schulfächer", "3. Gesamtdurchschnitt Noten", "4. Zurück", "5. Beenden"));
    }

    public static void printEditStudentMenu(Student student) {
        String headline = student.getName();
        printMenu(headline, List.of("1. Namen ändern", "2. Jahr ändern", "3. ID ändern", "4. Zurück", "5. Beenden"));
    }

    public static void printSubjectsMenu(Student student) {
        String headline = "Schulfächer " + student.getName();
        printMenu(headline, student.getSubjectNames());
    }

    public static void printChosenSubjectMenu(Student student, Subject subject) {
        String headline = subject.getName() + "  " + student.getName();
        printMenu(headline,
            List.of("1. Note hinzufügen", "2. Noten anzeigen", "3. Note löschen", "4. Durchschnitt", "5. Zurück", "6. Beenden"));
    }



}
