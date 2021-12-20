package tests.steps;

import io.cucumber.java.en.*;
import tests.model.BaseModel;
import tests.utils.BaseActions;
import tests.utils.ConfigFileReader;

import static io.restassured.RestAssured.given;

public class GeneralSteps{
	
	private ConfigFileReader config = ConfigFileReader.getInstance();
	private String url = config.getConfiguracion().getUri();
	private BaseModel baseModel;
	private BaseActions accion;

	public GeneralSteps(BaseModel baseModel) {
		this.baseModel = baseModel;
		this.accion = new BaseActions(baseModel);
	}

	@Given("^el servicio API está levantado$")
	public void elServicioAPIEstáLevantado() {
		given().when().get(url).then().statusCode(200);
	}

}
