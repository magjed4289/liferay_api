
package tests.model.object.objectDefinition;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Status {

    private String label;
    private String labelI18n;
}
