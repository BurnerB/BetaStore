package Pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseClass;

public class OrdersPage extends BaseClass {

    @FindBy(id = "orderForSearch")
    WebElement searchCustomer;

    @FindBy(className = "customerDetails")
    WebElement foundCustomer;

    @FindBy(xpath = "//*[@id=\"order-form\"]/div[1]/fieldset[3]/div/ul/li[1]/div[2]/button")
    WebElement address;

    @FindBy(xpath = "//a[contains(text(),'New Contact')]")
    WebElement newContactLink;


    @FindBy(xpath = "//a[contains(text(),'Deals')]")
    WebElement dealsLink;

    @FindBy(xpath = "//a[contains(text(),'Tasks')]")
    WebElement tasksLink;

    @FindBy(id = "quote-item-search")
    WebElement ordersSearch;

    @FindBy(id = "qty")
    WebElement quantityInput;

    @FindBy(xpath = "//input[@name='price']")
    WebElement itemPrice;

    @FindBy(xpath = "//*[@id=\"productAutocompleteResults\"]/li[1]")
    WebElement foundOrder;

    @FindBy(id = "paymentMethod")
    WebElement paymentSelectDropdown;

    @FindBy(className = "alert alert-success")
    WebElement alert;

    @FindBy(xpath = "//*[@id=\"keyword-filter\"]")
    WebElement orderSearch;

    @FindBy(xpath = "//*[@id=\"orders-quick-filter\"]/li[7]/a")
    WebElement more;



    @FindBy(className = "status-select")
    WebElement orderStatus;

    @FindBy(xpath = "//input[@value='11']")
    WebElement awaitingFulfillment;
    Actions action = new Actions(driver);



    // Initializing the Page Objects:
    public OrdersPage() {
        PageFactory.initElements(driver, this);
    }

    public String verifyHomePageTitle() {
        return driver.getTitle();
    }


    public void searchCustomer(String customer) {
        searchCustomer.sendKeys(customer);
        foundCustomer.click();
    }

    public void selectAddress() {
        address.click();
    }

    public void searchOrder(String strArg1) {
        ordersSearch.sendKeys(strArg1);
        foundOrder.click();
    }

    public void enterQty(String strArg1) throws InterruptedException {
        quantityInput.clear();
        quantityInput.sendKeys(strArg1);

    }

    public void closeDialogueBox() throws InterruptedException {

        Thread.sleep(2000);
        buttonContainsText("Ok").click();
    }

    public String orderTotal() {

        String quantity=  quantityInput.getAttribute("value");
        String price= itemPrice.getAttribute("value");
        int total = Integer.parseInt(quantity) * Integer.parseInt(price);

        return Integer.toString(total);
    }

    public void actualTotal(String strArg1) {
        quantityInput.clear();
        quantityInput.sendKeys(strArg1);
    }

    public void selectPaymentMethod(String strArg1) {
        paymentSelectDropdown.click();
        optionContainsText(strArg1).click();
    }

    public void alertDisplayed() {
        alert.isDisplayed();
    }

    public String grabOrderId() {
        String success = pContainsText("has been created successfully.").getText();

        return success.substring(7, 10);
    }

    public void viewForReview(String strArg1) throws InterruptedException {
        more.click();
        Thread.sleep(2000);
        anchortagContainsText(strArg1).click();
    }

    public void searchOrderByOrderID(String strArg1) {
        orderSearch.isDisplayed();
        orderSearch.sendKeys(strArg1);
        orderSearch.sendKeys(Keys.ENTER);
    }

    public boolean orderStatus(String strArg1) {
//        pContainsText("order matched your search criteria and is shown below.").isDisplayed();
//        orderSearch.sendKeys(Keys.ENTER);
        return optionContainsText(strArg1).isDisplayed();


    }

    public void changeOrderTo(String text) throws InterruptedException {
        orderStatus.click();
        Thread.sleep(200);
//        Assert.assertEquals(text,awaitingFulfillment.getText());
//        action.doubleClick(Outcome).build().perform();
//        Outcome.click();
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }
}



