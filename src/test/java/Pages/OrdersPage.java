package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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

    @FindBy(xpath = "//*[@id=\"productAutocompleteResults\"]/li[1]")
    WebElement foundOrder;


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

    public void enterQty(String strArg1) {
        quantityInput.clear();
        quantityInput.sendKeys(strArg1);
    }
}



