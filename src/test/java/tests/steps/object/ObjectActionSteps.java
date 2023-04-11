package tests.steps.object;

import com.google.gson.Gson;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.fasterxml.jackson.core.JsonParseException;
import io.cucumber.messages.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.JsonMappingException;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import tests.model.BaseModel;
import tests.model.object.objectAction.Parameters;
import tests.model.object.objectAction.ObjectActionCreator;
import tests.model.object.objectAction.allObjectActions.AllObjectActions;
import tests.model.object.objectAction.payload.PayloadCustomObject;
import tests.model.object.objectAction.payload.userAccountPayload.PayloadUserAccountCreated;
import tests.model.object.objectAction.payload.userAccountPayload.PayloadUserAccountUpdated;
import tests.model.object.objectDefinition.ObjectDefinitionEndpoints;
import tests.model.object.objectDefinition.objectDefinitions.ObjectDefinitions;
import tests.model.object.objectAction.ObjectActionEndpoints;
import tests.steps.headlessAdminUser.HeadlessAdminUserSteps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.Map;

import static junit.framework.TestCase.*;
import static org.junit.jupiter.api.Assertions.assertAll;


public class ObjectActionSteps {
    public final ObjectActionEndpoints objectActionEndpoints;
    public final ObjectDefinitionEndpoints objectDefinitionEndpoints;
    public final ObjectDefinitionSteps objectDefinitionSteps;
    public final HeadlessAdminUserSteps headlessAdminUserSteps;


    public final BaseModel baseModel;
    private final Gson gson = new Gson();
    ServerSocket serverSocket;
    public String outputResponse = null;
    public String definedURL = null;
    public String actionId = null;


    public ObjectActionSteps(ObjectActionEndpoints objectActionEndpoints, ObjectDefinitionEndpoints objectDefinitionEndpoints, BaseModel baseModel, ObjectDefinitionSteps objectDefinitionSteps, HeadlessAdminUserSteps headlessAdminUserSteps) {
        this.objectActionEndpoints = objectActionEndpoints;
        this.baseModel = baseModel;
        this.objectDefinitionEndpoints = objectDefinitionEndpoints;
        this.objectDefinitionSteps = objectDefinitionSteps;
        this.headlessAdminUserSteps = headlessAdminUserSteps;
    }

    //@After()
    public void deleteObjectDefinitionActions() {
        ObjectDefinitions objectDefinitions = getObjectDefinitions();
        for (int i = 0; i < objectDefinitions.getItems().size(); i++) {
            AllObjectActions allObjectActions = getAllObjectActions(objectDefinitions.getItems().get(i).getId());
            for (int j = 0; j < allObjectActions.getItems().size(); j++) {
                baseModel.setResponse(objectActionEndpoints.deleteObjectAction(allObjectActions.getItems().get(j).getId().toString()));
                baseModel.checkResponseCode(204);
            }
        }
    }

   // @After()
    public void deleteAccountsAndUserAccounts(){
        headlessAdminUserSteps.deleteAccountsAndUserAccounts();
    }

    private AllObjectActions getAllObjectActions(Integer objectId) {
        baseModel.setResponse(objectActionEndpoints.getDefinitionObjectActions(objectId));
        return gson.fromJson(baseModel.getResponse().asString(), AllObjectActions.class);
    }

    @And("a webhook {string} with URL {string} for {string} created")
    public void aWebhookWithURLForCreated(String objectActionTriggerKey, String url, String objectName) {
        definedURL = url;
        ObjectDefinitions objectDefinitions = getObjectDefinitions();
        Integer objectId = null;
        for (int i = 0; i < objectDefinitions.getItems().size(); i++) {
            if (objectDefinitions.getItems().get(i).getName().equals(objectName)) {
                objectId = objectDefinitions.getItems().get(i).getId();
            }
            assert objectId != null;
        }
        Parameters parameters = getParameters(url);
        ObjectActionCreator objectActionCreator = getObjectActionCreator(objectName, objectActionTriggerKey, parameters);
        baseModel.setResponse(objectActionEndpoints.postObjectDefinitionObjectAction(objectActionCreator, objectId));
        baseModel.checkResponseCode(200);
        actionId = baseModel.getResponse().then().extract().path("id").toString();
    }

