package day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HeadersDemo {

    @Test(priority = 1)
    public void getHeaderInfo() {
        given().
                baseUri("https://www.google.com/").
        when().
                get().
        then().
                log().headers().
                header("content-type", "text/html; charset=ISO-8859-1").
                and().
                header("Content-Encoding", "gzip").
                header("Server", "gws");
    }

    @Test(priority = 2)
    public void getHeaders() {
        Response response = given().
                baseUri("https://www.google.com/").
                when().
                get();

        // Get single header info
        String header_value = response.getHeader("Content-Type");
        System.out.println("The value of Content-Type is :"+header_value);

        // Get all headers info
        Headers all_headers_value = response.getHeaders();

        for (Header k: all_headers_value){
            String current_header_value = k.getValue();
            System.out.println(k+ " ====> "+current_header_value);
        }
    }
}
