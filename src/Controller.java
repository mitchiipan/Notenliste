import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    // Daten merken

    private List<Student> studentList = new ArrayList<>();

    public void start() { //todo, wenn usereingabe außerhalb der Möglichkeiten ist abdecken

        Student student1 = new Student("Lisa",2,1111);// anlegen von Test Students für die Liste
        Student student2 = new Student("Daniel",3,1112);
        Student student3 = new Student("Amon",1,1113);
        Student student4 = new Student("Lilly",2,1114);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);


        Scanner eingabe = new Scanner(System.in);
        Student chosenStudent;
        while (true) {

            printMainMenu();

            System.out.print("Auswahl: ");
            int userEingabe = eingabe.nextInt();
            if (userEingabe == 1) {                 // in methode chooseStudent ausgelagert // todo jetzt in diesem Zweig Schülermenü weiter machen
                chosenStudent = choseStudent(eingabe);
                printStudentMenu(chosenStudent);
                System.out.print("Auswahl: ");
                userEingabe = eingabe.nextInt();
                switch (userEingabe) {
                case 1:// Änderungen am bestehenden ausgewählten Schüler
                    printEditStudentMenu(chosenStudent);
                    System.out.print("Auswahl: ");
                    userEingabe = eingabe.nextInt();
                    changeChosenStudent(eingabe, chosenStudent, userEingabe);
                    break;
                case 2:// Schulfächer --> geht weiter in das menü der Schulfächer, erste Maske : was?

                case 3:
                case 4:
                case 5:

                }

                break;
            } else if (userEingabe == 2) {
                createStudent(eingabe);             // Anlegen eines Studenten in Methode (s. unten) ausgelagert
            } else if (userEingabe == 3) {
                deleteStudent(eingabe);
            } else if (userEingabe == 4) {          // break, wenn user  beenden gewählt hat
                break;
            }
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

    private void printMainMenu() {
        List<String> menuList = new ArrayList<>();
        menuList.add("1. Schüler auswählen");
        menuList.add("2. Schüler anlegen");
        menuList.add("3. Schüler löschen");
        menuList.add("4. Beenden");

        printMenu("Hauptmenü", menuList);
    }





    private void printStudentMenu(Student student) {
        String headline = student.getName();
        List<String> menuList = new ArrayList<>();
        menuList.add("1. Schüler bearbeiten");
        menuList.add("2. Schulfächer");
        menuList.add("3. Gesamtdurchschnitt Note");
        menuList.add("4. Zurück");
        menuList.add("5. Beenden");
        printMenu(headline, menuList);
    }

    private void printEditStudentMenu(Student student) {
        String headline = student.getName();
        List<String> menuList2 = new ArrayList<>();
        menuList2.add("1. Namen ändern");
        menuList2.add("2. Jahr ändern");
        menuList2.add("3. ID ändern");
        menuList2.add("4. Zurück");
        menuList2.add("5. Beenden");
        printMenu(headline, menuList2);






    }    private Student choseStudent(Scanner eingabe) {   //todo kann ich bestimmt schlanker machen --> nochmals in Methoden aufsplitten
        String studentName;                           //TODO ein zurück oder abbrechen wäre nicht schlecht XD
        int studentID = 0;
        int studentYear = 0;
        Student chosenStudent;
        List<String> studentNamesList = new ArrayList<>();

        for (int i = 0; i < studentList.size(); i++) {
            studentName = studentList.get(i).getName();
            studentID = studentList.get(i).getId();
            studentYear = studentList.get(i).getYear();
            studentNamesList.add(studentName);
        }
        printMenu("Schülerauswahl Menü", studentNamesList);
        System.out.print("Name: ");
        studentName = eingabe.next();
        for (int i = 0; i < studentList.size(); i++) {
            String studentNameComparison = studentList.get(i).getName();

            if ((studentName.equals(studentNameComparison))) {
                chosenStudent = new Student(studentName, studentYear, studentID);
                System.out.println("Schüler gefunden.");
                return chosenStudent;
            }
        }
        return null;


    }  private void createStudent(Scanner eingabe) {
        int studentYear;
        String studentName;
        int studentId;
        System.out.print("Name: ");
        studentName = eingabe.next();
        System.out.print("Schüler-ID: ");
        studentId = eingabe.nextInt();
        System.out.print("Schuljahr: ");
        studentYear = eingabe.nextInt();
        System.out.print(
            "Sind die Angaben \n Name: " + studentName + "\n Schüler-ID: " + studentId + "\n Schuljahr: " + studentYear + " \n korrekt? j/n » ");
        String confirmation = eingabe.next();
        if (confirmation.equals("j")) {
            Student newStudent = new Student(studentName, studentYear,
                studentId); // das zeug jetzt, weil confirmed, in ein Objekt schmeißen und inne Liste der Schüler speichern
            studentList.add(newStudent);
            System.out.println("Neuer Schüler wurde angelegt."); //und jetzt wieder zurück zum Hauptmenü -->  1 2 oder 3
        } // todo, was wenn nein oder x oder stuff --> do while maybe oder nur while will see

    }

    //System.out.println("Schüler konnte nicht gefunden werden, \nbitte Schreibweise überprüfen und Eingabe wiederholen.");
    private void deleteStudent(Scanner eingabe) {       //todo Rattenschwanz an Daten mit löschen
        System.out.print("Schüler-Id des zu löschenden Schülers: ");
        int toBeErasedStudentId = eingabe.nextInt();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == toBeErasedStudentId) {
                System.out.println("Soll\n Schüler: " + studentList.get(i)
                    .getName() + "\n Schüler-ID: " + toBeErasedStudentId + "\nwirklich gelöscht werden? j/n » ");
                String confirmation = eingabe.next();
                if (confirmation.equals("j")) {
                    studentList.remove(i);
                    System.out.println("Schüler wurde gelöscht."); //und jetzt wieder zurück zum Hauptmenü

                }
            }
        }
    }
    private void changeChosenStudent(Scanner eingabe, Student chosenStudent, int userEingabe) {
        switch (userEingabe) {
        case 1:
            System.out.print("neuer Name: ");
            String newStudentName = eingabe.next();
            chosenStudent.setName(newStudentName);
            break;
        case 2:
            System.out.print("neues Jahr: ");
            userEingabe = eingabe.nextInt();
            chosenStudent.setYear(userEingabe);
            break;
        case 3:
            System.out.print("neue Schüler-ID: ");
            userEingabe = eingabe.nextInt();
            chosenStudent.setId(userEingabe);
            break;
        case 5:
            return;
        case 6:
            break;
        }
    }
}
