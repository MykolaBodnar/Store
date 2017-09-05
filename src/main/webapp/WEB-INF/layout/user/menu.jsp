<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Store</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Categories <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" id="categories">
                    </ul>
                </li>
            </ul>
            <form:form class="navbar-form navbar-left" method="get" action="/item">
                <div class="input-group item-search">
                    <input id="name" name="name" class="form-control" placeholder="Search"/>
                    <div class="input-group-btn">
                        <button class="btn btn-default">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </form:form>
            <ul class="nav navbar-nav navbar-right">

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="/admin/category"><span class="glyphicon glyphicon-user"></span> Admin page</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <li><a href="/profile"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <li><a href="/registration"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="/login-page"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="#" onclick="document.getElementById('logoutForm').submit()"><span
                                class="glyphicon glyphicon-log-out"></span> Logout</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="!hasRole('ROLE_ADMIN')">
                    <li><a href="#" id="basket-btn" data-toggle="modal" data-target="#basketModal"><span
                            class="glyphicon glyphicon-shopping-cart"></span> Basket</a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
<form:form action="/logout" method="post" id="logoutForm"></form:form>

<div class="modal fade" id="basketModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Basket</h4>
            </div>
            <div class="modal-body">
                <div id="basketBody">

                </div>
                <div class="basket-total">
                    <h4>
                        <b>Total price: </b>
                        <span id="totalPrice"></span>
                    </h4>
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <form:form action="/made-order" method="post">
                            <button class="btn btn-default order-btn">
                                to order
                            </button>
                        </form:form>
                    </sec:authorize>
                    <sec:authorize access="!hasRole('ROLE_USER')">
                        Please sing in or sign up for for shopping
                    </sec:authorize>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
