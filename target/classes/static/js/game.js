$(function(){


$(".cj-p").click(function () {
    alert("123");
});


$(".cj-queren").click(function () {

    var name = $("#hunterName").val();
    var url = "/game";
    var postData = {name : name};

    $.ajax({
        type:"get",
        url:url,
        data:postData,
        dataType:'json',
        success:function(data){

            if(data != null){

                var success = data.success;

                if(success != true){

                    $.diy_alert({"cont":data.msg});
                }
            }

        }
    })

});


$(".cj-cx-info").click(function () {
        var name = $("#hunterName").val();
        var url = "/message";
        var postData = {name : name};

        $.ajax({
            type: "get",
            url: url,
            data: postData,
            dataType: 'json',
            success: function (data) {

                $(".cj-fight-info").remove();

                if(data != null && data.success){

                    var list = data.object;

                    $.each(list, function(index,value){

                        var info = value;

                        var DomDev = $(

                            '<ul class="ul-table-header cj-fight-info">'+info+'</ul>'
                        );

                        if($(".cj-fight-info").length>0){

                            $(".cj-fight-info:last").after(DomDev);

                        }else{

                            $(".cj-fight-info-list").append(DomDev);
                        }

                    })
                }else{

                    $(".cj-fight-info-list").append('<ul class="ul-table-header cj-fight-info">'+data.msg+'</ul>');
                }
            }
        })
})



})
