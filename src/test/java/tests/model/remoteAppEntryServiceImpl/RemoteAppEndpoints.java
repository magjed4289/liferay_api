package tests.model.remoteAppEntryServiceImpl;

import io.restassured.response.Response;
import tests.utils.ConfigFileReader;

import static io.restassured.RestAssured.given;


public class RemoteAppEndpoints {

    private final ConfigFileReader config = ConfigFileReader.getInstance();

    private final String remoteAppEntry = config.getConfiguration().getJsnows()+"/remoteapp.remoteappentry";

    private RemoteAppEntryModel remoteAppEntryModel;

    public RemoteAppEndpoints() {
    }

    public Response addIframeRemoteAppEntry(AppCreatorBody appCreatorBody) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .param("description", "")
                .param("friendlyURLMapping", "")
                .param("iFrameURL", appCreatorBody.getIFrameURL())
                .param("instanceable", appCreatorBody.getInstantiable())
                .param("nameMap", appCreatorBody.getNameMap())
                .param("portletCategoryName", appCreatorBody.getPortletCategoryName())
                .param("properties", "")
                .param("sourceCodeURL", "")
                .when()
                .post("/api/jsonws/remoteapp.remoteappentry/add-i-frame-remote-app-entry/");
    }

    public Response getRemoteAppEntry() {
        return
                given()
                        .auth()
                        .preemptive()
                        .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                        .pathParam("remoteAppEntryId", "42474")
                        .when()
                        .get(remoteAppEntry+"/get-remote-app-entry/remote-app-entry-id/{remoteAppEntryId}");
    }
}
