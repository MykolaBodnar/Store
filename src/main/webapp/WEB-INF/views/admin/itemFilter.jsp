<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form action="/admin/item/${category.id}/showFilter" method="get" modelAttribute="filter">
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
<input type="range">