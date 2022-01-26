
package tests.model.object.objectDefinition;

import lombok.Data;
import tests.model.object.Actions;


import java.util.List;

@Data
public class ObjectDefinitions {

    public Actions actions;
    public List<Object> facets = null;
    public List<Item> items = null;
    public Integer lastPage;
    public Integer page;
    public Integer pageSize;
    public Integer totalCount;
}
