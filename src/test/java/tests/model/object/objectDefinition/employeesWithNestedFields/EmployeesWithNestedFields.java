
package tests.model.object.objectDefinition.employeesWithNestedFields;

import lombok.Builder;
import lombok.Data;
import tests.model.object.Actions;

import java.util.List;

@Data @Builder
public class EmployeesWithNestedFields {

    public Actions actions;
    public List<Object> facets;
    public List<Item> items;
    public Integer lastPage;
    public Integer page;
    public Integer pageSize;
    public Integer totalCount;
}
