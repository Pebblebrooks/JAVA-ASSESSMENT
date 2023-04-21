package com.generation.service;

import com.generation.model.Course;
import com.generation.model.EnrolledCourse;
import com.generation.model.Student;

import java.util.HashMap;


public class StudentService
{
    private final HashMap<String, Student> students = new HashMap<>();

    public void registerStudent( Student student )
    {
        //TODO Add new student to the students hashmap
        students.put(student.getId(), student);
    }

    public Student findStudent( String studentId )
    {
        //TODO Find the student from the Hashmap with the student id
        return students.get(studentId);
    }

    public void enrollToCourse( String studentId, Course course )
    {
        //TODO check if students hashmap contains the studentsId, if have add enroll student to the course
        Student student = students.get(studentId);
        if (student != null) {
            student.enrollToCourse(course);
        }
    }

    public void showSummary()
    {
        //Check if student's hashmap is empty
        if(students.isEmpty()) {
        System.out.println("No students enrolled.");
        return;
    }
        //TODO Loop through students hashmap and print out students' details including the enrolled courses
        for (HashMap.Entry<String, Student> entry : students.entrySet()) {
            System.out.println(entry.getValue().toString());
            for (EnrolledCourse enrolledCourse : entry.getValue().getEnrolledCourses().values()) {
                System.out.println("\t" + enrolledCourse.toString());
            }
        }
    }

    public HashMap<String, EnrolledCourse> enrolledCourses(Student student)
    {
        //TODO return a HashMap of all the enrolledCourses
        return student.getEnrolledCourses();
    }

    public Course findEnrolledCourse( Student student, String courseId )
    {
        //TODO return the course enrolled by the student from the course Id
        EnrolledCourse enrolledCourse = student.getEnrolledCourses().get(courseId);
        if (enrolledCourse != null) {
            return enrolledCourse;
        }
        return null;
    }
    // grade the student in the course by the course code.
    public void gradeStudent(Student student, Course course, double grade) {
        student.gradeCourse(course.getCode(), grade);
    }
    //return a HashMap of all the passed courses for a given student.
    public HashMap<String, EnrolledCourse> getPassedCourses(Student student) {
        return student.findPassedCourses();
    }
}
