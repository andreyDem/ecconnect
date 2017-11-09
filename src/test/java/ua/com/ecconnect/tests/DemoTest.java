package ua.com.ecconnect.tests;

import org.junit.Test;
import ua.com.ecconnect.drivers.FirefoxSettings;
import ua.com.ecconnect.pages.*;

public class DemoTest extends FirefoxSettings {

    @Test
    public void correctCard() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickDemoTestButton();
        FirstProcessingPage first = mainPage.clickLeftButton();
        first.chooseTypeCard();
        first.inputAllFields(mainPage.firstCardInfo(), "someemail@gmail.com");
        SecondProcessingPage second = first.clickSubmittButton();
        second.inputCvc(mainPage.firstCardInfo());
        SuccessPage successPage = second.clickSubmitButtonCorrect();
        successPage.assertMsg();
    }

    @Test
    public void incorectCard(){
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
        first.clickReturn();
    }

    
}
