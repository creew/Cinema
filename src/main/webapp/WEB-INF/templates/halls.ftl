<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Halls List</title>
</head>
<body>
<h3>Halls List</h3>
<form action="halls/add" method="post">
    <label for="id"><b>Номер зала</b></label>
    <input id="id" type="text" name="id">
    <label for="seats"><b>Количество сидячих мест</b></label>
    <input id="seats" type="text" name="seats">
    <button type="submit">Добавить</button>
</form>
<br><br>
<div>
    <table border="1">
        <tr>
            <th>Hall id</th>
            <th>Number of seats</th>
        </tr>
        <#list halls as hall>
            <tr>
                <td>${hall.id}</td>
                <td>${hall.numberOfSeats}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>