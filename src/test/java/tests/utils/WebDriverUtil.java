package tests.utils;

import io.cucumber.java.Scenario;
import lombok.extern.java.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

@Log
public class WebDriverUtil {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions action;
    private static JavascriptExecutor js;

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public Actions getAction() {
        return action;
    }

    public JavascriptExecutor getJavascript() {
        return js;
    }

    public void tearUpLocal() {
        String version = System.getProperty("chromedriver.version");
        System.setProperty("webdriver.chrome.driver", "/usr/local/Caskroom/chromedriver/96.0.4664.45/chromedriver");

        Map<String, Object> commandParams = new HashMap<>();
        commandParams.put("cmd", "Page.setDownloadBehavior");

        HashMap<String, Object> chromePrefs = new HashMap<>();


        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
        chromePrefs.put("behaviour", "allow");
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "/src/test/resources/downloads/");
        chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
        chromePrefs.put("download.prompt_for_download", false);
        commandParams.put("params", chromePrefs);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        options.addArguments("--headless");
        options.addArguments("--no-sandbox", "--window-size=1280,960", "--ignore-certificate-errors");
        options.addArguments("--disable-extensions", "--test-type", "--disable-infobars", "--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20, 1000);
        action = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Toma una captura...
        }
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
            driver = null;
        }
        log.info("This will run after Test");
    }

}