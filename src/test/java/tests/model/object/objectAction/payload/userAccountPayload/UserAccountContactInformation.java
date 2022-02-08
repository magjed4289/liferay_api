
package tests.model.object.objectAction.payload.userAccountPayload;

import lombok.Data;

import java.util.List;

@Data
public class UserAccountContactInformation {

    public String skype;
    public String jabber;
    public String twitter;
    public List<Object> emailAddresses = null;
    public Object xClassName;
    public List<Object> webUrls = null;
    public String facebook;
    public String sms;
    public List<Object> telephones = null;
    public Object id;
    public List<Object> postalAddresses = null;
}
