
package tests.model.object.objectAction.payload;

import lombok.Data;

@Data
public class PayloadCustomObject {

    public String companyId;
    public String objectActionTriggerKey;
    public ObjectEntry objectEntry;
    public String userId;
}
