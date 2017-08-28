$(document).ready(
    function () {
        $("#file").change(function () {
            var imageCount = event.target.files.length;
            if (imageCount > 0) {
                var path = URL.createObjectURL(event.target.files[0]);
                $("#image").attr("src", path);

            }

        });
        $(".btn-av-dialog").click(
            function () {
                var attributeName = $(this).data("attributeName");
                var attributeId = $(this).data("attributeId");
                $("#myModal .modal-header h4").text(attributeName);
                $("#btn-av-submit").data("attributeId", attributeId)
                    .data("attributeName", attributeName);
            }
        );
        $("#btn-av-submit").click(
            function () {
                var attributeId = $(this).data("attributeId");
                var attributeName = $(this).data("attributeName");
                var attributeValue = {
                    attribute:{
                        id: attributeId
                    },
                    value: $("#new-attribute-value").val()
                };
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                $.ajax({
                    url: "/admin/attribute-value?async",
                    method: 'POST',
                    contentType: 'application/json; charset=UTF-8',
                    dataType: 'json',
                    data: JSON.stringify(attributeValue),
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader (header, token);
                    },
                    success: function (data) {
                        var option = $("<option></option>").text(data.value).attr("value", data.id).attr("selected", true);
                        $("#" + attributeName + "option[selected]").attr("selected", false);
                        $("#" + attributeName).append(option);
                    }
                });
            }
        );

    }
);
