package tests.model;

import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import tests.model.remoteAppEntryServiceImpl.AppCreatorBody;
import tests.utils.ConfigFileReader;

@Data
public class BaseModel {
    private Response response;
    private Header header;
    private Gson gson = new Gson();
    private ConfigFileReader config = ConfigFileReader.getInstance();
    private AppCreatorBody appCreatorBody;
    private RequestSpecification requestSpecification;

    public BaseModel() {
    }

    public void checkResponseCode(int code) {
        getResponse().then().assertThat().statusCode(code);
    }
}
