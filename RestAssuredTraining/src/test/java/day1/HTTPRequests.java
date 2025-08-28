package day1;

import org.testng.annotations.Test;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HTTPRequests {

    int id;

    @Test(priority = 1)
    public void listUsers(){
        given().    // Under given should be content type, cookies, authentication, add param, set header info, etc.
            baseUri("https://reqres.in/api").
            basePath("users").
            queryParam("page", 2).
        when().     // specify request type
            get().
        then().     // specify all validations i.e. validate status code, extract response, extract header cookies, response body
           log().all().
           statusCode(200).
           body("page", equalTo(2));
    }

    @Test(priority = 2)
    public void createUser() {
//        String body = "{\n" +
//                "    \"name\": \"morpheus\",\n" +
//                "    \"job\": \"leader\"\n" +
//                "}";

        HashMap data = new HashMap();
        data.put("name", "morpheus");
        data.put("job", "leader");

        id = given().
                baseUri("https://reqres.in/api/").
                contentType("application/json; charset=utf-8").
                header("x-api-key", "reqres-free-v1").
                basePath("users").
                body(data).
        when().
                post().jsonPath().getInt("id");

    }

    @Test(priority = 3)
    public void updateUser(){

        HashMap data = new HashMap();
        data.put("name", "updated_morpheus");
        data.put("job", "leader1");

        given().
                baseUri("https://reqres.in/api/").
                contentType("application/json; charset=utf-8").
                header("x-api-key", "reqres-free-v1").
                basePath("users/").
                body(data).
        when().
                 put(String.valueOf(id)).
        then().
                log().all().
                statusCode(200).
                body("name", equalTo("updated_morpheus"));
    }

    @Test(priority = 4)
    public void deleteUser(){
        given().
                baseUri("https://reqres.in/api/").
                contentType("application/json; charset=utf-8").
                header("x-api-key", "reqres-free-v1").
                basePath("users/").
        when().
                delete(String.valueOf(id)).
        then().
                statusCode(204);
    }

}
