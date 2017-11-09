package ua.com.ecconnect.tests;

import org.junit.Test;
import ua.com.ecconnect.drivers.FirefoxSettings;
import ua.com.ecconnect.pages.*;

public class DemoVersionTest extends FirefoxSettings {

    @Test
    public void testing() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickDemoTestButton();
//        mainPage.firstCardInfo();
        FirstProcessingPage first = mainPage.clickLeftButton();
        first.chooseTypeCard();
        first.inputAllFields();
        SecondProcessingPage second = first.clickSubmittButton();
        second.inputCvc();
        SuccessPage successPage = second.clickSubmitButton();
    }

    
}
