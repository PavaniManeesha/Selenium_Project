package com.orangehrm.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;


public class BaseClass {

    protected static Properties prop;
    protected static WebDriver driver;

    @BeforeSuite
    public void loadConfig() throws IOException {
        //Load the configuration file
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src\\main\\resources\\config.properties");
        prop.load(fis);
    }

    @BeforeMethod
    public void setup() throws IOException {
        loadConfig();
        launchBrowser();
        configureBrowser();

    }
    private void launchBrowser() {
        //Initialize the WebDriver based on the browser specified in the configuration file
        String browser = prop.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser \"" + browser + "\" not supported.");
        }
    }
    private void configureBrowser(){
        //Implicit wait
        int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(implicitWait));

        //maximize the browser window
        driver.manage().window().maximize();

        //Navigate to the URL specified in the configuration file
        try {
            driver.get(prop.getProperty("url"));
        } catch (Exception e) {
            System.out.println("failed to navigate to the URL:"+e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Failed to close the browser: " + e.getMessage());
            }
        }
    }
    //getter method for prop
    public static Properties getProp(){
        return prop;

    }
    //Driver getter method
    public static WebDriver getDriver() {
        return driver;
    }
    //Driver setter method
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    //static wait for pause
    public void staticWait(int seconds){
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
    }

}
