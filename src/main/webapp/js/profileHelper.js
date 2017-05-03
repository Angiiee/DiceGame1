$(document).ready(function(){

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#iavatar').attr('src', e.target.result);
            };

            reader.readAsDataURL(input.files[0]);
        }
    };
        

    $("#lavatar").change(function(){
        readURL(this);
    });

    $(".labelShowProfile").click(function(){
        $(".profile").show();
        $(".statisticsWrap").hide();
        $(".creditAccountWrap").hide();
    });

    $(".labelShowStastistics").click(function(){
        $(".profile").hide();
        $(".statisticsWrap").show();
        $(".creditAccountWrap").hide();
    });

    $(".labelShowCreditCardAccount").click(function(){
        $(".profile").hide();
        $(".statisticsWrap").hide();
        $(".creditAccountWrap").show();
    });

});