    @When("the URL in the webhook {string} for {string} updated to {string}")
    public void theURLInTheWebhookForUpdatedTo(String objectActionTriggerKey, String objectName, String url) {
        definedURL = url;
        ObjectDefinitions objectDefinitions = getObjectDefinitions();
        Integer objectId = null;
        for (int i = 0; i < objectDefinitions.getItems().size(); i++) {
            if (objectDefinitions.getItems().get(i).getName().equals(objectName)) {
                objectId = objectDefinitions.getItems().get(i).getId();
            }
            assert objectId != null;
        }
        Parameters parameters = getParameters(url);
        ObjectActionCreator objectActionCreator = getObjectActionCreator(objectName, objectActionTriggerKey, parameters);
        baseModel.setResponse(objectActionEndpoints.updateObjectDefinitionObjectAction(objectActionCreator, actionId));
        baseModel.checkResponseCode(200);
        Assert.assertEquals(baseModel.getResponse().then().extract().path("parameters.url").toString(), url);
    }

    @And("a webhook deleted")
    public void aWebhookWithURLForDeleted() {
        baseModel.setResponse(objectActionEndpoints.deleteObjectAction(actionId));
        baseModel.checkResponseCode(204);
    }

    private ObjectDefinitions getObjectDefinitions() {
        baseModel.setResponse(objectDefinitionEndpoints.getObjectDefinitions());
        return gson.fromJson(baseModel.getResponse().asString(), ObjectDefinitions.class);
    }

    private Parameters getParameters(String url) {
        return Parameters.builder()
                .url(url)
                .build();
    }

    private ObjectActionCreator getObjectActionCreator(String objectName, String objectActionTriggerKey, Parameters parameters) {
        return ObjectActionCreator.builder()
                .active(true)
                .name(objectActionTriggerKey + objectName)
                .objectActionTriggerKey(objectActionTriggerKey)
                .objectActionExecutorKey("webhook")
                .parameters(parameters)
                .build();
    }

