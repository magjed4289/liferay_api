package tests.steps;

import tests.model.object.objectDefinition.customObjects.EmployeeData;
import tests.model.object.objectDefinition.customObjects.ManagerData;
import tests.model.object.objectDefinition.nestedObjectData.NestedObjectData;
import tests.model.object.objectDefinition.ObjectDefinitionData;

import java.util.ArrayList;
import java.util.List;

public class DataCatalog {

    private List<ObjectDefinitionData> objectDefinitionDataList = new ArrayList();
    private List<ManagerData> managerDataList = new ArrayList<>();
    private List<EmployeeData> employeeDataList = new ArrayList<>();
    private List<NestedObjectData> nestedObjectDataList = new ArrayList<>();


    public void addObjectDefinitionData(ObjectDefinitionData objectDefinitionData) {
        objectDefinitionDataList.add(objectDefinitionData);
    }

    public void addManagerData(ManagerData managerData) {
        managerDataList.add(managerData);
    }

    public void addEmployeeData(EmployeeData employeeData) {
        employeeDataList.add(employeeData);
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

    public List<NestedObjectData> getNestedObjectDataList() {
        return nestedObjectDataList;
    }
}
