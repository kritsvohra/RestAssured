package day6;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import io.restassured.module.jsv.JsonSchemaValidator;

public class JsonSchemaValidation {

    @Test
    public void jsonSchemaValidation(){
        given().
                when().
                get("http://localhost:3000/store").
                then().
                log().all().
                statusCode(200).
                assertThat().
                body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));

    }
}
