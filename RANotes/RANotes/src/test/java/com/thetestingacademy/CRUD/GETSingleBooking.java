package com.thetestingacademy.CRUD;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class GETSingleBooking {

    @Test
    public void testSingle(){

        RestAssured.given().baseUri("https://restful-booker.herokuapp.com/booking")
                .basePath("/573").when().get().then().log().all().statusCode(200);

    }



}
