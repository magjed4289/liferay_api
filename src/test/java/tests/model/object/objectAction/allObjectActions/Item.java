
package tests.model.object.objectAction.allObjectActions;

import lombok.Builder;
import lombok.Data;
import tests.model.object.Actions;
import tests.model.object.objectAction.Parameters;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Data
public class Item {

    public Actions actions;
    public Boolean active;
    public String dateCreated;
    public String dateModified;
    public Integer id;
    public String name;
    public String objectActionExecutorKey;
    public String objectActionTriggerKey;
    public Parameters parameters;
}
