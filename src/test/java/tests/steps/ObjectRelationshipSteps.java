package tests.steps;

import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import tests.model.BaseModel;
import tests.model.object.Label;
import tests.model.object.objectDefinition.ObjectDefinitionEndpoints;
import tests.model.object.objectRelationship.ObjectRelationshipData;
import tests.model.object.objectRelationship.ObjectRelationshipEndopoints;
import tests.model.object.objectRelationship.ObjectRelationships;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectRelationshipSteps {
    private final BaseModel baseModel;
    private final ObjectRelationshipEndopoints objectRelationshipEndpoints;
    private final ObjectDefinitionEndpoints objectDefinitionEndpoints;
    private final Gson gson = new Gson();
    private final List<String> objectsIdsList;
    private final List<String> relationshipIdsList;
    private final List<String> relationshipNamesList;

    public ObjectRelationshipSteps(BaseModel baseModel, ObjectDefinitionSteps objectDefinitionSteps, ObjectDefinitionEndpoints objectDefinitionEndpoints) {
        this.baseModel = baseModel;
        this.objectRelationshipEndpoints = new ObjectRelationshipEndopoints();
        this.objectDefinitionEndpoints = new ObjectDefinitionEndpoints();
        this.objectsIdsList = objectDefinitionSteps.objectIdsList;
        this.relationshipIdsList = new ArrayList<>();
        this.relationshipNamesList = new ArrayList<>();
    }

    @Given("a relationship between two object definitions created")
    public void aRelationshipBetweenTwoObjectDefinitionsCreated(Map<String, String> data) {
        String object1id = null;
        String object2id = null;
        for (String objectID : objectsIdsList) {
            baseModel.setResponse(objectDefinitionEndpoints.getObjectDefinition(objectID));
            String name = baseModel.getResponse().then().extract().path("name").toString();
            if (name.equals(data.get("object1name"))) {
                object1id = objectID;
            } else if (name.equals(data.get("object2name"))) {
                object2id = objectID;
            }
        }
        Label labelRelationship = Label.builder()
                .en_US(data.get("en_US_label"))
                .build();
        assert object2id != null;
        baseModel.setResponse(objectRelationshipEndpoints.getObjectRelationships(object1id));
        ObjectRelationships objectRelationships = gson.fromJson(baseModel.getResponse().asString(), ObjectRelationships.class);
        if (objectRelationships.getItems().size() > 0) {
            for (int i = 0; i < objectRelationships.getItems().size(); i++) {
                String relationshipName = objectRelationships.getItems().get(i).getName();
                relationshipNamesList.add(relationshipName);
            }
        }
        if (relationshipNamesList.isEmpty() || !relationshipNamesList.contains(data.get("name"))) {
            ObjectRelationshipData objectRelationshipData = ObjectRelationshipData.builder()
                    .deletionType("cascade")
                    .name(data.get("name"))
                    .label(labelRelationship)
                    .type(data.get("type"))
                    .objectDefinitionId2(Integer.valueOf(object2id))
                    .build();
            baseModel.setResponse(objectRelationshipEndpoints.createObjectRelationship(object1id, objectRelationshipData));
            String relationshipId = baseModel.getResponse().then().extract().path("id").toString();
            relationshipIdsList.add(relationshipId);
            baseModel.checkResponseCode(200);
        } else {
            for (int i = 0; i < objectRelationships.getItems().size(); i++) {
                if(objectRelationships.getItems().get(i).getName().equals(data.get("name"))){
                    String relationshipId = String.valueOf(objectRelationships.getItems().get(i).getId());
                    relationshipIdsList.add(relationshipId);
                }
            }
        }
    }
}
