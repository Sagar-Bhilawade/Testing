package com.thetestingacademy.CRUD.PostReq;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import javax.naming.spi.ResolveResult;

public class BDDStylePost {

    @Test
    public void testBDDPost() {

        // TC # 1 - Verify that with when we pass the username and pass with the
        // url we will get the 200 Ok

        // HTTP - Methos - POST
        // url - https://restful-booker.herokuapp.com/
        // Header - Content-Type -
        // Base - /auth
        // Body / Payload  -  {
        //    "username" : "admin",
        //    "password" : "password123"
        //}

        // Status Code - 200
        // TC #2 - Verify that Token should not null

        // Payload - String, Hashmap, Pojo Class
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .body(payload)
                .when().post()
                .then().log().all()
                .statusCode(200)
                .body("token", Matchers.notNullValue());

        // Non BDD

        RequestSpecification requestSpecification = RestAssured.given();

        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);

        Response response = requestSpecification.when().post();

        ValidatableResponse validatableResponse = response.then();


        validatableResponse.body("token", Matchers.notNullValue());
        validatableResponse.body("token.length", Matchers.is(15));


//        given -> RequestSpecification
//                when -> Response
//                        then -> ValidatableResponse




    }

}
