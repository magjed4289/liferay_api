package tests.model.object.objectRelationship;

import io.restassured.response.Response;
import tests.model.object.objectDefinition.ObjectDefinition;
import tests.utils.ConfigFileReader;

import static io.restassured.RestAssured.given;

public class ObjectRelationshipEndopoints {
    private final ConfigFileReader config = ConfigFileReader.getInstance();
    private final String objectUri = "/o/object-admin/v1.0";

    public ObjectRelationshipEndopoints() {
    }

    public Response createObjectRelationship(String object1id, ObjectRelationshipData objectRelationshipData) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguracion().getEmail(), config.getConfiguracion().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(objectRelationshipData)
                .when()
                .post(objectUri +"/object-definitions/"+object1id+"/object-relationships");
    }

    public Response getObjectRelationships(String object1id) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguracion().getEmail(), config.getConfiguracion().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get(objectUri +"/object-definitions/"+object1id+"/object-relationships");
    }

    public Response deleteObjectRelationship(String relationshipId) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguracion().getEmail(), config.getConfiguracion().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .delete(objectUri +"/object-relationships/"+relationshipId);
    }
}
