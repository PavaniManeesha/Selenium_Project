package com.orangehrm.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }


    @Test
    public void LoginDetails(){
        //Enter username
        WebElement username = wait.until(
                ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//input[@name='username']")));
        username.sendKeys("Admin");
        //Enter password
        WebElement password = wait.until
                (ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//input[@name='password']")));
        password.sendKeys("admin123");
        //ButtonClick
        WebElement button = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[@type='submit']")));
        button.click();

        WebElement dashboard = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h6[text()='Dashboard']"))
        );
        Assert.assertTrue(dashboard.isDisplayed(),"Dasboard is not displayed, login might have failed");

        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//p[@class='oxd-userdropdown-name']"))
        );
        dropdown.click();

        WebElement menu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@role='menuitem' and text()='About'] "))
                );
        menu.click();

    }



    @AfterMethod
    public void tearDown(){
        driver.quit();

    }

}