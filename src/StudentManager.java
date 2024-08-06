import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.regex.Pattern;
import java.util.Scanner;

public class StudentManager {
    private ArrayList<Student> students;
    private Scanner scanner;

    // Constructor
    public StudentManager() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Method to validate age
    private boolean isValidAge(int age) {
        return age >= 0 && age <= 150;
    }

    // Method to validate email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

    // Method to validate phone number
    private boolean isValidPhone(String phone) {
        String phoneRegex = "^\\+?[0-9]{10,15}$";
        return Pattern.matches(phoneRegex, phone);
    }

    // Method to validate code
    private boolean isValidCode(String code) {
        return code != null && !code.trim().isEmpty();
    }

    // Method to validate grade
    private boolean isValidGrade(float grade) {
        return grade >= 0 && grade <= 10;
    }

    // Method to validate gender
    private boolean isValidGender(int gender) {
        return gender == 0 || gender == 1;
    }

    // Method to add a new student
    public void addStudent() {
        String name;
        int age;
        String email;
        String phone;
        String code;
        int gender;
        float grade;

        // Get and validate name
        System.out.println("Enter name: ");
        name = scanner.nextLine();

        // Get and validate age
        while (true) {
            System.out.println("Enter age: ");
            try {
                age = scanner.nextInt();
                if (isValidAge(age)) break;
                System.out.println("Invalid age. Age should be between 0 and 150.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid age.");
                scanner.next(); // Clear the invalid input
            }
        }

        scanner.nextLine(); // Consume newline

        // Get and validate email
        while (true) {
            System.out.println("Enter email: ");
            email = scanner.nextLine();
            if (isValidEmail(email)) break;
            System.out.println("Invalid email format.");
        }

        // Get and validate phone
        while (true) {
            System.out.println("Enter phone: ");
            phone = scanner.nextLine();
            if (isValidPhone(phone)) break;
            System.out.println("Invalid phone number format.");
        }

        // Get and validate code
        while (true) {
            System.out.println("Enter code: ");
            code = scanner.nextLine();
            if (isValidCode(code)) break;
            System.out.println("Invalid code. Code cannot be empty.");
        }

        // Get and validate gender
        while (true) {
            System.out.println("Enter gender (0 for Male, 1 for Female): ");
            try {
                gender = scanner.nextInt();
                if (isValidGender(gender)) break;
                System.out.println("Invalid gender. Enter 0 for Male or 1 for Female.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid gender.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Get and validate grade
        while (true) {
            System.out.println("Enter grade: ");
            try {
                grade = scanner.nextFloat();
                if (isValidGrade(grade)) break;
                System.out.println("Invalid grade. Grade should be between 0 and 10.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid grade.");
                scanner.next(); // Clear the invalid input
            }
        }

        scanner.nextLine(); // Consume newline

        // Create and add student
        Student student = new Student(name, age, email, phone, code, gender, grade);
        students.add(student);
    }

    // Method to print the list of students
    public void printStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Method to delete a student by code
    public void deleteStudentByCode(String code) {
        students.removeIf(student -> student.getCode().equals(code));
    }

    // Method to sort students by grade in descending order
    public void sortStudentsByGrade() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Float.compare(s2.getGrade(), s1.getGrade());
            }
        });
    }

    // Method to search for students by code or name
    public void searchStudent(String query) {
        for (Student student : students) {
            if (student.getCode().equals(query) || student.getName().equalsIgnoreCase(query)) {
                System.out.println(student);
            }
        }
    }

    // Method to search for students with grade >= x
    public void searchStudentByGrade(float x) {
        for (Student student : students) {
            if (student.getGrade() >= x) {
                System.out.println(student);
            }
        }
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Print Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students by Grade");
            System.out.println("5. Search Student");
            System.out.println("6. Search Student by Grade");
            System.out.println("7. Exit");
            System.out.println("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    manager.addStudent();
                    break;
                case 2:
                    manager.printStudents();
                    break;
                case 3:
                    System.out.println("Enter code of student to delete: ");
                    String code = scanner.nextLine();
                    manager.deleteStudentByCode(code);
                    break;
                case 4:
                    manager.sortStudentsByGrade();
                    System.out.println("Students sorted by grade in descending order.");
                    break;
                case 5:
                    System.out.println("Enter student code or name to search: ");
                    String query = scanner.nextLine();
                    manager.searchStudent(query);
                    break;
                case 6:
                    System.out.println("Enter minimum grade: ");
                    float grade;
                    try {
                        grade = scanner.nextFloat();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine(); // Clear the invalid input
                        continue;
                    }
                    manager.searchStudentByGrade(grade);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
