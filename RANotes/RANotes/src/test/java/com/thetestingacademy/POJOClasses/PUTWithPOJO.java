package com.thetestingacademy.POJOClasses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class PUTWithPOJO {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    @Test
    public void putRequest() throws JsonProcessingException {

        Booking booking = new Booking();
        booking.setFirstname("Pramod");
        booking.setLastname("lastname");
        booking.setTotalprice(340);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Breakfast,Lunch");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2023-05-02");
        bookingdates.setCheckout("2023-05-04");

        booking.setBookingdates(bookingdates);


//        System.out.println(booking);
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(booking);
//        System.out.println(jsonString);
//
//        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
//        String employeeJsonSringUsingJsonBuilder = gsonBuilder.toJson(booking);
//        System.out.println("Pretty JSON String :- ");
//        System.out.println(employeeJsonSringUsingJsonBuilder);


        ObjectMapper objectMapper = new ObjectMapper();
        String convertedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        System.out.println(convertedJson);



        String jsonString2 = "{\n" +
                "        \"firstname\" : \"Pramod\",\n" +
                "        \"lastname\" : \"Dutta\",\n" +
                "        \"totalprice\" : 342,\n" +
                "        \"depositpaid\" : true,\n" +
                "        \"additionalneeds\" : \"Breakfast\",\n" +
                "        \"bookingdates\" : {\n" +
                "            \"checkin\" : \"2023-05-01\",\n" +
                "            \"checkout\" : \"2023-05-01\"\n" +
                "        }\n" +
                "}";


//        Booking booking1 = gson.fromJson(jsonString2,Booking.class);
//        System.out.println(booking1.getFirstname());


        // Jackson
        // Pass JSON string and the POJO class  		Employee employeeObject = objectMapper.readValue(jsonString, Employee.class);
//        Booking employeeObject = objectMapper.readValue(jsonString2, Booking.class);
//        String firsName = employeeObject.getFirstname();
//        System.out.println(firsName);



//        JACKSON -> JSONsTRING -> Object / Class
//                Object / Class -> JSON String















//        String token_from_previous_req = "3d6b859c7813807";
//
//        requestSpecification = RestAssured.given();
//        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
//        requestSpecification.basePath("/booking/2349");
//        requestSpecification.contentType(ContentType.JSON);
//        //requestSpecification.headers("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=");
//        //requestSpecification.cookie("token",token_from_previous_req);
//        requestSpecification.auth().preemptive().basic("admin","password123");
//
//
//        // Adding body as string
//        requestSpecification.body(booking).log().all();
//
//        // Calling PUT method on URI. After hitting we get Response
//        Response response = requestSpecification.put();
//
//        // Printing Response as string
//        System.out.println(response.asString());
//
//        // Get Validatable response to perform validation
//        validatableResponse = response.then().log().all();
//
//        // Validate status code as 200
//        validatableResponse.statusCode(200);
//
//        // Validate value of firstName is updated
//        validatableResponse.body("firstname", Matchers.equalTo("Pramod"));
//







    }

}
