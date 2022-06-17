package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class FindPatientRecordPage {
    WebDriver driver;
    SoftAssert softAssert;

    public FindPatientRecordPage(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//tr[@class='odd']/td[1]")
    private List<WebElement> firstPatientId;

    @FindBy(xpath = "//tr[1][@class='odd']//td")
    private List<WebElement> firstTableResult;

    @FindBy(id = "patient-search")
    private WebElement findById;


    public void patientRecordVerification(Map<String, String> data) {
        System.out.println(firstTableResult.size());
        softAssert.assertEquals(firstTableResult.size(), 5);
        findById.sendKeys(data.get("trimID"));


    }

}