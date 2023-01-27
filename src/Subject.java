import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Subject {

    private String name;
    private String teacher;
    private List<Grade> gradeList = new ArrayList<>();          // Listen sollte man nicht null lassen, weil bei Aufruf mancher Methoden zur Liste gäbe es ungewünsche exceptions (nullpointer)
    public Subject(String name, String teacher) {                               // will ich noch iwelche Parameter übergebeben???
        this.name = name;
        this.teacher = teacher;
    }
    public Optional<Float> getAverage() {
        /*
        Durchschnitt der vorhandenen Noten für das jeweilige Fach Noten beschaffen --> aus der Liste holen also
        alle Noten des Typs EXAM wreden doppelt gewertet
        */
        int amount = 0;
        int sumAll = 0;
        int sum;
        int sumAmount;
        for (Grade grade : gradeList) {
            if (grade.getTestType().equals(TestType.EXAM)) {
                sum = grade.getGrade() * 2;
                sumAmount = 2;
            } else {
                sum= grade.getGrade();
                sumAmount = 1;
            }
            sumAll = sumAll + sum;
            amount = amount + sumAmount;
        }
        if (amount > 0) {
            return Optional.of( (float)sumAll /  (float)amount);
        }
        return Optional.empty() ;
    }
        public void addGrade (Grade grade){
            gradeList.add(grade);
        }
        public List<Grade> getGradeList ()
        {
            return new ArrayList<>(this.gradeList);
        }
        public void deleteGrade (Grade grade){
            gradeList.remove(grade);
        }
    }


