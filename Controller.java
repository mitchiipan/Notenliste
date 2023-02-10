import java.util.*;

public class Controller {
    // Daten merken
    private Scanner input = new Scanner(System.in);

    private List<Student> studentList = new ArrayList<>();

    public void start() {

        // anlegen von Test Input

        Student student1 = new Student("Lisa", 2, 1111);
        Student student2 = new Student("Daniel", 3, 1112);
        Student student3 = new Student("Amon", 1, 1113);
        Student student4 = new Student("Lilly", 2, 1114);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        Subject lf1 = new Subject("LF 1", "Schlüter");
        Subject lf2 = new Subject("LF 2", "Schmidt");
        Subject lf3 = new Subject("LF 3", "Schmidt");
        Subject lf4 = new Subject("LF 4", "Schlüter");
        Subject lf5 = new Subject("LF 5", "Lewerenz");

        student1.addSubject(lf1);
        student1.addSubject(lf2);
        student1.addSubject(lf3);
        student1.addSubject(lf4);
        student1.addSubject(lf5);

        // Test Input ende

        openMainMenu();
    }

    private void openMainMenu() {
        do {
            printMenu("Hauptmenü", List.of("1. Schüler auswählen", "2. Schüler anlegen", "3. Schüler löschen", "4. Beenden"));

            System.out.print("Auswahl: ");
            //Todo try catch input mismatch exception überall wie hier bei input
            int userInput;
            try {
                userInput = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Bitte nur Zahlen gemäß Auswahloptionen eingeben.");
                continue;
            }
            switch (userInput) {
            case 1:
                Student chosenStudent = openChoseStudentMenu();
                openStudentMenu(chosenStudent);
                break;
            case 2:
                createStudent();
                break;
            case 3:
                deleteStudent();
                break;
            case 4:
                break;
            // wenn ich jetzt hiernach noch was hinschreiben würde, nicht nur ne Prüfung, sondern richtiger Code, dann müsste genau hier noch ein default hin
            // im default wäre denn, was passiert, wenn User Zahl außerhalb des Wertebereiches eingibt
            }
            //TODO Prüfen auf Programm beenden ( boolean?)
        } while (true);
    }

    private Student openChoseStudentMenu() {   //todo kann ich bestimmt schlanker machen --> nochmals in Methoden aufsplitten
        String studentName;                           //TODO ein zurück oder abbrechen wäre nicht schlecht XD
        List<String> studentNamesList =
            new ArrayList<>(studentList.size()); //parameter capacity Länge der Student List (Performanceoptimierung)

        for (Student student : studentList) {   // die Schüler Liste ist voll mit Objekten(Students) von denen nur die Namen nötig zum Abgleich
            studentName = student.getName();
            studentNamesList.add(studentName);  //rein inne neue Liste nur mit Namen
        }
        printMenu("Schülerauswahl Menü", studentNamesList);
        do {
            System.out.print("Name: ");
            studentName = input.next();
            for (Student student : studentList) {
                String studentNameComparison = student.getName();
                boolean isStudentNameEqual = studentName.equals(studentNameComparison);
                if (isStudentNameEqual) {
                    System.out.println("Schüler gefunden.");
                    return student;
                }
            }
            System.out.println("Bitte Schreibweise überprüfen.");
        } while (true);
    }

    private void openStudentMenu(Student chosenStudent) {  // hab Schüler auswählen gewählt

        boolean hasAnotherRun = true;

        do {
            printStudentMenu(chosenStudent);
            System.out.print("Auswahl: ");
            int userInput = input.nextInt();
            switch (userInput) {
            case 1:// Schüler bearbeiten
                openEditStudentMenu(chosenStudent);
                break;
            case 2:// Schulfächer
                openSubjectMenu(chosenStudent);
                break;
            case 3://Gesamtdurchschnitt Noten
                Optional<Float> totalAverage = chosenStudent.getTotalAverage();
                System.out.println(totalAverage);
                break;// durch das break direkt zum openStudentMenü zurück
            case 4://zurück //TODO nur ein menü weiter zurück wird bestimmt nicht via MethodenAufruf gemacht des vorherigen Menüs?
                hasAnotherRun = false;
                break;
            case 5:// TODO Programm soll zur Gänze beendet werden
                break;
            }
        } while (hasAnotherRun);
    }

    private void openSubjectMenu(Student chosenStudent) {
        boolean hasAnotherRun = true;
        do {
            printMenu("Schulfächer " + chosenStudent.getName(),
                List.of("1. Fach auswählen", "2. Fach anlegen", "3. Fach löschen", "4. Zurück", "5. Beenden"));
            System.out.print("Auswahl: ");
            int userInput = input.nextInt();
            switch (userInput) {
            case 1://Fach auswählen
                Subject chosenSubject = openChoseSubjectMenu(chosenStudent);
                openChosenSubjectMenu(chosenSubject);
                break;
            case 2://Fach anlegen
                createSubject(chosenStudent);
                break;
            case 3://Fach löschen
                break;
            case 4://zurück
                hasAnotherRun = false;
                break;
            case 5://beenden
                break;
            default:

            }
        } while (hasAnotherRun);
    }

