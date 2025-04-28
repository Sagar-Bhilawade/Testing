package com.thetestingacademy.CRUD.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class PUTNonBDDStyle {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;


    @Test
    public void putRequest(){
        //3380
        String payloadString = "{\n" +
                "        \"firstname\" : \"Pramod\",\n" +
                "        \"lastname\" : \"sING\",\n" +
                "        \"totalprice\" : 340,\n" +
                "        \"depositpaid\" : true,\n" +
                "        \"additionalneeds\" : \"Breakfast,Lunch\",\n" +
                "        \"bookingdates\" : {\n" +
                "            \"checkin\" : \"2023-05-02\",\n" +
                "            \"checkout\" : \"2023-05-04\"\n" +
                "        }\n" +
                "\n" +
                "}";


        String token_from_previous_req = "3d6b859c7813807";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/6758");
        requestSpecification.contentType(ContentType.JSON);
        //requestSpecification.headers("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=");
        //requestSpecification.cookie("token",token_from_previous_req);
        requestSpecification.auth().preemptive().basic("admin","password123");


        // Adding body as string
        requestSpecification.body(payloadString).log().all();

        // Calling PUT method on URI. After hitting we get Response
        Response response = requestSpecification.put();

        // Printing Response as string
        System.out.println(response.asString());

        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();

        // Validate status code as 200
        validatableResponse.statusCode(200);

        // Validate value of firstName is updated
        validatableResponse.body("firstname", Matchers.equalTo("Pramod"));








    }

    @Test
    public void DeleteRequest(){




        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/1302");
        requestSpecification.contentType(ContentType.JSON);
        //requestSpecification.headers("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=");
        //requestSpecification.cookie("token",token_from_previous_req);
        requestSpecification.auth().preemptive().basic("admin","password123");

        // Calling PUT method on URI. After hitting we get Response
        Response response = requestSpecification.when().delete();

        // Printing Response as string
        System.out.println(response.asString());

        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();

        // Validate status code as 200
        validatableResponse.statusCode(201);






    }
}
