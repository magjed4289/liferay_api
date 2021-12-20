package tests.model.remote_app_entry_service_impl;

import lombok.Data;

@Data
public class RemoteAppCreated {

    private String IFrameURL;
    private String companyId;
    private Long createDate;
    private String customElementCSSURLs;
    private String customElementHTMLElementName;
    private String customElementURLs;
    private String description;
    private String friendlyURLMapping;
    private Boolean instanceable;
    private Long modifiedDate;
    private String mvccVersion;
    private String name;
    private String nameCurrentValue;
    private String portletCategoryName;
    private String properties;
    private String remoteAppEntryId;
    private String sourceCodeURL;
    private Integer status;
    private String statusByUserId;
    private String statusByUserName;
    private Long statusDate;
    private String type;
    private String userId;
    private String userName;
    private String uuid;
}
