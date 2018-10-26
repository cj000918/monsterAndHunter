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


})
