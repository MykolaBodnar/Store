<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form method="post" action="/reset-password" modelAttribute="resetForm" cssClass="form-horizontal">
    <form:hidden path="uuid" value="${uuid}"/>
    <form:errors path="uuid"/>
    <div class="form-group">
        <label class="control-label col-sm-2" for="email">Email:</label>
        <div class="col-sm-6">
    <form:input path="email" cssClass="form-control"/>
        </div>
    </div>
    <form:errors path="email"/>
    <div class="form-group">
        <label class="control-label col-sm-2" for="password">Password:</label>
        <div class="col-sm-6">
    <form:password path="password" cssClass="form-control"/>
        </div>
    </div>
    <form:errors path="password"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-default">Submit</button>
        </div>
    </div>
</form:form>