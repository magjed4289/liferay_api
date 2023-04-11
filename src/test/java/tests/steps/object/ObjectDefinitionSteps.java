package tests.steps.object;

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
import tests.model.object.objectDefinition.aggregationTermsData.AggregationTermsData;
import tests.model.object.objectDefinition.customObjects.Employee;
import tests.model.object.objectDefinition.customObjects.EmployeeData;
import tests.model.object.objectDefinition.customObjects.Manager;
import tests.model.object.objectDefinition.customObjects.ManagerData;
import tests.model.object.objectDefinition.employeesWithAggregationTerms.EmployeesWithAggregationTerms;
import tests.model.object.objectDefinition.employeesWithNestedFields.EmployeesWithNestedFields;
import tests.model.object.objectDefinition.nestedObjectData.*;
import tests.model.object.objectDefinition.objectDefinitions.ObjectDefinitions;
import tests.model.object.objectRelationship.relationshipsAsNestedFields.EmployeeManagerRelationshipAsNestedFields;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectDefinitionSteps {
    private final BaseModel baseModel;
    private final ObjectDefinitionEndpoints objectDefinitionEndpoints;
    private final Gson gson = new Gson();
    public final List<String> objectIdsList;
    public final List<String> managersIdsList;
    public final List<String> employeesIdsList;
    public final List<String> objectNamesList;


    public ObjectDefinitionSteps(BaseModel baseModel) {
        this.baseModel = baseModel;
        this.objectDefinitionEndpoints = new ObjectDefinitionEndpoints();
        this.objectIdsList = new ArrayList<>();
        this.managersIdsList = new ArrayList<>();
        this.employeesIdsList = new ArrayList<>();
        this.objectNamesList = new ArrayList<>();
    }

    private ObjectDefinitionDataCatalog objectDefinitionDataCatalog;

    @Before
    public void setUp() {
        objectDefinitionDataCatalog = new ObjectDefinitionDataCatalog();
    }

    //@After
    public void cleanUp() {
        if (!managersIdsList.isEmpty()) {
            deleteManagers();
        }
        if (!employeesIdsList.isEmpty()) {
            deleteObjects(employeesIdsList);
        }
    }

    public void deleteManagers() {
        deleteObjects(managersIdsList);
    }

    private void deleteObjects(List<String> objectIdsList) {
        for (String objectId : objectIdsList) {
            baseModel.setResponse(objectDefinitionEndpoints.deleteManager(objectId));
        }
    }

    @Given("active object definitions created")
    public void activeObjectDefinitionsCreated(DataTable objectDefinitionDataTable) {
        for (Map<String, String> columns : getRows(objectDefinitionDataTable)) {
            objectDefinitionDataCatalog.addObjectDefinitionData(new ObjectDefinitionData(columns.get("name"), columns.get("en_US_label"), columns.get("en_US_plural_label"), columns.get("requiredStringField")));
        }
        baseModel.setResponse(objectDefinitionEndpoints.getObjectDefinitions());
        baseModel.checkResponseCode(200);
        checkForAlreadyCreatedObjectDefinitions();
        createObjectDefinitions();
    }

    private List<Map<String, String>> getRows(DataTable objectDefinitionDataTable) {
        return objectDefinitionDataTable.asMaps(String.class, String.class);
    }

    private void checkForAlreadyCreatedObjectDefinitions() {
        ObjectDefinitions objectDefinitions = getObjectDefinitions();
        for (int i = 0; i < objectDefinitions.getItems().size(); i++) {
            objectNamesList.add(objectDefinitions.getItems().get(i).getName());
            objectIdsList.add(objectDefinitions.getItems().get(i).getId().toString());
        }
    }

    private void createObjectDefinitions() {
        for (ObjectDefinitionData objectDefinitionData : objectDefinitionDataCatalog.getObjectDefinitionDataList()) {
            if (!objectNamesList.contains(objectDefinitionData.getName())) {
                ObjectDefinition objectDefinition = getObjectDefinition(
                        objectDefinitionData,
                        buildLabel(objectDefinitionData.getEn_US_label()),
                        buildLabel(objectDefinitionData.getEn_US_plural_label()),
                        getObjectFieldList(
                                objectField(buildLabel(objectDefinitionData.getRequiredStringField()), objectDefinitionData)
                        )
                );
                baseModel.setResponse(objectDefinitionEndpoints.createObjectDefinition(objectDefinition));
                String objectId = baseModel.getResponse().then().extract().path("id").toString();
                String createdObjectName = baseModel.getResponse().then().extract().path("name").toString();
                baseModel.setResponse(objectDefinitionEndpoints.publishObjectDefinition(objectId));
                baseModel.checkResponseCode(204);
                objectIdsList.add(objectId);
                objectNamesList.add(createdObjectName);
            }
        }
    }

    private ObjectDefinition getObjectDefinition(ObjectDefinitionData objectDefinitionData, Label labelObject, Label pluralLabel, List<ObjectField> objectFieldList) {
        return ObjectDefinition.builder()
                .label(labelObject)
                .pluralLabel(pluralLabel)
                .objectFields(objectFieldList)
                .active(true)
                .portlet(true)
                .name(objectDefinitionData.getName())
                .scope("company")
                .build();
    }

    private List<ObjectField> getObjectFieldList(ObjectField objectField) {
        List<ObjectField> objectFieldList = new ArrayList<>();
        objectFieldList.add(objectField);
        return objectFieldList;
    }

    private Label buildLabel(String label) {
        return Label.builder()
                .en_US(label)
                .build();
    }

    private ObjectDefinitions getObjectDefinitions() {
        baseModel.setResponse(objectDefinitionEndpoints.getObjectDefinitions());
        return gson.fromJson(baseModel.getResponse().asString(), ObjectDefinitions.class);
    }

    private ObjectField objectField(Label labelField, ObjectDefinitionData objectDefinitionData) {
        return ObjectField.builder()
                .DBType("String")
                .businessType("Text")
                .indexed(true)
                .indexedAsKeyword(true)
                .label(labelField)
                .name(objectDefinitionData.getRequiredStringField())
                .required(true)
                .build();
    }

    @When("calling for employees with {string} as nestedFields parameter")
    public void callingForWithAsNestedFieldsParameter(String manager) {
        baseModel.setResponse(objectDefinitionEndpoints.getEmployeesWithNestedField(manager));
    }

    @Then("{string} information should be provided with {string} information nested as an object")
    public void informationShouldBeProvidedWithInformationNestedAsAnObject(String employee, String manager, DataTable nestedObjectDataTable) {
        for (Map<String, String> columns : getRows(nestedObjectDataTable)) {
            objectDefinitionDataCatalog.addNestedObjectData(new NestedObjectData(columns.get(employee), columns.get(manager)));
        }
        System.out.println(baseModel.getResponse().asString());
        EmployeesWithNestedFields employeesWithNestedFields = gson.fromJson(baseModel.getResponse().asString(), EmployeesWithNestedFields.class);
        for (NestedObjectData nestedObjectData : objectDefinitionDataCatalog.getNestedObjectDataList()) {
            if (employeesWithNestedFields.getItems().size() == objectDefinitionDataCatalog.getNestedObjectDataList().size()) {
                for (int i = 0; i < employeesWithNestedFields.getItems().size(); i++) {
                    if (employeesWithNestedFields.getItems().get(i).getFirstname().equals(nestedObjectData.getEmployee())) {
                        Assert.assertEquals(employeesWithNestedFields.getItems().get(i).getR_supervisor_c_manager().getFirstname(), nestedObjectData.getManager());
                    }
                }
            } else {
                Assert.fail("There's more items in the employees objects than in the table!");
            }

        }
    }

    @Then("the data is being delivered correctly")
    public void theDataIsBeingDeliveredCorrectly(DataTable nestedObjectDataTable) {
        for (Map<String, String> columns : getRows(nestedObjectDataTable)) {
            objectDefinitionDataCatalog.addNestedObjectData(new NestedObjectData(columns.get("employee"), columns.get("manager")));
        }
        System.out.println(baseModel.getResponse().asString());
        EmployeeManagerRelationshipAsNestedFields employeeManagerRelationshipAsNestedFields = gson.fromJson(baseModel.getResponse().asString(), EmployeeManagerRelationshipAsNestedFields.class);
        for (NestedObjectData nestedObjectData : objectDefinitionDataCatalog.getNestedObjectDataList()) {
            if (employeeManagerRelationshipAsNestedFields.getData().getC().getEmployees().getItems().size() == objectDefinitionDataCatalog.getNestedObjectDataList().size()) {
                for (int i = 0; i < employeeManagerRelationshipAsNestedFields.getData().getC().getEmployees().getItems().size(); i++) {
                    if (employeeManagerRelationshipAsNestedFields.getData().getC().getEmployees().getItems().get(i).getFirstname().equals(nestedObjectData.getEmployee())) {
                        Assert.assertNotNull("ManagerId is null.",employeeManagerRelationshipAsNestedFields.getData().getC().getEmployees().getItems().get(i).getManagerId());
                        baseModel.setResponse(objectDefinitionEndpoints.getManager(employeeManagerRelationshipAsNestedFields.getData().getC().getEmployees().getItems().get(i).getManagerId().toString()));
                        Manager manager = gson.fromJson(baseModel.getResponse().asString(), Manager.class);
                        Assert.assertEquals(manager.getFirstname(),nestedObjectData.getManager());
                    }
                }
            } else {
                Assert.fail("There's more items in the employees objects than in the table!");
            }

        }
    }

    @Given("manager accounts created")
    public void managerAccountsCreated(DataTable managers) {
        for (Map<String, String> columns : getRows(managers)) {
            objectDefinitionDataCatalog.addManagerData(new ManagerData(columns.get("firstname")));
        }
        for (ManagerData managerData : objectDefinitionDataCatalog.getManagerDataList()) {
            baseModel.setResponse(objectDefinitionEndpoints.createManager(getManager(managerData)));
            baseModel.checkResponseCode(200);
            managersIdsList.add(baseModel.getResponse().then().extract().path("id").toString());
        }
    }

    private Manager getManager(ManagerData managerData) {
        return Manager.builder()
                .firstname(managerData.getFirstname())
                .build();
    }

    @Given("employee accounts created")
    public void employeeAccountsCreated(DataTable employees) {
        for (Map<String, String> columns : getRows(employees)) {
            objectDefinitionDataCatalog.addEmployeeData(new EmployeeData(columns.get("firstname"), columns.get("supervisor")));
        }
        for (EmployeeData employeeData : objectDefinitionDataCatalog.getEmployeeDataList()) {
            String supervisorId = null;
            for (String managerId : managersIdsList) {
                baseModel.setResponse(objectDefinitionEndpoints.getManager(managerId));
                if (baseModel.getResponse().then().extract().path("firstname").equals(employeeData.getSupervisor())) {
                    supervisorId = managerId;
                }
            }
            assert supervisorId != null;
            baseModel.setResponse(objectDefinitionEndpoints.createEmployee(getEmployee(employeeData, supervisorId)));
            baseModel.checkResponseCode(200);
            employeesIdsList.add(baseModel.getResponse().then().extract().path("id").toString());
        }
    }

    private Employee getEmployee(EmployeeData employeeData, String supervisorId) {
        return Employee.builder()
                .firstname(employeeData.getFirstname())
                .r_supervisor_c_managerId(Integer.parseInt(supervisorId))
                .build();
    }

    @Given("employee accounts updated")
    public void employeeAccountsUpdated(DataTable employees) {
        for (Map<String, String> columns : getRows(employees)) {
            objectDefinitionDataCatalog.addEmployeeDataToUpdate(new EmployeeData(columns.get("firstname"), columns.get("supervisor")));
        }
        for (EmployeeData employeeData : objectDefinitionDataCatalog.getEmployeeDataToUpdateList()) {
            for (String employeeId : employeesIdsList) {
                baseModel.setResponse(objectDefinitionEndpoints.getEmployee(employeeId));
                Employee employee = gson.fromJson(baseModel.getResponse().asString(), Employee.class);
                if (employee.getFirstname().equals(employeeData.getFirstname())) {
                    String supervisorId = null;
                    for (String managerId : managersIdsList) {
                        baseModel.setResponse(objectDefinitionEndpoints.getManager(managerId));
                        if (baseModel.getResponse().then().extract().path("firstname").equals(employeeData.getSupervisor())) {
                            supervisorId = managerId;
                        }
                    }
                    assert supervisorId != null;
                    baseModel.setResponse(objectDefinitionEndpoints.updateEmployee(getEmployee(employeeData, supervisorId), employeeId));
                    baseModel.checkResponseCode(200);
                }
            }
        }
    }

    @When("deleting one of the employees")
    public void deletingOneOfTheEmployees(DataTable employees) {
        for (Map<String, String> columns : getRows(employees)) {
            objectDefinitionDataCatalog.addEmployeeDataToUpdate(new EmployeeData(columns.get("firstname"), columns.get("supervisor")));
        }
        for (EmployeeData employeeData : objectDefinitionDataCatalog.getEmployeeDataToUpdateList()) {
            for (String employeeId : employeesIdsList) {
                baseModel.setResponse(objectDefinitionEndpoints.getEmployee(employeeId));
                Employee employee = gson.fromJson(baseModel.getResponse().asString(), Employee.class);
                if (employee.getFirstname().equals(employeeData.getFirstname())) {
                    baseModel.setResponse(objectDefinitionEndpoints.deleteEmployee(employeeId));
                    baseModel.checkResponseCode(204);
                }
            }
        }
    }

    @When("calling for employees with {string} as aggregationTerms parameter")
    public void callingForWithAsAggregationTermsParameter(String aggregationTerm) {
        baseModel.setResponse(objectDefinitionEndpoints.getEmployeesWithAggregationTerm(aggregationTerm));
    }

    @Then("I receive the information about the aggregationTerm as facets of the response")
    public void iReceiveTheInformationAboutTheAggregationTermAsFacetsOfTheResponse(DataTable aggregationTermsDataTable) {
        System.out.println(baseModel.getResponse().asString());
        for (Map<String, String> columns : getRows(aggregationTermsDataTable)) {
            objectDefinitionDataCatalog.addAggregationTermsData(new AggregationTermsData(columns.get("numberOfOccurrences"), columns.get("relatedManagerFirstname")));
        }
        EmployeesWithAggregationTerms employeesWithAggregationTerms = gson.fromJson(baseModel.getResponse().asString(), EmployeesWithAggregationTerms.class);
        if (employeesWithAggregationTerms.getFacets().get(0).getFacetValues().size() == objectDefinitionDataCatalog.getAggregationTermsDataList().size()) {
            for (AggregationTermsData aggregationTermsData : objectDefinitionDataCatalog.getAggregationTermsDataList()) {
                boolean managerIdIsFound = false;
                for (String managerId : managersIdsList) {
                    baseModel.setResponse(objectDefinitionEndpoints.getManager(managerId));
                    Manager manager = gson.fromJson(baseModel.getResponse().asString(), Manager.class);
                    if (manager.getFirstname().equals(aggregationTermsData.getRelatedManagerFirstname())) {
                        for (int i = 0; i < employeesWithAggregationTerms.getFacets().get(0).getFacetValues().size(); i++) {
                            if (employeesWithAggregationTerms.getFacets().get(0).getFacetValues().get(i).getTerm().equals(managerId)) {
                                managerIdIsFound = true;
                                Assert.assertEquals(employeesWithAggregationTerms.getFacets().get(0).getFacetValues().get(i).getNumberOfOccurrences().toString(), aggregationTermsData.getNumberOfOccurrences());
                            }
                        }
                    }
                }
                Assert.assertTrue("No manager with firstname " + aggregationTermsData.getRelatedManagerFirstname() + " matches any of the ids on the list", managerIdIsFound);
            }
        } else {
            Assert.fail("There's more items in the employees objects than in the table!");
        }
    }

    @Given("a site with {string} object defined")
    public void aSiteWithCommerceOrderObjectDefined(String objectName) {
        baseModel.setResponse(objectDefinitionEndpoints.getObjectDefinitions());
        baseModel.checkResponseCode(200);
        checkForAlreadyCreatedObjectDefinitions();
        Assert.assertTrue(objectNamesList.contains(objectName));
    }
}
