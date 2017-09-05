<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form method="post" enctype="multipart/form-data" class="form-inline">
    <label class="btn btn-primary">
        Browse <input name="images" type="file" id="files" multiple style="display: none;">
    </label>
    <div class="form-group">
        <button class="btn btn-default">Submit</button>
    </div>
</form:form>
<c:forEach items="${images}" var="image">
    <img src="/images/${image.path}"> <a href="delete/${image.id}">delete</a>
</c:forEach>