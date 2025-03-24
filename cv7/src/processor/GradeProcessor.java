package processor;

import model.Student;
import model.Grade;
import java.util.*;

public class GradeProcessor {
    public static Map<String, Double> calculateClassAverages(List<Student> students, List<Grade> grades) {
        Map<String, List<Integer>> subjectToGrades = new HashMap<>();

        for (int i = 0; i < students.size(); i++) {
            Grade g = grades.get(i);
            for (Map.Entry<String, Integer> entry : g.getSubjectGrades().entrySet()) {
                subjectToGrades
                    .computeIfAbsent(entry.getKey(), k -> new ArrayList<>())
                    .add(entry.getValue());
            }
        }

        Map<String, Double> averages = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : subjectToGrades.entrySet()) {
            double avg = entry.getValue().stream().mapToInt(Integer::intValue).average().orElse(0);
            averages.put(entry.getKey(), avg);
        }

        return averages;
    }

    public static Map<String, Double> calculateClassAveragesByGrades(List<Grade> grades) {
        Map<String, List<Integer>> subjectToGrades = new HashMap<>();

        for (Grade g : grades) {
            for (Map.Entry<String, Integer> entry : g.getSubjectGrades().entrySet()) {
                subjectToGrades
                        .computeIfAbsent(entry.getKey(), k -> new ArrayList<>())
                        .add(entry.getValue());
            }
        }

        Map<String, Double> averages = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : subjectToGrades.entrySet()) {
            double avg = entry.getValue().stream().mapToInt(Integer::intValue).average().orElse(0);
            averages.put(entry.getKey(), avg);
        }

        return averages;
    }

}