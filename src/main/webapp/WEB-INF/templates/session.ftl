<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Сеанс фильма "${session.film.title}"</title>
</head>
<body>
<h2>${session.film.title}</h2>
<div>
    <img src="/images/${session.film.poster.id}" width="400" height="400">
    <h2>Сеанс в ${session.date}</h2>
    <h2>Зал №${session.hall.id}</h2>

    <h2>Количество мест: ${session.hall.numberOfSeats}</h2>
    <h2>Стоимость билета: ${session.ticketCost}</h2>
    <h2><a href="/films/${session.film.id}/chat">Чат</a></h2>
</div>
<br><br>
</body>
</html>