jQuery(document).ready(function ($) {

    $('.metadata-menu > li > a').on('click',function(e){
        e.preventDefault();

        if($(this).hasClass('on')){

            $(this).removeClass('on').next('.metadata-menu-02').slideUp();
        }else{
            $('.metadata-menu > li > a').removeClass('on');
            $('.metadata-menu-02').slideUp();
            $(this).addClass('on').next('.metadata-menu-02').slideDown();
        }

    });

    $('.qa-list > li').on('click',function(){
        if($(this).hasClass('on')){
            $(this).removeClass('on').find('.qa-txt').stop().slideUp();
        }else{
            $('.qa-list > li').removeClass('on').find('.qa-txt').stop().slideUp();
            $(this).addClass('on').find('.qa-txt').stop().slideDown();
        }
    });

});