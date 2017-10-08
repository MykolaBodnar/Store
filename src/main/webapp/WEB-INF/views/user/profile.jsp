<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${orders}" var="order">
    <div>
        <h4>Order №${order.id}</h4>
        <h4>Price: ${order.price}</h4>
        <c:forEach items="${order.items}" var="item">
            <div class="row orders">
                <div class="col-xs-2">
                    <img src="/images/${item.mainImage}">
                </div>
                <div class="basket-name col-xs-5">
                    <a href="/item/${item.id}">${item.name}</a>
                </div>
                <div class="col-xs-2">${item.count}</div>
                <div class="col-xs-2 basket-price">${item.price}</div>
            </div>
            <hr>
        </c:forEach>
    </div>
    <hr>
</c:forEach>