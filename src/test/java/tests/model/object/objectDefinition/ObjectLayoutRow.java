
package tests.model.object.objectDefinition;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class ObjectLayoutRow {

    private List<ObjectLayoutColumn> objectLayoutColumns;
    private Integer priority;

}