    private ServerSocket createServerSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        return serverSocket;
    }

    private java.net.Socket socket(ServerSocket serverSocket) throws IOException {
        return serverSocket.accept();
    }

    private Thread getCreateManagerAccounts(DataTable dataTable) {
        return new Thread(() -> objectDefinitionSteps.managerAccountsCreated(dataTable));
    }

    private Thread getDeleteManagerAccounts() {
        return new Thread(objectDefinitionSteps::deleteManagers);
    }

    private Thread getUpdateUserAccount(Map<String, String> cucumberData) {
        return new Thread(() -> headlessAdminUserSteps.updateTheUser(cucumberData));
    }

    private Thread getCreateUserAccount(Map<String, String> cucumberData) {
        return new Thread(() -> headlessAdminUserSteps.aNewUserIsCreated(cucumberData));
    }

    private Thread getOpenThePortAndListen() {
        return new Thread(() -> {
            try {
                java.net.Socket echoSocket;
                BufferedReader in;
                echoSocket = socket(createServerSocket(Integer.parseInt(definedURL.substring(definedURL.length() - 4))));
                in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                char[] buffer = new char[3000];
                int output = in.read(buffer, 0, 3000);
                outputResponse = new String(buffer, 0, output);
                in.close();
                echoSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        );
    }

    private void joinThread(Thread thread) {
        for (; ; ) {
            try {
                thread.join(10000);
                break;
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

    @When("manager accounts created after setting up the webhook")
    public void managerAccountsCreatedAfterSettingUpTheWebhook(DataTable managers) {
        Thread openThePortAndListen = getOpenThePortAndListen();
        Thread createManagerAccounts = getCreateManagerAccounts(managers);
        openThePortAndListen.start();
        createManagerAccounts.start();
        joinThread(openThePortAndListen);
        joinThread(createManagerAccounts);
    }

    @Then("the information is sent to the URL {string} defined in the webhook")
    public void theInformationIsSentToTheURLDefinedInTheWebhook(String definedURL) {
        Assert.assertTrue(outputResponse.contains(definedURL));
    }

    @When("manager account deleted after setting up the webhook")
    public void managerAccountDeletedAfterSettingUpTheWebhook() {
        Thread openThePortAndListen = getOpenThePortAndListen();
        Thread deleteManagerAccounts = getDeleteManagerAccounts();
        openThePortAndListen.start();
        deleteManagerAccounts.start();
        joinThread(openThePortAndListen);
        joinThread(deleteManagerAccounts);
    }

    @When("updating the user after setting up the webhook")
    public void updatingTheUserAfterSettingUpTheWebhook(Map<String, String> cucumberData) {
        Thread openThePortAndListen = getOpenThePortAndListen();
        Thread updateTheUser = getUpdateUserAccount(cucumberData);
        openThePortAndListen.start();
        updateTheUser.start();
        joinThread(openThePortAndListen);
        joinThread(updateTheUser);
    }

    @Then("the payload is matching the JSON format defined for {string} in the Headless API")
    public void thePayloadIsMatchingTheJSONFormatDefinedForInTheHeadlessAPI(String objectDefinitionType,Map<String, String> cucumberData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        switch (objectDefinitionType) {
            case "userUpdate":
                try {
                    mapper.readValue(outputResponse.substring(outputResponse.indexOf("{")), PayloadUserAccountUpdated.class);
                } catch (JsonParseException | JsonMappingException e) {
                    Assert.fail("Does not match! "+e);
                }
                PayloadUserAccountUpdated payloadUserAccountUpdated = gson.fromJson(outputResponse.substring(outputResponse.indexOf("{")), PayloadUserAccountUpdated.class);
                Assert.assertNotNull(payloadUserAccountUpdated.getModelUser());
                Assert.assertNotNull(payloadUserAccountUpdated.getOriginalUser());
                assertAll("Should return body with correct information",
                        () -> assertEquals(cucumberData.get("alternateName"),payloadUserAccountUpdated.getModelUser().getAlternateName()),
                        () -> assertEquals(cucumberData.get("emailAddress"),payloadUserAccountUpdated.getModelUser().getEmailAddress()),
                        () -> assertEquals(cucumberData.get("givenName"),payloadUserAccountUpdated.getModelUser().getGivenName()),
                        () -> assertEquals(cucumberData.get("familyName"),payloadUserAccountUpdated.getModelUser().getFamilyName())
                );
                break;
            case "managerDeletion":
                try {
                    mapper.readValue(outputResponse.substring(outputResponse.indexOf("{")), PayloadCustomObject.class);
                } catch (JsonParseException | JsonMappingException e) {
                    Assert.fail("Does not match! "+e);
                }
                PayloadCustomObject payloadCustomObject = gson.fromJson(outputResponse.substring(outputResponse.indexOf("{")), PayloadCustomObject.class);
                Assert.assertNotNull(payloadCustomObject.getObjectEntry());
                break;
            case "managerExternalModel":
                try {
                    mapper.readValue(outputResponse.substring(outputResponse.indexOf("{")), PayloadCustomObject.class);
                } catch (JsonParseException | JsonMappingException e) {
                    Assert.fail("Does not match! "+e);
                }
                PayloadCustomObject payloadCustomObject1 = gson.fromJson(outputResponse.substring(outputResponse.indexOf("{")),PayloadCustomObject.class);
                Assert.assertEquals(cucumberData.get("firstname"),payloadCustomObject1.getObjectEntry().getProperties().getFirstname());
                break;
            case "userExternalModel":
                try {
                    mapper.readValue(outputResponse.substring(outputResponse.indexOf("{")), PayloadUserAccountCreated.class);
                } catch (JsonParseException | JsonMappingException e) {
                    Assert.fail("Does not match! "+e);
                }
                PayloadUserAccountCreated payloadUserAccountCreated = gson.fromJson(outputResponse.substring(outputResponse.indexOf("{")), PayloadUserAccountCreated.class);
                assertAll("Should return body with correct information",
                        () -> assertEquals(cucumberData.get("alternateName"),payloadUserAccountCreated.getModelUser().getAlternateName()),
                        () -> assertEquals(cucumberData.get("emailAddress"),payloadUserAccountCreated.getModelUser().getEmailAddress()),
                        () -> assertEquals(cucumberData.get("givenName"),payloadUserAccountCreated.getModelUser().getGivenName()),
                        () -> assertEquals(cucumberData.get("familyName"),payloadUserAccountCreated.getModelUser().getFamilyName())
                );
                break;
            default:
                Assert.fail("no match");
        }
    }

    @Then("the information is not sent to the URL defined in the webhook")
    public void theInformationIsNotSentToTheURLDefinedInTheWebhook() {
        Assert.assertNull(outputResponse);
    }

    @When("a new user is created after setting up the webhook")
    public void aNewUserIsCreatedAfterSettingUpTheWebhook(Map<String, String> cucumberData) {
        Thread openThePortAndListen = getOpenThePortAndListen();
        Thread createTheUser = getCreateUserAccount(cucumberData);
        openThePortAndListen.start();
        createTheUser.start();
        joinThread(openThePortAndListen);
        joinThread(createTheUser);
    }
}
