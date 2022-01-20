package tests.steps;

import com.google.gson.Gson;
import tests.model.BaseModel;
import tests.model.object.objectDefinition.ObjectDefinitionEndpoints;

import java.util.HashMap;
import java.util.Map;

public class ObjectDefinitionSteps {
    private final BaseModel baseModel;
    private final ObjectDefinitionEndpoints objectDefinitionEndpoints;
    private final Gson gson = new Gson();
    private Map<String, String> entryData = new HashMap<>();

    public ObjectDefinitionSteps(BaseModel baseModel) {
        this.baseModel = baseModel;
        this.objectDefinitionEndpoints = new ObjectDefinitionEndpoints();
    }
}
