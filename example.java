import java.util.*;

class Student {
    String firstName, lastName, address, className;
    Date birthDate;
    Map<String, Double> grades;

    public Student(String firstName, String lastName, Date birthDate, String address, String className, Map<String, Double> grades) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.className = className;
        this.grades = grades;
    }

    public double getAverageGrade() {
        return grades.values().stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public String getRank() {
        double avg = getAverageGrade();
        if (avg >= 8.5) return "A";
        if (avg >= 7.0) return "B";
        if (avg >= 5.5) return "C";
        if (avg >= 4.0) return "D";
        return "<D";
    }

    public String getAddress() { return address; }
    public String getClassName() { return className; }
    public Date getBirthDate() { return birthDate; }

    @Override
    public String toString() {
        return lastName + " " + firstName + " - Avg: " + String.format("%.2f", getAverageGrade()) + " - Rank: " + getRank();
    }
}

class Classroom {
    String className;
    List<Student> students;

    public Classroom(String className) {
        this.className = className;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Map<String, Long> getRankSummary() {
        Map<String, Long> rankCount = new HashMap<>();
        for (String rank : Arrays.asList("A", "B", "C", "D", "<D")) {
            rankCount.put(rank, students.stream().filter(s -> s.getRank().equals(rank)).count());
        }
        return rankCount;
    }

    public void displayClassInfo() {
        System.out.println("Class: " + className);
        students.forEach(System.out::println);
        System.out.println("Rank Summary: " + getRankSummary());
    }
}

public class example {
    public static void main(String[] args) {
        Map<String, Classroom> classes = new HashMap<>();

        // Tạo lớp HTT1 và thêm sinh viên cho lớp này
        Classroom classHTT1 = new Classroom("HTT1");
        Student s1 = new Student("An", "Nguyen", new Date(), "Hanoi", "HTT1",
                Map.of("OOP", 8.5, "PM", 7.8, "ML", 9.0, "DB", 6.5, "Mobile", 8.2));
        Student s2 = new Student("Binh", "Tran", new Date(), "Hanoi", "HTT1",
                Map.of("OOP", 6.5, "PM", 5.5, "ML", 6.0, "DB", 7.2, "Mobile", 6.8));
        Student s3 = new Student("Cuong", "Le", new Date(), "Hanoi", "HTT1",
                Map.of("OOP", 7.5, "PM", 8.0, "ML", 8.2, "DB", 7.5, "Mobile", 8.0));
        classHTT1.addStudent(s1);
        classHTT1.addStudent(s2);
        classHTT1.addStudent(s3);

        // Tạo lớp HTT2 và thêm sinh viên cho lớp này
        Classroom classHTT2 = new Classroom("HTT2");
        Student s4 = new Student("Duc", "Pham", new Date(), "Hanoi", "HTT2",
                Map.of("OOP", 8.0, "PM", 7.0, "ML", 8.5, "DB", 6.0, "Mobile", 7.5));
        Student s5 = new Student("Giang", "Hoang", new Date(), "Hanoi", "HTT2",
                Map.of("OOP", 9.0, "PM", 8.5, "ML", 9.2, "DB", 8.0, "Mobile", 8.5));
        Student s6 = new Student("Hien", "Vo", new Date(), "Hanoi", "HTT2",
                Map.of("OOP", 5.5, "PM", 6.0, "ML", 5.8, "DB", 6.0, "Mobile", 5.5));
        classHTT2.addStudent(s4);
        classHTT2.addStudent(s5);
        classHTT2.addStudent(s6);

        // Thêm các lớp vào danh sách
        classes.put("HTT1", classHTT1);
        classes.put("HTT2", classHTT2);

        System.out.println("Available Classes: " + classes.keySet());
        System.out.print("Enter class code: ");
        try (Scanner scanner = new Scanner(System.in)) {
            String classCode = scanner.nextLine();
            if (classes.containsKey(classCode)) {
                classes.get(classCode).displayClassInfo();
            } else {
                System.out.println("Class not found!");
            }
        }
    }
}
