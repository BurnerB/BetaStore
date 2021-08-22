package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//Base class to allow access to browser from hooks
public class BaseClass {
    //local variable that gets assigned below after properties class is instantiated
    public static WebDriver driver;
    public static WebDriver prop;


    public static void initialization() throws IOException {

        //Initiate browser and link
        System.setProperty("webdriver.chrome.driver", "./Browsers/chromedriver_92");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //Load properties
        Properties prop = new Properties();
        FileInputStream fls = new FileInputStream("./src/test/resources/Objects/object.properties");
        prop.load(fls);

    }

    public static void openAndMoveToTab(){
        //Open new tab
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.open()");

        //Switch to new tab
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public static void switchToFirstTab() {
        //Switch to first tab
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    public WebElement anchortagContainsText(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" +text+ "')]")));
    }

    public WebElement h1ContainsText(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'" +text+ "')]")));
    }


    public WebElement optionContainsText(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[contains(text(),'" +text+ "')]")));
    }

    public WebElement buttonContainsText(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'" +text+ "')]")));
    }

    public WebElement divContainsText(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+text+"')]")));
    }

    public WebElement spanContainsText(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+text+"')]")));
    }

    public WebElement pContainsText(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'"+text+"')]")));
    }

    public void scrollIntoView(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void byPassPrivateConnCheck(WebDriver obj) {
        obj.findElement(By.xpath("/html/body/div/div[2]/button[3]")).click();
        obj.findElement(By.xpath("/html/body/div/div[3]/p[2]/a")).click();
    }

    public static void clickAnywhereOnPage() {
        Actions actions = new Actions(driver);
        actions.click().build().perform();
    }
    public static void pressEscape() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
    }






    public static String randomDate() {

        LocalDate from = LocalDate.of(2000, 1, 1);
        LocalDate to = LocalDate.of(2017, 1, 1);
        long days = from.until(to, ChronoUnit.DAYS);
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
        LocalDate randomDate = from.plusDays(randomDays);
        return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String todaysDate() {
        LocalDate today = LocalDate.now();
        return today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    }

    public static String tomorrowsDate() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        return tomorrow.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    }

    public static void waitForPageToLoad()
    {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getRandom(int n)
    {

        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);
        String randomString
                = new String(array, StandardCharsets.UTF_8);
        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();
        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);
            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (n > 0)) {
                r.append(ch);
                n--;
            }
        }
        // return the resultant string
        return r.toString();
    }
}