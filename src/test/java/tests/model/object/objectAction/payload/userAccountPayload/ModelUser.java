
package tests.model.object.objectAction.payload.userAccountPayload;

import lombok.Data;
import tests.model.object.Actions;

import java.util.List;

@Data
public class ModelUser {

    public String profileURL;
    public List<RoleBrief> roleBriefs = null;
    public Object xClassName;
    public List<Object> keywords = null;
    public List<Object> customFields = null;
    public String jobTitle;
    public Object lastLoginDate;
    public String externalReferenceCode;
    public Object honorificSuffix;
    public Object accountBriefs;
    public String emailAddress;
    public Long dateCreated;
    public UserAccountContactInformation userAccountContactInformation;
    public String familyName;
    public List<Object> organizationBriefs = null;
    public String id;
    public Object image;
    public String dashboardURL;
    public List<SiteBrief> siteBriefs = null;
    public String givenName;
    public Object honorificPrefix;
    public String alternateName;
    public Long dateModified;
    public Long birthDate;
    public String name;
    public Actions actions;
    public String additionalName;
}
