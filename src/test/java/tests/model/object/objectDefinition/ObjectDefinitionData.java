package tests.model.object.objectDefinition;

public class ObjectDefinitionData {
    private String name;
    private String en_US_label;
    private String en_US_plural_label;
    private String requiredStringField;

    public ObjectDefinitionData(String name, String en_US_label, String en_US_plural_label, String requiredStringField) {
        this.name = name;
        this.en_US_label = en_US_label;
        this.en_US_plural_label = en_US_plural_label;
        this.requiredStringField = requiredStringField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEn_US_label() {
        return en_US_label;
    }

    public void setEn_US_label(String en_US_label) {
        this.en_US_label = en_US_label;
    }

    public String getEn_US_plural_label() {
        return en_US_plural_label;
    }

    public void setEn_US_plural_label(String en_US_plural_label) {
        this.en_US_plural_label = en_US_plural_label;
    }

    public String getRequiredStringField() {
        return requiredStringField;
    }

    public void setRequiredStringField(String requiredStringField) {
        this.requiredStringField = requiredStringField;
    }
}
