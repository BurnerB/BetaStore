package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class LoginPage extends BaseClass {
    //Page Factory - OR:
    @FindBy(id = "user_email")
    WebElement username;

    @FindBy(id = "user_password")
    WebElement password;

    @FindBy(xpath = "//input[@value='Log in']")
    WebElement loginBtn;

    @FindBy(id = "identifierId")
    WebElement gmailUsername;

    @FindBy(xpath = "//input[@type='password']")
    WebElement gmailPassword;

    @FindBy(xpath = "//span[contains(text(),'Next')]")
    WebElement nextBtn;

    @FindBy(className = "zE")
    WebElement OTPemail;

    @FindBy(className = "zE")
    List<WebElement> unreademails;

    @FindBy(xpath = "//span[@style='font-size:40px;line-height:200px']")
    WebElement OTPcode;

    @FindBy(xpath = "//*[@id='device_verification_otp_code']")
    WebElement inputOTP;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/form/div/div/div/div[6]/span/input")
    WebElement verifyOTPbutton;

    @FindBy(xpath = "//input[@value='Email me reset link']")
    WebElement resetButton;





    WebDriverWait wait = new WebDriverWait(driver, 10);
    //Initializing the Page Objects:
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    //Actions:
    public static void runURL(String url){
        driver.get(url);
    }
    public String validatePageTitle() {
        return driver.getTitle();
    }

    public void login(String un, String pwd) {
        username.sendKeys(un);
        password.sendKeys(pwd);
        //loginBtn.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", loginBtn);
    }

    public void gmaillogin(String un, String pwd) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        gmailUsername.sendKeys(un);
        //loginBtn.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", nextBtn);

        Thread.sleep(3000);
        gmailPassword.sendKeys(pwd);

        js.executeScript("arguments[0].click();", nextBtn);
    }

    public WebElement gmailValdation(){
        return OTPemail;

    }

    public WebElement validatePageText( String strArg1){
        return divContainsText(strArg1);
    }

    public int clickEmail(){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        unreademails.get(0).click();

        return unreademails.size();
    }

    public String getOTP(String text) {
        WebElement element = anchortagContainsText(text);

        scrollIntoView(element);

        return OTPcode.getText();

    }

    public void inputOTP(String text) {
        inputOTP.sendKeys(text);
        verifyOTPbutton.click();


    }

    public void pswdReset(String strArg1) throws InterruptedException {
        username.click();
        Thread.sleep(3000);
        username.sendKeys(strArg1);
        resetButton.click();
    }

}
