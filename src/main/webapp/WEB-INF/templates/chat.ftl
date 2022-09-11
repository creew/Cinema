<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat </title>
    <style>
        html {
            height: 100%;
        }

        body {
            height: 100%;
        }
        /* Chat containers */
        .container {
            border: 2px solid #dedede;
            background-color: #f1f1f1;
            border-radius: 5px;
            padding: 10px;
            margin: 10px 0;
        }

        /* Darker chat container */
        .darker {
            border-color: #ccc;
            background-color: #ddd;
        }

        /* Clear floats */
        .container::after {
            content: "";
            clear: both;
            display: table;
        }

        /* Style images */
        .container img {
            float: left;
            max-width: 60px;
            width: 100%;
            margin-right: 20px;
            border-radius: 50%;
        }

        /* Style the right image */
        .container img.right {
            float: right;
            margin-left: 20px;
            margin-right: 0;
        }

        /* Style time text */
        .time-right {
            float: right;
            color: #aaa;
        }

        /* Style time text */
        .time-left {
            float: left;
            color: #999;
        }

        .chat {
            float: left;
        }

        .chat_history {
            width:70%;
            height: 95%;
            overflow-y: auto;
        }

        .logins {
            width:15%;
            height: 95%;
        }

        #message {
            width: 100%; /* Full-width */
            font-size: 16px; /* Increase font-size */
            padding: 12px 12px 12px 12px; /* Add some padding */
            border: 1px solid #ddd; /* Add a grey border */
            margin-bottom: 5px; /* Add some space below the input */
        }
    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
</head>
<body>
<div class="chat chat_history" id="chat_window">
    <input type="file" id="imgupload" accept="image/*" style="display:none"/>
    <#list messages as message>
        <div ${(message.author.id == user.id) ? then('class="container darker"', 'class="container"')}>
            <img src="/images/${message.author.avatar.id}"
                 alt="Avatar" ${(message.author.id == user.id) ? then('class="right uploadAvatar"', '')}>
            <h2>user-${message.author.id}:</h2><p>${message.message}</p>
            <span class="time-right">${message.created}</span>
        </div>
    </#list>
</div>
<div class="chat logins">
    <div class="message">
        <input type="text" id="message" placeholder="Message...">
    </div>
    <h2>История логинов:</h2>
    <table border="1">
        <tr>
            <th>Время логина</th>
            <th>Ip</th>
        </tr>
        <#list logins as login>
            <tr>
                <td>${login.loginTime}</td>
                <td>${login.ip}</td>
            </tr>
        </#list>
    </table>
</div>

<script>
    $( document ).ready(function() {
        const url = "ws://" + location.hostname + ":8080/chat";
        const client = Stomp.client(url);
        client.connect({}, (value) => {

            client.subscribe(`/topic/films/${filmId}/chat/messages`, (v) => {
                const message = JSON.parse(v.body)
                const row$ = $('<div/>').attr("class", message.author.id===${user.id}?'container darker':'container')
                row$.append($('<img/>').attr('src', '/images/' + message.author.avatar.id)
                    .attr('alt', 'Avatar').attr('class', message.author.id===${user.id}?'right uploadAvatar':''))
                row$.append($('<h2/>').html("user-" + message.author.id + ":"))
                row$.append($('<p/>').html(message.message))
                row$.append($('<span/>').attr('class', 'time-right').html(message.created))
                $("#chat_window").append(row$)
                scrollDown()
            })
        })
        const input = document.getElementById("message");
        input.addEventListener("keypress", function (event) {
            if (event.key === "Enter") {
                event.preventDefault();
                client.send('/app/films/${filmId}/chat/messages', {"sessionId": "${user.sessionId}"}, JSON.stringify({"message": input.value}))
                input.value = ''
            }
        });
        $(document).on('click', '.uploadAvatar', function(){
            $('#imgupload').click()
        });
        $('#imgupload').change(function () {
            const files = $('#imgupload').prop('files')
            if (files.length === 1) {
                const formData = new FormData();
                formData.append('file', files[0]);
                $.ajax({
                    url : `/avatar/${user.id}`,
                    type : 'POST',
                    data : formData,
                    contentType: false,
                    processData: false,
                });
            }
        })
        $(document).ajaxStop(function(){
            window.location.reload();
        });
        function scrollDown() {
            const selector = $('.chat_history')
            selector.scrollTop(selector.height()*selector.height());
        }
        scrollDown()
    });
</script>
</body>
</html>
