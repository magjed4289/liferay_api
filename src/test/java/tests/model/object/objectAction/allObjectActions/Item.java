
package tests.model.object.objectAction.allObjectActions;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Item {

    public Actions__1 actions;
    public Boolean active;
    public String dateCreated;
    public String dateModified;
    public Integer id;
    public String name;
    public String objectActionExecutorKey;
    public String objectActionTriggerKey;
    public Parameters parameters;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
