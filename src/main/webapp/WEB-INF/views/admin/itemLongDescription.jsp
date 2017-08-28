<%@ page contentType="text/html;charset=UTF-8"%>
<form method="post" action="/admin/item/19/longDescription">
            <textarea name="description" id="description" rows="10" cols="80">
                ${description}
            </textarea>
    <script>
        CKEDITOR.replace( 'description' );
    </script>
    <button>save</button>
</form>
