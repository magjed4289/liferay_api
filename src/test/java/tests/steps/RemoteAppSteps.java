package tests.steps;

import com.google.gson.Gson;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tests.model.BaseModel;
import tests.model.remote_app_entry_service_impl.AppCreatorBody;
import tests.model.remote_app_entry_service_impl.RemoteAppCreated;
import tests.model.remote_app_entry_service_impl.RemoteAppEndpoints;


import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RemoteAppSteps {

    private final BaseModel baseModel;
    private final RemoteAppEndpoints remoteAppEndpoints;
    private final Gson gson = new Gson();
    private Map<String, String> entryData = new HashMap<>();


    public RemoteAppSteps(BaseModel baseModel) {
        this.baseModel = baseModel;
        this.remoteAppEndpoints = new RemoteAppEndpoints();

    }

    @When("I make a valid call to create a remote app as iframe")
    public void iMakeAValidCallToCreateARemoteAppAsIframe(Map<String, String> cucumberData) {
        entryData = cucumberData;
        String remoteAppName = cucumberData.get("nameMap");
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("en_US", remoteAppName);
        AppCreatorBody appCreatorBody = AppCreatorBody.builder()
                .friendlyURLMapping(cucumberData.get("friendlyURLMapping"))
                .iFrameURL(cucumberData.get("iFrameURL"))
                .instantiable(cucumberData.get("instanceable"))
                .portletCategoryName(cucumberData.get("portletCategoryName"))
                .sourceCodeURL(cucumberData.get("sourceCodeURL"))
                .description(cucumberData.get("description"))
                .properties(cucumberData.get("properties"))
                .nameMap(nameMap)
                .build();
        baseModel.setResponse(remoteAppEndpoints.addIframeRemoteAppEntry(appCreatorBody));
    }

    @Then("the response code is {int}")
    public void theResponseCodeIs(int responseCode) {
        baseModel.checkResponseCode(responseCode);
    }

    @Then("I receive a proper body response in return")
    public void iReceiveAProperBodyResponseInReturn() {
        RemoteAppCreated remoteAppCreated = gson.fromJson(baseModel.getResponse().asString(), RemoteAppCreated.class);
        assertAll("Should return body with correct information",
                () -> assertEquals(entryData.get("iFrameURL"), remoteAppCreated.getIFrameURL()),
                () -> assertEquals(entryData.get("instanceable"), remoteAppCreated.getInstanceable().toString()),
                () -> assertTrue(remoteAppCreated.getPortletCategoryName().contains(entryData.get("portletCategoryName"))),
                () -> assertEquals(entryData.get("nameMap"), remoteAppCreated.getName()),
                () -> assertFalse(remoteAppCreated.getRemoteAppEntryId().isEmpty()));
    }
}
