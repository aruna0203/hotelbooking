package org.hotelbooking;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
This class has below tests
1. Add User
2. Delete User
 */


public class TestFixture extends BaseTest {

    /*
    This method will create a new record.
     */
    @Test
    public void createHotelBooking()
    {
        System.out.println("Test Started");
        WebElement firstName = getFirstName();
        WebDriverWait expliicitwait = new WebDriverWait(driver, 10);
        try{
            firstName.sendKeys("ArunaTest");

            WebElement surName = driver.findElement(By.id("lastname"));
            surName.sendKeys("Appam");


            WebElement price = driver.findElement(By.id("totalprice"));
            price.sendKeys("100");

            WebElement deposit = driver.findElement(By.id("depositpaid"));
            Select depositDD = new Select(deposit);
            depositDD.selectByIndex(0);

            WebElement checkIn = driver.findElement(By.id("checkin"));
            checkIn.click();

            WebElement fromDate = driver.findElement(By.cssSelector("td a.ui-state-highlight"));
            fromDate.click();

            WebElement checkOut = driver.findElement(By.id("checkout"));
            checkOut.click();
            WebElement toDate = driver.findElement(By.cssSelector("td a.ui-state-highlight"));
            toDate.click();
            WebElement saveButton = driver.findElement(By.xpath("//input[@type='button'][@value=' Save ']"));
            saveButton.click();
            Thread.sleep(5000);
            WebElement myName = driver.findElement(By.xpath("//div[@class='row']//p[contains(text(),'ArunaTest')]"));
            expliicitwait.until(ExpectedConditions.textToBePresentInElement(myName,"ArunaTest"));
            String getMyname = myName.getText();
            System.out.println("Added user name is : " +getMyname);
            Assert.assertEquals(getMyname, "ArunaTest");
            System.out.println("Assertion Passed");
            System.out.println("Test Passed");

        }catch (Exception error){
            System.out.println("Test Failed,due to this below error,Please find the stack"+error);
        }
    }

    /*
    This method is to Delete a record which is created in the previous test.
     */
    @Test(dependsOnMethods = "createHotelBooking")
    public void deleteUser(){
        System.out.println("Delete User Test Started");
        WebDriverWait expliicitwait = new WebDriverWait(driver, 10);
        try {

            getFirstName();
            driver.navigate().refresh();
            Thread.sleep(5000);
            WebElement myName = driver.findElement(By.xpath("//div[@class='row']//p[contains(text(),'ArunaTest')]"));
            expliicitwait.until(ExpectedConditions.visibilityOf(myName));

            String getMyname = myName.getText();
            System.out.println("User name" +getMyname + "is visible");
            WebElement nameDelete = driver.findElement(By.xpath("//div//p[contains(text(),'ArunaTest')]/parent::div/following-sibling::div//input"));
            nameDelete.click();
            System.out.println("User is deleted successfully");


        }catch (Exception error){
            System.out.println("Test Failed while running the delete user test case with below error,Please find the stack"+error);
        }
    }

    /*
    This method is to scroll down to New record entry fields. It's a reusable method.
     */
    public WebElement getFirstName(){
        WebDriverWait expliicitwait = new WebDriverWait(driver, 10);
        WebElement firstName = driver.findElement(By.id("firstname"));
        expliicitwait.until(ExpectedConditions.elementToBeClickable(firstName));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", firstName);
        return firstName;
    }



}
