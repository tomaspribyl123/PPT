package model;

import java.util.Map;

public class Grade {
    private int studentId;
    private Map<String, Integer> subjectGrades;

    public Grade(int studentId, Map<String, Integer> subjectGrades) {
        this.studentId = studentId;
        this.subjectGrades = subjectGrades;
    }

    public int getStudentId() { return studentId; }
    public Map<String, Integer> getSubjectGrades() { return subjectGrades; }
}