import java.util.InputMismatchException;

public class GradeController extends Controller{
    SubjectController subjectController = new SubjectController();

    private void createGrade(Subject subject) {
        // TODO wenn Exception, printed es das Menü noc einmal mit "Auswahl:" der Noten , dann stürzt es ab
        int userInput;
        int testTypeInput;
        TestType test;
        try {
            System.out.print("Note: ");
            userInput = input.nextInt();
            System.out.print("Art der Note ( 1 > Klausur; 2 > Test ): ");
            testTypeInput = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Bitte nur Zahlen gemäß Auswahloptionen eingeben.");
            return;
        }
        if (testTypeInput == 1) {
            test = TestType.TEST;
        } else if (testTypeInput == 2) {
            test = TestType.EXAM;
        } else {
            System.out.println("Bitte nur Zahlen gemäß Auswahloptionen eingeben2.");
            return;
        }
        Grade newGrade = new Grade(userInput, test);
        subject.addGrade(newGrade);
        System.out.println("Note wurde hinzugefügt.");
    }

    private void deleteGrade(Subject chosenSubject) {
        subjectController.getAllGradesOfSubject(chosenSubject);
        System.out.print("Welche Note soll gelöscht werden? \nAuswahl: ");
        int indexOfToBeErasedGrade = input.nextInt() - 1;
        int amountGrades = chosenSubject.getGradeList().size();
        for (int i = 0; i < amountGrades; i++) {
            if (indexOfToBeErasedGrade == i) {
                System.out.println(
                    "Soll\n Note: " + chosenSubject.getGradeList().get(i).getGrade() + " (" + chosenSubject.getGradeList().get(i)
                        .getTestType() + ")" + "\nwirklich gelöscht werden? j/n » ");
                String confirmation = input.next();
                if (confirmation.equals("j")) {
                    Grade grade = chosenSubject.getGradeList().get(i);
                    chosenSubject.deleteGrade(grade);
                    System.out.println("Note wurde gelöscht."); //und jetzt wieder zurück zum Hauptmenü
                }
            }
        }
    }



}
