
package tests.model.object.objectAction.payload.userAccountPayload;

import lombok.Data;

@Data
public class PayloadUserAccountUpdated {

    public String companyId;
    public OriginalUser originalUser;
    public String objectActionTriggerKey;
    public ModelUser modelUser;
    public String userId;
}
