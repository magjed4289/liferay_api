package tests.utils;

import tests.model.BaseModel;

public class BaseActions {

    private BaseModel baseModel;

    private final String dir = System.getProperty("user.dir") + "/src/test/resources/filesToLoad/";

    public BaseActions(BaseModel baseModel) {
        this.baseModel = baseModel;
    }
}