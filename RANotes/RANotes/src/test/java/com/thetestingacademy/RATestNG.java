package com.thetestingacademy;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RATestNG {

    @Test
    public void testGETRequest(){
        RestAssured.
                // Step 1
                        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("booking/3").

                // Step 2
                        when().
                get().

                // Step 3
                        then().
                log().all().
                statusCode(200);

        Assert.assertTrue(false);





    }
    @Test(enabled = false)
    public void testGETRequest2(){
        RestAssured.
                // Step 1
                        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("booking/3").

                // Step 2
                        when().
                get().

                // Step 3
                        then().
                log().all().
                statusCode(200);

    }
}
