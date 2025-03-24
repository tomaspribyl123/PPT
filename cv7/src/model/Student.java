package model;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String classCode;

    public Student(int id, String fullName, String classCode) {
        this.id = id;
        this.classCode = classCode;
        parseFullName(fullName);
    }

    private void parseFullName(String fullName) {
        String[] parts = fullName.trim().split(" ");
        this.lastName = parts[parts.length - 1];
        this.firstName = String.join(" ", java.util.Arrays.copyOf(parts, parts.length - 1));
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getClassCode() { return classCode; }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}
