
var hasScroll = true;
$(function(){


function scrollThing(){

    (function($){
        $.fn.scrollTopThing = function(options){
            var defaults = {
                speed:30
            }
            var opts = $.extend(defaults,options);
            this.each(function(){
                var $timer;
                var scroll_top=0;
                var obj = $(this);
                var $height = obj.find(".cj-fight-info-list").height();
                // obj.find(".cj-fight-info-list").clone().appendTo(obj);
                obj.hover(function(){
                    clearInterval($timer);
                },function(){
                    $timer = setInterval(function(){
                        scroll_top++;
                        if(scroll_top > $height){
                            scroll_top = 0;
                        }
                        obj.find(".cj-fight-info-list").first().css("margin-top",-scroll_top);
                    },opts.speed);
                }).trigger("mouseleave");
            })
        }
        scrollMessage();
    })(jQuery);

    $(".maquee").scrollTopThing({
        speed:10 //数值越大 速度越慢
    });
}

function scrollMessage() {

    var name = $("#hunterName").val();
    var postData = {name : name};

    if(hasScroll){
        getMessage(postData);
    }
}


function getMessage(postData) {
    $.ajax({
        type: "get",
        url: "/message",
        data: postData,
        dataType: 'json',
        success: function (data) {

            $(".Top_Record").show();
            $(".cj-fight-info").remove();

            if(data != null && data.success){

                var list = data.object;

                $.each(list, function(index,value){

                    var info = value;

                    var DomDev = $(

                        '<li class="cj-fight-info">'+info+'</li>'

                    );

                    if($(".cj-fight-info").length>0){

                        $(".cj-fight-info:last").after(DomDev);

                    }else{

                        $(".cj-fight-info-list").append(DomDev);
                    }

                    if(info.indexOf("GAME_OVER") >= 0){
                        hasScroll = false;
                        return false;
                    }

                })

                //autoScroll(".maquee");
                scrollThing();
            }else{

                $(".cj-fight-info-list").append('<ul class="ul-table-header cj-fight-info">'+data.msg+'</ul>');
            }
        }
    })
}


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
    var postData = {name : name};
    getMessage(postData);

})






})
