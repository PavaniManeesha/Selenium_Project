package com.orangehrm.test;

import com.orangehrm.base.BaseClass;
import org.testng.annotations.Test;

public class DummyClass extends BaseClass {

    @Test
    public void dummyTest (){

       String title =driver.getTitle();
       assert title.equals("OrangeHRM"):"Test Failed - Title is not matching";

       System.out.println("Test Passed - Title is matching");

        // Add this pause to see the browser
        try {
            Thread.sleep(5000);  // Wait 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
