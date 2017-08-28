package ua.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Attribute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private boolean forSelect;

	@ManyToMany
	@JoinTable(name = "category_attribute",
			joinColumns = @JoinColumn(name = "attribute_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private List<Category> categories;

	@OneToMany(mappedBy="attribute", orphanRemoval=true)
	List<AttributeValue> attributeValues;

	public Attribute() {

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

	public boolean isForSelect() {
		return forSelect;
	}

	public void setForSelect(boolean forSelect) {
		this.forSelect = forSelect;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<AttributeValue> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(List<AttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Attribute that = (Attribute) o;

		if (id != that.id) return false;
		return name != null ? name.equals(that.name) : that.name == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Attribute{" +
				"id=" + id +
				", name='" + name + '\'' +
				", forSelect=" + forSelect +
				'}';
	}
}
