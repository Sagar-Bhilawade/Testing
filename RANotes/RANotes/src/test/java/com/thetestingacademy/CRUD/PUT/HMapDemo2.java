package com.thetestingacademy.CRUD.PUT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HMapDemo2 {
    public static void main(String[] args) {

        Map<String,Object> parent = new HashMap<>();
        parent.put("squadName", "Super hero squad");
        parent.put("homeTown", "Super hero squad");
        parent.put("formed", "Super hero squad");
        parent.put("secretBase", "Super hero squad");
        parent.put("active", true);


        List<Map<String,Object>> members = new ArrayList<>();

        Map<String,Object> member1 = new HashMap<>();
        member1.put("name", "Molecule Man");
        member1.put("age", 29);
        member1.put("secretIdentity", "Dan Jukes");

        List power = new ArrayList();
        power.add("Radiation resistance");
        power.add("Turning tiny");
        power.add("Radiation blast");

        member1.put("powers", power);

        Map<String,Object> member2 = new HashMap<>();
        member2.put("name", "Molecule Man");
        member2.put("age", 29);
        member2.put("secretIdentity", "Dan Jukes");

        List power1 = new ArrayList();
        power1.add("Radiation resistance");
        power1.add("Turning tiny");
        power1.add("Radiation blast");

        member2.put("powers", power1);

        members.add(member1);
        members.add(member2);

        parent.put("members",members);
        System.out.println(parent);

        // json object - > map
        // json array -> Arraylist






    }
}
