package tests.model.headlessAdminUser;

import io.restassured.response.Response;
import tests.utils.ConfigFileReader;

import static io.restassured.RestAssured.given;

public class HeadlessAdminUserEndpoints {
    private final ConfigFileReader config = ConfigFileReader.getInstance();
    private final String objectUri = "/o/headless-admin-user/v1.0";

    public HeadlessAdminUserEndpoints() {
    }

    public Response createAccount(String name) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\"name\":\"" + name + "\"}")
                .when()
                .post(objectUri + "/accounts");
    }

    public Response deleteAccount(String accountId) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .delete(objectUri + "/accounts/" + accountId);
    }

    public Response deleteUserAccount(String userAccountId) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .delete(objectUri + "/user-accounts/" + userAccountId);
    }

    public Response createUserAccount(String accountId, UserAccount userAccount) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(userAccount)
                .when()
                .post(objectUri + "/accounts/" + accountId + "/user-accounts");
    }

    public Response updateUserAccount(String userAccountId, UserAccount userAccount) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(userAccount)
                .when()
                .put(objectUri + "/user-accounts/" + userAccountId);
    }
}
