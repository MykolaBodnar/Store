package ua.dto;

public class ItemListDto {
    private int id;
    private String name;
    private String price;
    private String mainImage;
    private String description;

    public ItemListDto() {
    }

    public ItemListDto(int id, String name, String price, String mainImage, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mainImage = mainImage;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ItemListDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
