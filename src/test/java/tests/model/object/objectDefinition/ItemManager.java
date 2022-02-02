
package tests.model.object.objectDefinition;

import lombok.Data;
import tests.model.Creator;
import tests.model.object.Actions;
import tests.model.object.objectDefinition.employeesWithNestedFields.Manager;

@Data
public class ItemManager {

    public Actions actions;
    public Creator creator;
    public String dateCreated;
    public String dateModified;
    public String externalReferenceCode;
    public Integer id;
    public Status status;
    public String firstname;
    public Manager manager;
    public Integer managerId;
}
