package day5;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class FileUpload {

    @Test(priority = 1)
    public void singleFileUpload(){
        File myFile = new File("/Users/kritikavohra/Tutorials/RestAssuredTutorials/RestAssured/RestAssuredTraining/src/test/java/day5/locators.txt");
        given()
                .multiPart("file", myFile)
                .when()
                .post("https://postman-echo.com/post")
                .then().
                log().all()
                .statusCode(200);
    }

    @Test(priority = 2)
    public void multipleFilesUpload() {
        File myFile1 = new File("/Users/kritikavohra/Tutorials/RestAssuredTutorials/RestAssured/RestAssuredTraining/src/test/java/day5/locators.txt");
        File myFile2 = new File("/Users/kritikavohra/Tutorials/RestAssuredTutorials/RestAssured/RestAssuredTraining/src/test/java/day5/locators2.txt");

        given()
                .multiPart("files", myFile1).
                multiPart("files", myFile2)
                .when()
                .post("https://postman-echo.com/post")
                .then().
                log().all()
                .statusCode(200);
    }

}
