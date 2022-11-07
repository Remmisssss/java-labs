package lab2.service;

import lab2.model.*;

import java.time.LocalDate;
import java.util.*;

import static lab2.Data.DataLists.*;

public class StudentServiceImpl implements StudentService {
    @Override
    public ActionStatus subscribe(long studentId, long courseId) {
        Student currentStudent = getBachelorStudent(studentId);

        if (currentStudent == null) {
            currentStudent = getMasterStudent(studentId);
            category = StudentCategory.MASTER;
        } else {
            category = StudentCategory.BACHELOR;
        }

        if (currentStudent == null) {
            System.out.println("Студент не найден");
            return ActionStatus.NOK;
        }

        CourseInstance currentCourseInstance = getCourseInstance(courseId);

        if (currentCourseInstance == null) {
            System.out.println("Курс не найден");
            return ActionStatus.NOK;
        }
        if (currentCourseInstance.getStartDate().isBefore(LocalDate.now())) {
            System.out.println("Курс уже завершен");
            return ActionStatus.NOK;
        }
        if (currentCourseInstance.getCapacity() == 0) {
            System.out.println("На курсе нет места");
            return ActionStatus.NOK;
        }

        CourseInfo currentCourseInfo = getCourseInfo(currentCourseInstance);

        if (currentCourseInfo == null) {
            System.out.println("Курс не найден");
            return ActionStatus.NOK;
        }

        if (currentCourseInfo.getStudentCategories()
                .stream()
                .noneMatch(studentCategory -> studentCategory == category)) {
            System.out.println("Курс не предназначен для данной категории студента");
            return ActionStatus.NOK;
        }
        if (currentCourseInfo.getPrerequisites() != null
                && currentCourseInfo.getPrerequisites().size() != 0
                && currentStudent.getCompletedCourses().stream()
                .noneMatch(completeCourse -> currentCourseInfo.getPrerequisites()
                        .stream()
                        .anyMatch(completeCourse::equals))) {
            System.out.println("Студент " + currentStudent.getName() + " не окончил требуемые курсы");
            return ActionStatus.NOK;
        }
        if (currentStudent.getCurrentCourses() != null && currentStudent.getCurrentCourses()
                .stream()
                .anyMatch(course -> course == currentCourseInstance.getId())) {
            System.out.println("Студент " + currentStudent.getName() + " уже записан на этот курс");
            return ActionStatus.NOK;
        }
        if (currentStudent.getCompletedCourses() != null && currentStudent.getCompletedCourses()
                .stream()
                .anyMatch(course -> course == currentCourseInstance.getCourseId())) {
            System.out.println("Студент " + currentStudent.getName() + " уже закончил этот курс");
            return ActionStatus.NOK;
        }
        if (currentCourseInstance.getCapacity() > 0) {
            currentCourseInstance.setCapacity(currentCourseInstance.getCapacity() - 1);
        }

        switch (category) {
            case MASTER -> {
                Student masterCurrentStudent = currentStudent;

                masterStudents.forEach(student -> {
                    if (student.getId() == masterCurrentStudent.getId()) {
                        student.setCurrentCourses(Collections.singletonList(currentCourseInstance.getId()));
                    }
                });
            }

            case BACHELOR -> {
                Student bachelorCurrentStudent = currentStudent;

                bachelorStudents.forEach(student -> {
                    if (student.getId() == bachelorCurrentStudent.getId()) {

                        student.setCurrentCourses(Collections.singletonList(currentCourseInstance.getId()));
                    }
                });
            }
        }
        System.out.println("Студент " + currentStudent.getName() + " успешно записан на курс");
        return ActionStatus.OK;

    }

    @Override
    public ActionStatus unsubscribe(long studentId, long courseId) {
        Student currentStudent = getBachelorStudent(studentId);

        if (currentStudent == null) {
            currentStudent = getMasterStudent(studentId);
            category = StudentCategory.MASTER;
        } else {
            category = StudentCategory.BACHELOR;
        }

        if (currentStudent == null) {
            System.out.println("Студент не найден");
            return ActionStatus.NOK;
        }
        if (currentStudent.getCurrentCourses().stream().noneMatch(currentCourse -> currentCourse == courseId)) {
            System.out.println("Студент не записан на курсе");
            return ActionStatus.NOK;
        }

        CourseInstance currentCourseInstance = getCourseInstance(courseId);

        if (currentCourseInstance == null) {
            System.out.println("Курс не найден");
            return ActionStatus.NOK;
        }
        if (currentCourseInstance.getStartDate().isBefore(LocalDate.now())) {
            System.out.println("Курс уже идет");
            return ActionStatus.NOK;
        }

        currentCourseInstance.setCapacity(currentCourseInstance.getCapacity() + 1);

        List<Long> newCourses;

        if (currentStudent.getCurrentCourses() != null) {
            newCourses = currentStudent.getCurrentCourses()
                    .stream()
                    .filter(course -> course != currentCourseInstance.getId())
                    .toList();
        } else {
            newCourses = Collections.emptyList();
        }
        switch (category) {
            case BACHELOR -> {
                Student bachelorStudent = currentStudent;

                bachelorStudents.forEach(student -> {
                    if (student.getId() == bachelorStudent.getId()) {
                        student.setCurrentCourses(newCourses);
                    }
                });
            }
            case MASTER -> {
                Student masterStudent = currentStudent;

                masterStudents.forEach(student -> {
                    if (student.getId() == masterStudent.getId()) {
                        student.setCurrentCourses(newCourses);
                    }
                });
            }
        }
        System.out.println("Регистрация студента отменена");
        return ActionStatus.OK;
    }

    @Override
    public List<CourseInstance> findAllSubscriptionsByStudentId(long studentId) {
        Student currentStudent = getStudentFromAll(studentId);

        if (currentStudent == null) {
            System.out.println("Студент не найден");
            return Collections.emptyList();
        }

        List<Long> courseInstancesId = currentStudent.getCurrentCourses();

        if (courseInstancesId == null || courseInstancesId.size() == 0) {
            System.out.println(currentStudent.getName() + " Не проходит курсы");
            return Collections.emptyList();
        }

        List<CourseInstance> coursesInstances = courseInstances.stream()
                .filter(courseInstance -> courseInstancesId
                        .stream()
                        .allMatch(currentCourseInstanceId -> currentCourseInstanceId == courseInstance.getId()))
                .toList();

        if (coursesInstances.size() == 0) {
            System.out.println("Курсы не найдены");
            return Collections.emptyList();
        }
        System.out.println(currentStudent.getName() + " Проходит курсы: ");
        return coursesInstances;
    }

    private Student getBachelorStudent(long studentId) {
        return bachelorStudents
                .stream()
                .filter(student -> student.getId() == studentId)
                .findFirst()
                .orElse(null);
    }

    private Student getMasterStudent(long studentId) {
        return masterStudents
                .stream()
                .filter(student -> student.getId() == studentId)
                .findFirst()
                .orElse(null);
    }

    private Student getStudentFromAll(long studentId) {
        return allStudents
                .stream()
                .filter(student -> student.getId() == studentId)
                .findFirst()
                .orElse(null);
    }

    private CourseInstance getCourseInstance(long courseId) {
        return courseInstances
                .stream()
                .filter(courseInstance -> courseInstance.getId() == courseId)
                .findFirst()
                .orElse(null);
    }

    private CourseInfo getCourseInfo(CourseInstance courseInstance) {
        return courseInfos
                .stream()
                .filter(courseInfo -> courseInfo.getId() == courseInstance.getCourseId())
                .findFirst()
                .orElse(null);
    }
}
