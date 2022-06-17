package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class HomePage {
    public WebDriver driver;
    public SoftAssert softAssert;
 public String locationFromLP;

    public HomePage(WebDriver driver, SoftAssert softAssert, String locationFromLP) {
        this.driver = driver;
        this.softAssert = softAssert;
        this.locationFromLP=locationFromLP;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@class='nav-item identifier']")
    private WebElement adminLoginBtn;

    public WebElement getSelectedLocationBtn() {
        return selectedLocationBtn;
    }

    @FindBy(id = "selected-location")
    private WebElement selectedLocationBtn;

    @FindBy(linkText = "Logout")
    private WebElement logOut;

    public WebElement getLoginAsHeader() {
        return loginAsHeader;
    }

    @FindBy(xpath = "//h4[contains(text(),'  Logged in as Super User (admin) at Laboratory.')]")
    private WebElement loginAsHeader;

    @FindBy(xpath = "//div[@id='navbarSupportedContent']//li")
    private List<WebElement> topOptions;

    public List<WebElement> getHomePageFunctionality() {
        return homePageFunctionality;
    }

    @FindBy(xpath = "//div[@id='apps']//a")
    private List<WebElement> homePageFunctionality;

    @FindBy(xpath = "//a[contains(@id,'referenceapplication')][1]")
    private WebElement registerAPatientBtn;

    public List<WebElement> getTabs() {
        return tabs;
    }

    @FindBy(xpath = "//div[@id='apps']/a")
    private List<WebElement> tabs;
    String[] tabsArray = {"Find Patient Record", "Active Visits", "Register a Patient", "Capture Vitals",
              "Appointment Scheduling","Reports", "Data Management", "Configure Metadata", "System Administration"};


    public void verifyHP() {
        softAssert.assertEquals(driver.getTitle(), "Home");
        softAssert.assertEquals(adminLoginBtn.getText().trim(), "admin");
        System.out.println(loginAsHeader.getText().trim());
        softAssert.assertEquals(homePageFunctionality.size(), 10);
        for (int i = 0; i < tabs.size()-2; i++) {
            softAssert.assertEquals(tabs.get(i).getText().toLowerCase(), tabsArray[i].toLowerCase());
        }
            LoginPage loginPage = new LoginPage(driver, softAssert);
            softAssert.assertTrue((selectedLocationBtn.getText().trim()).equalsIgnoreCase(locationFromLP));
            softAssert.assertEquals(topOptions.size(), 4);
            registerAPatientBtn.click();
        }
    public void clickOnFindPatientRecord(){
        getHomePageFunctionality().get(0).click();
    }
}
