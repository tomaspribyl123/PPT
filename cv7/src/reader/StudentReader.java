package reader;

import model.Student;
import java.io.*;
import java.util.*;

public class StudentReader {
    public static List<Student> readFromCSV(String filePath) throws IOException {
        List<Student> students = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int id = 1;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            if (parts.length < 2) continue;

            String fullName = parts[0].trim() + " " + parts[1].trim();
            String classCode = "UNKNOWN"; // Class will be set externally if needed

            students.add(new Student(id++, fullName, classCode));
        }

        reader.close();
        return students;
    }
}