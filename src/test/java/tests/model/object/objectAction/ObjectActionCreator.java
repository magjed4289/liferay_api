package tests.model.object.objectAction;

import lombok.Builder;
import lombok.Data;
import tests.model.object.Label;

@Builder @Data
public class ObjectActionCreator {

    public Boolean active;
    public Label label;
    public String name;
    public String objectActionExecutorKey;
    public String objectActionTriggerKey;
    public Parameters parameters;
}
