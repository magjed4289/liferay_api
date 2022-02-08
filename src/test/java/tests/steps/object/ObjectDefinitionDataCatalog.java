package tests.steps.object;

import tests.model.object.objectDefinition.aggregationTermsData.AggregationTermsData;
import tests.model.object.objectDefinition.customObjects.EmployeeData;
import tests.model.object.objectDefinition.customObjects.ManagerData;
import tests.model.object.objectDefinition.nestedObjectData.NestedObjectData;
import tests.model.object.objectDefinition.ObjectDefinitionData;

import java.util.ArrayList;
import java.util.List;

public class ObjectDefinitionDataCatalog {

    private final List<ObjectDefinitionData> objectDefinitionDataList = new ArrayList<>();
    private final List<ManagerData> managerDataList = new ArrayList<>();
    private final List<EmployeeData> employeeDataList = new ArrayList<>();
    private final List<EmployeeData> employeeDataToUpdateList = new ArrayList<>();
    private final List<NestedObjectData> nestedObjectDataList = new ArrayList<>();
    private final List<AggregationTermsData> aggregationTermsDataList = new ArrayList<>();


    public void addObjectDefinitionData(ObjectDefinitionData objectDefinitionData) {
        objectDefinitionDataList.add(objectDefinitionData);
    }

    public void addAggregationTermsData(AggregationTermsData aggregationTermsData){
        aggregationTermsDataList.add(aggregationTermsData);
    }
    public void addManagerData(ManagerData managerData) {
        managerDataList.add(managerData);
    }

    public void addEmployeeData(EmployeeData employeeData) {
        employeeDataList.add(employeeData);
    }

    public void addEmployeeDataToUpdate(EmployeeData employeeData) {
        employeeDataToUpdateList.add(employeeData);
    }

    public void addNestedObjectData(NestedObjectData nestedObjectData) {
        nestedObjectDataList.add(nestedObjectData);
    }

    public List<ObjectDefinitionData> getObjectDefinitionDataList() {
        return objectDefinitionDataList;
    }

    public List<ManagerData> getManagerDataList() {
        return managerDataList;
    }

    public List<EmployeeData> getEmployeeDataList() {
        return employeeDataList;
    }

    public List<EmployeeData> getEmployeeDataToUpdateList() {
        return employeeDataToUpdateList;
    }

    public List<NestedObjectData> getNestedObjectDataList() {
        return nestedObjectDataList;
    }

    public List<AggregationTermsData> getAggregationTermsDataList() {
        return aggregationTermsDataList;
    }
}
