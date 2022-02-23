
package tests.model.object.objectRelationship.relationshipsAsNestedFields;

import lombok.Data;

import java.util.List;

@Data
public class Employees {

    public List<Item> items = null;

}
