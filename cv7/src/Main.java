import model.Student;
import model.Grade;
import reader.StudentReader;
import reader.GradeReader;
import processor.GradeProcessor;

import java.io.*;
import java.util.*;

public class Main {

    private static List<Student> students;
    private static List<Grade> grades;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            students = StudentReader.readFromCSV("import/students.csv");
            grades = GradeReader.readFromCSV("import/grades.csv");

            if (students.size() != grades.size()) {
                System.out.println("Chyba: Počet studentů a známek nesouhlasí.");
                return;
            }

            assignClassesFromFile("import/classes.csv");

            int choice;
            do {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Report pro studenta");
                System.out.println("2. Report pro třídy");
                System.out.println("3. Zobrazit seznam studentů");
                System.out.println("4. Zobrazit seznam tříd");
                System.out.println("5. Přidat studenta");
                System.out.println("6. Přidat třídu");
                System.out.println("7. Import CSV podle názvu");
                System.out.println("8. Export seznamu studentů podle tříd");
                System.out.println("0. Konec");
                System.out.print("Zadej volbu: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> showStudentReport();
                    case 2 -> showClassReport();
                    case 3 -> listStudents();
                    case 4 -> listClasses();
                    case 5 -> addStudent();
                    case 6 -> addClass();
                    case 7 -> importCsvByName();
                    case 8 -> exportClassStudentLists();
                    case 0 -> System.out.println("Ukončuji program...");
                    default -> System.out.println("Neplatná volba.");
                }

            } while (choice != 0);

        } catch (Exception e) {
            System.out.println("Chyba při načítání: " + e.getMessage());
        }
    }

    private static void assignClassesFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<String> classCodes = new ArrayList<>();
        List<Integer> studentCounts = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            classCodes.add(parts[0]);
            studentCounts.add(Integer.parseInt(parts[1]));
        }
        reader.close();

        int index = 0;
        for (int i = 0; i < classCodes.size(); i++) {
            String code = classCodes.get(i);
            int count = studentCounts.get(i);
            for (int j = 0; j < count && index < students.size(); j++) {
                students.get(index).setClassCode(code);
                index++;
            }
        }
    }

    private static void showStudentReport() {
        System.out.print("Zadej ID studenta (1-" + students.size() + "): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id < 1 || id > students.size()) {
            System.out.println("Neplatné ID.");
            return;
        }

        Student student = students.get(id - 1);
        Grade grade = grades.get(id - 1);

        System.out.println("Student: " + student.getFirstName() + " " + student.getLastName() + " (" + student.getClassCode() + ")");
        for (Map.Entry<String, Integer> entry : grade.getSubjectGrades().entrySet()) {
            System.out.printf("%-4s: %d\n", entry.getKey(), entry.getValue());
        }
    }

    private static void showClassReport() {
        Map<String, List<Grade>> classGrades = new HashMap<>();

        for (int i = 0; i < students.size(); i++) {
            String classCode = students.get(i).getClassCode();
            classGrades.computeIfAbsent(classCode, k -> new ArrayList<>()).add(grades.get(i));
        }

        for (Map.Entry<String, List<Grade>> entry : classGrades.entrySet()) {
            String classCode = entry.getKey();
            List<Grade> gradeList = entry.getValue();

            Map<String, Double> avg = GradeProcessor.calculateClassAveragesByGrades(gradeList);
            System.out.println("Třída: " + classCode);
            for (Map.Entry<String, Double> subjectAvg : avg.entrySet()) {
                System.out.printf("  %-4s: %.2f\n", subjectAvg.getKey(), subjectAvg.getValue());
            }
        }
    }

    private static void listStudents() {
        System.out.printf("%-4s | %-20s | %-20s | %-5s\n", "ID", "Jméno", "Příjmení", "Třída");
        System.out.println("-------------------------------------------------------------");
        for (Student s : students) {
            System.out.printf("%-4d | %-20s | %-20s | %-5s\n",
                    s.getId(), s.getFirstName(), s.getLastName(), s.getClassCode());
        }
    }

    private static void listClasses() {
        Map<String, Integer> classCount = new HashMap<>();
        for (Student s : students) {
            classCount.put(s.getClassCode(), classCount.getOrDefault(s.getClassCode(), 0) + 1);
        }
        System.out.printf("%-10s | %-10s\n", "Třída", "Počet studentů");
        System.out.println("---------------------------");
        for (Map.Entry<String, Integer> entry : classCount.entrySet()) {
            System.out.printf("%-10s | %-10d\n", entry.getKey(), entry.getValue());
        }
    }

    private static void addStudent() {
        System.out.print("Zadej jméno studenta: ");
        String firstName = scanner.nextLine();

        System.out.print("Zadej příjmení studenta: ");
        String lastName = scanner.nextLine();

        System.out.print("Zadej třídu (např. 1A): ");
        String classCode = scanner.nextLine().toUpperCase(); // 🔧 převod na velká písmena

        // Ověření existence třídy
        Set<String> existingClasses = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("import/classes.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0) {
                    existingClasses.add(parts[0].trim().toUpperCase()); // 🔧 převod i zde
                }
            }
        } catch (IOException e) {
            System.out.println("Chyba při čtení tříd: " + e.getMessage());
            return;
        }

        if (!existingClasses.contains(classCode)) {
            System.out.println("❌ Třída \"" + classCode + "\" neexistuje v import/classes.csv. Studenta nelze přidat.");
            return;
        }

        int newId = students.size() + 1;
        Student student = new Student(newId, firstName + " " + lastName, classCode);
        students.add(student);

        // Dummy známky
        Map<String, Integer> gradesMap = new HashMap<>();
        gradesMap.put("MAT", 1);
        gradesMap.put("FYZ", 1);
        gradesMap.put("IT", 1);
        gradesMap.put("CJ", 1);
        gradesMap.put("TV", 1);
        grades.add(new Grade(newId, gradesMap));

        System.out.println("✅ Student přidán.");
    }



    private static void addClass() {
        System.out.print("Zadej název třídy (např. 1A): ");
        String classCode = scanner.nextLine();

        Set<String> existingClasses = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("import/classes.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0) {
                    existingClasses.add(parts[0].trim().toUpperCase());
                }
            }
        } catch (IOException e) {
            System.out.println("Chyba při čtení tříd: " + e.getMessage());
            return;
        }

        if (existingClasses.contains(classCode)) {
            System.out.println("❌ Třída \"" + classCode + "\" již existuje. Nelze ji přidat znovu.");
            return;
        }

        if (!classCode.matches("\\d+[A-Z]")) {
            System.out.println("❌ Neplatný formát třídy.");
            return;
        }

        System.out.print("Zadej počet studentů: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        File file = new File("import/classes.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(classCode + ";" + count);
            writer.newLine();
            System.out.println("✅ Třída přidána do import/classes.csv");
        } catch (IOException e) {
            System.out.println("Chyba při zapisování třídy: " + e.getMessage());
        }
    }

    private static void importCsvByName() {
        System.out.print("Zadej název CSV souboru k importu (např. students.csv): ");
        String fileName = scanner.nextLine();
        File source = new File("import/" + fileName);

        if (!source.exists()) {
            System.out.println("Soubor neexistuje.");
            return;
        }

        try {
            if (fileName.equalsIgnoreCase("students.csv")) {
                students = StudentReader.readFromCSV(source.getPath());
                System.out.println("✅ Načteno " + students.size() + " studentů.");
            } else if (fileName.equalsIgnoreCase("grades.csv")) {
                grades = GradeReader.readFromCSV(source.getPath());
                System.out.println("✅ Načteno " + grades.size() + " sad známek.");
            } else if (fileName.equalsIgnoreCase("classes.csv")) {
                assignClassesFromFile(source.getPath());
                System.out.println("✅ Třídy přiřazeny.");
            } else {
                System.out.println("Neznámý typ souboru.");
            }
        } catch (IOException e) {
            System.out.println("Chyba při importu: " + e.getMessage());
        }
    }

    private static void exportClassStudentLists() {
        System.out.print("Zadej název exportu (např. export1): ");
        String exportName = scanner.nextLine().trim();
        if (exportName.isEmpty()) {
            System.out.println("❌ Název exportu nesmí být prázdný.");
            return;
        }

        File exportDir = new File("export/" + exportName);
        if (!exportDir.exists()) {
            exportDir.mkdirs(); // Vytvoří složku export/export1
        }

        Map<String, List<Student>> classMap = new HashMap<>();
        for (Student s : students) {
            classMap.computeIfAbsent(s.getClassCode(), k -> new ArrayList<>()).add(s);
        }

        for (Map.Entry<String, List<Student>> entry : classMap.entrySet()) {
            File file = new File(exportDir, entry.getKey() + ".csv");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Student s : entry.getValue()) {
                    writer.write(s.getFirstName() + ";" + s.getLastName());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Chyba při exportu třídy " + entry.getKey() + ": " + e.getMessage());
            }
        }

        System.out.println("✅ Export proběhl do složky: export/" + exportName);
    }

}
