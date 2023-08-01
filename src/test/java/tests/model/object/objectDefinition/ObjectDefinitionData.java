package tests.model.object.objectDefinition;

public class ObjectDefinitionData {
    private String name;
    private String en_US_label;
    private String en_US_plural_label;
    private String fieldErc;
    private String requiredStringField;
    private String externalReferenceCode;

    public ObjectDefinitionData(String name, String en_US_label, String en_US_plural_label, String fieldErc, String requiredStringField,String externalReferenceCode) {
        this.name = name;
        this.en_US_label = en_US_label;
        this.en_US_plural_label = en_US_plural_label;
        this.fieldErc = fieldErc;
        this.requiredStringField = requiredStringField;
        this.externalReferenceCode = externalReferenceCode;
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

    public String getFieldErc() {
        return fieldErc;
    }

    public void setFieldErc(String fieldErc) {
        this.fieldErc = fieldErc;
    }

    public String getExternalReferenceCode() {
        return externalReferenceCode;
    }

    public void setExternalReferenceCode(String externalReferenceCode) {
        this.externalReferenceCode = externalReferenceCode;
    }

}
