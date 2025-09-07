package day7;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FakerDataGenerator {

    @Test
    public void testGenerateDummyData() {

        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userName = faker.name().username();
        String password = faker.internet().password();
        String phoneNumber = faker.phoneNumber().cellPhone();
        String emailAddress = faker.internet().safeEmailAddress();
        System.out.println("Full Name:: "+fullName);
        System.out.println("First Name:: "+firstName);
        System.out.println("Last Name::"+lastName);
        System.out.println("Username:: "+userName);
        System.out.println("Password:: "+password);
        System.out.println("Phone NUmber:: "+phoneNumber);
        System.out.println("Email Address:: "+emailAddress);

    }
}
