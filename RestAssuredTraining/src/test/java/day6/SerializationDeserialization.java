package day6;

// Serialization POJO --> JSON Object
// Deserialization JSON Object --> POJO

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

public class SerializationDeserialization {

    // Serialization
    @Test
    public void convertPOJOToJson() throws JsonProcessingException {

        // created Java object using POJO class
        Student student = new Student();    // pojo class
        student.setName("Scott");
        student.setLocation("France");
        student.setPhone("123456789");
        String[] courseArr = {"C", "C++"};
        student.setCourses(courseArr);

        // Convert JAVA Object to JSON object (serialization)
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
        System.out.println(jsonData);
    }

    // Deserialization
    @Test
    public void convertJSONToPOJO() throws JsonProcessingException {

        String jsonData = "{\n" +
                "  \"name\" : \"Scott\",\n" +
                "  \"location\" : \"France\",\n" +
                "  \"phone\" : \"123456789\",\n" +
                "  \"courses\" : [ \"C\", \"C++\" ]\n" +
                "}\n";

        // Convert JSO Object to POJO
        ObjectMapper objectMapper = new ObjectMapper();
        Student stu = objectMapper.readValue(jsonData, Student.class);

        System.out.println(stu.getName());
        System.out.println(stu.getLocation());
        System.out.println(stu.getCourses()[0]);
        System.out.println(stu.getCourses()[1]);
        System.out.println(stu.getPhone());

    }
}
