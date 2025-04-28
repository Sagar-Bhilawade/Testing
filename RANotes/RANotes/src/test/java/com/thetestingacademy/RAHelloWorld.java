package com.thetestingacademy;

import io.restassured.RestAssured;

public class RAHelloWorld {
    public static void main(String[] args) {

        // 1. GIVEN()
       // URL - https://restful-booker.herokuapp.com/booking/3
        // Headers ?
        // Auth?
        // Parameters ? path

        /// 2. When()
        // Method - GET


        // 3. Then()
        // Validation
        // Body
        // Status Code
        // Reponse Time

        // Rest Assured


        // Report - HTML?
        // Assertion?
        // CSV File?
        //

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


        // MAVen - bUILD, dEPEN, AND dOCU
        // rEST - HTTP rEQ
        // TstGN = AsSertion



    }
}
