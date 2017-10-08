$(document).ready(
    function () {
        $('[data-toggle="popover"]').popover();
        $.cookie.json = true;
        if ($.cookie("items") === undefined) {
            alert("");
            $.cookie("items", {}, {path: '/'});
        }
        $.removeCookie("items", {path: '/item'});

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/category",
            method: 'get',
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (categories) {
                for (var i = 0; i < categories.length; i++) {
                    var a = $("<a></a>").text(categories[i].name).attr("href", "/category/" + categories[i].id);
                    var li = $("<li></li>").append(a);
                    $("#categories").append(li);
                }
            }
        });


        $(".buy").on("click", function () {
            var id = $(this).attr("item-id");
            var items = $.cookie("items");
            if (items[id] === undefined) {
                items[id] = getItem(id);
            } else {
                items[id].count++;
            }
            $.cookie("items", items, {path: '/', expires: 7});
        });

        $("#basket-btn").on("click", function () {
            $("#basketBody").empty();
            var items = $.cookie("items");
            var totalPrice = 0;
            for (var id in items) {
                if (!isNaN(parseFloat(id)) && isFinite(id)) {
                    var itemsPrice = items[id].price * items[id].count;
                    totalPrice += itemsPrice;
                    var row = $("<div class='row' id='basket-item-" + id + "'>").html(
                        "<div class=\"col-xs-2\" >" +
                        "<img src='" + items[id].image + "'>" +
                        "</div>" +
                        "<div class=\"basket-name col-xs-5\">" +
                        "<a href='/item/" + id + "'>" + items[id].name + "</a>\n" +
                        "</div>" +
                        "<div class=\"col-xs-2\">" +
                        "<input item-id='" + id + "' type='number' value='" + items[id].count + "' class='form-control count-input'>" +
                        "</div>" +
                        "<div class='col-xs-2 basket-price' id = 'basket-item-price-" + id + "'>" +
                        items[id].price +
                        "</div>" +
                        "<div class='col-xs-1'>" +
                        "<button item-id='" + id + "' class='close remove-item'>&times;</button>" +
                        "</div>"
                    );
                    $("#basketBody").append(row);

                }
            }
            setTotalPrice(totalPrice);

            $("#basketBody").append(row);
            $(".count-input").on("change", function () {
                var id = $(this).attr("item-id");
                var items = $.cookie("items");
                var oldCount = items[id].count;
                items[id].count = $(this).val();
                $.cookie("items", items, {path: '/'});
                var price = items[id].price;
                var countChange = items[id].count - oldCount;
                var totalPrice = parseInt($("#totalPrice").text());
                totalPrice += countChange * price;
                setTotalPrice(totalPrice);
            });

            $(".remove-item").on("click", function () {
                var id = $(this).attr("item-id");
                var items = $.cookie("items");
                var price = items[id].price * items[id].count;
                items[id] = undefined;
                $.cookie("items", items, {path: '/'});
                var containerId = "#basket-item-" + id;
                $(containerId).remove();
                var totalPrice = parseInt($("#totalPrice").text());
                totalPrice -= price;
                setTotalPrice(totalPrice);
            });
        });
    }
);


function setTotalPrice(totalPrice) {
    $("#totalPrice").text(totalPrice);
    if (totalPrice == 0) {
        $(".order-btn").addClass("hidden");
    } else {
        $(".order-btn").removeClass("hidden");
    }
}

function getItem(id) {
    var containerId = "#item-" + id;

    var item = {
        name: $(containerId + "-name").text(),
        price: $(containerId + "-price").text(),
        image: $(containerId + "-image").attr("src"),
        count: 1
    };
    return item;
}
