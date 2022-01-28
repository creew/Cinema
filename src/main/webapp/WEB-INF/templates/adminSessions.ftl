<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Films List</title>
</head>
<body>
<h3>Films List</h3>
<form id="add_session" action="sessions/add" method="post">
    <p><label for="hall"><b>Зал</b></label>
    <select id="hall" name="hall">
        <#list halls as hall>
            <option value="${hall.id}">${hall.id}</option>
        </#list>
    </select></p>
    <p><label for="film"><b>Фильм</b></label>
    <select id="film" name="film">
        <#list films as film>
            <option value="${film.id}">${film.title}, ${film.yearOfRelease}</option>
        </#list>
    </select></p>
    <label for="date"><b>Дата</b></label>
    <input id="date" type="datetime-local" name="date"><br/>
    <label for="cost"><b>Стоимость</b></label>
    <input id="cost" type="number" name="cost"><br/>
    <button type="submit">Добавить</button>
</form>
<br><br>
<div>
    <table border="1">
        <tr>
            <th>Зал</th>
            <th>Фильм</th>
            <th>Дата</th>
            <th>Стоимость</th>
        </tr>
        <#list sessions as session>
            <tr>
                <td>${session.hall.id}</td>
                <td>${session.film.title}</td>
                <td>${session.date}</td>
                <td>${session.ticketCost}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>