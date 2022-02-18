package tests.model.object.objectAction;

import io.restassured.response.Response;
import tests.utils.ConfigFileReader;

import static io.restassured.RestAssured.given;

public class ObjectActionEndpoints {

    public ObjectActionEndpoints() {
    }

    private final ConfigFileReader config = ConfigFileReader.getInstance();
    private final String objectUri = "/o/object-admin/v1.0";

    public Response postObjectDefinitionObjectAction(ObjectActionCreator objectActionCreator, Integer objectId) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(objectActionCreator)
                .when()
                .post(objectUri +"/object-definitions/"+objectId+"/object-actions");
    }

    public Response updateObjectDefinitionObjectAction(ObjectActionCreator objectActionCreator, String objectActionId) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(objectActionCreator)
                .when()
                .put(objectUri +"/object-actions/"+objectActionId);
    }

    public Response getDefinitionObjectActions(Integer objectId) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get(objectUri +"/object-definitions/"+objectId+"/object-actions");
    }

    public Response deleteObjectAction(String objectId) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .delete(objectUri +"/object-actions/"+objectId);
    }
}
