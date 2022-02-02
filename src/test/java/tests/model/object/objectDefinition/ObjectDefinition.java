
package tests.model.object.objectDefinition;

import lombok.Builder;
import lombok.Data;
import tests.model.object.Label;

import java.util.List;

@Data @Builder
public class ObjectDefinition {

    public Boolean active;
    public Label label;
    public String name;
    public List<ObjectField> objectFields;
    public Label pluralLabel;
    public Boolean portlet;
    public String scope;
}
