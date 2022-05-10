<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat </title>
    <style>
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
            margin-left: 20%;
            margin-right: 20%;
        }

        #message {
            width: 100%; /* Full-width */
            font-size: 16px; /* Increase font-size */
            padding: 12px 12px 12px 12px; /* Add some padding */
            border: 1px solid #ddd; /* Add a grey border */
            margin-bottom: 12px; /* Add some space below the input */
        }
    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
</head>
<body>
<div class="chat" id="chat_window">
    <#list messages as message>
        <div ${(message.author.id == user.id) ? then('class="container darker"', 'class="container"')}>
            <img src="https://www.w3schools.com/w3images/bandmember.jpg"
                 alt="Avatar" ${(message.author.id == user.id) ? then('class="right"', '')}>
            <p>${message.message}</p>
            <span class="time-right">${message.created}</span>
        </div>
    </#list>
</div>
<div class="chat">
    <input type="text" id="message" placeholder="Search for films..">
</div>

<script>
    const url = "ws://localhost:8080/chat";
    const client = Stomp.client(url);
    client.connect({}, (value) => {
        client.subscribe(`/topic/films/${filmId}/chat/messages`, (v) => {
            const message = JSON.parse(v.body)
            const row$ = $('<div/>').attr("class", message.author.id==='${user.id}'?'container darker':'container')
            row$.append($('<img/>').attr('src', 'https://www.w3schools.com/w3images/bandmember.jpg')
                .attr('alt', 'Avatar').attr('class', message.author.id==='${user.id}'?'right':''))
            row$.append($('<p/>').html(message.message))
            row$.append($('<span/>').attr('class', 'time-right').html(message.created))
            $("#chat_window").append(row$)
        })
    })
    const input = document.getElementById("message");
    input.addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            event.preventDefault();
            client.send('/app/films/${filmId}/chat/messages', {"userId": "${user.id}"}, JSON.stringify({"message": input.value}))
            input.value = ''
        }
    });
</script>
</body>
</html>
