package ua.dto.filter;

public class NameFilter {
    private String name = "";

    public NameFilter() {
    }

    public NameFilter(String name) {
        this.name = name;
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
