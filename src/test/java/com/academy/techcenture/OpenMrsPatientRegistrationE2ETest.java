package com.academy.techcenture;

import com.academy.techcenture.config.ConfigReader;
import com.academy.techcenture.config.Driver;
import com.academy.techcenture.pages.*;
import com.academy.techcenture.util.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Map;

public class OpenMrsPatientRegistrationE2ETest {

    private WebDriver driver;
    private SoftAssert softAssert;
//private String locationFromLP;


    @BeforeMethod
    public void driverSetup() {

        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("URL"));
        softAssert = new SoftAssert();

    }

    @Test(dataProvider = "data")
    public void testRun(Map<String, String> data) throws IOException, InterruptedException {
        LoginPage loginPage = new LoginPage(driver, softAssert);
        loginPage.verifyLPElements();
        String locationFromLP = loginPage.locationList.get(2).getText();

        HomePage homePage = new HomePage(driver, softAssert, locationFromLP);
        loginPage.login();
        homePage.verifyHP();

        RegisterPage registerPage = new RegisterPage(driver, softAssert);
        registerPage.registration(data);


        PatienDetailsPage patientDetails = new PatienDetailsPage(driver, softAssert);
        patientDetails.patientDetails(data);
        FindPatientRecordPage patientRecord = new FindPatientRecordPage(driver, softAssert);

        homePage.clickOnFindPatientRecord();
        patientRecord.patientRecordVerification(data);

        softAssert.assertAll();
    }

    @DataProvider(name = "data")
    public Object[][] registration() {
        ExcelReader excelReadr = new ExcelReader("src/main/resources/testData/Data.xlsx", "data");
        return excelReadr.getData();
    }
   /* @AfterMethod
    public void clearUp(){
        if (driver != null){
            driver.quit();
        }
    }*/

}

