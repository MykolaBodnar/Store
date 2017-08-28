package ua.service.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.dto.filter.NameFilter;
import ua.entity.Attribute;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AttributeSpecification implements Specification<Attribute> {

    private final NameFilter filter;

    public AttributeSpecification(NameFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Attribute> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (filter.getName().isEmpty()) return null;
        return criteriaBuilder.like(root.get("name"), filter.getName() + "%");
    }
}
