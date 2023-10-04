package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class LoginPage extends BasePage {

    WebDriverWait wait;
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        PageFactory.initElements(webDriver,this);
    }
    public static final String URL_LOGIN_PAGE = "https://www.saucedemo.com/";

    @FindBy(id = "user-name")
    WebElement usernameLabel;

    @FindBy(id = "password")
    WebElement passwordLabel;

    public void attemptLoginWith(String username, String password) {

        wait.until(ExpectedConditions.urlToBe(URL_LOGIN_PAGE));

        wait.until(ExpectedConditions.visibilityOf(usernameLabel));
        usernameLabel.sendKeys(username);

        wait.until(ExpectedConditions.visibilityOf(passwordLabel));
        passwordLabel.sendKeys(password);
        
        passwordLabel.submit();
    }
}