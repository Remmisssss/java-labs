package lab2.service;

import lab2.model.*;

import java.util.Collections;
import java.util.List;

import static lab2.Data.DataLists.*;

public class CourseInstructorServiceImpl implements CourseInstructorService {
    @Override
    public List<Student> findStudentsByCourseId(long courseId) {
        List<Student> students = getAllStudents(courseId);

        if (students.size() == 0) {
            System.out.println("На данном курсе нет студентов");
            return Collections.emptyList();
        }
        System.out.println("Cписок студентов, зарегистрированных курс " + courseId);
        return students;
    }

    @Override
    public List<Student> findStudentsByInstructorId(long instructorId) {

        List<Long> coursesID = getCourseInstancesId(instructorId);

        if (coursesID.size() == 0) {
            System.out.println("Преподаватель не найден");
            return null;
        }

        List<Student> students = allStudents.stream()
                .filter(currentStudent -> {
                    if (currentStudent.getCurrentCourses() == null) {
                        return false;
                    }
                    return currentStudent.getCurrentCourses().stream()
                            .anyMatch(course -> coursesID.stream().anyMatch(courseId -> courseId.equals(course)));
                })
                .toList();


        if (students.size() == 0) {
            System.out.println("У данного преподователя нет студентов");
            return Collections.emptyList();
        }
        System.out.println("Cписок студентов, посещающих один из курсов преподавателя с ID " + instructorId);
        return students;
    }

    @Override
    public List<Instructor> findReplacement(long instructorId, long courseId) {
        long idCourse;

        CourseInstance courseInstance = courseInstances.stream()
                .filter(course -> course.getInstructorId() == instructorId && course.getId() == courseId)
                .findFirst()
                .orElse(null);
        if (courseInstance == null) {
            idCourse = -1;
        } else {
            idCourse = courseInstance.getCourseId();
        }

        if (idCourse == -1) {
            System.out.println("Данный перподователь не ведет этот курс");
            return Collections.emptyList();
        }
        List<Instructor> instructorList = instructors.stream()
                .filter(instructor -> instructor.getId() != instructorId
                        && instructor.getCanTeach()
                        .stream()
                        .anyMatch(course -> course == idCourse))
                .toList();

        if (instructorList.size() == 0) {
            System.out.println("Данного преподователя никто не может заменить");
            return Collections.emptyList();
        }

        System.out.println("Преподаватели на замену: ");
        return instructorList;
    }

    private List<Student> getAllStudents(long courseId) {
        return allStudents.stream()
                .filter(currentStudent -> {
                    if (currentStudent.getCurrentCourses() == null) {
                        return false;
                    }
                    return currentStudent.getCurrentCourses()
                            .stream()
                            .anyMatch(course -> course == courseId);
                })
                .toList();
    }

    private List<Long> getCourseInstancesId(long instructorId) {
        return courseInstances.stream()
                .filter(course -> course.getInstructorId() == instructorId)
                .map(CourseInstance::getId)
                .toList();
    }
}
