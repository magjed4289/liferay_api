package tests.model.graphQL;

import lombok.Data;

@Data
public class GraphQLQuery {

    private String query;
    private Object variables;
}
