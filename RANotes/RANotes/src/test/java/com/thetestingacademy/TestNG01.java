package com.thetestingacademy;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNG01 {

    // Assertion - What is assertion? - Condition to Check
    // Soft vs Hard

    @Test
    public void HardAssertion(){
        System.out.println("Before");
        int id = 3;
        if(id !=3){
            Assert.fail();
        }
//        Assert.assertEquals(id,4);

        System.out.println("After");
    }

    @Test
    public void SoftAssertion(){
        System.out.println("Before");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(3,4);
        System.out.println("Execute Remaining Code ... ");
        softAssert.assertAll();
    }


}
