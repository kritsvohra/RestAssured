package day2;
/*
How many ways we can create request body
———————
1. HashMap
2. Using org.json
3. Using POJO (Plain Old Java Object) class
4. Using external external json file
 */

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class WaysToCreatePostRequestBody {

    // Post request body using HashMap
    @Test(priority = 1)
    public void testPostUsingHashMap(){
        HashMap data = new HashMap();
        data.put("name", "Scott");
        data.put("location", "France");
        data.put("phone", "123456");

        String courseArr[] = {"C", "C++"};

        data.put("courses", courseArr);

        given().
                contentType("application/json").
                body(data).
        when().
                post("http://localhost:3000/students").
        then().
                log().all().
                statusCode(201).
                body("name", equalTo("Scott")).
                body("location", equalTo("France")).
                body("phone", equalTo("123456")).
                body("courses[0]", equalTo("C")).
                body("courses[1]", equalTo("C++")).
                header("Content-Type", equalTo("application/json; charset=utf-8"));
    }

    @Test(priority = 2)
    public void testDelete(){
        given().
                baseUri("http://localhost:3000/students/6").
        when().
                delete().
        then().
                log().all().
                statusCode(200);
    }

    // Post request body using org.json library
    @Test(priority = 3)
    public void testPostUsingOrgJson(){
        JSONObject data = new JSONObject();
        data.put("name", "Scott");
        data.put("location", "France");
        data.put("phone", "123456");

        String coursesArr[] = {"C", "C++"};
        data.put("courses", coursesArr);

        given().
                contentType("application/json").
                body(data.toString()).
        when().
                post("http://localhost:3000/students").
        then().
                log().all().
                statusCode(201).
                body("name", equalTo("Scott")).
                body("location", equalTo("France")).
                body("phone", equalTo("123456")).
                body("courses[0]", equalTo("C")).
                body("courses[1]", equalTo("C++")).
                header("Content-Type", equalTo("application/json; charset=utf-8"));
    }

    // Post request body using POJO class
    @Test
    public void testPostUsingPOJO(){
        POJO_PostRequest data = new POJO_PostRequest();
        data.setName("Scott");
        data.setLocation("France");
        data.setPhone("123456");
        String courses[] = {"C", "C++"};
        data.setCourses(courses);

        given().
                contentType("application/json").
                body(data).
        when().
                post("http://localhost:3000/students").
        then().
                log().all().
                statusCode(201).
                body("name", equalTo("Scott")).
                body("location", equalTo("France")).
                body("phone", equalTo("123456")).
                body("courses[0]", equalTo("C")).
                body("courses[1]", equalTo("C++")).
                header("Content-Type", equalTo("application/json; charset=utf-8"));
    }

    // Post request using external json file
    @Test
    public void testPostUsingExternalJsonFile() throws FileNotFoundException {
        File f = new File("/Users/kritikavohra/Tutorials/RestAssuredTutorials/RestAssured/RestAssuredTraining/src/test/java/day2/body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        given().
                contentType("application/json").
                body(data.toString()).
        when().
                post("http://localhost:3000/students").
        then().
                log().all().
                statusCode(201).
                body("name", equalTo("Scott")).
                body("location", equalTo("France")).
                body("phone", equalTo("123456")).
                body("courses[0]", equalTo("C")).
                body("courses[1]", equalTo("C++")).
                header("Content-Type", equalTo("application/json; charset=utf-8"));
    }
}
