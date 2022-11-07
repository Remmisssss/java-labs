package lab2.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.model.Student;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Класс для чтения информации о студентах из файлов
 */
public class StudentDataReader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @return список студентов-бакалавров
     */
    public List<Student> readBachelorStudentData() {
        try {
            return List.of(objectMapper.readValue(new File("src/main/resources/bachelorStudents.json"), Student[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return список студентов-магистров
     */
    public List<Student> readMasterStudentData() {
        try {
            return List.of(objectMapper.readValue(new File("src/main/resources/masterStudents.json"), Student[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
