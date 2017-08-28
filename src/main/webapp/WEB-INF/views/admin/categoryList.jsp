<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom" %>

<a href="/admin/category?form" >Create</a>
<ul>
    <c:forEach items="${page.content}" var="category">
      <li>${category.name}
          <a href="/admin/category/update/${category.id}" >update</a>
          <a href="/admin/category/delete/${category.id}" >delete</a>
          <a href="/admin/category/${category.id}/attributes" >attributes</a>
          <a href="/admin/category/${category.id}/items" >items</a>
          <a href="/admin/item/add-to-category/${category.id}">addItems</a>
      </li>
    </c:forEach>
</ul>
<form:form method="get" action="/admin/category" modelAttribute="filter" class ="form-vertical">
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">Name:</label>
        <div class="col-sm-6">
            <form:input path="name" type="text" placeholder="Search name" id = "name" class = "form-control"/>
        </div>
    </div>
    <div class="form-group">
            <button class="btn btn-default">Search name</button>
    </div>
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