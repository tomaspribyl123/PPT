package reader;

import model.Grade;
import java.io.*;
import java.util.*;

public class GradeReader {
    public static List<Grade> readFromCSV(String filePath) throws IOException {
        List<Grade> grades = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int id = 1;

        while ((line = reader.readLine()) != null) {
            Map<String, Integer> subjectGrades = new HashMap<>();
            String[] entries = line.split(";");
            for (String entry : entries) {
                String[] parts = entry.split(":");
                if (parts.length == 2) {
                    subjectGrades.put(parts[0].trim(), Integer.parseInt(parts[1].trim()));
                }
            }
            grades.add(new Grade(id++, subjectGrades));
        }

        reader.close();
        return grades;
    }
}