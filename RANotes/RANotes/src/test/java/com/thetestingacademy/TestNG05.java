package com.thetestingacademy;

import org.testng.annotations.Test;

public class TestNG05 {

    @Test(priority = 1)
    public void getReq() {
        System.out.println("RA GET Code");
    }

    @Test(priority = 2)
    public void postReq() {
        System.out.println("RA POST Code");
    }

    @Test(priority = 3)
    public void putReq() {
        System.out.println("RA PUT Code");
    }

    @Test(priority = 4)
    public void deleteReq() {
        System.out.println("RA DELETE Code");
    }

}
