package com.orangehrm.actiondriver;

import com.orangehrm.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionDriver {

    private WebDriver driver;
    private WebDriverWait wait;

    public ActionDriver (WebDriver driver){
        this.driver =driver;
        int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicitWait"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));

    }
    //method to click on element
    public void click(By by){
        try {
            driver.findElement(by).click();
        } catch (Exception e) {
            System.out.println("Unable to click on element: " + e.getMessage());
        }
    }
    //method to enter text into an input field--avoid code duplication
    public void enterText(By by, String value){

        try {
            WebElement element = driver.findElement(by);
            element.clear();
            element.sendKeys(value);
            //driver.findElement(by).clear();
            //driver.findElement(by).sendKeys(value);
        } catch (Exception e) {
            System.out.println("unable to enter the value:"+e.getMessage());
        }
    }
    //method to get text from an input field
    public String getText(By by){
        try {
            waitForElementToBeVisible(by);
            return driver.findElement(by).getText();
        } catch (Exception e) {
            System.out.println("Unable to get text: " + e.getMessage());
            return "";
        }

    }
    //method to compare Two text
    public void compareText(By by,String expectedText){
        try {
            waitForElementToBeVisible(by);
            String actualText = driver.findElement(by).getText();
            if(expectedText.equals(actualText)){
                System.out.println("Text are matching:"+actualText+"equals"+expectedText);
            }else{
                System.out.println("Text are not matching:"+actualText+"not equals"+expectedText);
            }
        } catch (Exception e) {
            System.out.println("Unable to compare text: " + e.getMessage());
        }
    }
    //method to check if an element is displayed
//    public boolean isDisplayed(By by){
//        try {
//            waitForElementToBeVisible(by);
//            boolean isDisplayed = driver.findElement(by).isDisplayed();
//            if(isDisplayed){
//                System.out.println("Element is displayed");
//                return isDisplayed;
//         }else{
//                return isDisplayed;
//            }
//        } catch (Exception e) {
//            System.out.println("Unable to check if element is displayed: " + e.getMessage());
//            return false;
//        }
//    }
    //simplified the method and remove redundant condition
    public boolean isDisplayed(By by){
        try {
            waitForElementToBeVisible(by);
            return driver.findElement(by).isDisplayed();
        }catch (Exception e){
            System.out.println("Element is not displayed:"+e.getMessage());
            return false;
        }
    }
    //wait for the page load
    public void waitForPageLoad(int timeOutInsec){
        try {
            wait.withTimeout(Duration.ofSeconds(timeOutInsec)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
                    .executeScript("return document.readyState").equals("complete"));
            System.out.println("Page loaded successfully.");
        }catch (Exception e){
            System.out.println("page did not load within"+timeOutInsec +"seconds. Exception:"+e.getMessage());

        }
    }
    //scroll to an element
    public void scrollToElement(By by){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element =driver.findElement(by);
        js.executeScript("arguments[0],scrollIntoView",element);

    }

    //wait for element to be clickable
    private void waitForElementToBeClickable(By by){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            System.out.println("Element not clickable: " + e.getMessage());
        }

    }
    //wait for element to be visible
    private void waitForElementToBeVisible(By by){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("Element not visible: " + e.getMessage());

        }
    }
}
