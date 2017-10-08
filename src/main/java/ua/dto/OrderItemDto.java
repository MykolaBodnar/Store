package ua.dto;

public class OrderItemDto {
    private int id;
    private int count;
    private String price;
    private String name;
    private  String mainImage;

    public OrderItemDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    @Override
    public String toString() {
        return "OrderItemDto{" +
                "id=" + id +
                ", count=" + count +
                ", price='" + price + '\'' +
                ", name='" + name + '\'' +
                ", mainImage='" + mainImage + '\'' +
                '}';
    }
}
