package tests.steps;

import io.cucumber.java.en.*;
import tests.model.BaseModel;
import tests.utils.BaseActions;
import tests.utils.ConfigFileReader;

import static io.restassured.RestAssured.given;

public class GeneralSteps{
	
	private final ConfigFileReader config = ConfigFileReader.getInstance();
	private final String url = config.getConfiguracion().getUri();

	public GeneralSteps(BaseModel baseModel) {
		BaseActions accion = new BaseActions(baseModel);
	}

}
