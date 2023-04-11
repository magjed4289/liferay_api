package tests.steps.object;

import com.google.gson.Gson;
import io.cucumber.java.en.When;
import tests.model.BaseModel;
import tests.model.graphQL.GraphQLEndpoint;
import tests.model.graphQL.GraphQLQuery;

public class GraphQLSteps {

    public final BaseModel baseModel;
    public final GraphQLEndpoint graphQLEndpoint;
    private final Gson gson = new Gson();


    public GraphQLSteps(BaseModel baseModel, GraphQLEndpoint graphQLEndpoint) {
        this.baseModel = baseModel;
        this.graphQLEndpoint = graphQLEndpoint;
    }

    @When("performing a GraphQL query {string}")
    public void performingAGraphQLQuery(String query) {
        GraphQLQuery graphQLQuery = new GraphQLQuery();
        graphQLQuery.setQuery(query);
        baseModel.setResponse(graphQLEndpoint.graphQLQuery(graphQLQuery));
        System.out.println(baseModel.getResponse().prettyPrint());
    }
}
