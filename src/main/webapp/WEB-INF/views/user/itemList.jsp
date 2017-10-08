<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom" %>
<div id="left" class="col-md-2 col-sm-3 col-xs-4">
    <h3>${filterParam.category.name}</h3>

    <form:form action="/category/${filterParam.category.id}" method="get" modelAttribute="filter">
        <div class="row">
            <div class="dropdown col-md-6">
                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span
                        class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <custom:sort innerHtml="Name asc" paramValue="name"/>
                    <custom:sort innerHtml="Name desc" paramValue="name,desc"/>
                </ul>
            </div>
            <div class="col-md-6">
                <button class="btn btn-info">filter</button>
            </div>

        </div>
        <div class="form-group">
            <label for="name">Name:</label>
            <form:input path="name" cssClass="form-control"/>
        </div>
        <b>Producer: </b>
        <c:forEach items="${filterParam.producers}" var="producer">
            <div class="checkbox">
                <label>
                    <form:checkbox path="producerIds" value="${producer.id}"/>
                        ${producer.name}
                </label>
            </div>
        </c:forEach>
        <br>
        <c:forEach items="${filterParam.attributes}" var="attribute" varStatus="status">
            <b>${attribute.name}: </b>
            <c:forEach items="${attribute.values}" var="attributeValue">
                <div class="checkbox">
                    <label>
                        <form:checkbox path="stringAttributeIds[${status.index}]" value="${attributeValue.id}"/>
                            ${attributeValue.value}
                    </label>
                </div>
            </c:forEach>
            <br>
        </c:forEach>
        <div class="form-group">
            <label for="minPrice">Min price:</label>
            <form:input path="minPrice" type="number" cssClass="form-control"/>
        </div>
        <div class="form-group">
            <label for="maxPrice">Max price:</label>
            <form:input path="maxPrice" type="number" cssClass="form-control"/>
        </div>
    </form:form>
</div>
<main class="col-md-10 col-sm-9 col-xs-8">
    <c:forEach items="${page.content}" var="item">
        <div class="row item-list" id="item-${item.id}">
            <div class="item-list-image col-sm-3">
                <!--<img src="https://i2.rozetka.ua/goods/1535207/samsung_galaxy_j7_ds_gold_images_1535207451.jpg">-->
                <a href="/item/${item.id}">
                    <img id="item-${item.id}-image" src="/images/${item.mainImage}">
                </a>
            </div>
            <div class="item-list-description col-sm-6">
                <h4><a id="item-${item.id}-name" href="/item/${item.id}">${item.name}</a></h4>
                <div>
                        ${item.description}
                </div>
            </div>
            <div class="item-list-shop col-sm-2">
                <h4><b>Price: </b><span id="item-${item.id}-price">${item.price}</span></h4>
                <button item-id="${item.id}" class="buy btn btn-success" data-toggle="popover" data-trigger="focus"
                        data-content="Item add to basket">
                    <span class="glyphicon glyphicon-shopping-cart"></span> Buy
                </button>
            </div>
        </div>
        <hr>
    </c:forEach>
    <div class="row">
        <div class="col-md-12 col-xs-12 text-center">
            <custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
        </div>
    </div>
</main>