package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class PostsAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        //RestAssured.port = 3030;
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200); //method type of this is validatable response
    }

    // 1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("total.size().toString()", equalTo("25"));
    }

    //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto
//centum.”
    @Test
    public void test002() {
        response.body("title[2]", equalTo("Ad ipsa coruscus ipsam eos demitto centum."));
    }

    //3. Check the single user_id in the Array list (5522)
    @Test
    public void test003() {
        response.body("user_id[4].toString()", equalTo("5522"));
    }

    //4. Check the multiple ids in the ArrayList (2617, 2664, 2693)
    @Test
    public void test004() {
        response.body("id.sort()", hasItems(2617, 2664, 2693));
    }


//5. Verify the body of userid = 2678 is equal “Totidem desolo tabula. Confero tabula laborum.
// Sed confugo cilicium. Cur tantillus et. Suadeo defessus distinctio. Perferendis speciosus
// somniculosus. Id trado maiores. Tunc aeternus charisma. Cauda territo defluo. Thymum amplus ustilo.
// Decet caecus concido. Canonicus aeneus cito. Vulgivagus stultus non. Et confero corporis. Cribro ducimus
// thesaurus. Amplexus vomer aliquam. Laudantium amplexus debeo. Celebrer unde ademptio. Unus vulnero suscipit.
// Antea approbo tollo. Curto iusto ultio.”
@Test
public void test005() {
    response.body("body[1]", equalTo("Totidem desolo tabula. Confero tabula laborum. Sed confugo cilicium. " +
            "Cur tantillus et. Suadeo defessus distinctio. Perferendis speciosus somniculosus. Id trado maiores. Tunc " +
            "aeternus charisma. Cauda territo defluo. Thymum amplus ustilo. Decet caecus concido. Canonicus aeneus cito. " +
            "Vulgivagus stultus non. Et confero corporis. Cribro ducimus thesaurus. Amplexus vomer aliquam. Laudantium " +
            "amplexus debeo. Celebrer unde ademptio. Unus vulnero suscipit. Antea approbo tollo. Curto iusto ultio."));
}
}
