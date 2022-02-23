package tests.model.graphQL;

import io.restassured.response.Response;
import tests.utils.ConfigFileReader;

import static io.restassured.RestAssured.given;

public class GraphQLEndpoint {

    private final ConfigFileReader config = ConfigFileReader.getInstance();

    public GraphQLEndpoint() {
    }

    public Response graphQLQuery(GraphQLQuery query) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(query)
                .when()
                .post("/o/graphql");
    }
}
