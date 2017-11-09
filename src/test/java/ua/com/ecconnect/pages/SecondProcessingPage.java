package ua.com.ecconnect.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class SecondProcessingPage {

    private WebDriver driver;
    private MainPage mainPage;

    public SecondProcessingPage(WebDriver driver, MainPage mainPage) {
        PageFactory.initElements(driver, this);
        this.mainPage = mainPage;
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@name='Cvc']")
    private WebElement fieldCvc;

    @FindBy(xpath = "//input[@id='CVCSubmit']")
    private WebElement submitButton;

    public void inputCvc(){
        fieldCvc.click();
        fieldCvc.clear();
        fieldCvc.sendKeys(mainPage.getInfoFromString(mainPage.firstCardInfo())[3]);
    }

    public SuccessPage clickSubmitButton(){
        submitButton.click();
        return new SuccessPage(driver);
    }
}
