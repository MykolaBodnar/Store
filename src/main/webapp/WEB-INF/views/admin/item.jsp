<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <a class="btn btn-danger" href="/admin/item/delete/${item.id}">delete</a>
    <a class="btn btn-warning" href="/admin/item/update/${item.id}">update</a>
</div>
<h3>${item.name}</h3>
<div class="row">
    <div class="col-md-3">
        <img class="main-image" src="/images/${item.mainImage}" alt="no image">
    </div>
    <div class="col-md-3">
        <div>
            <b>Category:</b>
            <a href="/admin/category/${item.category.id}/items">${item.category.name}</a>
        </div>
        <div>
            <b>Price:</b>
            <span>${item.price}</span>
        </div>
        <div>
            ${item.description}
        </div>
    </div>
</div>
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
<div>
    <b>Long description</b>
    <a href="/admin/item/${item.id}/longDescription">edit</a>
</div>
<div>
    ${description}
</div>
<div>
    <b>Images</b>
    <a href="/admin/item/${item.id}/images">edit</a>
</div>

<c:forEach items="${item.images}" var="image">
    <img class="item-image" src="/images/${image}">
</c:forEach>


