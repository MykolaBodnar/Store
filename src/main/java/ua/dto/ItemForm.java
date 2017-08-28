package ua.dto;

import org.springframework.web.multipart.MultipartFile;
import ua.entity.AttributeValue;
import ua.entity.Category;
import ua.entity.Producer;

import java.math.BigDecimal;
import java.util.List;

public class ItemForm {
    private int id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private Producer producer;
    private Category category;
    private MultipartFile file;
    private List<AttributeValue> attributeValues;

    public ItemForm() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ItemForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", producer=" + producer +
                ", category=" + category +
                ", file=" + file +
                ", attributeValues=" + attributeValues +
                '}';
    }
}
