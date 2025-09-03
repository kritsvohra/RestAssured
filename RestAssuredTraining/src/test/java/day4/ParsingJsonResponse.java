package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParsingJsonResponse {

    @Test(priority = 1)
    public void testJsonResponse() {

        // Approach 1
//        given().
//                contentType(ContentType.JSON).
//        when().
//                get("http://localhost:3000/store").
//        then().
//                log().all().
//                statusCode(200).
//                header("Content-Type","application/json; charset=utf-8").
//                body("book[3].title", equalTo("The Lord of the Rings"));

        // Approach 2
        Response response = given().
                contentType(ContentType.JSON).
                when().
                get("http://localhost:3000/store");

        Assert.assertEquals(response.getStatusCode(), 200); // validation 1
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");
        String bookName = response.jsonPath().get("book[3].title").toString();
        Assert.assertEquals(bookName, "The Lord of the Rings");
    }

    @Test(priority = 2)
    public void testJsonResponseBodyData() {

        // Approach 2
        Response response = given().
                contentType(ContentType.JSON).
                when().
                get("http://localhost:3000/store");

//        Assert.assertEquals(response.getStatusCode(), 200); // validation 1
//        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");
//        String bookName = response.jsonPath().get("book[3].title").toString();
//        Assert.assertEquals(bookName, "The Lord of the Rings");

        // JSONObject class
        JSONObject jo = new JSONObject(response.asString());    // converting response to JSONObject type

        // Print all titles of books
        for (int i = 0; i<jo.getJSONArray("book").length(); i++){
            String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
            System.out.println(bookTitle);
        }

        // Search for title of book in json
        boolean status = false;

        for (int i = 0; i<jo.getJSONArray("book").length(); i++){
            String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
            if (bookTitle.equals("The Lord of the Rings")) {
                status = true;
                break;
            }
        }
        Assert.assertEquals(status, true);

        // Validate total price of books    // validation 2
        double totalPrice = 0;
        for (int i = 0; i<jo.getJSONArray("book").length(); i++){
            String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
            totalPrice = totalPrice + Double.parseDouble(price);
        }
        System.out.println("Total price of books is: "+totalPrice);
        Assert.assertEquals(totalPrice, 53.92);

    }


}
