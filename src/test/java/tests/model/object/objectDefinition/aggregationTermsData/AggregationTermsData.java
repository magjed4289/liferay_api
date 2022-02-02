package tests.model.object.objectDefinition.aggregationTermsData;

import lombok.Data;

@Data
public class AggregationTermsData {
    String numberOfOccurrences;
    String relatedManagerFirstname;

    public AggregationTermsData(String numberOfOccurrences, String relatedManagerFirstname) {
        this.numberOfOccurrences = numberOfOccurrences;
        this.relatedManagerFirstname = relatedManagerFirstname;
    }
}
