package com.thetestingacademy.CRUD.PUT;

import java.util.HashMap;
import java.util.Map;

public class HMapDemo {
    public static void main(String[] args) {

//        {
//            "id": 1,
//                "title": "hello"
//        }

        Map<String,Object> json = new HashMap<>();
        json.put("id",1);
        json.put("title","Hello");

        System.out.println("json");


//        {
//            "bookingid": 1314,
//                "booking": {
//            "firstname": "Pramod",
//                    "lastname": "Dutta",
//                    "totalprice": 342,
//                    "depositpaid": true,
//                    "bookingdates": {
//                "checkin": "2023-05-01",
//                        "checkout": "2023-05-01"
//            },
//            "additionalneeds": "Breakfast"
//        }
//        }


        //  JSON Object
        Map<String,Object> parent = new HashMap<>();
        parent.put("bookingid",1314);

        Map<String,Object> jsonBodyUsingMap = new HashMap<>();
        jsonBodyUsingMap.put("firstname","Jim");
        jsonBodyUsingMap.put("lastname", "Brown");
        jsonBodyUsingMap.put("totalprice", 111);
        jsonBodyUsingMap.put("depositpaid", true);

        Map<String,Object> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", "2021-07-01");
        bookingDatesMap.put("checkout", "2021-07-01");

        jsonBodyUsingMap.put("bookingdates", bookingDatesMap);
        jsonBodyUsingMap.put("additionalneeds", "Breakfast");


        parent.put("booking",jsonBodyUsingMap);
        System.out.println(parent);






    }
}
