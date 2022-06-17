package com.academy.techcenture.pages;

import com.academy.techcenture.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class Main {

    static WebDriver driver;
    static SoftAssert softAssert = new SoftAssert();


    public static void main(String[] args) {
        getDriver();
        driver.get(ConfigReader.getProperty("URL"));
        LoginPage loginPage=new LoginPage(driver,softAssert);

        String locationFromLP = loginPage.locationList.get(3).getText();
        loginPage.login();
HomePage homePage=new HomePage(driver,softAssert,locationFromLP);

  //     String s = homePage.locationFromLP;
        System.out.println("The element is "+homePage.locationFromLP+" and "+homePage.getSelectedLocationBtn().getText());
        System.out.println("Log in as HEADER contains "+homePage.getLoginAsHeader().getText());
        System.out.println("Home page functionality list size: "+homePage.getHomePageFunctionality().size());

        for(int i=0; i<homePage.getTabs().size();i++){
            System.out.println(homePage.getTabs().get(i).getText());
            System.out.println(homePage.tabsArray[i]);
        }
        homePage.verifyHP();

        RegisterPage registerPage=new RegisterPage(driver,softAssert);

        String text = registerPage.getConfirmationList().get(0).getText();
        System.out.println(text);


    }
   public static WebDriver getDriver() {


        String browser = ConfigReader.getProperty("browser").toLowerCase();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
        }

        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait"))));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("pageLoadTime"))));
            return driver;
        }
        throw new RuntimeException("Be smart, put right browser name!!");



    }





    }

