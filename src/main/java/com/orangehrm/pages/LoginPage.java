package com.orangehrm.pages;

import com.orangehrm.actiondriver.ActionDriver;
import org.openqa.selenium.By;

public class LoginPage {

    private ActionDriver actionDriver;

    //Define locators using By class
    private By userNameField = By.name("username");
    private By passWordFeild = By.cssSelector("input[type=\"password\"]");
}
