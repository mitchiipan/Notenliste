import java.util.ArrayList;
import java.util.List;

public class Subject {

    private String name;
    private String teacher;
    private List<Grade> gradeList = new ArrayList<>();          // Listen sollte man nicht null lassen, weil bei Aufruf mancher Methoden zur Liste gäbe es ungewünsche exceptions (nullpointer)



    public float getAverage() {                                 //Durchschnitt der vorhandenen Noten für das jeweilige Fach
        int amount=0;                                           // Noten beschaffen --> aus der Liste holen also
        int sumAll=0;
        int sumExam = 0;
        int sumTest = 0;

        for (Grade grade : gradeList) {

            if (grade.controlType.equals(ControlType.EXAM)) {
                sumExam = grade.getGrade() * 2;
            } else {
                sumTest = grade.getGrade();
            }
            sumAll =sumAll + sumExam + sumTest;

        }
        for (Grade grade : gradeList) {                         //brauche die Anzahl aller Noten
            int sumAmount =0;
            if (grade.controlType.equals(ControlType.EXAM)) {   //wieder durch die Liste gehen jedes Teil prüfen ob EXAM is,
                sumAmount= 2;                                   // wenn es EXAM is, wirds mit doppelten faktor gewertet
            } else {
                sumAmount = 1;                                  // ansonsten normal einfach
            }
            amount = amount + sumAmount;                        // alles rin inne variable die dann die gesamte Anzahl inklusive der Faktorisierten hat
        }
        float average = (float)sumAll / (float)amount;                        // uuuund, den Durchschnitt ausrechenen, als float, weil Kommazahlen

        return average;     /** WIEVIELE NACHKOMMASTELLEN? --> 2 */
    }

    public void addGrade(Grade grade) {                         /** hier sollen neue Noten hinzugefügt werden zu einem jeweiligen Fach
                                                                Noten sind Objekte der Klasse Grade*/
    }

    public void deleteGrade(Grade grade) {                      /** Falsche Noten etc. wieder entfernen*/

    }
    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

}