package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.InventoryPage;
import pages.LoginPage;
import utils.WebDriverManager;


public class InventorySteps {

    private final InventoryPage inventoryPage;
    public InventorySteps() {
        WebDriver webDriver = WebDriverManager.getWebDriver();
        this.inventoryPage = new InventoryPage(webDriver);
    }

    @Given("I'm on the Inventory Page")
    public void i_m_on_the_inventory_page() {
        inventoryPage.navigateToRestrictPage(InventoryPage.URL_INVENTORY_PAGE);
        Assertions.assertTrue(inventoryPage.isThere(InventoryPage.URL_INVENTORY_PAGE));
    }
//
//    @When("I Click in Add to Cart")
//    public void i_click_in(String string) {
//
//    }
//
//    @Then("The item should be added to the cart")
//    public void the_item_should_be_added_to_the_cart() {
//
//    }





}
