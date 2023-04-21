package com.generation.java;

import com.generation.model.Course;
import com.generation.model.EnrolledCourse;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args)
            throws ParseException {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            PrinterHelper.showMainMenu(); // Show the main menu and get the selected option
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    registerStudent(studentService, scanner);
                    break;
                case 2:
                    findStudent(studentService, scanner);
                    break;
                case 3:
                    gradeStudent(studentService, scanner);
                    break;
                case 4:
                    enrollStudentToCourse(studentService, courseService, scanner);
                    break;
                case 5:
                    showStudentsSummary(studentService, scanner);
                    break;
                case 6:
                    showCoursesSummary(courseService, scanner);
                    break;
                case 7:
                    showPassedCourses(studentService, scanner);
                    break;
            }
        }
        while (option != 8);
    }

    private static void enrollStudentToCourse(StudentService studentService, CourseService courseService,
                                              Scanner scanner) {
        //Get student ID
        System.out.println("Insert student ID");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        // Find the student by ID
        if (student == null) {
            System.out.println("Invalid Student ID");
            return;
        }
        // Print the student information
        System.out.println(student);
        // Get the course ID
        System.out.println("Insert course ID");
        String courseId = scanner.next();
        Course course = courseService.getCourse(courseId);
        if (course == null) {
            // If the course doesn't exist, print an error message and return
            System.out.println("Invalid Course ID");
            return;
        }
        // Print the course information
        System.out.println(course);
        // Enroll the student to the course and print a success message
        studentService.enrollToCourse(studentId, course);
        System.out.println("Student with ID: " + studentId + " enrolled successfully to " + courseId);

    }

    private static void showCoursesSummary(CourseService courseService, Scanner scanner) {
        // Show the summary of all courses
        courseService.showSummary();
    }

    private static void showStudentsSummary(StudentService studentService, Scanner scanner) {
        // Show the summary of all students
        studentService.showSummary();
    }

    private static void gradeStudent(StudentService studentService, Scanner scanner) {
        // Get the student information
        Student student = getStudentInformation(studentService, scanner);
        System.out.println("Enrolled course:");

        //TODO Loop through the student enrolled courses, and use the scanner object to get the course ID to insert
        // the course grade
        if (student == null) {
            return;
        }
        System.out.println("Enrolled course:");
        // Retrieve the HashMap of the student's enrolled courses
        HashMap<String, EnrolledCourse> enrolledCourses = student.getEnrolledCourses();
        // If the HashMap is empty, print out a message indicating that the student is not enrolled
        // in any courses, and exit the method
        if (enrolledCourses.isEmpty()) {
            System.out.println("This student is not enrolled in any courses");
            return;
        }
        // Iterate through the values in the HashMap (i.e., the enrolled courses), and
        // prompt the user to input a grade for each course
        for (EnrolledCourse enrolledCourse : enrolledCourses.values()) {
            System.out.println(enrolledCourse.getCourse().toString());
            System.out.println("Insert grade for course " + enrolledCourse.getCourse().getCode());
            double grade = scanner.nextDouble();
            enrolledCourse.setGrade(grade);
        }
        System.out.println("Grades for enrolled courses have been updated");
    }
    // Get the student information from the user (i.e., prompt the user to input the student's ID)
    private static Student getStudentInformation(StudentService studentService, Scanner scanner) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        // Check if the student is null
        if (student == null) {
            System.out.println("Student not found");
        }
        return student;
    }

    // Find a student with the given ID and print out their information
    private static void findStudent(StudentService studentService, Scanner scanner) {
        Student student = getStudentInformation(studentService, scanner);
        // If the student object is not null, print out the student's information
        if (student != null) {
            System.out.println("Student Found: ");
            System.out.println(student);
        }
    }

    private static void registerStudent(StudentService studentService, Scanner scanner) throws ParseException {
        // Prompt the user to enter the required information for a new student using the
        // createStudentMenu() method from the PrinterHelper class
        Student student = PrinterHelper.createStudentMenu(scanner);
        // Register the new student with the studentService
        studentService.registerStudent(student);
    }

    private static void showPassedCourses(StudentService studentService, Scanner scanner) {
        //TODO Loop through the student enrolled courses, and show all the passed courses
        // Get the student information
        Student student = getStudentInformation(studentService, scanner);

        // If the student is found, loop through their enrolled courses and display the courses
        // they have passed with a grade of 3.0 or higher
        if (student != null) {
            HashMap<String, EnrolledCourse> enrolledCourses = student.getEnrolledCourses();
            System.out.println("Passed Courses:");
            for (EnrolledCourse enrolledCourse : enrolledCourses.values()) {
                if (enrolledCourse.getGrade() >= 3.0) {
                    System.out.println(enrolledCourse.getCourse().getName() + " - Grade: " + enrolledCourse.getGrade());
                }
            }

        }

    }

}
