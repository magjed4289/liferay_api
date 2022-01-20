
package tests.model.object.objectDefinition;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Parameters {

    private AdditionalProp1 additionalProp1;
    private AdditionalProp2 additionalProp2;
    private AdditionalProp3 additionalProp3;
}
