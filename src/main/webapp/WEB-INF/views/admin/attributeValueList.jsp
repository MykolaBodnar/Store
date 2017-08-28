<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom" %>
<a href="/admin/attribute-value/add-to-attribute/${attributeId}">Create</a>
<ul>
    <c:forEach items="${page.content}" var="attributeValue">
        <li>
                ${attributeValue.value}
            <a href="/admin/attribute-value/update/${attributeValue.id}">update</a>
            <a href="/admin/attribute-value/delete/${attributeValue.id}">delete</a>
        </li>
    </c:forEach>
</ul>
<form:form method="get" action="/admin/attribute/${attributeId}/values" modelAttribute="valueFilter" class="form-vertical">
    <div class="form-group">
        <label class="control-label col-sm-2" for="value">Value:</label>
        <div class="col-sm-6">
            <form:input path="value" type="text" placeholder="Search name" id="value" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <button class="btn btn-default">Search value</button>
    </div>
</form:form>
<div class="dropdown">
    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span
            class="caret"></span>
    </button>
    <ul class="dropdown-menu">
        <custom:sort innerHtml="Value asc" paramValue="value"/>
        <custom:sort innerHtml="Value desc" paramValue="value,desc"/>
    </ul>
</div>
<div class="row">
    <div class="col-md-12 col-xs-12 text-center">
        <custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
    </div>
</div>