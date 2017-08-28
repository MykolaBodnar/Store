<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h3>Forgot password</h3>

<form:form action="/forgot-password" method="post">
    <input name="emailForResetPassword"/>
    <button>Submit</button>
</form:form>