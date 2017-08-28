<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom" %>
<h2>${category.name}</h2>
<a href="/admin/item/add-to-category/${category.id}">add</a>
<ul>
    <c:forEach items="${page.content}" var="item">
        <li>${item.name}
            <a href="/admin/item/${item.id}/images">images</a>
            <a href="/admin/item/update/${item.id}/">update</a>
            <a href="/admin/item/delete/${item.id}">delete</a>
            <br>
                ${item.description}
        </li>
    </c:forEach>
</ul>
<form:form action="/admin/category/${category.id}/items" method="get" modelAttribute="itemFilter">
    <c:forEach items="${attributes}" var="attribute" varStatus="status">
        <b>${attribute.name}: </b>
        <c:forEach items="${attribute.attributeValues}" var="attributeValue">
            <label>
                    ${attributeValue.value}
                <form:checkbox path="stringAttributeIds[${status.index}]" value="${attributeValue.id}"/>
            </label>
        </c:forEach>
        <br>
    </c:forEach>
    <br>

    <c:forEach items="${producers}" var="producer">
        <label>
                ${producer.name}
            <form:checkbox path="producerIds" value="${producer.id}"/>
        </label>

    </c:forEach>
    <br>
    <form:input path="minPrice" type="number"/>
    <form:input path="maxPrice" type="number"/>
    <form:hidden path="categoryId" value="${category.id}"/>
    <button>submit</button>
</form:form>


<div class="dropdown">
    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span
            class="caret"></span>
    </button>
    <ul class="dropdown-menu">
        <custom:sort innerHtml="Name asc" paramValue="name"/>
        <custom:sort innerHtml="Name desc" paramValue="name,desc"/>
    </ul>
</div>

<div class="row">
    <div class="col-md-12 col-xs-12 text-center">
        <custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
    </div>
</div>