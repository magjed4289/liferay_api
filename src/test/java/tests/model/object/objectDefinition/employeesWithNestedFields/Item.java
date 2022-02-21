
package tests.model.object.objectDefinition.employeesWithNestedFields;

import lombok.Builder;
import lombok.Data;
import tests.model.Creator;
import tests.model.object.Actions;
import tests.model.object.objectDefinition.Status;

@Data
@Builder
public class Item {

    public Actions actions;
    public Creator creator;
    public String dateCreated;
    public String dateModified;
    public String externalReferenceCode;
    public Integer id;
    public Status status;
    public String firstname;
    public Manager r_supervisor_c_manager;
    public Integer r_supervisor_c_managerId;
}
