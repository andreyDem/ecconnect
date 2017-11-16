package ua.com.ecconnect.tests;

import org.junit.Assert;
import org.junit.Test;
import ua.com.ecconnect.drivers.ChromeSettings;
import ua.com.ecconnect.pages.*;

import static org.junit.Assert.assertEquals;

public class DemoTest extends ChromeSettings {

    @Test
    public void correctCard() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickDemoTestButton();
        FirstProcessingPage first = mainPage.clickLeftButton();
        first.chooseTypeCard();
        first.inputAllFields(mainPage.firstCardInfo(), "someemail@gmail.com");
        SecondProcessingPage second = first.clickSubmittButton();
        second.inputCvc(mainPage.firstCardInfo());
        SuccessPage successPage = second.SubmitButtonChrome();
        successPage.assertMsg();
    }

    @Test
    public void incorrectCard(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickDemoTestButton();
        FirstProcessingPage first = mainPage.clickLeftButton();
        first.chooseTypeCard();
        first.inputAllFields(mainPage.secondCardInfo(), "someemail@gmail.com");
        SecondProcessingPage second = first.clickSubmittButton();
        second.inputCvc(mainPage.secondCardInfo());
        SuccessPage successPage = second.clickSubmitInCorrect();
        second.assertError();
    }

    @Test
    public void returnMain(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickDemoTestButton();
        FirstProcessingPage first = mainPage.clickLeftButton();
        String previousURL = mainPage.getCurrentURL();
        first.clickReturn();
        Assert.assertNotEquals(previousURL, mainPage.getCurrentURL());
    }

    @Test
    public void checkValueGoods() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickDemoTestButton();
        FirstProcessingPage first = mainPage.clickRigthButton();
        first.assertValueRight();
    }

    @Test
    public void checkVisaInfo(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickDemoTestButton();
        FirstProcessingPage first = mainPage.clickLeftButton();
        first.clickVisaInfo();
        first.assertVisaText();
    }

    @Test
    public void inputIncorrectSymbols(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickDemoTestButton();
        FirstProcessingPage first = mainPage.clickLeftButton();
        first.chooseTypeCard();
        String[] symbols = {"wawdaw","13", "1960", "0001"};
        first.inputCardNumber(symbols);
        first.inputEmail("some.gmail.com");
        SecondProcessingPage second = first.clickSubmittButton();
        first.assertErrorCardMsg();
        first.assertErrorEmailMsg();
    }


}
