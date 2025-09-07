package day7;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Authentications {

    @Test(priority = 1)
    public void basicAuthentication(){
        given().
                auth().basic("postman", "password").
        when().
                get("https://postman-echo.com/basic-auth").
        then().
                log().all().
                statusCode(200).body("authenticated", equalTo(true));
    }

    @Test(priority = 2)
    public void digestAuthentication(){
        given().
                auth().digest("postman", "password").
        when().
                get("https://postman-echo.com/basic-auth").
        then().
                log().all().
                statusCode(200).
                body("authenticated", equalTo(true));
    }

    @Test(priority = 3)
    public void preemptiveAuthentication(){
        given().
                auth().preemptive().basic("postman", "password").
        when().
                get("https://postman-echo.com/basic-auth").
        then().
                log().all().
                statusCode(200).
                body("authenticated", equalTo(true));
    }

    @Test(priority = 4)
    public void bearerTokenAuthentication() {
        String bearerToken = "asdfsdfsdfsdgsdg";
        given().
                headers("Authorization", "Bearer "+bearerToken).
        when().
                get("https://api.github.com/user/repos").
        then().log().all();
    }

    // oauth1.0
    @Test(priority = 5)
    public void testOAuth1Authentication() {
        given().
                auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret").
        when().
                get("https://api.github.com/user/repos").
        then().
                log().all();
    }

    // oauth2.0
    @Test(priority = 6)
    public void testOAuth2Authentication() {

        String token = "oauth2 token sdfsdfsdfsdf";
        given().
                auth().oauth2(token).
        when().
                get("https://api.github.com/user/repos").
        then().
                log().all();
    }

    @Test(priority = 7)
    public void testAPIKeyAuthentication(){
        given().
                header("x-api-key", "reqres-free-v1").
        when().
                get("https://reqres.in/api/users").
        then().
                log().all();

        // Method2
        given().
                queryParam("appid", "wefasfsdgfsdg").
                pathParam("myPath", "data/2.5/forecast/daily").
                queryParam("q", "Delhi").
                queryParam("units", "metric").
                queryParam("cnt", 7).
        when().
                get("https://api.openweathermap.org/{myPath}").
                then().log().all();
    }




}
