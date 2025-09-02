package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoggingDemo {

    @Test
    public void testLogs(){
        given().
                baseUri("https://reqres.in/api/users").
                queryParam("page", 2).
                header("x-api-key", "reqres-free-v1").
        when().
                get().
        then().
                //log().body();
                //log().cookies();
                //log().headers();
                log().all();
    }
}
