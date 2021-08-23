package Pages;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class OrdersPage extends BaseClass {

    @FindBy(id = "orderForSearch")
    WebElement searchCustomer;

    @FindBy(className = "customerDetails")
    WebElement foundCustomer;

    @FindBy(xpath = "//*[@id=\"order-form\"]/div[1]/fieldset[3]/div/ul/li[1]/div[2]/button")
    WebElement address;

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


//    TODO -MAKE MORE EFFICIENT
    public static void searchOrdersExcel(String strArg1) throws InterruptedException {
//        enter data from excel
        driver.findElement(By.id("quote-item-search")).clear();
        driver.findElement(By.id("quote-item-search")).sendKeys(strArg1);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"productAutocompleteResults\"]/li[1]")).click();
        clickAnywhereOnPage();

//        enter random quantity to each order
//        Thread.sleep(2000);
//        driver.findElement(By.id("qty")).clear();
//        Thread.sleep(2000);
//        loop through each quantity if order searched
//        List<WebElement> products=  driver.findElements(By.id("qty"));
//        for (WebElement element : products) {
//            if (element.isDisplayed()) {
//                element.sendKeys(String.valueOf(randomNumbers(1, 5)));
//            }
//        }


    }

    public void searchOrder(String strArg1) throws InterruptedException {
        ordersSearch.clear();
        ordersSearch.sendKeys(strArg1);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"productAutocompleteResults\"]/li[1]")).click();
        clickAnywhereOnPage();

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
        return optionContainsText(strArg1).isDisplayed();


    }

    //    TODO -MAKE MORE EFFICIENT
    public void changeOrderTo(String text) throws InterruptedException {
        orderStatus.click();
        Thread.sleep(200);
//        Assert.assertEquals(text,awaitingFulfillment.getText());
//        action.doubleClick(Outcome).build().perform();
//        Outcome.click();
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    //    TODO -MAKE MORE EFFICIENT
    public static void readExcel(String excel) throws IOException, InterruptedException {

        FileInputStream inputstream = new FileInputStream(excel);

        XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
        // GET worksheet if more than one
        XSSFSheet sheet = workbook.getSheetAt(0);

        ////  REMINDER LOOK FOR BETTER ITERATION
        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(1).getLastCellNum();

        //loop through row and then cell in column
        for (int r = 1; r <= rows; r++) {
            XSSFRow row = sheet.getRow(r);
            for (int c = 0; c < cols; c++) {
                XSSFCell cell = row.getCell(c);
                //       check if cell contains data in string and not blank
                if (cell.getCellType() != CellType.BLANK && cell.getCellType() == CellType.STRING ) {
                    System.out.println(cell.getStringCellValue());
                    searchOrdersExcel(cell.getStringCellValue());

                }
            }
        }
    }
}



