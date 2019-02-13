package com.briteERP.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    private static WebDriver driver;

    private Driver(){}



    public static WebDriver getDriver() {
         if (driver == null) {
            switch (ConfigurationReader.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver= new ChromeDriver();
                    break;
                case "chrome-headless" :
                    WebDriverManager.chromedriver().setup();
                    driver= new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    driver= new FirefoxDriver();
                    break;
                case "firefox-headless" :
                    WebDriverManager.firefoxdriver().setup();
                    driver= new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                    break;
                case "ie" :
                    if(System.getProperty("os.name").toLowerCase().contains("mac")){
                        throw new WebDriverException("ios does not support ie driver");
                    }
                    WebDriverManager.iedriver().setup();
                    driver= new InternetExplorerDriver();
                    break;
                case "edge" :
                    WebDriverManager.edgedriver().setup();
                    driver= new EdgeDriver();
                    break;
                case "safari" :
                    if(System.getProperty("os.name").toLowerCase().contains("windows")){
                        throw new WebDriverException("windows os does not support safari");
                    }
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver= new SafariDriver();
                    break;
            }

        }
        return driver;
    }
    public static void closeDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}
