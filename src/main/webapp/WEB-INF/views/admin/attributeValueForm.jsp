<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form method="post" action="/admin/attribute-value" modelAttribute="form" class="form-horizontal">
    <form:input path="id" type="hidden" value="${form.id}"/>
    <form:hidden path="attribute" value="${attributeId}"/>
    <div class="form-group">
        <label class="control-label col-sm-2" for="value">Name:</label>
        <div class="col-sm-6">
            <form:input path="value" type="text" placeholder="Enter value" id="value" class="form-control"/>
            <form:errors path="value" cssClass="error"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-default">Submit</button>
        </div>
    </div>
</form:form>