package ua.dto.filter;

public class NameFilter {
    private String name = "";

    public NameFilter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NameFilter{" +
                "name='" + name + '\'' +
                '}';
    }
}
