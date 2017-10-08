package ua.dto.filter;

import ua.dto.AttributeDto;
import ua.dto.CategoryDto;
import ua.dto.ProducerDto;

import java.util.ArrayList;
import java.util.List;

public class ItemFilterParam {
    CategoryDto category;
    List<AttributeDto> attributes = new ArrayList<>();
    List<ProducerDto> producers = new ArrayList<>();

    public ItemFilterParam() {
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<AttributeDto> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeDto> attributes) {
        this.attributes = attributes;
    }

    public List<ProducerDto> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducerDto> producers) {
        this.producers = producers;
    }

    @Override
    public String toString() {
        return "ItemFilterParam{" +
                "category=" + category +
                ", attributes=" + attributes +
                ", producers=" + producers +
                '}';
    }
}
