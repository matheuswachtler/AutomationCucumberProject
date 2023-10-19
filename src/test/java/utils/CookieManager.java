package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Set;

public class CookieManager {

    private static final String COOKIES_FILE = "cookies.json";

    public static Set<Cookie> captureCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public static void saveCookiesToFile(Set<Cookie> cookies) {
        Gson gson = new Gson();
        String cookiesJson = gson.toJson(cookies);

        try (FileWriter fileWriter = new FileWriter(COOKIES_FILE)) {
            fileWriter.write(cookiesJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Cookie> loadCookiesFromFile() {
        Gson gson = new Gson();
        Type cookieSetType = new TypeToken<Set<Cookie>>() {}.getType();

        try (FileReader fileReader = new FileReader(COOKIES_FILE)) {
            return gson.fromJson(fileReader, cookieSetType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addCookiesToWebDriver(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
    }
}