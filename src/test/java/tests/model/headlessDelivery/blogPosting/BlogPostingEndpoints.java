package tests.model.headlessDelivery.blogPosting;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tests.model.RemoteAppEntryServiceImpl.AppCreatorBody;
import tests.model.RemoteAppEntryServiceImpl.RemoteAppEntryModel;
import tests.utils.ConfigFileReader;

import static io.restassured.RestAssured.given;

public class BlogPostingEndpoints {
    private final ConfigFileReader config = ConfigFileReader.getInstance();
    public RequestSpecification requestSpecification;

    public BlogPostingEndpoints() {
    }

    public Response createBlogPost(BlogPostCreator blogPostCreator) {
        return given()
                .auth()
                .preemptive()
                .basic(config.getConfiguracion().getEmail(), config.getConfiguracion().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .pathParam("siteId",config.getConfiguracion().getSiteId())
                .body(blogPostCreator)
                        .when()
                        .post("/o/headless-delivery/v1.0/sites/{siteId}/blog-postings");
    }
}
