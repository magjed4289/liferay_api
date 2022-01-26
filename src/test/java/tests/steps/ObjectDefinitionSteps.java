package tests.steps;

import com.google.gson.Gson;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tests.model.BaseModel;
import tests.model.object.Label;
import tests.model.object.objectDefinition.*;
import tests.model.object.objectDefinition.customObjects.Employee;
import tests.model.object.objectDefinition.customObjects.EmployeeData;
import tests.model.object.objectDefinition.customObjects.Manager;
import tests.model.object.objectDefinition.customObjects.ManagerData;
import tests.model.object.objectDefinition.employeesWithNestedManager.EmployeesWithNestedManager;
import tests.model.object.objectDefinition.nestedObjectData.NestedObjectData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectDefinitionSteps {
    private final BaseModel baseModel;
    private final ObjectDefinitionEndpoints objectDefinitionEndpoints;
    private final Gson gson = new Gson();
    private Map<String, String> entryData = new HashMap<>();
    public List<String> objectIdsList;
    public List<String> managersIdsList;
    public List<String> employeesIdsList;
    public List<String> objectNamesList;


    public ObjectDefinitionSteps(BaseModel baseModel) {
        this.baseModel = baseModel;
        this.objectDefinitionEndpoints = new ObjectDefinitionEndpoints();
        this.objectIdsList = new ArrayList<>();
        this.managersIdsList = new ArrayList<>();
        this.employeesIdsList = new ArrayList<>();
        this.objectNamesList = new ArrayList<>();
    }

    private DataCatalog dataCatalog;

    @Before
    public void setUp() {
        dataCatalog = new DataCatalog();
    }

    @After
    public void cleanUp() {
        if (!managersIdsList.isEmpty()) {
            for (String managerId : managersIdsList) {
                baseModel.setResponse(objectDefinitionEndpoints.deleteManager(managerId));
            }
        }
        if (!employeesIdsList.isEmpty()) {
            for (String employeeId : employeesIdsList) {
                baseModel.setResponse(objectDefinitionEndpoints.deleteEmployee(employeeId));
            }
        }
    }

    @Given("active object definitions created")
    public void activeObjectDefinitionsCreated(DataTable objectDefinitionDataTable) {
        List<Map<String, String>> rows = objectDefinitionDataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            dataCatalog.addObjectDefinitionData(new ObjectDefinitionData(columns.get("name"), columns.get("en_US_label"), columns.get("en_US_plural_label"), columns.get("requiredStringField")));
        }
        baseModel.setResponse(objectDefinitionEndpoints.getObjectDefinitions());
        baseModel.checkResponseCode(200);
        ObjectDefinitions objectDefinitions = gson.fromJson(baseModel.getResponse().asString(), ObjectDefinitions.class);
        for (int i = 0; i < objectDefinitions.getItems().size(); i++) {
            String objectName = objectDefinitions.getItems().get(i).getName();
            objectNamesList.add(objectName);
        }
        for (ObjectDefinitionData objectDefinitionData : dataCatalog.getObjectDefinitionDataList()) {
            for (int i = 0; i < objectDefinitions.getItems().size(); i++) {
                if (!objectNamesList.contains(objectDefinitionData.getName())) {
                    Label labelObject = Label.builder()
                            .en_US(objectDefinitionData.getEn_US_label())
                            .build();
                    Label pluralLabel = Label.builder()
                            .en_US(objectDefinitionData.getEn_US_plural_label())
                            .build();
                    Label labelField = Label.builder()
                            .en_US(objectDefinitionData.getRequiredStringField())
                            .build();
                    ObjectField objectField = ObjectField.builder()
                            .DBType("String")
                            .businessType("Boolean")
                            .indexed(true)
                            .indexedAsKeyword(true)
                            .label(labelField)
                            .name(objectDefinitionData.getRequiredStringField())
                            .required(true)
                            .build();
                    List<ObjectField> objectFieldList = new ArrayList<>();
                    objectFieldList.add(objectField);
                    ObjectDefinition objectDefinition = ObjectDefinition.builder()
                            .label(labelObject)
                            .pluralLabel(pluralLabel)
                            .objectFields(objectFieldList)
                            .active(true)
                            .portlet(true)
                            .name(objectDefinitionData.getName())
                            .scope("company")
                            .build();
                    baseModel.setResponse(objectDefinitionEndpoints.createObjectDefinition(objectDefinition));
                    String objectId = baseModel.getResponse().then().extract().path("id").toString();
                    String createdObjectName = baseModel.getResponse().then().extract().path("name").toString();
                    objectIdsList.add(objectId);
                    objectNamesList.add(createdObjectName);
                    baseModel.setResponse(objectDefinitionEndpoints.publishObjectDefinition(objectId));
                } else {
                    if (objectDefinitions.getItems().get(i).getName().equals(objectDefinitionData.getName())) {
                        objectIdsList.add(String.valueOf(objectDefinitions.getItems().get(i).getId()));
                    }
                }
            }
        }
    }

    @When("calling for {string} with {string} as nestedfields parameter")
    public void callingForWithAsNestedfieldsParameter(String employees, String manager) {
        baseModel.setResponse(objectDefinitionEndpoints.getObjectWithNestedField(employees, manager));
    }

    @Then("{string} information should be provided with {string} information nested as an object")
    public void informationShouldBeProvidedWithInformationNestedAsAnObject(String employee, String manager, DataTable nestedObjectDataTable) {
        List<Map<String, String>> rows = nestedObjectDataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            dataCatalog.addNestedObjectData(new NestedObjectData(columns.get(employee), columns.get(manager)));
        }
        EmployeesWithNestedManager employeesWithNestedManager = gson.fromJson(baseModel.getResponse().asString(), EmployeesWithNestedManager.class);
        for (NestedObjectData nestedObjectData : dataCatalog.getNestedObjectDataList()) {
            if (employeesWithNestedManager.getItems().size() == dataCatalog.getNestedObjectDataList().size()) {
                for (int i = 0; i < employeesWithNestedManager.getItems().size(); i++) {
                    if (employeesWithNestedManager.getItems().get(i).getFirstname().equals(nestedObjectData.getEmployee())) {
                        Assert.assertEquals(employeesWithNestedManager.getItems().get(i).getManager().getFirstname(), nestedObjectData.getManager());
                    }
                }
            } else {
                Assert.fail("There's more items in the employees objects than in the table!");
            }

        }
    }

    @Given("manager accounts created")
    public void managerAccountsCreated(DataTable managers) {
        List<Map<String, String>> rows = managers.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            dataCatalog.addManagerData(new ManagerData(columns.get("firstname")));
        }
        for (ManagerData managerData : dataCatalog.getManagerDataList()) {
            Manager manager = Manager.builder()
                    .firstname(managerData.getFirstname())
                    .build();
            baseModel.setResponse(objectDefinitionEndpoints.createManager(manager));
            baseModel.checkResponseCode(200);
            String managerId = baseModel.getResponse().then().extract().path("id").toString();
            managersIdsList.add(managerId);
        }
    }

    @Given("employee accounts created")
    public void employeeAccountsCreated(DataTable employees) {
        List<Map<String, String>> rows = employees.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            dataCatalog.addEmployeeData(new EmployeeData(columns.get("firstname"), columns.get("supervisor")));
        }
        for (EmployeeData employeeData : dataCatalog.getEmployeeDataList()) {
            String supervidorId = null;
            for (String managerId : managersIdsList) {
                baseModel.setResponse(objectDefinitionEndpoints.getManager(managerId));
                String firstname = baseModel.getResponse().then().extract().path("firstname").toString();
                if (firstname.equals(employeeData.getSupervisor())) {
                    supervidorId = managerId;
                }
            }
            assert supervidorId != null;
            Employee employee = Employee.builder()
                    .firstname(employeeData.getFirstname())
                    .r_supervisor_c_managerId(Integer.parseInt(supervidorId))
                    .build();
            baseModel.setResponse(objectDefinitionEndpoints.createEmployee(employee));
            baseModel.checkResponseCode(200);
            String managerId = baseModel.getResponse().then().extract().path("id").toString();
            managersIdsList.add(managerId);
        }
    }
}
