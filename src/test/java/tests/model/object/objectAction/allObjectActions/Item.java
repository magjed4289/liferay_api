
package tests.model.object.objectAction.allObjectActions;

import lombok.Data;
import tests.model.object.Actions;
import tests.model.object.objectAction.Parameters;

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
