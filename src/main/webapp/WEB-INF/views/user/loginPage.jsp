<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="error">
    ${message}
</div>
<form:form action="/login-processing" method="post" cssClass="form-horizontal">
    <div class="form-group">
        <label class="control-label col-sm-2" for="username">Name:</label>
        <div class="col-sm-6">
            <input name="username" id="username" placeholder="email" class="form-control"/>
        </div>
    </div>
     <div class="form-group">
        <label class="control-label col-sm-2" for="password">Password:</label>
        <div class="col-sm-6">
            <input name="password" type="password" id="password" placeholder="password" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-default">Login</button>
        </div>
    </div>
</form:form>