package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.utils.ConfigFileReader;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage extends BasePage {
    private final ConfigFileReader config = ConfigFileReader.getInstance();
    private final String url = config.getConfiguracion().getUri() + config.getConfiguracion().getLoginPage();


    @FindBy(how = How.ID, using = "username")
    public WebElement usernameBox;

    @FindBy(how = How.ID, using = "password")
    public WebElement passwordBox;

    @FindBy(how = How.CLASS_NAME, using = "ikss-btn-login")
    public WebElement loginBtn;

    @FindBy(how = How.CLASS_NAME, using = "ikss-div-error")
    public WebElement alertMsg;

    @FindBy(how = How.CSS, using = "body > div > img")
    public WebElement imageContainer;


    public void login(){
        driver.navigate().to("http://localhost:8080/home");
    }

    public void errorMessage(String message) {
        assertEquals(alertMsg.getText(),message,wait.until(ExpectedConditions.visibilityOf(alertMsg)).getText());
    }

    public void errorPage() {
        assertEquals(config.getConfiguracion().getUri()+"/ik-ss/img/error.png", wait.until(ExpectedConditions.visibilityOf(imageContainer)).getAttribute("src"));
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void getContentOfUserPage() {
        assertEquals(driver.getCurrentUrl(), config.getConfiguracion().getUri()+"/ik-ss/user/me");
        String pageSource = driver.getPageSource();
        assertAll(
                () -> assertTrue(pageSource.contains("correo@gmail.com"),"1"),
                () -> assertTrue(pageSource.contains("VALIDATED"),"2"),
                () -> assertTrue(pageSource.contains("#123"), "3"),
                () -> assertTrue(pageSource.contains("role"), "4"),
                () -> assertTrue(pageSource.contains("Mouse"), "5"),
                () -> assertTrue(pageSource.contains("donaldDuck"), "6"),
                () -> assertTrue(pageSource.contains("Mickey"), "7"));
    }

    public void checkRedirectionWhenLogout() {
        assertEquals("http://192.168.242.103:8880/ik-ss/custom-logout",driver.getCurrentUrl());
    }
}
