package tests.model;

import com.google.gson.Gson;
import com.sun.deploy.net.MessageHeader;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import tests.model.remote_app_entry_service_impl.AppCreatorBody;
import tests.utils.ConfigFileReader;

import java.util.ArrayList;
import java.util.List;

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