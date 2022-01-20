
package tests.model.object.objectDefinition;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ObjectLayoutColumn {

    private Integer objectFieldId;
    private Integer priority;
    private Integer size;

}
