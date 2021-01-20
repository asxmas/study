jQuery(function($) {

    const appendPoint = function(data){
        var pointCode = '<a href="#" class="point-link" data-id="' +
            data.id + '">' + data.name + '</a><br>';
        $('#point-list')
            .append('<div>' + pointCode + '</div>');
    };

    Loading points on load page
    $.get('/points/', function(response)
    {
        for(i in response) {
            appendPoint(response[i]);
        }
    });

    //Show adding point form
    $('#show-add-point-form').click(function(){
        $('#point-form').css('display', 'flex');
    });

    //Closing adding point form
    $('#point-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting point
    $(document).on('click', '.point-link', function(){
        var link = $(this);
        var pointId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/points/' + pointId,
            success: function(response)
            {
                var code = '<span>Описание:' + response.description + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Задача не найдена!');
                }
            }
        });
        return false;
    });

    //Adding point
    $('#save-point').click(function()
    {
        var data = $('#point-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/points/',
            data: data,
            success: function(response)
            {
                $('#point-form').css('display', 'none');
                var point = {};
                point.id = response;
                var dataArray = $('#point-form form').serializeArray();
                for(i in dataArray) {
                    point[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendPoint(point);
            }
        });
        return false;
    });

});