package com.thetestingacademy.IntegrationScenario;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class PostNPut {

    // Post - Get a Token
    // Post - to Create a New Booking and GET THE id
    // Pass the Token, ID to PUT

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    String token;
    Integer booking_id;


    @Test(priority = 1)
    public void CreateToken(){
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);
        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then();

        token = response.then().extract().path("token");
        System.out.println("token -> "+token);

        validatableResponse.body("token", Matchers.notNullValue());
        validatableResponse.body("token.length()", Matchers.is(15));



    }


    @Test(priority = 2)
    public void CreateBooking(){

        String payloadString = "{\n" +
                "        \"firstname\" : \"James\",\n" +
                "        \"lastname\" : \"sigh\",\n" +
                "        \"totalprice\" : 340,\n" +
                "        \"depositpaid\" : true,\n" +
                "        \"additionalneeds\" : \"Breakfast,Lunch\",\n" +
                "        \"bookingdates\" : {\n" +
                "            \"checkin\" : \"2023-05-02\",\n" +
                "            \"checkout\" : \"2023-05-04\"\n" +
                "        }\n" +
                "\n" +
                "}";

        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);

        // Adding body as string
        requestSpecification.body(payloadString).log().all();

        // Calling PUT method on URI. After hitting we get Response
        Response response = requestSpecification.post();
        // Printing Response as string
        System.out.println(response.asString());
        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();
        // Validate status code as 200
        validatableResponse.statusCode(200);
        // Validate value of firstName is updated
        validatableResponse.body("booking.firstname", Matchers.equalTo("James"));
        booking_id = response.then().extract().body().path("bookingid");
        System.out.println(booking_id);


    }

    @Test(priority = 3)
    public void UpdateBookingWithTokenID(){

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

        requestSpecification.basePath("/booking/"+booking_id);
        requestSpecification.contentType(ContentType.JSON);
        //requestSpecification.headers("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=");
        requestSpecification.cookie("token",token);
        //requestSpecification.auth().preemptive().basic("admin","password123");


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







}
