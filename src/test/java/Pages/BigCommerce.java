package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.LaunchWebDriver;

public class BigCommerce {
//    private static Faker faker = new Faker();

    public static String enterUserName(WebDriver driver, String uName, String path) throws InterruptedException {
        driver.findElement(By.id(LaunchWebDriver.returnBillManagerPropertyValue(path))).sendKeys(LaunchWebDriver.returnBillManagerPropertyValue(uName));
        Thread.sleep(1000);
        return null;
    }

    public static String enterCorrectPass(WebDriver driver, String pass, String path) throws InterruptedException {
        driver.findElement(By.id(LaunchWebDriver.returnBillManagerPropertyValue(path))).sendKeys(LaunchWebDriver.returnBillManagerPropertyValue(pass));
        Thread.sleep(1000);
        return null;
    }

    public static void clicButtons(WebDriver driver, String path) throws InterruptedException {
        driver.findElement(By.xpath(LaunchWebDriver.returnBillManagerPropertyValue(path))).click();
        Thread.sleep(5000);
    }

}
