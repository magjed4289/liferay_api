
package tests.model.object.objectDefinition.employeesWithAggregationTerms;

import lombok.Builder;
import lombok.Data;
import tests.model.object.Actions;
import tests.model.object.objectDefinition.Item;

import java.util.List;

@Data @Builder
public class EmployeesWithAggregationTerms {

    public Actions actions;
    public List<Facet> facets;
    public List<Item> items;
    public Integer lastPage;
    public Integer page;
    public Integer pageSize;
    public Integer totalCount;
}
