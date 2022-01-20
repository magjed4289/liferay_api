
package tests.model.object.objectDefinition;


import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ObjectRelationship {

    private String deletionType;
    private Label__2 label;
    private String name;
    private Integer objectDefinitionId1;
    private Integer objectDefinitionId2;
    private String objectDefinitionName2;
    private String type;
}
