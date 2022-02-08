package tests.steps.object;

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
    private final List<String> relationshipNamesList;

    public ObjectRelationshipSteps(BaseModel baseModel, ObjectDefinitionSteps objectDefinitionSteps) {
        this.baseModel = baseModel;
        this.objectRelationshipEndpoints = new ObjectRelationshipEndopoints();
        this.objectDefinitionEndpoints = new ObjectDefinitionEndpoints();
        this.objectsIdsList = objectDefinitionSteps.objectIdsList;
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
        assert object2id != null;
        baseModel.setResponse(objectRelationshipEndpoints.getObjectRelationships(object1id));
        ObjectRelationships objectRelationships = gson.fromJson(baseModel.getResponse().asString(), ObjectRelationships.class);
        if (objectRelationships.getItems().size() > 0) {
            for (int i = 0; i < objectRelationships.getItems().size(); i++) {
                relationshipNamesList.add(objectRelationships.getItems().get(i).getName());
            }
        }
        if (relationshipNamesList.isEmpty() || !relationshipNamesList.contains(data.get("name"))) {
            baseModel.setResponse(objectRelationshipEndpoints.createObjectRelationship(
                    object1id,
                    getObjectRelationshipData(
                            data,
                            object2id
                    )
            ));
            baseModel.checkResponseCode(200);
        }
    }

    private ObjectRelationshipData getObjectRelationshipData(Map<String, String> data, String object2id) {
        return ObjectRelationshipData.builder()
                .deletionType("cascade")
                .name(data.get("name"))
                .label(getLabel(data))
                .type(data.get("type"))
                .objectDefinitionId2(Integer.valueOf(object2id))
                .build();
    }

    private Label getLabel(Map<String, String> data) {
        return Label.builder()
                .en_US(data.get("en_US_label"))
                .build();
    }
}
