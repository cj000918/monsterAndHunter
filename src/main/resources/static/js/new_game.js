

$(function(){

    $(".operation_fight_but").click(function () {
        var hunterName = $("#hunterName").val();
        var url = "/hunter/add_hunter_2";
        var postData = {name : hunterName};

        $.ajax({
            type:"get",
            url:url,
            data:postData,
            dataType:'json',
            success:function(data){

                if(data != null){

                    var hunterId = data.hunterId;
                    var parameter = {hunterId : hunterId};

                    doFighting(parameter);
                }
            }
        })
    });


    function doFighting(postData) {
        $.ajax({
            type: "get",
            url: "/hunter/fight",
            data: postData,
            dataType: 'json',
            success: function (data) {

                if(data != null){

                }

            }
        })
    }






})