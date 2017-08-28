<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form action="/registration" method="post" modelAttribute="form">
    <form:input path="name" placeholder="name"/>
    <form:errors path="name" cssClass="error"/>
    <form:input path="lastName" placeholder="last name"/>
    <form:errors path="lastName" cssClass="error"/>
    <form:input path="email" placeholder="email"/>
    <form:errors path="email" cssClass="error"/>
    <form:input path="password" type="password" placeholder="password"/>
    <form:errors path="password" cssClass="error"/>
    <button>Submit</button>
</form:form>