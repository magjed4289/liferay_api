
package tests.model.object.objectDefinition;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ObjectLayout {

    private Boolean defaultObjectLayout;
    private Name name;
    private Integer objectDefinitionId;
    private List<ObjectLayoutTab> objectLayoutTabs;
}
