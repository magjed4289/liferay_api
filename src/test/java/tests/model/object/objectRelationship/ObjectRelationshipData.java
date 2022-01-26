package tests.model.object.objectRelationship;

import lombok.Builder;
import lombok.Data;
import tests.model.object.Label;

@Data @Builder
public class ObjectRelationshipData {

    public String deletionType;
    public Label label;
    public String name;
    public Integer objectDefinitionId2;
    public String type;
}
