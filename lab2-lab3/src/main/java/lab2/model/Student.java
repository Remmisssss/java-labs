package lab2.model;

import java.util.List;

/**
 * Класс для информации о студенте
 */
public class Student extends Person {

    /**
     * список идентификаторов курсов (CourseInstance.id), пройденных студентом
     */
    private List<Long> completedCourses;

    /**
     * список идентификаторов курсов (CourseInstance.id), которые студент проходит на данный момент
     */
    private List<Long> currentCourses;

    public List<Long> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(List<Long> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public List<Long> getCurrentCourses() {
        return currentCourses;
    }

    public void setCurrentCourses(List<Long> currentCourses) {
        this.currentCourses = currentCourses;
    }

    }



