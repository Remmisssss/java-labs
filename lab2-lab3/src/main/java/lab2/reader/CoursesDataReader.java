package lab2.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lab2.model.CourseInfo;
import lab2.model.CourseInstance;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CoursesDataReader {
    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    public List<CourseInfo> readCourseInfoData() {
        try {
            return List.of(objectMapper.readValue(new File("src/main/resources/courseInfos.json"), CourseInfo[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CourseInstance> readCourseInstanceData() {
        try {
            return List.of(objectMapper.readValue(new File("src/main/resources/courseInstances.json"), CourseInstance[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
