package com.generation.model;

import java.util.Date;
import java.util.HashMap;

public class Student extends Person {
    public static final double PASS_MIN_GRADE = 3.0;
    private final HashMap<String, EnrolledCourse> enrolledCourses = new HashMap<>();

    public Student(String id, String name, String email, Date birthDate) {
        super(id, name, email, birthDate);
    }

    public boolean enrollToCourse(Course course) {
        // Check if student has already enrolled to the course
        if (enrolledCourses.containsKey(course.getCode())) {
            return false; // Student already enrolled
        }
        // Add the course to enrolledCourses hashmap
        enrolledCourses.put(course.getCode(), new EnrolledCourse(course));
        return true;
    }

    public HashMap<String, EnrolledCourse> getEnrolledCourses() {
        // return a Hashmap of all the enrolledCourses
        return enrolledCourses;
    }

    public void gradeCourse(String courseCode, double grade) throws IllegalArgumentException {
        // Check if grade is within the accepted range of 1 to 6
        if (grade < 1 || grade > 6) {
            throw new IllegalArgumentException("Grade must be between 1 and 6.");
        }
        // TODO Set the grade for the enrolled Course
        EnrolledCourse enrolledCourse = enrolledCourses.get(courseCode);
        if (enrolledCourse != null) {
            enrolledCourse.setGrade(grade);
        }
    }

    public HashMap<String, EnrolledCourse> findPassedCourses() {
        // Check the enrolled courses grade and compare to the passing grade
        HashMap<String, EnrolledCourse> passedCourses = new HashMap<>();
        for (EnrolledCourse enrolledCourse : enrolledCourses.values()) {
            if (enrolledCourse.getGrade() >= PASS_MIN_GRADE) {
                passedCourses.put(enrolledCourse.getCourse().getCode(), enrolledCourse);
            }
        }
        return passedCourses;
    }

    public String toString() {
        return "Student {" + super.toString() + "}";
    }
}
