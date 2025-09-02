package day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookiesDemo {

    @Test(priority = 1)
    public void testCookies() {
        given().
               baseUri("https://www.google.com/").
        when().
                get().
        then().
                cookie("AEC").
                log().all();
    }

    @Test(priority = 2)
    public void getCookiesInfo() {
        Response response = given().
                baseUri("https://www.google.com/").
        when().
                get();

        // Get single cookie info
        String cookie_value = response.getCookie("AEC");
        System.out.println("The value of cookie is: "+cookie_value);

        // Get all cookies information
        Map<String, String> all_cookies_value = response.getCookies();
        System.out.println("All cookies value:");
        for (String k: all_cookies_value.keySet()){
            String current_cookie_value = response.getCookie(k);
            System.out.println(k+" => "+current_cookie_value);
        }
    }
}
