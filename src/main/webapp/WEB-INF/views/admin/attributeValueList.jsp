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
            <custom:sort innerHtml="Value asc" paramValue="value"/>
            <custom:sort innerHtml="Value desc" paramValue="value,desc"/>
        </ul>
    </div>
    <form:form method="get" action="/admin/attribute/${attributeId}/values" modelAttribute="valueFilter">
        <div class="form-group col-md-4 col-sm-6 col-xs-9">
            <form:input path="value" type="text" placeholder="Search name" id="value" class="form-control"/>
        </div>
        <button class="btn btn-default">
            <span>Search</span>
            <i class="glyphicon glyphicon-search"></i>
        </button>
        <a href="/admin/attribute-value/add-to-attribute/${attributeId}" class="btn btn-success">Create</a>
    </form:form>
</div>
<ul class="list-group">
    <c:forEach items="${page.content}" var="attributeValue">
        <li class="list-group-item">
            <div class="row">
                <div class="line-name">
                        ${attributeValue.value}
                </div>
                <div class="right-btn">
                    <a class="btn btn-warning" href="/admin/attribute-value/update/${attributeValue.id}">update</a>
                    <a class="btn btn-danger" href="/admin/attribute-value/delete/${attributeValue.id}">delete</a>
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


<%--<a href="/admin/attribute-value/add-to-attribute/${attributeId}">Create</a>--%>
<%--<ul>--%>
<%--<c:forEach items="${page.content}" var="attributeValue">--%>
<%--<li>--%>
<%--${attributeValue.value}--%>
<%--<a href="/admin/attribute-value/update/${attributeValue.id}">update</a>--%>
<%--<a href="/admin/attribute-value/delete/${attributeValue.id}">delete</a>--%>
<%--</li>--%>
<%--</c:forEach>--%>
<%--</ul>--%>
<%--<form:form method="get" action="/admin/attribute/${attributeId}/values" modelAttribute="valueFilter" class="form-vertical">--%>
<%--<div class="form-group">--%>
<%--<label class="control-label col-sm-2" for="value">Value:</label>--%>
<%--<div class="col-sm-6">--%>
<%--<form:input path="value" type="text" placeholder="Search name" id="value" class="form-control"/>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<button class="btn btn-default">Search value</button>--%>
<%--</div>--%>
<%--</form:form>--%>
<%--<div class="dropdown">--%>
<%--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span--%>
<%--class="caret"></span>--%>
<%--</button>--%>
<%--<ul class="dropdown-menu">--%>

<%--</ul>--%>
<%--</div>--%>
<%--<div class="row">--%>
<%--<div class="col-md-12 col-xs-12 text-center">--%>
<%--<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>--%>
<%--</div>--%>
<%--</div>--%>