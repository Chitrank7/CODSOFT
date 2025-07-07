import java.io.*;
import java.util.*;

public class Task4_StudentManagementSystem {

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n🎓 Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Edit Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            String choiceStr = sc.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Enter roll number: ");
                    String roll = sc.nextLine().trim();
                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine().trim();
                    if (name.isEmpty() || roll.isEmpty() || grade.isEmpty()) {
                        System.out.println(" All fields are required.");
                    } else {
                        sms.addStudent(new Student(name, roll, grade));
                    }
                    break;

                case 2:
                    System.out.print("Enter roll number to remove: ");
                    String rollToRemove = sc.nextLine().trim();
                    sms.removeStudent(rollToRemove);
                    break;

                case 3:
                    System.out.print("Enter roll number to edit: ");
                    String rollToEdit = sc.nextLine().trim();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine().trim();
                    System.out.print("Enter new grade: ");
                    String newGrade = sc.nextLine().trim();
                    sms.editStudent(rollToEdit, newName, newGrade);
                    break;

                case 4:
                    System.out.print("Enter roll number to search: ");
                    String rollToSearch = sc.nextLine().trim();
                    sms.searchStudent(rollToSearch);
                    break;

                case 5:
                    sms.displayAllStudents();
                    break;

                case 6:
                    System.out.println(" Exiting Student Management System.");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println(" Invalid choice.");
            }
        }
    }
}

class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name.trim();
        this.rollNumber = rollNumber.trim();
        this.grade = grade.trim();
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public void setGrade(String grade) {
        this.grade = grade.trim();
    }

    @Override
    public String toString() {
        return String.format("Name: %-15s Roll No: %-10s Grade: %-2s", name, rollNumber, grade);
    }
}

class StudentManagementSystem {
    private List<Student> students;
    private static final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = loadStudents();
    }

    public void addStudent(Student student) {
        if (findStudent(student.getRollNumber()) != null) {
            System.out.println(" Student with roll number already exists.");
            return;
        }
        students.add(student);
        saveStudents();
        System.out.println(" Student added successfully.");
    }

    public void removeStudent(String rollNumber) {
        Student s = findStudent(rollNumber);
        if (s != null) {
            students.remove(s);
            saveStudents();
            System.out.println(" Student removed.");
        } else {
            System.out.println(" Student not found.");
        }
    }

    public void editStudent(String rollNumber, String newName, String newGrade) {
        Student s = findStudent(rollNumber);
        if (s != null) {
            s.setName(newName);
            s.setGrade(newGrade);
            saveStudents();
            System.out.println(" Student updated.");
        } else {
            System.out.println(" Student not found.");
        }
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("ℹ No students found.");
        } else {
            System.out.println(" Student List:");
            students.forEach(System.out::println);
        }
    }

    public void searchStudent(String rollNumber) {
        Student s = findStudent(rollNumber);
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println(" Student not found.");
        }
    }

    private Student findStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber.trim())) {
                return s;
            }
        }
        return null;
    }

    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println(" Error saving students: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Student> loadStudents() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠ Error loading students: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
