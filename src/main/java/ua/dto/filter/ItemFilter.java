package ua.dto.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemFilter {
    private String name = "";
    private int minPrice = 0;
    private int maxPrice = 0;
    private List<Integer> producerIds = new ArrayList<>();
    private List<List<Integer>> stringAttributeIds = new ArrayList<>();
    private int categoryId = 0;

    public ItemFilter() {

    }

    public List<Integer> getProducerIds() {
        return producerIds;
    }

    public void setProducerIds(List<Integer> producerIds) {
        this.producerIds = producerIds;
    }

    public List<List<Integer>> getStringAttributeIds() {
        return stringAttributeIds;
    }

    public void setStringAttributeIds(List<List<Integer>> stringAttributeIds) {
        this.stringAttributeIds = stringAttributeIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ItemFilter{" +
                "name='" + name + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", producerIds=" + producerIds +
                ", stringAttributeIds=" + stringAttributeIds +
                ", categoryId=" + categoryId +
                '}';
    }
}
