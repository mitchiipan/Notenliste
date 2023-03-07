import java.util.*;
//import static PrintController.*;

public class Controller {
    // Daten merken
    public Scanner input = new Scanner(System.in);

    private List<Student> studentList = new ArrayList<>();
    SubjectController subjectController = new SubjectController();

    public static int count = 1000000;

    public static int[] array = new int[count];

    public static Random random = new Random();

    public void start() {

        // anlegen von Test Input

        Student student1 = new Student("Lisa", 2, 1111);
        //        Student student1;
        //        try {
        //            FileInputStream fout = new FileInputStream("f.txt");
        //            ObjectInputStream in = new ObjectInputStream(fout);
        //            student1 = (Student)in.readObject();
        //            in.close();
        //        } catch (Exception e) {
        //            throw new RuntimeException(e);
        //        }
        Student student2 = new Student("Daniel", 3, 1112);
        Student student3 = new Student("Amon", 1, 1113);
        Student student4 = new Student("Lilly", 2, 1114);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        Subject lf1 = new Subject("LF1", "Schlüter");
        Subject lf2 = new Subject("LF2", "Schmidt");
        Subject lf3 = new Subject("LF3", "Schmidt");
        Subject lf4 = new Subject("LF4", "Schlüter");
        Subject lf5 = new Subject("LF5", "Lewerenz");

        student1.addSubject(lf1);
        student1.addSubject(lf2);
        student1.addSubject(lf3);
        student1.addSubject(lf4);
        student1.addSubject(lf5);

//        for (int i = 1; i <= count; i++) {
//            array[i - 1] = i;
//        }


        // Test Input ende

        openMainMenu();
    }


    private void openMainMenu() {
        do {
            UtilsPrint.printMenu("Hauptmenü", List.of("1. Schüler auswählen", "2. Schüler anlegen", "3. Schüler löschen", "4. Beenden"));

            System.out.print("Auswahl: ");//Todo try catch input mismatch exception überall wie hier bei input
            int userInput;
            try {
                userInput = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Bitte nur Zahlen gemäß Auswahloptionen eingeben.");
                input.next();
                continue;
            }
            switch (userInput) {
            case 1:
                Student chosenStudent = openChoseStudentMenu();
                openChosenStudentMenu(chosenStudent);
                break;
            case 2:
                createStudent();
                break;
            case 3:
                deleteStudent();
                break;
            case 4:
                return;
            default:// im default: was passiert, wenn User Zahl außerhalb des Wertebereiches eingibt, aber halt ne Zahl
                System.out.println("Bitte Eingabe im Wertebereich.");
            }
        } while (true);
    }

    private Student openChoseStudentMenu() {
        UtilsPrint.printMenu("Schülerauswahl Menü", getStudentListNames());
        do {
            System.out.print("Auswahl: ");
            String studentName = input.next();
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

    private void openChosenStudentMenu(Student chosenStudent) {  // hab Schüler auswählen gewählt

        boolean hasAnotherRun = true;
        do {
            UtilsPrint.printStudentMenu(chosenStudent);
            System.out.print("Auswahl: ");
            int userInput;// TODO könnte ich es des nicht direkt im try initialisieren ?
            try {
                userInput = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Bitte nur Zahlen gemäß Auswahloptionen eingeben.");
                input.next();
                continue;
            }
            switch (userInput) {
            case 1:// Schüler bearbeiten
                openEditChosenStudentMenu(chosenStudent);
                break;
            case 2:// Schulfächer
                subjectController.openSubjectMenu(chosenStudent);
                break;
            case 3://Gesamtdurchschnitt Noten
                Optional<Float> totalAverage = chosenStudent.getTotalAverage();
                totalAverage.ifPresent(System.out::println); // todo iwie noch was abfangen, wenn optional leer ( nici said so) ?
                break;// durch das break direkt zum openStudentMenü zurück
            case 4://zurück
                hasAnotherRun = false;
                break;
            case 5:
                closeApp();
                return;
            default:
                System.out.println("Bitte nur Zahlen gemäß Auswahloptionen eingeben.");
            }
        } while (hasAnotherRun);
    }

    private void openEditChosenStudentMenu(Student chosenStudent) {
        int userInput = 0;
        UtilsPrint.printEditStudentMenu(chosenStudent);
        System.out.print("Auswahl: ");
        try {
            userInput = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Bitte nur Zahlen gemäß Auswahloptionen eingeben.");
            input.next();
        }
        switch (userInput) { // TODO in Methoden auslagern konsequent durchziehen (in eine Methode)
        case 1://Name ändern
            System.out.println("aktueller Name: " + chosenStudent.getName());
            System.out.print("neuer Name: ");
            String newStudentName = input.next();
            chosenStudent.setName(newStudentName);
            break;
        case 2:// Jahrgang ändern
            System.out.println("aktuelles Jahr: " + chosenStudent.getYear());
            System.out.print("neues Jahr: ");
            userInput = input.nextInt();
            chosenStudent.setYear(userInput);
            break;
        case 3:// ID ändern
            System.out.println("aktuelle ID: " + chosenStudent.getId());
            System.out.print("neue Schüler-ID: ");
            userInput = input.nextInt();
            chosenStudent.setId(userInput);
            break;
        case 4://zurück
            break;
        case 5://beenden
            closeApp();
            break;
        default:
            System.out.println("Bitte nur Zahlen gemäß Auswahloptionen eingeben.");
        }
    }

    private List<String> getStudentListNames() {
        String studentName;
        List<String> studentNamesList =
            new ArrayList<>(studentList.size()); //parameter capacity Länge der Student List (Performanceoptimierung)

        for (Student student : studentList) {   // die Schüler Liste ist voll mit Objekten(Students) von denen nur die Namen nötig zum Abgleich
            studentName = student.getName();
            studentNamesList.add(studentName);  //rein inne neue Liste nur mit Namen
        }
        return studentNamesList;
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

    public void closeApp() { //TODO follow-up an Datenbank und co. auch hier einbinden zum schließen derer
        input.close();
        System.exit(0);
    }

}
