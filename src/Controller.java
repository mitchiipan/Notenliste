import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    // Daten merken

    private List<Student> studentList = new ArrayList<>();

    public void start() {
        {
            List<String> menuList = new ArrayList<>();
            menuList.add("1. Schüler auswählen");
            menuList.add("2. Schüler anlegen");
            menuList.add("3. Schüler löschen");

            printMenu("Hauptmenü", menuList);

            Scanner eingabe = new Scanner(System.in);
            System.out.print("Ihre Eingabe: ");
            int userEingabe = eingabe.nextInt();

            if (userEingabe == 2) {
                createStudent(eingabe);             // Anlegen eines Studenten in Methode (s. unten) ausgelagert
            } else if (userEingabe == 1) {              // todo in methode chooseStudent auslagern
                chooseStudent(eingabe);
            }

        }

        {
            List<String> menuList = new ArrayList<>();  // todo hier hätte ich gerne den Namen des ausgewählten Schülers stehen
            menuList.add("1. Schulfächer");
            menuList.add("2. Gesamtdurchschnitt Note");
            menuList.add("3. -Stuff der mir noch einfällt-");
            menuList.add("Zurück");
            printMenu("Menü Schüler", menuList);
        }
    }



    private void printMenu(String headline, List<String> lines) {
        // todo exception, wenn headline-länge > maxspace
        int maxSpace = 66;

        printHeadline(headline, maxSpace);

        final int linesLeftSpace = 12;
        final int linesRightSpaceSubstring = 1;
        printLine("Optionen", 2, maxSpace - 10);

        for (int i = 0; i < lines.size(); i++) {
            String content = lines.get(i);

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
        //        printLine("Zurück", linesLeftSpace, maxSpace - "Zurück".length() - linesLeftSpace);
        //        printLine("Beenden", linesLeftSpace, maxSpace - "Beenden".length() - linesLeftSpace);

        System.out.println("╚" + ("═".repeat(maxSpace)) + "╝");
    }

    private static void printHeadline(String headline, int maxSpace) {

        int lineLength = headline.length();
        int leftSpace = (maxSpace - lineLength) / 2;
        int rightSpace = maxSpace - leftSpace - lineLength;

        System.out.println("╔" + ("═".repeat(maxSpace)) + "╗");
        printLine(headline, leftSpace, rightSpace);
        System.out.println("╠" + ("═".repeat(maxSpace)) + "╣");

    }

    private static void printLine(String text, int leftSpace, int rightSpace) {
        System.out.print("║");
        System.out.print(" ".repeat(leftSpace) + text + " ".repeat(rightSpace));
        System.out.println("║");
    }

    private void createStudent(Scanner eingabe) {
        int studentYear;
        String studentName;
        int studentId;
        System.out.print("Bitte den Namen eingeben: ");
        studentName = eingabe.next();
        System.out.print("Bitte die Schüler-ID eingeben: ");
        studentId = eingabe.nextInt();
        System.out.print("Welches Schuljahr? ");
        studentYear = eingabe.nextInt();
        System.out.print("Sind die Angaben \n Name: " + studentName + ",\n Schuljahr: " + studentYear + " \n korrekt? j/n » ");
        String confirmation = eingabe.next();
        if (confirmation.equals("j")) {
            Student newStudent = new Student(studentName, studentYear, studentId); // das zeug jetzt, weil confirmed, in ein Objekt schmeißen und inne Liste der Schüler speichern
            studentList.add(newStudent);

            System.out.println("Der neue Schüler wurde angelegt."); //und jetzt wieder zurück zum Hauptmenü -->  1 2 oder 3
            start();
        } else {
            start();          //komme jetzt wieder in die Anfangsmaske, so decke ich vertippen beim namen, klasse und eventuell falsche Auswahl direkt zu Beginn ab
        }


    }
    private void chooseStudent(Scanner eingabe) {
        int studentId;
        System.out.print("Bitte geben sie die Schüler-ID an: ");
        studentId = eingabe.nextInt();
        for (int i = 0; i < studentList.size(); i++) {

            int studentIdComparison =  studentList.get(i).getId();

            if (!(studentId == studentIdComparison)) {
                System.out.println(
                    "Der Schüler konnte nicht gefunden werden, \nbitte überprüfen Sie die Schreibweise und wiederholen sie die Eingabe.");
                    chooseStudent(eingabe);
                    //todo könnte ins undendliche gehen , abbruch einbauen --> ist es möglich, dass man das so einbaut, dass es 3 mal erscheint und dann auf start zurück?

            }
        }
    }
}
