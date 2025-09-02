package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathAndQueryParameters {

    @Test
    public void testQueryAndPathParameters() {
        given().
                baseUri("https://reqres.in/").
                pathParam("mypath1","api").
                pathParam("mypath2", "users").
                queryParam("page", 2).
                queryParam("id", 7).
                header("x-api-key", "reqres-free-v1").
        when().
                get("{mypath1}/{mypath2}").
        then().
                log().all().
                statusCode(200);
    }
}
