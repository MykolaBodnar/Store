<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="left" class="col-md-2 col-sm-2 col-xs-12">
    <a href="/category/${item.category.id}" class="btn btn-success category-btn">${item.category.name}</a>
</div>
<main class="col-md-10 col-sm-10 col-xs-12">
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


    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#attributes">Attributes</a></li>
        <li><a data-toggle="tab" id="description-btn" href="#description">Description</a></li>
        <li><a data-toggle="tab" href="#images">Images</a></li>
    </ul>

    <div class="tab-content">
        <div id="attributes" class="tab-pane fade in active">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Attribute</th>
                        <th>Value</th>
                    </tr>
                    </thead>
                    <c:forEach items="${item.attributes}" var="attribute">
                        <tr>
                            <td>${attribute.name}</td>
                            <td>${attribute.value}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div id="description" class="tab-pane fade">
            <div>
                <b>Long description</b>
            </div>
        </div>
        <div id="images" class="tab-pane fade">
            <c:forEach items="${item.images}" var="image">
                <img class="item-image" src="/images/${image}">
            </c:forEach>
        </div>
    </div>
    <input type="hidden" id="itemId" value="${item.id}">
</main>
<script src="/resources/js/item.js"></script>




