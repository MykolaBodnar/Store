<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<h3>${category.name}</h3>
<form:form action="/admin/item" method="post" modelAttribute="form" class="form-horizontal"

           enctype="multipart/form-data">
    <form:hidden path="id" value="${form.id}"/>
    <form:hidden path="category" value="${category.id}"/>
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">Name:</label>
        <div class="col-sm-6">
            <form:input path="name" id="name" type="text" class="form-control"/>
            <form:errors path="name" cssClass="error"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="price">Price:</label>
        <div class="col-sm-6">
            <form:input path="price" id="price" type="number" class="form-control"/>
            <form:errors path="price" cssClass="error"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="producer">Producer:</label>
        <div class="col-sm-6">
            <form:select path="producer" id="producer" class="form-control" itemLabel="name" itemValue="id"
                         items="${producers}"/>
        </div>
    </div>
    <c:forEach items="${attributes}" var="attribute">
        <div class="form-group">
            <label class="control-label col-sm-2" for="${attribute.name}">${attribute.name}:</label>
            <div class="col-sm-6 col-xs-12">
                <form:select path="attributeValues" id="${attribute.name}" multiple="false"
                             class="form-control"
                             items="${attribute.attributeValues}" itemValue="id" itemLabel="value"/>
            </div>
            <div>
                <button class="btn btn-success btn-av-dialog" type="button" data-attribute-id="${attribute.id}"
                        data-attribute-name="${attribute.name}" data-toggle="modal" data-target="#myModal">
                    Add new value
                </button>
            </div>
        </div>
    </c:forEach>
    <div class="form-group">
        <label class="control-label col-sm-2" for="file">Image:</label>
        <div class="col-sm-10">
            <label class="btn btn-primary">
                Browse <form:input name="image" path="file" type="file" id="file" style="display: none;"/>
            </label>
            <img id="image" width="300px" src="/images/${form.imageUrl}">
        </div>
    </div>
    <div class="col-sm-offset-2 col-sm-10">
        <form:button class="btn btn-default">Submit</form:button>
    </div>
    </div>
</form:form>
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <div class="col-sm-8">
                    <input class="form-control" id="new-attribute-value">
                </div>

                <button id="btn-av-submit" class="btn btn-success" data-dismiss="modal">Submit</button>
            </div>

        </div>

    </div>
</div>
<security:csrfMetaTags/>
<script src="/resources/js/itemForm.js"></script>