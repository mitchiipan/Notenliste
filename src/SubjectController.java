import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SubjectController extends Controller {
    public void openSubjectMenu(Student chosenStudent) {
        boolean hasAnotherRun = true;
        do {
            UtilsPrint.printMenu("Schulfächer " + chosenStudent.getName(),
                List.of("1. Fach auswählen", "2. Fach anlegen", "3. Fach löschen", "4. Zurück", "5. Beenden"));
            System.out.print("Auswahl: ");
            int userInput = this.input.nextInt();
            switch (userInput) {
            case 1://Fach auswählen
                choseSubject(chosenStudent);
                break;
            case 2://Fach anlegen
                createSubject(chosenStudent);
                break;
            case 3://Fach löschen
                deleteSubject(chosenStudent);
                break;
            case 4://zurück
                hasAnotherRun = false;
                break;
            case 5://beenden
                closeApp();
            default:

            }
        } while (hasAnotherRun);
    }


    private Subject openChoseSubjectMenu(
        Student chosenStudent) { // todo das ganze hier nochmals überprüfen, Endlosschleifen, sofern Facheingabe nicht übereinstimmt
        boolean hasAnotherRound = true;
        UtilsPrint.printSubjectsMenu(chosenStudent);//erstmal rausfinden, welche Fächer der Student hat
        System.out.print("Fach: ");
        do
        { // wenn Schüler noch gar keine Fächer hat, wird dies schon eine ebene früher abgefangen und direkt wieder zurück zur Auswahl geleitet
            String subjectNameComparison = input.nextLine();
            List<Subject> subjects = chosenStudent.getSubjects();
            for (Subject subject : subjects) {
                String subjectName = subject.getName();
                boolean isSubjectNameEqual = subjectName.equals(subjectNameComparison);
                if (isSubjectNameEqual) {
                    System.out.println("Fach gefunden.");
                    return subject;
                } else if (subjectName.contains("Zurück")) {
                    hasAnotherRound = false;
                }
            }
            //            System.out.println("Bitte Schreibweise überprüfen.");
        } while (hasAnotherRound);
        return null;
    }

    private void openChosenSubjectMenu(Student chosenStudent, Subject chosenSubject) {
        int userInput;
        boolean hasAnotherRound = true;
        do {
            UtilsPrint.printChosenSubjectMenu(chosenStudent, chosenSubject);
            System.out.print("Auswahl: ");
            userInput = input.nextInt();

            switch (userInput) {
            case 1:// Note hinzufügen
                createGrade(chosenSubject);
                break;
            case 2:// Noten anzeigen
                getAllGradesOfSubject(chosenSubject);
                break;
            case 3://Note löschen
                deleteGrade(chosenSubject);
                break;
            case 4://Durchschnitt der Noten dieses Faches
                Optional<Float> average = chosenSubject.getAverage();
                average.ifPresent(System.out::println); // todo iwie noch was abfangen, wenn optional leer ( nici said so) ?
                break;
            case 5://zurück zum openSubjectMenü
                hasAnotherRound = false;
                break;
            case 6:
                closeApp();
                break;
            }
        } while (hasAnotherRound);

    }

    private void choseSubject(Student chosenStudent) {
        if (chosenStudent.getSubjects().isEmpty()) {
            System.out.println("Noch keine Fächer vorhanden.");
            return;
        }
        Subject chosenSubject = openChoseSubjectMenu(chosenStudent);
        openChosenSubjectMenu(chosenStudent, chosenSubject);
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

    private void deleteSubject(Student student) {
        UtilsPrint.printSubjectsMenu(student);
        System.out.print("Welches Fach soll gelöscht werden? \nAuswahl: ");
        String toBeErasedSubject = input.nextLine();
        int amountSubjects = student.getSubjectNames().size();
        for (int i = 0; i < amountSubjects; i++) {
            String subject = student.getSubjectNames().get(i);
            if (toBeErasedSubject.equals(subject)) {
                System.out.println("Soll\n Fach: " + subject + "\nwirklich gelöscht werden? j/n » ");
                String confirmation = input.next();
                if (confirmation.equals("j")) {
                    Subject chosenSubject = student.getSubjects().get(i);
                    student.deleteSubject(chosenSubject);
                    System.out.println("Fach wurde gelöscht."); //und jetzt wieder zurück zum Hauptmenü
                }
            }
        }
    }

    public void getAllGradesOfSubject(Subject chosenSubject) {
        if (chosenSubject.getGradeList().isEmpty()) {
            System.out.println("Es sind noch keine Noten vorhanden.");
        } else {
            for (int i = 0; i < chosenSubject.getGradeList().size(); i++) {
                String testType;
                int index = i + 1;
                int grade = chosenSubject.getGradeList().get(i).getGrade();
                if (chosenSubject.getGradeList().get(i).getTestType().equals(TestType.EXAM)) {
                    testType = "Klausur";
                    System.out.println(index + ". " + grade + " (" + testType + ")");
                } else {
                    testType = "Test";
                    System.out.println(index + ". " + grade + " (" + testType + ")");
                }
            }
        }
    }

}
