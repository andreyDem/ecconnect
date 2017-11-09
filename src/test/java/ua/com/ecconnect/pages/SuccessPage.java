package ua.com.ecconnect.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessPage {

    private WebDriver driver;

    public SuccessPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[@class='page-name']")
    private WebElement successMsg;

    public void assertMsg() {
        Assert.assertEquals("No matches found", "Оплата товара прошла успешно", successMsg.getText());
    }




}
