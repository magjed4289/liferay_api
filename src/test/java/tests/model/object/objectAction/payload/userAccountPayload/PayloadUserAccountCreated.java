
package tests.model.object.objectAction.payload.userAccountPayload;

import lombok.Data;

@Data
public class PayloadUserAccountCreated {

    public String companyId;
    public String objectActionTriggerKey;
    public ModelUser modelUser;
    public String userId;
}
