package com.thetestingacademy.CRUD.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PUTDemoHashmap {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    @Test
    public void putDemo() {

//        {
//                    "firstname" : "Jim",
//                        "lastname" : "Brown",
//                        "totalprice" : 111,
//                        "depositpaid" : true,
//                        "bookingdates" : {
//                    "checkin" : "2018-01-01",
//                            "checkout" : "2019-01-01"
//                },
//                    "additionalneeds" : "Breakfast"
//        }

        // Why we want to do this? -
        // Payload - String, Map and Pojo Classes
        // 50 Test - String - Difficult to Maintain i Automation - 1-2% String as Payload in Automation.
        // 100 - Test - Map
        // > 100 - Classes to maintain your Payload


        // JSON Object or JSON Array
        // JSON Object -

        Scanner sc = new Scanner(System.in);
        String f_name = sc.nextLine();

        Map<String,Object> jsonBodyUsingMap = new HashMap<>();
        jsonBodyUsingMap.put("firstname",f_name);
        jsonBodyUsingMap.put("lastname", "Brown");
        jsonBodyUsingMap.put("totalprice", 111);
        jsonBodyUsingMap.put("depositpaid", true);

        Map<String,Object> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", "2021-07-01");
        bookingDatesMap.put("checkout", "2021-07-01");

        jsonBodyUsingMap.put("bookingdates", bookingDatesMap);
        jsonBodyUsingMap.put("additionalneeds", "Breakfast");


        System.out.println(jsonBodyUsingMap);

//        String payloadString = "{\n" +
//                "        \"firstname\" : \"+"+f_name+"+\",\n" +
//                "        \"lastname\" : \"sING\",\n" +
//                "        \"totalprice\" : 340,\n" +
//                "        \"depositpaid\" : true,\n" +
//                "        \"additionalneeds\" : \"Breakfast,Lunch\",\n" +
//                "        \"bookingdates\" : {\n" +
//                "            \"checkin\" : \"2023-05-02\",\n" +
//                "            \"checkout\" : \"2023-05-04\"\n" +
//                "        }\n" +
//                "\n" +
//                "}";

        String token_from_previous_req = "3d6b859c7813807";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/1314");
        requestSpecification.contentType(ContentType.JSON);
        //requestSpecification.headers("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=");
        //requestSpecification.cookie("token",token_from_previous_req);
        requestSpecification.auth().preemptive().basic("admin","password123");


        // Adding body as string
        requestSpecification.body(jsonBodyUsingMap).log().all();

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
