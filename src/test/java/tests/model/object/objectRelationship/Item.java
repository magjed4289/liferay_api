
package tests.model.object.objectRelationship;

import lombok.Builder;
import lombok.Data;
import tests.model.object.Label;
import tests.model.object.Actions;

@Data @Builder
public class Item {

    public Actions actions;
    public String deletionType;
    public Integer id;
    public Label label;
    public String name;
    public Integer objectDefinitionId1;
    public Integer objectDefinitionId2;
    public String objectDefinitionName2;
    public String type;
}
