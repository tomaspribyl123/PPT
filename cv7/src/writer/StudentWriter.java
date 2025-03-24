package writer;

import model.Student;
import java.io.*;
import java.util.*;

public class StudentWriter {
    public static void writePerClass(List<Student> students, String outputDir) throws IOException {
        Map<String, List<Student>> classMap = new HashMap<>();
        for (Student student : students) {
            classMap.computeIfAbsent(student.getClassCode(), k -> new ArrayList<>()).add(student);
        }

        File outputDirectory = new File(outputDir);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs(); // 💡 Vytvoří složku output, pokud ještě neexistuje
        }

        for (Map.Entry<String, List<Student>> entry : classMap.entrySet()) {
            String classCode = entry.getKey();
            File file = new File(outputDirectory, classCode + ".csv");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Student s : entry.getValue()) {
                    writer.write(s.getFirstName() + " " + s.getLastName() + ";" + s.getClassCode());
                    writer.newLine();
                }
            }
        }
    }
}
