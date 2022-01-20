
package tests.model.object.objectDefinition;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class ObjectLayoutTab {

    private Name__1 name;
    private List<ObjectLayoutBox> objectLayoutBoxes;
    private Integer objectRelationshipId;
    private Integer priority;
}
