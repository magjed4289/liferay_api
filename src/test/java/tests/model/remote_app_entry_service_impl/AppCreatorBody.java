package tests.model.remote_app_entry_service_impl;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data @Builder
public class AppCreatorBody {
    public String friendlyURLMapping;
    public String iFrameURL;
    public Map<String,String> nameMap;
    public String instantiable;
    public String portletCategoryName;
    public String properties;
    public String description;
    public String sourceCodeURL;
}
