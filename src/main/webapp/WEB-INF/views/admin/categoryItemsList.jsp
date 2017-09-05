<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom" %>
<h2>${category.name}</h2>
<div class="search-form">
    <div class="dropdown col-md-1">
        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span
                class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <custom:sort innerHtml="Name asc" paramValue="name"/>
            <custom:sort innerHtml="Name desc" paramValue="name,desc"/>
        </ul>
    </div>
    <form:form method="get" action="/admin/category/${category.id}/items" modelAttribute="itemFilter">
        <div class="form-group col-md-4 col-sm-6 col-xs-9">
            <form:input path="name" type="text" placeholder="Input name" id="name" class="form-control"/>
        </div>
        <button class="btn btn-default">
            <span>Search</span>
            <i class="glyphicon glyphicon-search"></i>
        </button>

        <a href="/admin/item/add-to-category/${category.id}" class="btn btn-success">Create</a>

    </form:form>
</div>
<ul class="list-group">
    <c:forEach items="${page.content}" var="item">
        <li class="list-group-item">
            <div class="row">
                <div class="line-name">
                    <a href="/admin/item/${item.id}">${item.name}</a>
                </div>
                <div class="right-btn">
                    <a class="btn btn-info" href="/admin/item/${item.id}/images">images</a>
                    <a class="btn btn-info" href="/admin/item/${item.id}/longDescription">description</a>
                    <a class="btn btn-warning" href="/admin/item/update/${item.id}">update</a>
                    <a class="btn btn-danger" href="/admin/item/delete/${item.id}">delete</a>
                </div>
            </div>
        </li>
    </c:forEach>
</ul>

<%--<form:form action="/admin/category/${category.id}/items" method="get" modelAttribute="itemFilter">--%>
<%--<c:forEach items="${attributes}" var="attribute" varStatus="status">--%>
<%--<b>${attribute.name}: </b>--%>
<%--<c:forEach items="${attribute.attributeValues}" var="attributeValue">--%>
<%--<label>--%>
<%--${attributeValue.value}--%>
<%--<form:checkbox path="stringAttributeIds[${status.index}]" value="${attributeValue.id}"/>--%>
<%--</label>--%>
<%--</c:forEach>--%>
<%--<br>--%>
<%--</c:forEach>--%>
<%--<br>--%>

<%--<c:forEach items="${producers}" var="producer">--%>
<%--<label>--%>
<%--${producer.name}--%>
<%--<form:checkbox path="producerIds" value="${producer.id}"/>--%>
<%--</label>--%>

<%--</c:forEach>--%>
<%--<br>--%>
<%--<form:input path="minPrice" type="number"/>--%>
<%--<form:input path="maxPrice" type="number"/>--%>
<%--<form:hidden path="categoryId" value="${category.id}"/>--%>
<%--<button>submit</button>--%>
<%--</form:form>--%>

<div class="row">
    <div class="col-md-12 col-xs-12 text-center">
        <custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
    </div>
</div>