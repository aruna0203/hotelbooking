package org.hotelbooking;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.internal.misc.FileSystemOption;
import jdk.jfr.Timespan;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;

    /*
    This method will initialize the Chrome browser and then opens the url
     */

    @BeforeMethod
    public void testInit()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("http://hotel-test.equalexperts.io/");
    }

    /*
    This method will close and quit the opened browsers
     */
    @AfterMethod
    public void tearDown()
    {
        driver.close();
        driver.quit();
    }


}
