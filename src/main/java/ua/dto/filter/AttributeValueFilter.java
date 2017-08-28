package ua.dto.filter;

public class AttributeValueFilter {
    private String value = "";

    public AttributeValueFilter() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AttributeValueFilter{" +
                "value='" + value + '\'' +
                '}';
    }
}
