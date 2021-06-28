/* 추가 */

var dateResult;

$(document).ready(function(){
    $.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년',

    });

    /*

    $( "#datepicker" ).multiDatesPicker();
    */

    $( "#datepicker-1" ).datepicker({
        onSelect: function (dateText, inst) {
            dateResult = dateText;

            $("#datepicker-2").datepicker( "option", "minDate", dateText );
        }
    });

    $( "#datepicker-2" ).datepicker({
        onSelect: function (dateText, inst) {
            dateResult = dateText;

            $("#datepicker-1").datepicker( "option", "maxDate", dateText );

        }
    });


});


/* 데이터 값 전달 */

function dateSave(obj){
    if(!dateResult){
        alert("날짜를 선택해주세요");
    }else{
        $(obj).val(dateResult);
    }

}
