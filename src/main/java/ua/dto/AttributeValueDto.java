package ua.dto;

public class AttributeValueDto {
    private int id;
    private String value;

    public AttributeValueDto(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AttributeValueDto{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
