package ua.com.ecconnect.drivers;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeSettings {
    protected WebDriver driver;
    
    @Before
    public void setUp() {
//        Optional, if not specified, WebDriver will search your path for chromedriver.
        System.setProperty("webdriver.chrome.driver", "D:\\JAVA\\Browser drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://ecconnect.com.ua");
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
