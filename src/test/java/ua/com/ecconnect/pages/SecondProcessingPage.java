package ua.com.ecconnect.pages;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @FindBy(xpath = "//table[@class='messageboxERROR']/tbody/tr[1]/td[2]")
    private WebElement errorMsg;

    public void inputCvc(String[] cardInfo){
        fieldCvc.click();
        fieldCvc.clear();
        fieldCvc.sendKeys(cardInfo[3]);
    }

    public SuccessPage clickSubmitButtonCorrect(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        submitButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return new SuccessPage(driver);
    }

    public SuccessPage clickSubmitInCorrect(){
        submitButton.click();
        return new SuccessPage(driver);
    }

    public void assertError(){
        Assert.assertEquals("Frame not found", "Відхилено банком емітентом", errorMsg.getText());
    }
}
