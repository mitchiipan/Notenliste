

public class Grade {


    private int grade;
    private TestType testType;


    public Grade(int grade, TestType testType) { //TODO int grade validieren lassen , dass da wirklich nur 1 bis 6 geht, oder n ENUM auch für die grades
        this.grade = grade;
        this.testType = testType;
    }

    public int getGrade() {
        return grade;
    }

    public TestType getTestType() {
        return testType;
    }




}
