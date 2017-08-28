package ua.service.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.dto.filter.ItemFilter;
import ua.entity.AttributeValue;
import ua.entity.Item;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ItemSpecification implements Specification<Item> {

    private final ItemFilter filter;
    private final List<Predicate> predicates = new ArrayList<>();

    public ItemSpecification(ItemFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        filterByName(root,criteriaBuilder);
        filterByPrice(root,criteriaBuilder);
        fetch(root,criteriaQuery);
        criteriaQuery.distinct(true);
        filterByProducer(root);
        filterByCategory(root);
        filterByStringAttribute(root);
        if(predicates.isEmpty()) return null;
        Predicate[] predicatesArray = new Predicate[predicates.size()];
        predicates.toArray(predicatesArray);
        return criteriaBuilder.and(predicatesArray);
    }

    private void filterByCategory(Root<Item> root) {
        if(filter.getCategoryId()!=0){
            predicates.add(root.get("category").in(filter.getCategoryId()));
        }

    }

    private void filterByStringAttribute(Root<Item> root) {
        if(!filter.getStringAttributeIds().isEmpty()){

            for (List<Integer> ids:filter.getStringAttributeIds()) {
                if(!ids.isEmpty()){
                    Join<Item, AttributeValue> join = root.join("attributeValues");
                    predicates.add(join.get("id").in(ids));
                }
            }

        }
    }

    private void filterByProducer(Root<Item> root) {
        if(!filter.getProducerIds().isEmpty()){
            predicates.add(root.get("producer").in(filter.getProducerIds()));
        }
    }

    private void filterByPrice(Root<Item> root, CriteriaBuilder criteriaBuilder) {
        if((filter.getMaxPrice()!=0)&&(filter.getMinPrice()!=0)){
            predicates.add(criteriaBuilder.between(root.get("price"), filter.getMinPrice(), filter.getMaxPrice()));
        } else if(filter.getMaxPrice()!=0){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),filter.getMaxPrice()));
        } else if(filter.getMinPrice()!=0){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),filter.getMinPrice()));
        }
    }

    private void filterByName(Root<Item> root, CriteriaBuilder criteriaBuilder) {
        if(!filter.getName().isEmpty()){
            predicates.add(criteriaBuilder.like(root.get("name"), filter.getName()+"%"));
        }
    }
    private void fetch(Root<Item> root, CriteriaQuery<?> query){
        if(query.getResultType()!=Long.class){
            root.fetch("category", JoinType.LEFT);
            root.fetch("producer", JoinType.LEFT);
        }
    }
}
