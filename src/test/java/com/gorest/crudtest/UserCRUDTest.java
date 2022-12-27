package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestUtils {


    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
        response = given()
                .when()
                .get("")
                .then().statusCode(200); //method type of this is validatable response
    }

    @Test
    public void verifyUserCreatedSuccessfully() {



        UserPojo userPojo = new UserPojo();


        userPojo.setName("Tappu Gada");
        userPojo.setEmail("tappu.gada" + getRandomValue() + "@email.com");
        userPojo.setGender("Male");
        userPojo.setStatus("active");

        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization" , "Bearer 6b414b347fd2fe75af1eeb80a68c93611789852e3d838d0bd9d315b441e79756")
                .when()
                .body(userPojo)
                .post();
        response.prettyPrint();
        response.then().log().all().statusCode(201);

    }
    @Test
    public void verifyUserUpdateSuccessfully(){
        UserPojo userPojo = new UserPojo();

        userPojo.setName("Pappu Gada");
        userPojo.setEmail("tappu.gada" + getRandomValue() + "@email.com");
        userPojo.setGender("Male");
        userPojo.setStatus("active");

        Response response = given()

                .header("Content-Type", "application/json")
                .header("Authorization" , "Bearer 6b414b347fd2fe75af1eeb80a68c93611789852e3d838d0bd9d315b441e79756")
                .body(userPojo)
                .pathParam("id", 10623) //passing parameter to .get()
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();


    }

    @Test
    public void VerifyUserDeleteSuccessfully(){

        UserPojo userPojo = new UserPojo();

        Response response = given()

                .header("Content-Type", "application/json")
                .header("Authorization" , "Bearer 6b414b347fd2fe75af1eeb80a68c93611789852e3d838d0bd9d315b441e79756")
                .body(userPojo)
                .pathParam("id", 10623) //passing parameter to .get()
                .when()
                .delete("/{id}");
        response.then().statusCode(204);
        response.prettyPrint();

    }
}
