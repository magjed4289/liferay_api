package tests.model.object.objectAction;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class ObjectActionCreator {

    public Boolean active;
    public String name;
    public String objectActionExecutorKey;
    public String objectActionTriggerKey;
    public Parameters parameters;
}
