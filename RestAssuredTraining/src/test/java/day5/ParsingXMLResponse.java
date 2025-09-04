package day5;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParsingXMLResponse {

    @Test
    public void testXMLResponse(){

        // Approach 1
//        given().
//        when().
//                get("https://mocktarget.apigee.net/xml").
//        then().
//                log().all().
//                statusCode(200).
//                header("Content-Type", "application/xml; charset=utf-8").
//                body("root.city", equalTo("San Jose"));

        // Approach 2
        Response response = given().
                when().
                get("https://mocktarget.apigee.net/xml");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/xml; charset=utf-8");
        String city = response.xmlPath().get("root.city").toString();
        Assert.assertEquals(city, "San Jose");
    }

    @Test
    public void testXMLResponseBody(){
        Response response = given().
                when().
                get("https://mocktarget.apigee.net/xml");

        XmlPath xmlobj = new XmlPath(response.asString());
        List<String> root = xmlobj.getList("root");
        Assert.assertEquals(root.size(), 1);

        // Verify city is present in response
        List<String> rootFirstName = xmlobj.getList("root.firstName");
        boolean status = false;
        for (String firstname: rootFirstName){
            System.out.println(firstname);
            if (firstname.equals("John")){
                status = true;
            }
        }
        Assert.assertEquals(status, true);
    }
}
