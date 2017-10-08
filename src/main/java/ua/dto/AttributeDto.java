package ua.dto;

import java.util.ArrayList;
import java.util.List;

public class AttributeDto {
    String name;
    List<AttributeValueDto> values = new ArrayList<>();

    public AttributeDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AttributeValueDto> getValues() {
        return values;
    }

    public void setValues(List<AttributeValueDto> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "AttributeDto{" +
                "name='" + name + '\'' +
                ", values=" + values +
                '}';
    }
}
