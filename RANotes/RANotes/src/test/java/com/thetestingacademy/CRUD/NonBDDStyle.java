package com.thetestingacademy.CRUD;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class NonBDDStyle {

    RequestSpecification requestSpecification;



    @Test
    public void testNonBDDStyleGet(){
        // Automation Framework, we will avoid the BDD Style
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.when().get();
        requestSpecification.then().statusCode(200);
    }

    @Test
    public void testNonBDDStyleGet2(){
        // Automation Framework, we will avoid the BDD Style
        requestSpecification.when().post();
        requestSpecification.then().statusCode(200);
    }
}
