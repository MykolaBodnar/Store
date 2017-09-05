<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<form:form method="post">
            <textarea name="description" id="description" rows="10" cols="80">
                    ${description}
            </textarea>
    <script>
        CKEDITOR.replace('description');
    </script>
    <button>save</button>
</form:form>
