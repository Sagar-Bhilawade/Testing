package com.thetestingacademy;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class AssertTime {

    @Test
    public void assertTimeOfResponse(){

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        Response response = RestAssured.given().spec(requestSpecification).get();


        ValidatableResponse valRes = response.then();
        valRes.time(Matchers.lessThan(5000L));


    }

}
