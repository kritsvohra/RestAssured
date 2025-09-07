package day8;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUser {

    @Test
    public void test_createUser(ITestContext context) {

        Faker faker = new Faker();
        JSONObject data = new JSONObject();
        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "inactive");

        String bearerToken = "408d52783143154574f967ca38b507bb7aed98b76649d2e2ee022ba77a54dad8";
        int id = given().
            headers("Authorization", "Bearer "+bearerToken).
                contentType("application/json").
                body(data.toString()).
        when().
                post("https://gorest.co.in/public/v2/users").jsonPath().getInt("id");
        System.out.println("Generated id is :: "+id);
        //context.setAttribute("user_id", id);
        context.getSuite().setAttribute("user_id", id);
    }
}
