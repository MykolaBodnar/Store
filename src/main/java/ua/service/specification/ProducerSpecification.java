package ua.service.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.dto.filter.NameFilter;
import ua.entity.Category;
import ua.entity.Producer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProducerSpecification implements Specification<Producer> {

    private final NameFilter filter;

    public ProducerSpecification(NameFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Producer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if(filter.getName().isEmpty()) return null;
        return criteriaBuilder.like(root.get("name"), filter.getName()+"%");
    }
}
