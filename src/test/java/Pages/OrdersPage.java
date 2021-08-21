package Pages;

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

    // Initializing the Page Objects:
    public OrdersPage() {
        PageFactory.initElements(driver, this);
    }

    public String verifyHomePageTitle(){
        return driver.getTitle();
    }


    public void searchCustomer(String customer){
        searchCustomer.sendKeys(customer);
        foundCustomer.click();
    }


    public void selectAddress() {
        address.click();
    }
}

