
package tests.model.object.objectDefinition.employeesWithAggregationTerms;

import lombok.Data;

import java.util.List;

@Data
public class Facet {

    public String facetCriteria;
    public List<FacetValue> facetValues = null;
}
