package day8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    @Test
    public void test_updateUser(ITestContext context){
        Faker faker = new Faker();
        JSONObject data = new JSONObject();
        data.put("name", faker.name().fullName());
        data.put("gender", "Female");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "active");

        String bearerToken = "408d52783143154574f967ca38b507bb7aed98b76649d2e2ee022ba77a54dad8";
        int id = (int) context.getSuite().getAttribute("user_id");
        given().
                headers("Authorization", "Bearer "+bearerToken).
                contentType("application/json").
                pathParam("id",id).
                body(data.toString()).
        when().
                put("https://gorest.co.in/public/v2/users/{id}").
        then().
                log().all().
                statusCode(200);
    }
}
