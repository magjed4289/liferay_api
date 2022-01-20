
package tests.model.object.objectDefinition;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ObjectLayoutBox {

    @SerializedName("collapsable")
    @Expose
    private Boolean collapsable;
    @SerializedName("name")
    @Expose
    private Name__2 name;
    @SerializedName("objectLayoutRows")
    @Expose
    private List<ObjectLayoutRow> objectLayoutRows = null;
    @SerializedName("priority")
    @Expose
    private Integer priority;

    public Boolean getCollapsable() {
        return collapsable;
    }

    public void setCollapsable(Boolean collapsable) {
        this.collapsable = collapsable;
    }

    public Name__2 getName() {
        return name;
    }

    public void setName(Name__2 name) {
        this.name = name;
    }

    public List<ObjectLayoutRow> getObjectLayoutRows() {
        return objectLayoutRows;
    }

    public void setObjectLayoutRows(List<ObjectLayoutRow> objectLayoutRows) {
        this.objectLayoutRows = objectLayoutRows;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}
