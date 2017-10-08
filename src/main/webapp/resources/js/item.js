var descriptionIsLoaded = false;
$(document).ready(
    function () {
        $("#description-btn").on("click", function () {
            if (!descriptionIsLoaded) {
                var id = $("#itemId").val();
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                $.ajax({
                    url: "/item/" + id + "/description",
                    method: 'get',
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(header, token);
                    },
                    success: function (data) {
                        $("#description").html(data);
                        descriptionIsLoaded = true;
                    }
                });
            }
        });


    }
);