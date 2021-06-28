$(document).ready(function(){

    var $body = $('body');
    var $header = $('#header');
    var $menu = $(' #gnb .menu > li');
    var $moMenu = $('.mo-menu .menu > li > a');


    

    var headerMove = $('#header').offset();

   $(window).on('load scroll',function(){
        if($(document).scrollTop() > headerMove.top){
            $('#header').addClass('move');
        }else{
            $('#header').removeClass('move');
        }
   });




    $menu.on('mouseover',function(){
        $header.addClass('on');
        $(this).find('.menu-02').stop().slideDown();
    });

    $menu.on('mouseleave',function(){
        $header.removeClass('on');
        $(this).find('.menu-02').stop().slideUp();
    });

    $(".sitemap-btn").on("click", function(e){
        e.preventDefault();

        var widthW = $(window).width();

        
        $body.addClass("action");

        if(widthW > 1400){
            $header.addClass("active");
        }else{
            $header.addClass("action");
        }

    });

    $(".sitemap-close").on("click", function(e){
        e.preventDefault();

        $body.removeClass("action");
        $header.removeClass("active");

    });

    $('.mo-menu-close').on("click", function(e){
        e.preventDefault();

        $body.removeClass("action");
        $header.removeClass("active action");
    });

    $(window).on("resize", function(){
        $body.removeClass("action");
        $header.removeClass("active action");
    });

    $(".mo-menu-btn").on("click", function(e){
        e.preventDefault();

        $("#header").addClass("on");

    });

    $(".mo-menu-close").on("click", function(e){
        e.preventDefault();

        $("#header").removeClass("on");

    });

   




    $moMenu.on('click',function(e){
        e.preventDefault();
        if($(this).hasClass('active')){
            $moMenu.removeClass('active').siblings('.menu-02').stop().slideUp();
        }else{
            $moMenu.removeClass('active').siblings('.menu-02').stop().slideUp();
            $(this).addClass('active').siblings('.menu-02').stop().slideDown();
        }
    });
    


    $('.top').on('click',function(e){
        e.preventDefault();
        $('html,body').animate({scrollTop:$(this.hash).offset().top},800);
    });

    var lastScroll = 0;

    $(window).scroll(function(){
        var scroll = $(this).scrollTop();
        if(scroll > 300){
            $('.top').addClass('active');
        }else{
            $('.top').removeClass('active');
        }
        lastScroll = scroll;
    });


});