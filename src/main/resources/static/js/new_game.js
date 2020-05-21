

//初始化,战斗信息滚动效果
function marquee(){

    var footer_id = document.getElementById("footer_id");
    var footer_ul_id = document.getElementById("footer_ul_id");
    var speed = 30;    //设置速度，数字越大越慢
    // 滚动函数
    function moveTop() {
        // 当main向上滚动超出部分>=滚动节点高度时, 设置为scrollTop为0
        if (footer_ul_id.offsetHeight - footer_id.scrollTop <= 0 ) {
            // footer_id.scrollTop = 0;
            footer_id.scrollTop -= footer_ul_id.offsetHeight;
        } else {
            footer_id.scrollTop++;
        }
    }

    var myMarquee = setInterval(moveTop, speed);  //设置定时器
    // 鼠标悬停时清除定时器停止滚动，移出后继续滚动
    footer_id.onmouseover = function() {
        clearInterval(myMarquee);
    }
    footer_id.onmouseout = function() {
        myMarquee = setInterval(moveTop, speed);
    }
}



$(function(){


    $(".operation_fight_but").click(function () {

        var hunterName = $("#hunterName").val();

        if(hunterName == undefined){
            alert("请输入名称");
        }
        var postData = {hunterName : hunterName};

        //开始战斗
        doFighting(postData);

    });

    //查询战斗信息
    $(".operation_query_but").click(function () {

        var hunterId = $("#hunterName").attr("hunterId");
        var parmater = {hunterId:hunterId};
        setInterval(getFigthingInfo(parmater),3000);

    })


    //开始战斗
    function doFighting(postData) {
        $.ajax({
            type: "get",
            url: "/hunter/fight",
            data: postData,
            dataType: 'json',
            success: function (data) {

                if(data != null){

                    var hunterId = data.hunterId;
                    var postData = {hunterId:hunterId};

                    //输入框不可编辑
                    $("#hunterName").attr("disabled","disabled");
                    //隐藏"开始战斗"按钮
                    $(".operation_fight_but").attr("style","display:none;");

                    $("#hunterName").attr("hunterId",hunterId);



                }
            }
        })
    }



//查询战斗信息
    function  getFigthingInfo(postData) {
        $.ajax({
            type: "get",
            url: "/hunter/get_fighting",
            data: postData,
            dataType: 'json',
            success: function (data) {

                if(data != null && data.code != 300){

                    var fightsList = data.fights;

                    $.each(fightsList, function (index, value) {
                        var createTime = value.createTime;
                        var remark = value.remark;
                        var hunterId = value.hunterId;
                        var monsterId = value.monsterId;
                        var id = value.id;

                        var liDom = $('<li class ="footer_li">'+ createTime +'  ' +remark+'</li>')

                        if($(".footer_li").length>0){

                            $(".footer_li:last").after(liDom);

                        }else{
                            $(".footer_ul").append(liDom);
                        }

                    })
                }
            }
        })
    }


})

