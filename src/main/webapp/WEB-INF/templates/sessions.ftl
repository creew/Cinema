<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Films List</title>
    <style>
        #live_search {
            background-image: url('/css/searchicon.png'); /* Add a search icon to input */
            background-position: 10px 12px; /* Position the search icon */
            background-repeat: no-repeat; /* Do not repeat the icon image */
            width: 100%; /* Full-width */
            font-size: 16px; /* Increase font-size */
            padding: 12px 20px 12px 40px; /* Add some padding */
            border: 1px solid #ddd; /* Add a grey border */
            margin-bottom: 12px; /* Add some space below the input */
        }
    </style>
</head>
<body>
<input type="text" id="live_search" placeholder="Search for films..">
<br/>
<div id="search_result">
    <table id=sessions border="1"></table>
</div>
<script
        src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous">
</script>
<script type="text/javascript">
    $(document).ready(function () {
        update('')
        $("#live_search").keyup(function (){
            const input = $(this).val();
            update(input)
        })
    })
    function update(val) {
        $.ajax({
            url:"/sessions/search",
            method:"GET",
            data:{ filmName: val},
            success:function (data) {
                $("#sessions tr").remove()
                for (const i in data) {
                    const row$ = $('<tr/>');
                    row$.append($('<td/>').html(data[i].film.poster));
                    row$.append($('<td/>').html(data[i].date));
                    row$.append($('<td/>').append($('<a>').attr("href", "/sessions/" + data[i].id).html(data[i].film.title)));
                    $("#sessions").append(row$)
                }
            }
        })
    }
</script>
</body>
</html>