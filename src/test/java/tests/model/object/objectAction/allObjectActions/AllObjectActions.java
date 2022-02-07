
package tests.model.object.objectAction.allObjectActions;

import lombok.Builder;
import lombok.Data;
import tests.model.object.Actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Data
public class AllObjectActions {

    public Actions actions;
    public List<Object> facets = null;
    public List<Item> items = null;
    public Integer lastPage;
    public Integer page;
    public Integer pageSize;
    public Integer totalCount;
}
