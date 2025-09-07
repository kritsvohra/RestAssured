package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUser {

    @Test
    public void test_getUser(ITestContext context){
        int id= (int) context.getSuite().getAttribute("user_id");
        String bearerToken = "408d52783143154574f967ca38b507bb7aed98b76649d2e2ee022ba77a54dad8";
        given().
                headers("Authorization", "Bearer "+bearerToken).
                pathParam("id",id).
        when().
                get("https://gorest.co.in/public/v2/users/{id}").
        then().
                log().all().
                statusCode(200);
    }
}
