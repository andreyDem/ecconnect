package ua.com.ecconnect.pages;

        import org.junit.Assert;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;
        import org.openqa.selenium.support.ui.Select;

public class FirstProcessingPage {

    private WebDriver driver;
    private MainPage mainPage;

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
    private WebElement emailLocator;

    @FindBy(xpath = "//input[@id='cardDataSubmit']")
    private WebElement submitButton;

    @FindBy(xpath = "//tr[@class='labeldata']/td[1]/a")
    private WebElement retreat;

    @FindBy(xpath = "//img[@src='/go/images/vbv.gif']")
    private WebElement visaInfo;

    @FindBy(xpath = "//b[@class='smalltext']")
    private WebElement helpInfo;

    @FindBy(xpath = "//tr[@class='labeldata'][8]/td[2]/span[@class='V_message']")
    private WebElement errorCardMessage;

    @FindBy(xpath = "//tr[@class='labeldata'][10]/td[2]/span[@class='V_message']")
    private WebElement errorEmailMessage;

    public void clickReturn(){
        retreat.click();
    }

    public void chooseTypeCard() {
        paymentTypeCard.click();
    }


    public void inputCardNumber(String[] cardInfo) {
        cardNumber.click();
        cardNumber.clear();
        cardNumber.sendKeys(cardInfo[0]);
    }

    public void inputMonth(String[] cardInfo) {
        Select select = new Select(month);
        select.selectByVisibleText(cardInfo[1]);
    }

    public void inputYear(String[] cardInfo) {
        Select select = new Select(year);
        select.selectByVisibleText(cardInfo[2]);
    }

    public void inputEmail(String email) {
        emailLocator.click();
        emailLocator.clear();
        emailLocator.sendKeys(email);
    }

    public void inputAllFields(String[] cardInfo, String email) {
        inputCardNumber(cardInfo);
        inputMonth(cardInfo);
        inputYear(cardInfo);
        inputEmail(email);
    }

    public SecondProcessingPage clickSubmittButton() {
        submitButton.click();
        return new SecondProcessingPage(driver, mainPage);
    }

    public void clickVisaInfo(){
        String winHandleBefore = driver.getWindowHandle();
        visaInfo.click();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
    }

    public void assertVisaText(){
        Assert.assertEquals("ДОПОМОГА", helpInfo.getText());
    }

    public void assertErrorCardMsg(){
        Assert.assertEquals("Некоректні символи у номері карти", errorCardMessage.getText());
    }

    public void assertErrorEmailMsg(){
        Assert.assertEquals("Некоректний E-mail", errorEmailMessage.getText());
    }


}
