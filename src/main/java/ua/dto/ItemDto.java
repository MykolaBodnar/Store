package ua.dto;

import java.util.ArrayList;
import java.util.List;

public class ItemDto {
    private int id;
    private String name;
    private String price;
    private String mainImage;
    private String description;
    private CategoryDto category;
    private List<ItemAttributeDto> attributes = new ArrayList<>();
    private List<String> images = new ArrayList<>();

    public ItemDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<ItemAttributeDto> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ItemAttributeDto> attributes) {
        this.attributes = attributes;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", category=" + category +
                ", attributes=" + attributes +
                ", images=" + images +
                '}';
    }
}
