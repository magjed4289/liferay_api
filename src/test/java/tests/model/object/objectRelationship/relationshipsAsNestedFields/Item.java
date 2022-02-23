
package tests.model.object.objectRelationship.relationshipsAsNestedFields;

import lombok.Data;

@Data
public class Item {

    public String firstname;
    public Integer employeeId;
    public Object managerId;

}
