package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser {

    @Test
    public void test_deleteUser(ITestContext context){

        String bearerToken = "408d52783143154574f967ca38b507bb7aed98b76649d2e2ee022ba77a54dad8";
        int id = (int) context.getSuite().getAttribute("user_id");
        given().
                headers("Authorization", "Bearer "+bearerToken).
                contentType("application/json").
                pathParam("id",id).
        when().
                delete("https://gorest.co.in/public/v2/users/{id}").
        then().
                log().all().
                statusCode(204);
    }
}
