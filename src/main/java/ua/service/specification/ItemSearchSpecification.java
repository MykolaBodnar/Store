package ua.service.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.dto.filter.NameFilter;
import ua.entity.Item;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ItemSearchSpecification implements Specification<Item> {
    private final NameFilter nameFilter;

    public ItemSearchSpecification(NameFilter nameFilter) {
        this.nameFilter = nameFilter;
    }

    @Override
    public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (nameFilter.getName().isEmpty()) {
            return null;
        }
        return criteriaBuilder.like(root.get("name"), nameFilter.getName() + "%");
    }
}
