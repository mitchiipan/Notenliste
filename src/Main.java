import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.start();


//        Student student = new Student("Nico", 2012);
//        List<Subject> subjects = student.getSubjects();
////        subjects.add(new Subject("", ""));
//        student.addSubject(new Subject("", ""));
//        List<Subject> subjects1 = student.getSubjects();
//
//        System.out.println(subjects.size());
//        System.out.println(subjects1.size());
//
//        int testType;
//        Grade ersteNote;
//        int subjectSelection;
//        Subject subject;                                           // via Auswahlmenü Parameter reinhauen
//        int teacher;
//        Scanner eingabe = new Scanner(System.in);                   // eingabe der Noten, und des ControlTypes, das dann als Grade Objekt abspeichern
//        System.out.println("Bitte Note eingeben.");                 // Exceptions, dass nue 1-6 erlaubt sind, keine Kommazahlen, keine Sonderzeichen etc.
//
//        int grade1 = eingabe.nextInt();
//
//
//        if (grade1 < 1 || grade1 > 6) {
//            throw new IllegalArgumentException("Bitte eine Note von 1 bis 6 eingeben.");
//        }
//        System.out.println("Bitte Art der Leistungskontrolle eingeben.\nFür eine Testnote eine 1, für eine Klausurnote eine 2 eingeben.");
//        testType = eingabe.nextInt();
//        if (testType == 1) {                                                             // Exception, dass nur 1 und 2 erlaubt sind
//            ersteNote = new Grade(grade1, TestType.TEST);
//            System.out.println("Ihr Notenblatt wird bearbeitet.");
//        } else if (testType == 2) {
//            ersteNote = new Grade(grade1, TestType.EXAM);
//            System.out.println("Ihr Notenblatt wird bearbeitet.");                          // iwie funzt das nicht so wie ich will XD --> wenn ich ne 2 eingebe, kann ich nochmals etwas eingeben --> fixed
//        } else {
//            throw new IllegalArgumentException("Bitte eine 1 für Testnoten, oder eine 2 für Klausurnoten."); // theoretoisch sollte jetzt alles abgedeckt sein, was keine 1 oder 2 is
//        }
//        System.out.println("Zu welchem Fach gehört die Note?\nBitte wähle aus:\nLF1 --> 1\nLF2 --> 2\nLF3 --> 3\nLF4 --> 4\nLF5 --> 5\nSozialkunde --> 6\nDeutsch --> 7\nPhilosophie --> 8");
//        subjectSelection = eingabe.nextInt();
//        if (subjectSelection < 1 || subjectSelection > 8) {
//            throw new IllegalArgumentException("Bitte eine Zahl von 1 bis 8 eingeben, entsprechend dem Unterrichtsfach.");
//        }
//
//
//        Grade LF22 = new Grade(1, TestType.TEST);                   // hier Objekte erstellt der Klasse Grade
//        Grade LF21= new Grade(2, TestType.TEST);
//        Grade LF33 = new Grade(3, TestType.TEST);
//        Grade LF32 = new Grade(3, TestType.TEST);
//        Grade LF31= new Grade(4, TestType.TEST);
//
//
//        Subject lernfeld3 = new Subject("Lernfeld3", "Lewerenz");                                      // Objekt erstellt der Klasse subject --> damit ich auf die Methode in Subject zugreifen kann
//        Subject lernfeld2 = new Subject("Lernfeld2", "Lewerenz");                                      // Objekt erstellt der Klasse subject --> damit ich auf die Methode in Subject zugreifen kann
//        Subject lernfeld1 = new Subject("Lernfeld1", "Lewerenz");                                      // Objekt erstellt der Klasse subject --> damit ich auf die Methode in Subject zugreifen kann
//
//        lernfeld3.addGrade(LF31);
//        lernfeld3.addGrade(LF32);
//        lernfeld3.addGrade(LF33);
//
//        lernfeld2.addGrade(LF22);
//        lernfeld2.addGrade(LF21);
//
//        Student anna = new Student("Anna", 2);
//
//        anna.addSubject(lernfeld3);
//        anna.addSubject(lernfeld2);
//        anna.addSubject(lernfeld1);
//
//
//        DecimalFormat twoDigits = new DecimalFormat();                          //ImportKlasse mit Methoden die meinen average float auf 2 Nachkommazahlen reduziert
//        twoDigits.setMaximumFractionDigits(2);
//
//        Optional<Float> totalAverage = anna.getTotalAverage();
//        if(totalAverage.isPresent()) {
//            System.out.println("Lernfelder Durchschnitt " + twoDigits.format(totalAverage.get()));
//        }
    }

}