    private void createSubject(Student chosenStudent) {
        String subjectName;
        String subjectTeacher;
        System.out.print("Name des Faches: ");
        subjectName = input.next();
        System.out.print("Lehrer: ");
        subjectTeacher = input.next();
        System.out.print("Sind die Angaben \n Fach: " + subjectName + "\n Lehrer: " + subjectTeacher + " \n korrekt? j/n » ");
        String confirmation = input.next();
        if (confirmation.equals("j")) {
            Subject newSubject = new Subject(subjectName, subjectTeacher);
            // das zeug jetzt, weil confirmed, in ein Objekt schmeißen und inne Liste der Fächer speichern
            chosenStudent.addSubject(newSubject);
            System.out.println("Neues Fach wurde angelegt.");
        } // todo, was wenn nein oder x oder stuff --> do while maybe oder nur while will see
    }

    private Subject openChoseSubjectMenu(Student chosenStudent) { // todo, wenn der Student keine Fächer hat wirds ne Endlosschleife
        printSubjectsMenu(chosenStudent);//erstmal rausfinden, welche Fächer der Student hat
        while (true){
            System.out.print("Fach: ");
            String subjectNameComparison = input.nextLine();
            List<Subject> subjects = chosenStudent.getSubjects();
            for (Subject subject : subjects) {
                String subjectName = subject.getName();
                boolean isSubjectNameEqual = subjectName.equals(subjectNameComparison);
                if (isSubjectNameEqual) {
                    System.out.println("Fach gefunden.");
                    return subject;
                }
            }
            System.out.println("Bitte Schreibweise überprüfen.");
        }
    }

    private void openEditStudentMenu(Student chosenStudent) {
        int userInput;
        printEditStudentMenu(chosenStudent);
        System.out.print("Auswahl: ");
        userInput = input.nextInt();
        editChosenStudent(input, chosenStudent, userInput);
    }

    private void openChosenSubjectMenu(Subject subject) {

    }

    private void printMenu(String headline, List<String> lines) {
        // todo exception, wenn headline-länge > maxspace
        int maxSpace = 66;

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

    private void printStudentMenu(Student student) {
        String headline = student.getName();
        printMenu(headline, List.of("1. Schüler bearbeiten", "2. Schulfächer", "3. Gesamtdurchschnitt Noten", "4. Zurück", "5. Beenden"));
    }

    private void printEditStudentMenu(Student student) {
        String headline = student.getName();
        printMenu(headline, List.of("1. Namen ändern", "2. Jahr ändern", "3. ID ändern", "4. Zurück", "5. Beenden"));
    }

    private void printSubjectsMenu(Student student) {
        String headline = "Schulfächer " + student.getName();
        printMenu(headline, student.getSubjectNames());
    }

    private void createStudent() {
        int studentYear;
        String studentName;
        int studentId;
        System.out.print("Name: ");
        studentName = input.next();
        System.out.print("Schüler-ID: ");
        studentId = input.nextInt();
        System.out.print("Schuljahr: ");
        studentYear = input.nextInt();
        System.out.print(
            "Sind die Angaben \n Name: " + studentName + "\n Schüler-ID: " + studentId + "\n Schuljahr: " + studentYear + " \n korrekt? j/n » ");
        String confirmation = input.next();
        if (confirmation.equals("j")) {
            Student newStudent = new Student(studentName, studentYear,
                studentId); // das zeug jetzt, weil confirmed, in ein Objekt schmeißen und inne Liste der Schüler speichern
            studentList.add(newStudent);
            System.out.println("Neuer Schüler wurde angelegt."); //und jetzt wieder zurück zum Hauptmenü -->  1 2 oder 3
        } // todo, was wenn nein oder x oder stuff --> do while maybe oder nur while will see

    }

    //System.out.println("Schüler konnte nicht gefunden werden, \nbitte Schreibweise überprüfen und Eingabe wiederholen.");
    private void deleteStudent() {       //todo Rattenschwanz an Daten mit löschen
        System.out.print("Schüler-Id des zu löschenden Schülers: ");
        int toBeErasedStudentId = input.nextInt();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == toBeErasedStudentId) {
                System.out.println("Soll\n Schüler: " + studentList.get(i)
                    .getName() + "\n Schüler-ID: " + toBeErasedStudentId + "\nwirklich gelöscht werden? j/n » ");
                String confirmation = input.next();
                if (confirmation.equals("j")) {
                    studentList.remove(i);
                    System.out.println("Schüler wurde gelöscht."); //und jetzt wieder zurück zum Hauptmenü
                }
            }
        }
    }

    private void editChosenStudent(Scanner eingabe, Student chosenStudent, int userEingabe) {

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
        case 4:
            break;
        case 5:
            break;
        }

    }
}
