package tests.steps;

import io.cucumber.java.en.*;
import tests.model.BaseModel;
import tests.pages.LoginPage;
import tests.utils.BaseActions;
import tests.utils.ConfigFileReader;

public class LoginPageSteps {
    private BaseModel baseModel;
    private BaseActions accion;
    private ConfigFileReader config = ConfigFileReader.getInstance();


    public LoginPageSteps(BaseModel baseModel){
        this.baseModel = baseModel;
        this.accion = new BaseActions(baseModel);
    }

    private LoginPage loginPage = new LoginPage();
    private String url = config.getConfiguracion().getUri() + config.getConfiguracion().getLoginPage();


    @When("^Example$")
    public void iTryToLoginWithValidData() {
        System.out.println("Example");
    }
}
