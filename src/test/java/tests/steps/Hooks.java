package tests.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import lombok.extern.java.Log;
import tests.utils.ConfigFileReader;
import tests.utils.WebDriverUtil;

@Log
public class Hooks {
    private final WebDriverUtil webDriverUtil;
    private final ConfigFileReader config = ConfigFileReader.getInstance();



    public Hooks() {
        webDriverUtil = new WebDriverUtil();
    }

    @Before()
    public void tearUpLocal() {
        log.info("This will run in the beginning of Web Test");
        //webDriverUtil.tearUpLocal();
        RestAssured.baseURI=config.getConfiguracion().getUri();
        RestAssured.port = config.getConfiguracion().getPort();
    }

    @After()
    public void deleteCookiesAndDestroy(Scenario scenario) {
        webDriverUtil.tearDown(scenario);
    }
}