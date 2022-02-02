
package tests.model.object.objectDefinition;

import lombok.Builder;
import lombok.Data;
import tests.model.object.Label;
import tests.model.object.Actions;

import java.util.List;

@Data @Builder
public class Item {

    public Actions actions;
    public Boolean active;
    public String dateCreated;
    public String dateModified;
    public Integer id;
    public Label label;
    public String name;
    public List<Object> objectActions;
    public List<ObjectField> objectFields;
    public List<Object> objectLayouts;
    public String panelCategoryKey;
    public Label pluralLabel;
    public Boolean portlet;
    public String scope;
    public Status status;
    public Boolean system;
    public Integer titleObjectFieldId;
}
