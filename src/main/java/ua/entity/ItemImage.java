package ua.entity;

import javax.persistence.*;

@Entity
public class ItemImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String path;
    @ManyToOne
    private Item item;

    public ItemImage() {

    }

    public ItemImage(Item item) {
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "CommodityImage{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", item=" + item +
                '}';
    }
}
