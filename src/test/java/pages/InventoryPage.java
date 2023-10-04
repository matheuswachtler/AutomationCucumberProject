package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver,this);
    }

    public static final String URL_INVENTORY_PAGE = "https://www.saucedemo.com/inventory.html";

}
