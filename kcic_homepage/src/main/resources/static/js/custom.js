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

    

});