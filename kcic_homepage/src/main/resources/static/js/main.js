$(document).ready(function(){

    $('#visual').addClass('active');


    $(window).on("resize", function(){
        $tabWrap.removeClass('active');
    }); 

    

    var $tabWrap = $('#section02 .tab-wrap');
    var $tabList = $('#section02 .tab .tab-list');
    
    var widthW = $(window).width();
    if(widthW < 1200){
        $tabList.on('click',function(){
            if($(this).hasClass('current')){
            if($tabWrap.hasClass('active')){
                    $tabWrap.removeClass('active');
            }else{
                    $tabWrap.addClass('active');
            }
            }else{
                $tabWrap.removeClass('active');
            }
        });
    }else{
    
    }


    $tabList.on('click',function(){
        var data_tab = $(this).attr('data-target');
        $tabList.removeClass('current');
        $('#section02 .tab-con').removeClass('current');

        $(this).addClass('current');
        $('#'+data_tab).addClass('current');
    });

    $(window).on("load scroll", function(){

        $(".section").each(function(){
            var el = $(this);
            if(el.offset().top - 500 <= $(window).scrollTop()) el.addClass("action");
            //else el.removeClass("action");
        });

    });



  

});