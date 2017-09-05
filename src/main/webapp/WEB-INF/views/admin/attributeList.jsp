<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom" %>

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
    <form:form method="get" action="/admin/attribute" modelAttribute="filter">
        <div class="form-group col-md-4 col-sm-6 col-xs-9">
            <form:input path="name" type="text" placeholder="Input name" class="form-control"/>
        </div>
        <button class="btn btn-default">
            <span>Search</span>
            <i class="glyphicon glyphicon-search"></i>
        </button>
        <a href="/admin/attribute?form" class="btn btn-success">Create</a>
    </form:form>
</div>
<ul class="list-group">
    <c:forEach items="${page.content}" var="attribute">
        <li class="list-group-item">
            <div class="row">
                <div class="line-name">
                        ${attribute.name}
                </div>
                <div class="right-btn">
                    <a class="btn btn-info" href="/admin/attribute/${attribute.id}/values">values</a>
                    <a class="btn btn-warning" href="/admin/attribute/update/${attribute.id}">update</a>
                    <a class="btn btn-danger" href="/admin/attribute/delete/${attribute.id}">delete</a>
                </div>
            </div>
        </li>
    </c:forEach>
</ul>

<div class="row">
    <div class="col-md-12 col-xs-12 text-center">
        <custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
    </div>
</div>