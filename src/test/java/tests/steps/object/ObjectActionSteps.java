package tests.steps.object;

import com.google.gson.Gson;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tests.model.BaseModel;
import tests.model.object.objectAction.Parameters;
import tests.model.object.objectAction.ObjectActionCreator;
import tests.model.object.objectAction.allObjectActions.AllObjectActions;
import tests.model.object.objectDefinition.ObjectDefinitionEndpoints;
import tests.model.object.objectDefinition.objectDefinitions.ObjectDefinitions;
import tests.model.object.objectAction.ObjectActionEndpoints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;


public class ObjectActionSteps {
    public final ObjectActionEndpoints objectActionEndpoints;
    public final ObjectDefinitionEndpoints objectDefinitionEndpoints;
    public final ObjectDefinitionSteps objectDefinitionSteps;

    public final BaseModel baseModel;
    private final Gson gson = new Gson();
    ServerSocket serverSocket;


    public ObjectActionSteps(ObjectActionEndpoints objectActionEndpoints, ObjectDefinitionEndpoints objectDefinitionEndpoints, BaseModel baseModel, ObjectDefinitionSteps objectDefinitionSteps) {
        this.objectActionEndpoints = objectActionEndpoints;
        this.baseModel = baseModel;
        this.objectDefinitionEndpoints = objectDefinitionEndpoints;
        this.objectDefinitionSteps = objectDefinitionSteps;
    }

    @After()
    public void deleteObjectDefinitionActions() {
        ObjectDefinitions objectDefinitions = getObjectDefinitions();
        for (int i = 0; i < objectDefinitions.getItems().size(); i++) {
            AllObjectActions allObjectActions = getAllObjectActions(objectDefinitions.getItems().get(i).getId());
            for (int j = 0; j < allObjectActions.getItems().size(); j++) {
                baseModel.setResponse(objectActionEndpoints.deleteObjectAction(allObjectActions.getItems().get(j).getId()));
                baseModel.checkResponseCode(204);
            }
        }
    }

    private AllObjectActions getAllObjectActions(Integer objectId) {
        baseModel.setResponse(objectActionEndpoints.getDefinitionObjectActions(objectId));
        return gson.fromJson(baseModel.getResponse().asString(), AllObjectActions.class);
    }

    @Given("up the port")
    public void upThePort() {

    }

    @And("a webhook {string} with url {string} for {string} created")
    public void aWebhookWithUrlForCreated(String objectActionTriggerKey, String url, String objectName) {
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

    public String checkTheOutputOfWebhookCreated(DataTable dataTable) {
        final String[] outputMessage = new String[1];
        Thread openThePortAndListen = new Thread(new Runnable() {
            public void run() {
                try {
                    java.net.Socket echoSocket;
                    BufferedReader in;
                    echoSocket = socket(createServerSocket(8888));
                    in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                    char[] buffer = new char[1000];
                    int output = in.read(buffer, 0, 1000);
                    outputMessage[0] = new String(buffer, 0, output);
                    System.out.println("This is my message: " + outputMessage[0]);
                    outputResponse= outputMessage[0];
                    in.close();
                    echoSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        );
        Thread createManagerAccounts = new Thread(new Runnable() {
            public void run() {
                objectDefinitionSteps.managerAccountsCreated(dataTable);
            }
        });
        openThePortAndListen.start();
        createManagerAccounts.start();
        return outputMessage[0];
    }

    public String outputResponse;

    @When("manager accounts created after setting up the webhook")
    public void managerAccountsCreatedAfterSettingUpTheWebhook(DataTable managers) {
        outputResponse = checkTheOutputOfWebhookCreated(managers);
    }

    @Then("the information of the {string} is sent to the URL defined in the webhook")
    public void theInformationOfTheIsSentToTheURLDefinedInTheWebhook(String customObject) {
        System.out.println("This is the final step "+outputResponse);
    }
}
