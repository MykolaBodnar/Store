package ua.service.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.dto.filter.NameFilter;
import ua.entity.Category;
import ua.entity.Attribute;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AttributeExcludeCategorySpecification implements Specification<Attribute> {

    private final NameFilter filter;

    private final Category category;

    private final List<Predicate> predicates = new ArrayList<>();

    public AttributeExcludeCategorySpecification(NameFilter filter, Category category) {
        this.category = category;
        this.filter = filter;
    }

    private void addExcludeFilter(Root<Attribute> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (category.getAttributes() != null && !category.getAttributes().isEmpty()) {
            List<Integer> ids = category.getAttributes().stream()
                    .map(Attribute::getId).collect(Collectors.toList());
            predicates.add(criteriaBuilder.not(root.get("id").in(ids)));
        }
    }

    private void addFilterByName(Root<Attribute> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (!filter.getName().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("name"), filter.getName() + "%"));
        }
    }

    @Override
    public Predicate toPredicate(Root<Attribute> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        addExcludeFilter(root, criteriaQuery, criteriaBuilder);
        addFilterByName(root, criteriaQuery, criteriaBuilder);
        if (predicates.isEmpty()) return null;
        Predicate[] result = new Predicate[predicates.size()];
        return criteriaBuilder.and(predicates.toArray(result));

    }
}
