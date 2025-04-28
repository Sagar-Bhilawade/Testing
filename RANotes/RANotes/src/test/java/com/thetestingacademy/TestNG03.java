package com.thetestingacademy;

import org.testng.annotations.Test;

public class TestNG03 {

    @Test(priority = 1)
    void demo1() {
        System.out.println("1");
    }

    @Test(priority = 3)
    void demo2() {
        System.out.println("2");
    }

    @Test(priority = 2)
    void demo3() {
        System.out.println("3");
    }
}
