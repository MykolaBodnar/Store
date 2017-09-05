<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul>
    <c:forEach items="${items}" var="item">
        <li>
                ${item.name}
            <a href="/admin/item/${item.id}/images">images</a>
            <a href="/admin/item/update/${item.id}">update</a>
            <a href="/admin/item/delete/${item.id}">delete</a>
        </li>
    </c:forEach>
</ul>