package com.academy.techcenture.pages;

import com.academy.techcenture.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class LoginPage {

    public WebDriver driver;
    public SoftAssert softAssert;
   // String locationFromLP;

    public LoginPage(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.softAssert = softAssert;

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div/div[2]/div/header/div/a/img")
    private WebElement headerLP;

    @FindBy(className = "w-auto")
    private WebElement loginImg;

    @FindBy(xpath = "//label[@for='sessionLocation']")
    private WebElement locationLabel;

    @FindBy(xpath = "//ul[@class='select']/li")
    public List<WebElement> locationList;

    @FindBy (xpath="//li[@class='selected']")
    public WebElement selectedLocation;

    @FindBy(id = "username")
    private WebElement userNameLP;

    @FindBy(id = "password")
    private WebElement passwordLP;

    @FindBy(id = "cantLogin")
    private WebElement cantLoginBtn;

    @FindBy(id = "loginButton")
    private WebElement loginBtn;

    @FindBy(xpath = "//label[@for='username']")
    private WebElement userNameLabel;

    @FindBy(xpath = "//label[@for='password']")
    private WebElement passwordLabel;


    public void verifyLPElements() {
        softAssert.assertEquals(driver.getTitle(), "Login");
        softAssert.assertTrue(headerLP.isDisplayed(), "Header not displayed");
        softAssert.assertTrue(locationList.size() == 6, "Location list consists of != 6");
        softAssert.assertTrue(loginBtn.isEnabled(), "Login button is not Enabled");
        softAssert.assertTrue(userNameLabel.isDisplayed(), "User name label is NOT displayed");
        softAssert.assertTrue(passwordLP.isDisplayed(), "Password label is NOT displayed");
    }

    public void login() {
        userNameLP.clear();
        userNameLP.sendKeys(ConfigReader.getProperty("user"));
        passwordLP.clear();
        passwordLP.sendKeys(ConfigReader.getProperty("userLogin"));

       // locationFromLP = locationList.get(3).getText();


        locationList.get(2).click();

        loginBtn.click();
    }

}
