package pages;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Set;

public class BasePage {
    private final WebDriver webDriver;
    private static Set<Cookie> cookies;
    private static final String COOKIES_FILE = "cookies.json";
    private static final String BASE_URL = "https://www.saucedemo.com/";


    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    //Cookies Manager

    public void getCookies() {
        cookies = webDriver.manage().getCookies();
    }

    public void saveCookiesToFile() {
        Gson gson = new Gson();
        String cookiesJson = gson.toJson(cookies);

        try (FileWriter fileWriter = new FileWriter(COOKIES_FILE)) {
            fileWriter.write(cookiesJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAndAddCookies() {
        Gson gson = new Gson();
        Type cookieSetType = new TypeToken<Set<Cookie>>() {}.getType();

        try (FileReader fileReader = new FileReader(COOKIES_FILE)) {
            Set<Cookie> loadedCookies = gson.fromJson(fileReader, cookieSetType);
            for (Cookie cookie : loadedCookies) {
                webDriver.manage().addCookie(cookie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Common methods

    public void navigate(String url) {
        webDriver.get(url);
    }

    public void navigateToRestrictPage(String url){
        webDriver.get(BASE_URL);
        loadAndAddCookies();
        webDriver.get(url);
    }

    public boolean isThere(String url) {
        return webDriver.getCurrentUrl().equals(url);
    }

    public boolean findTextInPageSource(String data) {
        return webDriver.getPageSource().contains(data);
    }

    public String convertNullValueToEmptyString(String data) {
        if (data.equals("null_value")) {
            data = "";
            return data;
        }
        return data;
    }


}
