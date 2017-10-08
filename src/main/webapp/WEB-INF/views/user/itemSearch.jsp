<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom" %>
<div id="left" class="col-md-2 col-sm-3 col-xs-4">
</div>
<main class="col-md-10 col-sm-9 col-xs-8">
    <c:forEach items="${page.content}" var="item">
        <div class="row item-list" id="item-${item.id}">
            <div class="item-list-image col-sm-3">
                <a href="/item/${item.id}">
                    <img id="item-${item.id}-image" src="/images/${item.mainImage}">
                </a>
            </div>
            <div class="item-list-description col-sm-6">
                <h4><a href="/item/${item.id}" id="item-${item.id}-name">${item.name}</a></h4>
                <div>
                        ${item.description}
                </div>
            </div>
            <div class="item-list-shop col-sm-2">
                <h4><b>Price: </b><span id="item-${item.id}-price">${item.price}</span></h4>
                <button item-id="${item.id}" class="buy btn btn-success" data-toggle="popover" data-trigger="focus"
                        data-content="Item add to basket">
                    <span class="glyphicon glyphicon-shopping-cart"></span> Buy
                </button>
            </div>
        </div>
        <hr>
    </c:forEach>
    <div class="row">
        <div class="col-md-12 col-xs-12 text-center">
            <custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
        </div>
    </div>
</main>