package Steps;

import java.io.IOException;

import Pages.LoginPage;
import Pages.OrdersPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.BaseClass;


public class steps extends BaseClass {

    public static sharedatastep sharedata;
    LoginPage loginPage;
    OrdersPage ordersPage;


    public steps(sharedatastep sharedata) throws Exception {
        steps.sharedata = sharedata;
    }

    @Before()
    public void setUp() throws IOException {
        initialization();
        loginPage = new LoginPage();
        ordersPage = new OrdersPage();
    }

    @Given("^User navigates to the page \"([^\"]*)\"$")
    public void user_navigates_to_the_page_something(String strArg1) throws Throwable {
        LoginPage.runURL(strArg1);
    }

    @When("^user clicks forgot password link \"([^\"]*)\"$")
    public void user_clicks_forgot_password_link_something(String strArg1) throws Throwable {
        anchortagContainsText(strArg1).click();
    }

    @And("^enters email \"([^\"]*)\" and clicks reset$")
    public void enters_email_something_and_clicks_reset(String strArg1) throws Throwable {
        loginPage.pswdReset(strArg1);
    }

    @When("^Enters the username \"([^\"]*)\" and password \"([^\"]*)\" to login$")
    public void enters_the_username_something_and_password_something_to_login(String strArg1, String strArg2) throws Throwable {
        String title = loginPage.validatePageTitle();
        org.testng.Assert.assertEquals(title, "Log In - BigCommerce");
        loginPage.login(strArg1,strArg2);

    }

    @Then("^user should be prompted for OTP \"([^\"]*)\"$")
    public void user_should_be_prompted_for_otp_something(String strArg1) throws Throwable {
        WebElement msg=loginPage.validatePageText(strArg1);
        Assert.assertTrue(msg.isDisplayed());
    }

    @And("^user opens a new tab$")
    public void user_opens_a_new_tab() throws Throwable {
        BaseClass.openAndMoveToTab();
    }

    @And("^user switches to first tab$")
    public void user_switches_to_first_tab() throws Throwable {
        switchToFirstTab();
    }

    @When("^user navigates to email and logs in in with credentials \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void user_navigates_to_email_and_logsi_in_with_credentials_something_and_password_something(String strArg1, String strArg2) throws Throwable {
        String title = loginPage.validatePageTitle();
        org.testng.Assert.assertEquals(title, "Gmail");

        loginPage.gmaillogin(strArg1,strArg2);
    }

    @Then("^User should be able to access their email$")
    public void user_should_be_able_to_access_their_email() throws Throwable {
        Thread.sleep(3000);
        WebElement OTPemail= loginPage.gmailValdation();
        Assert.assertTrue(OTPemail.isDisplayed());

    }
    @When("^user clicks email and grabs the otp generated$")
    public void user_clicks_email_and_grabs_the_otp_generated() throws Throwable {
//      click otp email and print out total number of unread emails
        System.out.println("Total No. of Unread Mails: " + loginPage.clickEmail());

        String OTP=loginPage.getOTP("No, this wasn’t me – secure my account.");

        sharedatastep.OTPcode = OTP;
        System.out.println(sharedatastep.OTPcode);

    }

    @Then("User should be loggedin")
    public void userShouldBeLoggedin() throws InterruptedException {
        String title = loginPage.validatePageTitle();
        org.testng.Assert.assertEquals(title, "SimpleMarket Test - BigCommerce Control Panel");
    }

    @Then("^message should be displayed \"([^\"]*)\"$")
    public void message_should_be_displayed_something(String strArg1) throws Throwable {
        Assert.assertTrue(divContainsText(strArg1).isDisplayed());
    }

    @And("^User enters the otp code and clicks verify$")
    public void user_enters_the_otp_code_and_clicks_verify() throws Throwable {
        loginPage.inputOTP(sharedatastep.OTPcode);
    }

    //---------------------------------------------------------------Orders----------------------------------------------------------------------------------//
    @Given("^user validates the sidebar fields$")
    public void user_validates_the_sidebar_fields() throws Throwable {
        spanContainsText("View Store").isDisplayed();
        spanContainsText("Home").isDisplayed();
        spanContainsText("Orders").isDisplayed();
        spanContainsText("Products").isDisplayed();
        spanContainsText("Customers").isDisplayed();
        spanContainsText("Storefront").isDisplayed();
        spanContainsText("Marketing").isDisplayed();
        spanContainsText("Analytics").isDisplayed();
        spanContainsText("Apps").isDisplayed();
        spanContainsText("Store Setup").isDisplayed();
        spanContainsText("Server Settings").isDisplayed();
        spanContainsText("Account Settings").isDisplayed();
        spanContainsText("My Profile").isDisplayed();
    }

    @Given("^User clicks on orders$")
    public void user_clicks_on_orders() throws Throwable {
        spanContainsText("Orders").click();

        spanContainsText("View").isDisplayed();
        anchortagContainsText("Add").isDisplayed();
        anchortagContainsText("Search").isDisplayed();
        anchortagContainsText("Export").isDisplayed();

    }

    @When("^clicks Add orders$")
    public void clicks_add_orders() throws Throwable {
        anchortagContainsText("Add").click();
    }

    @Then("^\"([^\"]*)\" page is displayed$")
    public void something_page_is_displayed(String strArg1) throws Throwable {
        h1ContainsText(strArg1).isDisplayed();
    }

    @And("^switch to content frame$")
    public void switch_to_content_frame() throws Throwable {
        driver.switchTo().frame("content-iframe");
    }
    @When("^user selects order for Existing Customer$")
    public void user_selects_order_for_existing_customer() throws Throwable {
        WebElement existingCustomer=driver.findElement(By.id("check-search-customer"));
        existingCustomer.click();
    }

    @Then("^customer billing address should be listed$")
    public void customer_billing_address_should_be_listed() throws Throwable {
        buttonContainsText("Use this address");
    }

    @And("^searches for customer \"([^\"]*)\"$")
    public void searches_for_customer_something(String strArg1) throws Throwable {
        ordersPage.searchCustomer(strArg1);

    }

    @When("^user selects the address to be used$")
    public void user_selects_the_address_to_be_used() throws Throwable {
        ordersPage.selectAddress();

    }

    @And("^clicks next button$")
    public void clicks_next_button() throws Throwable {
        buttonContainsText("Next").click();
    }

    @And("^switch to default frame$")
    public void switch_to_default_frame() throws Throwable {
        driver.switchTo().defaultContent();
    }

    @Then("^navigated to \"([^\"]*)\"$")
    public void navigated_to_something(String strArg1) throws Throwable {
        spanContainsText(strArg1).isDisplayed();

    }

    @When("^user searches for orders \"([^\"]*)\"$")
    public void user_searches_for_orders_something(String strArg1) throws Throwable {
        ordersPage.searchOrder(strArg1);
    }

    @And("^enters quantity \"([^\"]*)\"$")
    public void enters_quantity_something(String strArg1) throws Throwable {
        ordersPage.enterQty(strArg1);
    }
}