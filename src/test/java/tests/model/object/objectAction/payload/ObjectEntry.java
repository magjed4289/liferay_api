
package tests.model.object.objectAction.payload;

import lombok.Data;
import tests.model.Creator;
import tests.model.object.Actions;
import tests.model.object.objectDefinition.Status;

@Data
public class ObjectEntry {

    public Creator creator;
    public Long dateCreated;
    public Object xClassName;
    public Object scopeKey;
    public Long dateModified;
    public String id;
    public Actions actions;
    public PropertiesManager propertiesManager;
    public String externalReferenceCode;
    public Status status;
}
