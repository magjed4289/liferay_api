package tests.steps;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import lombok.extern.java.Log;
import tests.utils.ConfigFileReader;

@Log
public class Hooks {
    private final ConfigFileReader config = ConfigFileReader.getInstance();



    public Hooks() {
    }

    @Before()
    public void tearUpLocal() {
        RestAssured.baseURI=config.getConfiguration().getUri();
        RestAssured.port = config.getConfiguration().getPort();
    }
}