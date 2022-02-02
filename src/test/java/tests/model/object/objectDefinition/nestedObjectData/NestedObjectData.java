package tests.model.object.objectDefinition.nestedObjectData;

import lombok.Data;

@Data
public class NestedObjectData {
    private String employee;
    private String manager;

    public NestedObjectData(String employee, String manager) {
        this.employee=employee;
        this.manager=manager;
    }
}
