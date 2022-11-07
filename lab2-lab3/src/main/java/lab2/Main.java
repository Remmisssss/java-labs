package lab2;

import lab2.model.ActionStatus;
import lab2.model.CourseInstance;
import lab2.service.CourseInstructorService;
import lab2.service.CourseInstructorServiceImpl;
import lab2.service.StudentService;
import lab2.service.StudentServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentServiceImpl();
        CourseInstructorService instructorService = new CourseInstructorServiceImpl();

        System.out.println(studentService.subscribe(102,100002) + "\n");
        System.out.println(studentService.subscribe(103,100002) + "\n");
        System.out.println(studentService.subscribe(104,100002) + "\n");
        System.out.println(studentService.unsubscribe(104, 100002) + "\n");
        System.out.println(studentService.subscribe(104, 100002) + "\n");

        System.out.println(studentService.findAllSubscriptionsByStudentId(102) + "\n");
        System.out.println(studentService.findAllSubscriptionsByStudentId(103) + "\n");
        System.out.println(studentService.findAllSubscriptionsByStudentId(104) + "\n");

        System.out.println(instructorService.findStudentsByCourseId(100002) + "\n");
        System.out.println(instructorService.findStudentsByCourseId(100003) + "\n");

        System.out.println(instructorService.findStudentsByInstructorId(9002) + "\n");
        System.out.println(instructorService.findStudentsByInstructorId(10) + "\n");
        System.out.println(instructorService.findStudentsByInstructorId(9003) + "\n");

        System.out.println(instructorService.findReplacement(9002, 100002) + "\n");




    }
}
