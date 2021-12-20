package tests.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.utils.WebDriverUtil;

class BasePage {
    final WebDriver driver;
    final WebDriverWait wait;
    final JavascriptExecutor js;
    final Actions actions;

    BasePage() {
        WebDriverUtil webDriverUtil = new WebDriverUtil();
        this.driver = webDriverUtil.getDriver();
        this.wait = webDriverUtil.getWait();
        this.actions = webDriverUtil.getAction();
        this.js = webDriverUtil.getJavascript();
        PageFactory.initElements(driver, this);
    }
}
