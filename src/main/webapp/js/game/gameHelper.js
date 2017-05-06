$(document).ready(function () {

    $('#multiuserType').click(function () {
        $('#multiuserTypeOption').addClass('active');
    });

    $('#serverType').click(function () {
        $('#multiuserTypeOption').removeClass('active');
    });



});

function sendAjax() {

    // get inputs
    var gameRequest = new Object();
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
            // var gameResponse = new Object();
            // var obj = JSON.parse(data);
            
            var userConsignment = data.consignment;
            console.log("mess "+ userConsignment.length +"     "+ userConsignment[0].length);
            // $("tr:has(td)").remove();

            // $.each(data, function (gameResponse) {
                // $("#resultString").append(gameResponse.userMaxScore);
                // var gameResponse = data;
            //
            //     var td_categories = $("<td/>");
            //     $.each(article.categories, function (i, tag) {
            //         var span = $("<span class='label label-info' style='margin:4px;padding:4px' />");
            //         span.text(tag);
            //         td_categories.append(span);
            //     });
            //
            //     var td_tags = $("<td/>");
            //     $.each(article.tags, function (i, tag) {
            //         var span = $("<span class='label' style='margin:4px;padding:4px' />");
            //         span.text(tag);
            //         td_tags.append(span);
            //     });
            //
            //     $("#added-articles").append($('<tr/>')
            //         .append($('<td/>').html("<a href='"+article.url+"'>"+article.title+"</a>"))
            //         .append(td_categories)
            //         .append(td_tags)
            //     );
            //
            // });

            // $("#resultString").val(data);
            // alert("data" + data.toString());

        },
        error:function(status) {
            // var code = status;
            // console.log("error status: " + code.toString());
        }
    });
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
            $.each(data, function (key,item) {
                trHTML +=
                    '<tr><td>' + item.gameId +
                    '</td><td>' + item.userLogin +
                    '</td><td>' + item.rate +
                    '</td><td>' + '<label for="chooseGameOptionRadio">Join!</label><input type="radio" class="chooseGameId" name="chooseGameOptionRadio" value="' + item.gameId + '">' +
                    '</td></tr>';
            });

            $('.possibleGame tbody').append(trHTML);

        },
        error:function(status) {
            // var code = status;
            // console.log("error status: " + code.toString());
        }
    });
}