package com.thetestingacademy.CRUD.PostReq;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class NonStylePostClass {
    Auth auth;
    @Test
    public void testBDDPost() {

        // Payload - String, Hashmap, Pojo Class
//        String payload = "{\n" +
//                "    \"username\" : \"admin\",\n" +
//                "    \"password\" : \"password123\"\n" +
//                "}";

        auth  = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");

        // Non BDD

        RequestSpecification requestSpecification = RestAssured.given();

        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(auth).log().all();

        Response response = requestSpecification.when().post();

        ValidatableResponse validatableResponse = response.then();

        validatableResponse.body("token", Matchers.notNullValue());
        validatableResponse.body("token.length()", Matchers.is(15));


    }

    @Test
    public void testBDDPost2() {

        // Payload - String, Hashmap, Pojo Class
//        String payload = "{\n" +
//                "    \"username\" : \"admin\",\n" +
//                "    \"password\" : \"password123\"\n" +
//                "}";

        auth.setUsername("admin123");
        auth.setPassword("password123");

        // Non BDD

        RequestSpecification requestSpecification = RestAssured.given();

        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(auth).log().all();

        Response response = requestSpecification.when().post();

        ValidatableResponse validatableResponse = response.then();

        validatableResponse.body("token", Matchers.notNullValue());
        validatableResponse.body("token.length()", Matchers.is(15));


    }

}
