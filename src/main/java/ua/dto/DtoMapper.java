package ua.dto;

import org.springframework.data.domain.Page;
import ua.dto.filter.ItemFilterParam;
import ua.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {
    public static List<CategoryDto> categoryListToDto(List<Category> categories) {
        return  categories.stream()
                .map(c -> new CategoryDto(c.getId(), c.getName()))
                .collect(Collectors.toList());
    }


    public static ItemDto getItemDto(Item item, String producer, List<AttributeValue> attributeValues,
                                     List<ItemImage> itemImages) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(producer + " " + item.getName());
        itemDto.setPrice(item.getPrice().toString());
        itemDto.setMainImage(item.getImageUrl());
        itemDto.setDescription(item.getDescription());
        itemDto.setCategory(new CategoryDto(item.getCategory().getId(), item.getCategory().getName()));
        for (AttributeValue av : attributeValues) {
            itemDto.getAttributes().add(new ItemAttributeDto(av.getAttribute().getName(), av.getValue()));
        }
        for (ItemImage image : itemImages) {
            itemDto.getImages().add(image.getPath());
        }
        return itemDto;
    }

    public static Item itemFormToItem(ItemForm itemForm) {
        Item item = new Item();
        item.setId(itemForm.getId());
        item.setCategory(itemForm.getCategory());
        item.setPrice(itemForm.getPrice());
        item.setName(itemForm.getName());
        item.setProducer(itemForm.getProducer());
        item.setAttributeValues(itemForm.getAttributeValues());
        item.setDescription(generateDescription(itemForm));
        return item;
    }

    private static String generateDescription(ItemForm itemForm) {
        if(itemForm.getAttributeValues()!=null){
            String description = itemForm.getAttributeValues().stream()
                    .filter(sav -> sav.getAttribute().isForSelect())
                    .map(sav -> "<b>" + sav.getAttribute().getName() + ": </b>" + sav.getValue())
                    .collect(Collectors.joining(", "));

            return description;
        }
        return "";
    }

    public static ItemFilterParam getItemFilterParam(Category category, List<Attribute> attributes,
                                                     List<Producer> producers) {
        ItemFilterParam itemFilterParam = new ItemFilterParam();
        itemFilterParam.setCategory(new CategoryDto(category.getId(), category.getName()));
        itemFilterParam.setProducers(producers.stream().map(p -> new ProducerDto(p.getId(), p.getName()))
                .collect(Collectors.toList()));
        itemFilterParam.setAttributes(attributes.stream().map(a -> {
            AttributeDto attributeDto = new AttributeDto(a.getName());
            attributeDto.setValues(a.getAttributeValues().stream()
                    .map(av -> new AttributeValueDto(av.getId(), av.getValue()))
                    .collect(Collectors.toList()));
            return attributeDto;
        }).collect(Collectors.toList()));

        return itemFilterParam;
    }

    public static List<ItemListDto> itemPageToListDto(Page<Item> itemPage) {
        return itemPage.getContent().stream()
                .map(i -> new ItemListDto(
                        i.getId(), i.getName(), i.getPrice().toString(), i.getImageUrl(), i.getDescription()))
                .collect(Collectors.toList());
    }

    public static ItemForm getItemForm(Item item, Category category, Producer producer) {
        ItemForm itemForm = new ItemForm();
        itemForm.setAttributeValues(item.getAttributeValues());
        itemForm.setCategory(category);
        itemForm.setProducer(producer);
        itemForm.setId(item.getId());
        itemForm.setName(item.getName());
        itemForm.setPrice(item.getPrice());
        itemForm.setImageUrl(item.getImageUrl());
        return itemForm;
    }

    public static List<OrderDto> ordersToDto(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for(Order order: orders){
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            List<OrderItemDto> orderItemDtos = new ArrayList<>();
            int totalPrice = 0;
            for(OrderItem orderItem: order.getOrderItems()){
                OrderItemDto orderItemDto = new OrderItemDto();
                orderItemDto.setCount(orderItem.getCount());
                orderItemDto.setId(orderItem.getItem().getId());
                orderItemDto.setMainImage(orderItem.getItem().getImageUrl());
                orderItemDto.setName(orderItem.getItem().getName());
                BigDecimal price = (orderItem.getItem().getPrice());
                orderItemDto.setPrice(price.toString());
                totalPrice += price.intValue() * orderItem.getCount();
                orderItemDtos.add(orderItemDto);
            }
            orderDto.setPrice(String.valueOf(totalPrice));
            orderDto.setItems(orderItemDtos);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }
}
