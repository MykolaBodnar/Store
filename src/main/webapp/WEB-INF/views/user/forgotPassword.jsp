<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h3>Forgot password</h3>

<form:form action="/forgot-password" method="post" cssClass="form-inline">
    <div class="form-group">
        <label for="emailForResetPassword">Email address:</label>
        <input type="email" name="emailForResetPassword" class="form-control" id="emailForResetPassword">
    </div>
    <button class="btn btn-default">Submit</button>
</form:form>