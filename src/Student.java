import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Student {

    private String name;
    private int year;
    int id;
    private List<Subject> subjects = new ArrayList<>();

    public Student(String name, int year, int id) {
        this.name = name;
        this.year = year;
        this.id = id;
    }


    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public List<Subject> getSubjects() {
        return new ArrayList<>(this.subjects);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public Optional<Float> getTotalAverage() {
        /*
        ich nehme meine Liste mit allen subjects, hol da meine averages der jeweiligen Fächer und schmeiß die in sum
        nebenbei wird auch gleich mitgezählt wieviele Fächer es sind (die auch befüllt sind), sodass etwaige Fehler
        hinsichtlich leerer subjects durch die geteilt wird obsolet werden.
        */

        float sum = 0;
        int count = 0;

        for (Subject subject : subjects) {

            Optional<Float> average = subject.getAverage();
            if (average.isPresent()) {
                sum = sum + average.get();
                count = count + 1;
            }
        }
        if (count > 0) {
            return Optional.of(sum / count);
        }
        return Optional.empty();
    }
}
