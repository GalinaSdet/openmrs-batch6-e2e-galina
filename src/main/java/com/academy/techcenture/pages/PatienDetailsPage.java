package com.academy.techcenture.pages;

import com.academy.techcenture.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

public class PatienDetailsPage {
    WebDriver driver;
    SoftAssert softAssert;

    public PatienDetailsPage(WebDriver driver, SoftAssert softAssert){
        this.driver=driver;
        this.softAssert=softAssert;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "PersonName-givenName")
    private WebElement personGivenName;

    @FindBy(className = "PersonName-familyName")
    private WebElement personFamilyName;

    @FindBy(xpath = "//div[contains(@class,'gender-age')]//span[1]")
    private WebElement topSectionGender;

    @FindBy(xpath = "//div[contains(@class,'gender-age')]//span[2]")
    private WebElement topSectionDob;

    @FindBy(xpath = "//div[@class='float-sm-right']//span")
    private WebElement topSectionID;

    @FindBy(className = "icon-sticky-note")
    private WebElement yellowStickyNote;

    @FindBy(xpath = "//textarea[contains(@class,'editable-has-buttons')]")
    private WebElement textBox;

    @FindBy(xpath = "//button[contains(@class,'btn-primary')]")
    private WebElement noteCheckMark;

    @FindBy(xpath = "//div[contains(@class,'toast-container')]")
    private WebElement successfullyAdded;

    @FindBy(xpath = "//i[contains(@class,'icon-home')]")
    private WebElement goHomeBtn;

    public void patientDetails(Map<String,String> data){
       softAssert.assertEquals(personGivenName.getText().trim(), data.get("givenName"));
       softAssert.assertEquals(personFamilyName.getText().trim(), data.get("familyName"));
       softAssert.assertEquals(topSectionGender.getText().trim(), data.get("gender"));

        //constructor for current date
        LocalDate curDate = LocalDate.now();

        String dateOfBirth1 = data.get("dateOfBirth");
        String[] dobArray=dateOfBirth1.split("-");
        String BDMonth=null;
        if (dobArray[1].equalsIgnoreCase("Jan")){
            BDMonth = "01";
        }else if(dobArray[1].equalsIgnoreCase("Feb")){
            BDMonth = "02";
        }else if(dobArray[1].equalsIgnoreCase("Mar")){
            BDMonth = "03";
        }else if(dobArray[1].equalsIgnoreCase("Apr")){
            BDMonth = "04";
        }else if(dobArray[1].equalsIgnoreCase("May")){
            BDMonth = "05";
        }else if(dobArray[1].equalsIgnoreCase("Jun")){
            BDMonth = "06";
        }else if(dobArray[1].equalsIgnoreCase("Jul")){
            BDMonth = "07";
        }else if(dobArray[1].equalsIgnoreCase("Aug")){
            BDMonth = "08";
        }else if(dobArray[1].equalsIgnoreCase("Sep")){
            BDMonth = "09";
        }else if(dobArray[1].equalsIgnoreCase("Oct")){
            BDMonth = "10";
        }else if(dobArray[1].equalsIgnoreCase("Nov")){
            BDMonth = "11";
        }else if(dobArray[1].equalsIgnoreCase("Dec")){
            BDMonth = "12";
        }
        String dobString=dobArray[2]+"-"+BDMonth+"-"+dobArray[0];
        LocalDate dob = LocalDate.parse(dobString);

        int yearsOld = Period.between(dob, curDate).getYears();

        //System.out.println(dob);
        String dobConfirm=yearsOld+" year(s) (" +dateOfBirth1.replace("-",".")+ ")";
       softAssert.assertEquals(topSectionDob.getText().trim(), yearsOld);

    softAssert.assertTrue(topSectionID.isDisplayed());
 //   yellowStickyNote.click();
  //  textBox.sendKeys(ConfigReader.getProperty("randomMessage"));
 //   noteCheckMark.click();
    softAssert.assertTrue(successfullyAdded.isDisplayed());
        String trimID = topSectionID.getText().trim();
        data.put("trimID",trimID);

        goHomeBtn.click();
    }

}
