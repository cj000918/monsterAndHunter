(function ($) {
    $.diy_alert = function(options) {
        var dft = {
            paddingL: "80px",
            paddingR: "80px",
            paddingT: "50px",
            paddingB: "50px",
            fontSize:"32px",
            bgColor: "#FFF",
            fontColor: "#f30815",
            cont: "成功"
        };
        var ops = $.extend(dft,options||{});
        var box = $("<div>").css({
            "padding-left": ops.paddingL,
            "padding-right": ops.paddingR,
            "padding-top": ops.paddingT,
            "padding-bottom": ops.paddingB,
            // "border": "1px #000 solid",
            "position":"fixed",
            "left":"50%",
            "top":"50%",
            "background-color":ops.bgColor,
            "color":ops.fontColor,
            "font-size":ops.fontSize,"z-index":101,
            "border-radius":'3px'
        }).html(ops.cont).appendTo($("body"));
        var box_bg=$("<div style='position: fixed;left:0;top:0;background:rgba(0,0,0,.3);z-index:100;'></div>").appendTo($("body"));
        box.css({"margin-left":-(box.outerWidth(true)/2),"margin-top":-box.outerHeight(true)/2});
        setTimeout(function(){
            box.remove();
            box_bg.remove();
        },2000);
    }
})(jQuery);