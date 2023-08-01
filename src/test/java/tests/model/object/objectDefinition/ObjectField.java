
package tests.model.object.objectDefinition;

import lombok.Builder;
import lombok.Data;
import tests.model.object.Label;

@Data @Builder
public class ObjectField {

    public String DBType;
    public String businessType;
    public Boolean indexed;
    public Boolean indexedAsKeyword;
    public Label label;
    public String name;
    public Boolean required;
    public String externalReferenceCode;
}
