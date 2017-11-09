package ua.com.ecconnect.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstProcessingPage {

    private WebDriver driver;
    private MainPage mainPage;
    private String emailTest = "someone@gmail.com";

    public FirstProcessingPage(WebDriver driver, MainPage mainPage) {
        PageFactory.initElements(driver, this);
        this.mainPage = mainPage;
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id='paymentTypeCard']")
    private WebElement paymentTypeCard;

    @FindBy(xpath = "//input[@name='CardNumber']")
    private WebElement cardNumber;

    @FindBy(xpath = "//select[@name='ExpMonth']")
    private WebElement month;

    @FindBy(xpath = "//select[@name='ExpYear']")
    private WebElement year;

    @FindBy(xpath = "//input[@name='Email']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='cardDataSubmit']")
    private WebElement submitButton;

    @FindBy(xpath = "//tr[@class='labeldata']/td[1]/a")
    private WebElement retreat;

    public void clickRetreat(){
        retreat.click();
    }

    public void chooseTypeCard() {
        paymentTypeCard.click();
    }


    public void inputCardNumber() {
        cardNumber.click();
        cardNumber.clear();
        cardNumber.sendKeys(mainPage.getInfoFromString(mainPage.firstCardInfo())[0]);
    }

    public void inputMonth() {
        Select select = new Select(month);
        select.selectByVisibleText(mainPage.getInfoFromString(mainPage.firstCardInfo())[1]);
    }

    public void inputYear() {
        Select select = new Select(year);
        select.selectByVisibleText(mainPage.getInfoFromString(mainPage.firstCardInfo())[2]);
    }

    public void inputEmail() {
        email.click();
        email.clear();
        email.sendKeys(emailTest);
    }

    public void inputAllFields() {
        inputCardNumber();
        inputMonth();
        inputYear();
        inputEmail();
    }

    public SecondProcessingPage clickSubmittButton() {
        submitButton.click();
        return new SecondProcessingPage(driver, mainPage);
    }
}
