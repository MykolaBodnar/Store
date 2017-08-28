<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form action="/login-processing" method="post">
    <input name="username" placeholder="email"/>
    <input name="password" type="password" placeholder="password"/>
    <button>login</button>
</form:form>