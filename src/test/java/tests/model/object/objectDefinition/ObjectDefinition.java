
package tests.model.object.objectDefinition;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class ObjectDefinition {

    private Boolean active;
    private Label label;
    private String name;
    private List<ObjectAction> objectActions = null;
    private List<ObjectField> objectFields = null;
    private List<ObjectLayout> objectLayouts = null;
    private List<ObjectRelationship> objectRelationships = null;
    private String panelAppOrder;
    private String panelCategoryKey;
    private PluralLabel pluralLabel;
    private Boolean portlet;
    private String scope;
    private Status status;
    private Integer titleObjectFieldId;

}
