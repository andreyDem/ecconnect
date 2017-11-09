package ua.com.ecconnect.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class MainPage {
    private String firstCardInfoText;
    private String secondCardInfoText;
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@class='demo-btn']//img[@alt='Тестировать']")
    private WebElement demoTestButton;

    @FindBy(xpath = "//td[@class='adv-table-style0']/div[1]/ul/li[1]/span")
    private WebElement firstCardInfo;

    @FindBy(xpath = "//td[@class='adv-table-style0']/div[1]/ul/li[2]/span[2]")
    private WebElement secondCardInfo;

    @FindBy(css = "td.buy-item.buy-item1 input.submit")
//    @FindBy(xpath = "//td[@class='buy-item buy-item1']//input[@class='submit']")
    private WebElement leftSubmitButton;

    @FindBy(xpath = "//td[@class='buy-item']//input[@class='submit']")
    private WebElement rightSubmitButton;

    public void clickDemoTestButton() {
        demoTestButton.click();
        firstCardInfoText = firstCardInfo.getText();
        secondCardInfoText = secondCardInfo.getText();
    }

    // 0 - card number; 1,2 - validity; 3 - cvv2.
    private String[] getInfoFromString(String card) {
        String[] arr = new String[4];
        arr[0] = card.substring(0, 16);
        for (int i = 0; i < card.length(); i++) {
            if (card.charAt(i) == '/') {
                arr[1] = card.substring(i - 2, i);
                arr[2] = card.substring(i + 1, i + 5);
            }
            if (card.charAt(i) == ')') {
                arr[3] = card.substring(i - 3, i);
            }
        }
        return arr;
    }

    public String[] firstCardInfo() {
        return getInfoFromString(firstCardInfoText);
    }

    public String[] secondCardInfo() {
        return getInfoFromString(secondCardInfoText);
    }

    public FirstProcessingPage clickLeftButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        final String previousURL = driver.getCurrentUrl();
        leftSubmitButton.click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.chord(Keys.CONTROL, Keys.TAB)).perform();
        ArrayList<String> openTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(openTabs.get(1));
        ExpectedCondition e = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return !d.getCurrentUrl().equals(previousURL);
            }
        };
        wait.until(e);
        return new FirstProcessingPage(driver, this);
    }

    public FirstProcessingPage clickRigthButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        final String previousURL = driver.getCurrentUrl();
        rightSubmitButton.click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.chord(Keys.CONTROL, Keys.TAB)).perform();
        ArrayList<String> openTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(openTabs.get(1));
        ExpectedCondition e = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {

                return !d.getCurrentUrl().equals(previousURL);
            }
        };
        wait.until(e);
        return new FirstProcessingPage(driver, this);
    }



}
