

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {


        Grade ersteLF4 = new Grade(2,ControlType.TEST);              // hier Objekte erstellt der Klasse Grade
        Grade zweiteLF4 = new Grade(3,ControlType.TEST);
        Grade dritteLF4 = new Grade(2,ControlType.TEST);

        Subject testSubject = new Subject();        // Objekt erstellt der Klasse subject --> damit ich auf die Methode in Subject zugreifen kann

        List<Grade> gradeList = new ArrayList<>();

        gradeList.add(ersteLF4);
        gradeList.add(zweiteLF4);
        gradeList.add(dritteLF4);

        testSubject.setGradeList(gradeList);

        System.out.println(testSubject.getAverage());

    }
}