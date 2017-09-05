<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<nav class="navbar navbar-default" id="admin-nav">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar2">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Store</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar2">
            <ul class="nav navbar-nav">
                <li><a href="/admin/category">Category</a></li>
                <li><a href="/admin/attribute">Attribute</a></li>
                <li><a href="/admin/producer">Producer</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#" onclick="document.getElementById('logoutForm').submit()"><span
                            class="glyphicon glyphicon-log-out"></span> Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<form:form action="/logout" method="post" id="logoutForm"></form:form>