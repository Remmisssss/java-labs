package lab2.Data;

import lab2.model.*;
import lab2.reader.CoursesDataReader;
import lab2.reader.InstructorsDataReader;
import lab2.reader.StudentDataReader;

import java.util.List;
import java.util.stream.Stream;

public class DataLists {
    public static final List<Student> bachelorStudents = new StudentDataReader().readBachelorStudentData();
    public static final List<Student> masterStudents = new StudentDataReader().readMasterStudentData();
    public static final List<Student> allStudents = Stream.concat(bachelorStudents.stream(), masterStudents.stream()).toList();
    public static final List<CourseInstance> courseInstances = new CoursesDataReader().readCourseInstanceData();
    public static final List<CourseInfo> courseInfos = new CoursesDataReader().readCourseInfoData();

    public static final List<Instructor> instructors = new InstructorsDataReader().readInstructorData();

    public static StudentCategory category;
}
