package tests.steps;

import com.google.gson.Gson;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tests.model.BaseModel;
import tests.model.headlessDelivery.blogPostCreated.BlogPostCreated;
import tests.model.headlessDelivery.blogPosting.BlogPostCreator;
import tests.model.headlessDelivery.blogPosting.BlogPostingEndpoints;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BlogStepDefs {
    private BaseModel baseModel;
    private BlogPostCreator blogPostCreator;
    private BlogPostingEndpoints blogPostingEndpoints;
    private Gson gson = new Gson();
    private Map<String, String>entryData = new HashMap<>();

    public BlogStepDefs(BaseModel baseModel) {
        this.baseModel = baseModel;
        this.blogPostingEndpoints = new BlogPostingEndpoints();
    }

    @When("I make a valid call to create a blog post")
    public void iMakeAValidCallToCreateABlogPost(Map<String, String> data) {
        entryData = data;
        blogPostCreator = BlogPostCreator.builder()
                .articleBody(data.get("articleBody"))
                .dateCreated(data.get("dateCreated"))
                .dateModified(data.get("dateModified"))
                .datePublished(data.get("datePublished"))
                .encodingFormat(data.get("encodingFormat"))
                .friendlyUrlPath(data.get("friendlyUrlPath"))
                .headline(data.get("headline"))
                .numberOfComments(Integer.parseInt(data.get("numberOfComments")))
                .build();
        baseModel.setResponse(blogPostingEndpoints.createBlogPost(blogPostCreator));
    }

    @Then("the blog post is created")
    public void theBlogPostIsCreated() {
        baseModel.checkResponseCode(200);
    }

    @Then("the blog post is being created with a valid data")
    public void theBlogPostIsBeingCreatedWithAValidData() {
        BlogPostCreated blogPostCreated = gson.fromJson(baseModel.getResponse().asString(), BlogPostCreated.class);
        assertAll("Should return body with correct information",
                () -> assertEquals(entryData.get("friendlyUrlPath"),blogPostCreated.getFriendlyUrlPath()),
                () -> assertEquals(entryData.get("headline"),blogPostCreated.getHeadline()),
                () -> assertEquals(entryData.get("numberOfComments"),blogPostCreated.getNumberOfComments().toString()),
                () -> assertEquals(entryData.get("articleBody"),blogPostCreated.getArticleBody()),
                () -> assertFalse(blogPostCreated.getId().toString().isEmpty()));
    }
}
