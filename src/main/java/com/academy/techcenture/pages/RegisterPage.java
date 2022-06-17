package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;


public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private SoftAssert softAssert;

    public RegisterPage(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.softAssert = softAssert;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);

    }

    @FindBy(className = "logo")
    private WebElement logoIcon;
    @FindBy(name = "givenName")
    private WebElement givenName;
    @FindBy(name = "familyName")
    private WebElement familyName;
    @FindBy(id = "checkbox-unknown-patient")
    private WebElement boxCheck;
    @FindBy(xpath = "//label[@for='checkbox-unknown-patient']")
    private WebElement unidentifiedPatient;
    @FindBy(id = "next-button")
    public WebElement greenBtn;
    @FindBy(id = "gender-field")
    private WebElement genderField;

    @FindBy(id = "birthdateDay-field")
    public WebElement birthday;

    @FindBy(id = "birthdateMonth-field")
    public WebElement birthdayMonth;

    @FindBy(id = "birthdateYear-field")
    public WebElement birthdayYear;

    @FindBy(id = "birthdateYears-field")
    private WebElement estimatedYears;

    @FindBy(id = "birthdateMonths-field")
    private WebElement estimatedMonths;

    @FindBy(id = "address1")
    private WebElement addressOne;

    @FindBy(id = "address2")
    private WebElement addressTwo;

    @FindBy(id = "next-button")
    private WebElement nextBtn;

    @FindBy(id = "cityVillage")
    private WebElement city;

    @FindBy(id = "stateProvince")
    private WebElement state;

    @FindBy(id = "country")
    private WebElement country;

    @FindBy(id = "postalCode")
    private WebElement postalCode;

    @FindBy(name = "phoneNumber")
    private WebElement phoneNumber;
    @FindBy(id = "relationship_type")
    private WebElement patientRelated;
    @FindBy(xpath = "//input[contains(@class,'person-typeahead')]")
    private WebElement personNameRelated;

    public List<WebElement> getConfirmationList() {
        return confirmationList;
    }

    @FindBy(xpath = "//div[@id='dataCanvas']//p")
    private List<WebElement> confirmationList;

    @FindBy(id = "submit")
    private WebElement confirmBtn;

    public void fillOutDemographics(Map<String, String> data) throws InterruptedException {
        softAssert.assertEquals(driver.getTitle(), "OpenMRS Electronic Medical Record");
        givenName.sendKeys(data.get("givenName"));
        familyName.sendKeys(data.get("familyName"));
        greenBtn.click();

        Select select = new Select(genderField);
        select.selectByVisibleText(data.get("gender"));
        greenBtn.click();
        String estimatedYears;
        String estimatedMonths;

       if (data.get("ifDob").equalsIgnoreCase("yes")) {
            String dateOfBirth = data.get("dateOfBirth");
            String dateOfBirthDate = dateOfBirth.substring(0, 2);
           if (((dateOfBirthDate.charAt(0)) + "").equalsIgnoreCase("0")) {
                dateOfBirthDate = dateOfBirth.substring(1);
            }
            birthday.sendKeys(dateOfBirthDate);

            String BDMonth = null;
           String[] birthDateFull = data.get("dateOfBirth").split("-");
           if((birthDateFull[1].trim()).equalsIgnoreCase("Jan")){
               BDMonth = "January";
           }else if(birthDateFull[1].equalsIgnoreCase("Feb")){
               BDMonth = "February";
           }else if(birthDateFull[1].equalsIgnoreCase("Mar")){
               BDMonth = "March";
           }else if(birthDateFull[1].equalsIgnoreCase("Apr")){
               BDMonth = "April";
           }else if(birthDateFull[1].equalsIgnoreCase("May")){
               BDMonth = "May";
           }else if(birthDateFull[1].equalsIgnoreCase("Jun")){
               BDMonth = "June";
           }else if(birthDateFull[1].equalsIgnoreCase("Jul")){
               BDMonth = "July";
           }else if(birthDateFull[1].equalsIgnoreCase("Aug")){
               BDMonth = "August";
           }else if(birthDateFull[1].equalsIgnoreCase("Sep")){
               BDMonth = "September";
           }else if(birthDateFull[1].equalsIgnoreCase("Oct")){
               BDMonth = "October";
           }else if(birthDateFull[1].equalsIgnoreCase("Nov")){
               BDMonth = "November";
           }else if(birthDateFull[1].equalsIgnoreCase("Dec")){
               BDMonth = "December";
           }
            Select selectMonth = new Select(birthdayMonth);
            selectMonth.selectByVisibleText(BDMonth);

            String yearOfBirth = dateOfBirth.substring(data.get("dateOfBirth").length()-4);
            birthdayYear.sendKeys(yearOfBirth);

        } else {
            estimatedYears = data.get("estimatedYears") + "";
            estimatedYears = estimatedYears.substring(0, estimatedYears.indexOf("."));
            this.estimatedYears.sendKeys(estimatedYears);

            estimatedMonths = data.get("estimatedMonths" + "");
            estimatedMonths = estimatedMonths.substring(0, estimatedMonths.indexOf("."));
            this.estimatedMonths.sendKeys(estimatedMonths);
        }
        Thread.sleep(1000);
        greenBtn.click();
    }

    public void fillOutAddress(Map<String, String> data) {
    addressOne.sendKeys(data.get("adress1"));
    addressTwo.sendKeys(data.get("adress2"));
    city.sendKeys(data.get("cityVillage"));
    state.sendKeys(data.get("stateProvince"));
    country.sendKeys(data.get("country"));
    postalCode.sendKeys(data.get("postalCode"));
    greenBtn.click();
    phoneNumber.sendKeys(data.get("phoneNumber"));
    greenBtn.click();
    }

    public void relationships(Map<String,String> data){
        Select select=new Select(patientRelated);
        select.selectByVisibleText(data.get("relationshipType"));
        personNameRelated.sendKeys(data.get("personName"));
        greenBtn.click();
    }
    public void confirm(Map<String,String>data) {
        for(int i=0; i<confirmationList.size();i++) {
            String[] split = confirmationList.get(i).getText().split(":");
            String name = (data.get("givenName") + ", " + data.get("familyName"));
            String gender=data.get("gender");
            String birthDate = null;
            String BDMonth = null;
            if(data.get("ifDob").equalsIgnoreCase("yes")) {
                String[] birthDateFull = data.get("dateOfBirth").split("-");
                if(birthDateFull[1].equalsIgnoreCase("Jan")){
                    BDMonth = "January";
                }else if(birthDateFull[1].equalsIgnoreCase("Feb")){
                    BDMonth = "February";
                }else if(birthDateFull[1].equalsIgnoreCase("Mar")){
                    BDMonth = "March";
                }else if(birthDateFull[1].equalsIgnoreCase("Apr")){
                    BDMonth = "April";
                }else if(birthDateFull[1].equalsIgnoreCase("May")){
                    BDMonth = "May";
                }else if(birthDateFull[1].equalsIgnoreCase("Jun")){
                    BDMonth = "June";
                }else if(birthDateFull[1].equalsIgnoreCase("Jul")){
                    BDMonth = "July";
                }else if(birthDateFull[1].equalsIgnoreCase("Aug")){
                    BDMonth = "August";
                }else if(birthDateFull[1].equalsIgnoreCase("Sep")){
                    BDMonth = "September";
                }else if(birthDateFull[1].equalsIgnoreCase("Oct")){
                    BDMonth = "October";
                }else if(birthDateFull[1].equalsIgnoreCase("Nov")){
                    BDMonth = "November";
                }else if(birthDateFull[1].equalsIgnoreCase("Dec")){
                    BDMonth = "December";
                }

            birthDate=birthDateFull[0].trim()+", "+BDMonth+", "+birthDateFull[2].trim();
            }else {
                String years=data.get("estimatedYears");
                String months=data.get("estimatedMonths");
                birthDate=years.substring(0, years.indexOf("."))+" year(s), "+months.substring(0,months.indexOf("."))+" month(s)";
            }
            String address=data.get("adress1")+", "+data.get("adress2")+", "+data.get("cityVillage")+", "+data.get("stateProvince")+", "+data.get("country")+", "+data.get("postalCode");
            String phone=data.get("phoneNumber").trim();
String relations=data.get("personName")+" - "+data.get("relationshipType").trim();

            String [] verification={name,gender,birthDate,address,phone, relations};

        softAssert.assertEquals(split[1].trim(), verification[i]);


    }
        confirmBtn.click();
}
public void registration(Map<String,String>data) throws InterruptedException {
        fillOutDemographics(data);
        fillOutAddress(data);
        relationships(data);
        confirm(data);
}
}


