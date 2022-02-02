package tests.model.object.objectDefinition;

import io.restassured.response.Response;
import tests.model.object.objectDefinition.customObjects.Employee;
import tests.model.object.objectDefinition.customObjects.Manager;
import tests.utils.ConfigFileReader;

import static io.restassured.RestAssured.given;

public class ObjectDefinitionEndpoints {
    private final ConfigFileReader config = ConfigFileReader.getInstance();
    private final String objectUri = "/o/object-admin/v1.0";

    public ObjectDefinitionEndpoints() {
    }

    public Response createObjectDefinition(ObjectDefinition objectDefinition) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(objectDefinition)
                .when()
                .post(objectUri +"/object-definitions");
    }
    public Response publishObjectDefinition(String id) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .post(objectUri +"/object-definitions/"+id+"/publish");
    }

    public Response getObjectDefinition(String id) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get(objectUri +"/object-definitions/"+id);
    }

    public Response getEmployeesWithNestedField(String manager) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("o/c/employees/?nestedFields="+manager);
    }

    public Response getEmployeesWithAggregationTerm(String aggregationTerm) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("o/c/employees/?aggregationTerms="+aggregationTerm);
    }

    public Response createManager(Manager manager) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .body(manager)
                .post("o/c/managers");
    }

    public Response getManager(String managerId) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("o/c/managers/"+managerId);
    }

    public Response getEmployee(String employeeId) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("o/c/managers/"+employeeId);
    }

    public Response createEmployee(Employee employee) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .body(employee)
                .post("o/c/employees");
    }

    public Response updateEmployee(Employee employee, String employeeId) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .body(employee)
                .put("o/c/employees/"+employeeId);
    }

    public Response getObjectDefinitions() {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get(objectUri +"/object-definitions");
    }

    public Response deleteManager(String managerId) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .delete("o/c/managers/"+managerId);
    }

    public Response deleteEmployee(String employeeId) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .delete("o/c/employees/"+employeeId);
    }
}
