package tests.steps.object;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class ObjectActionCreator {

    public Boolean active;
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
