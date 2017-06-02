// $(document).ready(function () {
//
//     $('#multiuserType').click(function () {
//         $('#multiuserTypeOption').addClass('active');
//     });
//
//     $('#serverType').click(function () {
//         $('#multiuserTypeOption').removeClass('active');
//     });
//
//     $('#gameAction').css("display","none");
//
//     doRoll = function (throwArray) {
//         var i,
//             faceValue,
//             output = '';
//         for (i = 0; i < throwArray.length; i++) {
//             faceValue = throwArray[i] - 1;
//             output += "&#x268" + faceValue + "; ";
//         }
//         $('#dice').html(output);
//         //alert(throwArray.length);
//     }
//
// });
//
// function sendAjax() {
//     var gameRequest = {};
//     gameRequest.requestType = "GAME";
//     gameRequest.gameType = $('input[name=gameTypeRadio]:checked', '#selectGameType').val();
//     gameRequest.gameId = $('input[name=chooseGameOptionRadio]:checked', '.possibleGame').val();
//     gameRequest.multiuserGameType = $('input[name=multiuserGameOptionRadio]:checked', '#multiuserTypeOption').val();
//     gameRequest.rate = $('#inputRate').val();
//     console.log("OK");
//
//     $.ajax({
//         url: "/game",
//         type: 'POST',
//         dataType: 'json',
//         data: JSON.stringify(gameRequest),
//
//         success: function (data) {
//             console.log(data);
//             userConsignment = data.consignment;
//             var k = 0;
//             $('#dice').css("font-size","150px");
//             $('#gameAction').css("display","block");
//             $(document).on('click', '#rollButton', function () {
//                 if (k < userConsignment.length) {
//                     doRoll(userConsignment[k]);
//                     k++;
//                 }
//                 if (k == userConsignment.length) {
//                     $('#gameAction').css("display", "none");
//                     $('#gameResult').html("<label><fmt:message key='win'/></label>");
//                 }
//             });
//
//         },
//         error: function (status) {
//             // var code = status;
//             // console.log("error status: " + code.toString());
//         }
//     });
// }
//
//
// function getPossibleGames() {
//
//     var gameRequest = new Object();
//     gameRequest.requestType = "SHOWPOSSIBLE";
//
//     $.ajax({
//         url: "/game",
//         type: 'POST',
//         dataType: 'json',
//         data: JSON.stringify(gameRequest),
//         // contentType: 'application/json',
//         // mimeType: 'application/json',
//
//         success: function (data) {
//             $('#tbodyid').empty();
//             console.log(data);
//             var trHTML = '';
//             $.each(data, function (key, item) {
//                 trHTML +=
//                     '<tr><td>' + item.gameId +
//                     '</td><td>' + item.userLogin +
//                     '</td><td>' + item.rate +
//                     '</td><td>' + '<label for="chooseGameOptionRadio">Join!</label><input type="radio" class="chooseGameId" name="chooseGameOptionRadio" value="' + item.gameId + '">' +
//                     '</td></tr>';
//             });
//
//             $('.possibleGame tbody').append(trHTML);
//
//         },
//         error: function (status) {
//             // var code = status;
//             // console.log("error status: " + code.toString());
//         }
//     });
// }
$(document).ready(function () {

    $('#multiuserType').click(function () {
        $('#multiuserTypeOption').addClass('active');
        $('#rateOption').removeClass('active');
    });

    $('#serverType').click(function () {
        $('#multiuserTypeOption').removeClass('active');
        $('.container').removeClass('active');
        $('#rateOption').addClass('active');
    });

    $('#existingMultiuserGame').click(function () {
        $('.container').addClass('active');
        $('#rateOption').removeClass('active');
    });

    $('#newMultiuserGame').click(function () {
        $('.container').removeClass('active');
        $('#rateOption').addClass('active');
    });

    $('#gameAction').css("display","none");

    doRoll = function (throwArray) {
        var i,
            faceValue,
            output = '';
        for (i = 0; i < throwArray.length; i++) {
            faceValue = throwArray[i] - 1;
            output += "&#x268" + faceValue + "; ";
        }
        $('#dice').html(output);
        //alert(throwArray.length);
    }

});

function sendAjax() {
    var gameRequest = {};
    gameRequest.requestType = "GAME";
    gameRequest.gameType = $('input[name=gameTypeRadio]:checked', '#selectGameType').val();
    gameRequest.gameId = $('input[name=chooseGameOptionRadio]:checked', '.possibleGame').val();
    gameRequest.multiuserGameType = $('input[name=multiuserGameOptionRadio]:checked', '#multiuserTypeOption').val();
    gameRequest.rate = $('#inputRate').val();


    $.ajax({
        url: "/game",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(gameRequest),

        success: function (data) {
            console.log(data);
            userConsignment = data.consignment;
            var k = 0;
            $('#dice').css("font-size","150px");
            $('#gameAction').css("display","block");
            $(document).on('click', '#rollButton', function () {
                if (k < userConsignment.length) {
                    doRoll(userConsignment[k]);
                    k++;
                }
                if (k == userConsignment.length) {
                    $('#gameAction').css("display", "none");
                    $('#gameResult').html("<label><fmt:message key='win'/></label>");
                }
            });

        },
        error: function (status) {
            // var code = status;
            // console.log("error status: " + code.toString());
        }
    });

    jQuery('<div class="quantity-nav"><div class="quantity-button quantity-up">+</div><div class="quantity-button quantity-down">-</div></div>').insertAfter('.quantity input');
    jQuery('.quantity').each(function() {
        var spinner = jQuery(this),
            input = spinner.find('input[type="number"]'),
            btnUp = spinner.find('.quantity-up'),
            btnDown = spinner.find('.quantity-down'),
            min = input.attr('min'),
            max = input.attr('max');

        btnUp.click(function() {
            var oldValue = parseFloat(input.val());
            if (oldValue >= max) {
                var newVal = oldValue;
            } else {
                var newVal = roundToTwo(oldValue + 0.01);
            }
            spinner.find("input").val(newVal);
            spinner.find("input").trigger("change");
        });

        btnDown.click(function() {
            var oldValue = parseFloat(input.val());
            if (oldValue <= min) {
                var newVal = oldValue;
            } else {
                var newVal = roundToTwo(oldValue - 0.01);
            }
            spinner.find("input").val(newVal);
            spinner.find("input").trigger("change");
        });
    });
    function roundToTwo(num) {
        return +(Math.round(num + "e+2")  + "e-2");
    }


}


function getPossibleGames() {

    var gameRequest = new Object();
    gameRequest.requestType = "SHOWPOSSIBLE";

    $.ajax({
        url: "/game",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(gameRequest),
        // contentType: 'application/json',
        // mimeType: 'application/json',

        success: function (data) {
            $('#tbodyid').empty();
            console.log(data);
            var trHTML = '';
            $.each(data, function (key, item) {
                trHTML +=
                    '<tr><td>' + item.gameId +
                    '</td><td>' + item.userLogin +
                    '</td><td>' + item.rate +
                    '</td><td>' + '<label for="chooseGameOptionRadio">Join!</label><input type="radio" class="chooseGameId" name="chooseGameOptionRadio" value=" ' + item.gameId + '">' +
                    '</td></tr>';
            });

            $('.possibleGame tbody').append(trHTML);

        },
        error: function (status) {
            // var code = status;
            // console.log("error status: " + code.toString());
        }
    });
}