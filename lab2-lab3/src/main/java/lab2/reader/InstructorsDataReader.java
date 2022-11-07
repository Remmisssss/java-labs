package lab2.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.model.Instructor;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InstructorsDataReader {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Instructor> readInstructorData() {
        try {
            return List.of(objectMapper.readValue(new File("src/main/resources/instructors.json"), Instructor[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
