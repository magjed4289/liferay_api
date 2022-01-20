package tests.model.object.objectDefinition;

import io.restassured.response.Response;
import tests.utils.ConfigFileReader;

import static io.restassured.RestAssured.given;

public class ObjectDefinitionEndpoints {
    private final ConfigFileReader config = ConfigFileReader.getInstance();
    private final String objectDefinitions = "/o/object-admin/v1.0/";

    public ObjectDefinitionEndpoints() {
    }

    public Response createObjectDefinition(ObjectDefinition objectDefinition) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguracion().getEmail(), config.getConfiguracion().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(objectDefinition)
                .when()
                .post(objectDefinitions);
    }
}
