<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form method="post" action="/reset-password" modelAttribute="resetForm">
    <form:hidden path="uuid" value = "${uuid}"/>
    <form:errors path="uuid"/>
    <form:input path="email"/>
    <form:errors path="email"/>
    <form:password path="password"/>
    <form:errors path="password"/>
    <button>submit</button>
</form:form>