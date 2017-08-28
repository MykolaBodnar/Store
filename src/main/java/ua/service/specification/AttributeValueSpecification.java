package ua.service.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.dto.filter.AttributeValueFilter;
import ua.entity.AttributeValue;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class AttributeValueSpecification implements Specification<AttributeValue>{
    private final int stringAttributeId;
    private final AttributeValueFilter filter;
    private final List<Predicate> predicates = new ArrayList<>();

    public AttributeValueSpecification(int stringAttributeId, AttributeValueFilter filter) {
        this.stringAttributeId = stringAttributeId;
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<AttributeValue> root, CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {
        fetch(root,criteriaQuery);
        filterByStringAttribute(root);
        filterByValue(root,criteriaBuilder);
        if(predicates.isEmpty()) return null;
        Predicate[] predicatesArray = new Predicate[predicates.size()];
        predicates.toArray(predicatesArray);
        return criteriaBuilder.and(predicatesArray);
    }

    private void fetch(Root<AttributeValue> root, CriteriaQuery<?> query){
        if(query.getResultType()!=Long.class){
            root.fetch("attribute", JoinType.LEFT);
        }
    }
    private void filterByStringAttribute(Root<AttributeValue> root) {
        if(stringAttributeId!=0){
            predicates.add(root.get("attribute").in(stringAttributeId));
        }
    }
    private void filterByValue(Root<AttributeValue> root, CriteriaBuilder criteriaBuilder) {
        if(!filter.getValue().isEmpty()){
            predicates.add(criteriaBuilder.like(root.get("value"), filter.getValue()+"%"));
        }
    }
}