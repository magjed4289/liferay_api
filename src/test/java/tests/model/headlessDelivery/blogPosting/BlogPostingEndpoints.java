package tests.model.headlessDelivery.blogPosting;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
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
                .basic(config.getConfiguration().getEmail(), config.getConfiguration().getPassword())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .pathParam("siteId", config.getConfiguration().getSiteId())
                .body(blogPostCreator)
                .when()
                .post("/o/headless-delivery/v1.0/sites/{siteId}/blog-postings");
    }
}
