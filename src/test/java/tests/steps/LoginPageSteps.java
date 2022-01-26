package tests.steps;

import io.cucumber.java.en.When;
import tests.model.BaseModel;
import tests.pages.LoginPage;
import tests.utils.BaseActions;
import tests.utils.ConfigFileReader;

public class LoginPageSteps {
    private final ConfigFileReader config = ConfigFileReader.getInstance();


    public LoginPageSteps(BaseModel baseModel){
        BaseActions accion = new BaseActions(baseModel);
    }

    private final LoginPage loginPage = new LoginPage();
    private final String url = config.getConfiguracion().getUri() + config.getConfiguracion().getLoginPage();


    @When("^Example$")
    public void iTryToLoginWithValidData() {
    }
}
