package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        //RestAssured.port = 3030;
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200); //method type of this is validatable response
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("total.size().toString()", equalTo("20"));
    }

    //2. Verify the if the name of id = 5487 is equal to ”Raj Patil”
    @Test
    public void test002() {
        response.body("[4].name", equalTo("Raj Patil"));
    }

    //3. Check the single ‘Name’ in the Array list (Ganesh Kaniyar)
    @Test
    public void test003() {
        response.body("name", hasItem("Ganesh Kaniyar"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Raj Patil, Ganesh Kaniyar
//Mrs. Aruna Sharma)
    @Test
    public void test004() {
        response.body("name", hasItems("Raj Patil", "Ganesh Kaniyar", "Mrs. Aruna Sharma"));
    }

    //5. Verify the emai of userid = 5313 is equal “subodh_menon@haley.info”
    @Test
    public void test005() {
        response.body("email[8]", equalTo("subodh_menon@haley.info"));
    }

    //6. Verify the status is “Active” of user name is “Ganesh Kaniyar”
    @Test
    public void test006() {
        response.body("status[6]", equalTo("active"));
        response.body("name[6]", equalTo("Ganesh Kaniyar"));
    }

    //7. Verify the Gender = male of user name is “Raj Patil”
    @Test
    public void test007() {
        response.body("gender[1]", equalTo("male"));
        response.body("name[1]", equalTo("Raj Patil"));
    }

}
