package tests.model.object.objectDefinition;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ObjectField {

    private String dBType;
    private String businessType;
    private Boolean indexed;
    private Boolean indexedAsKeyword;
    private String indexedLanguageId;
    private Label__1 label;
    private Integer listTypeDefinitionId;
    private String name;
    private Boolean required;
}
