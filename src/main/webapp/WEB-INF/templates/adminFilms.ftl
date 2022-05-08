<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Films List</title>
</head>
<body>
<h3>Films List</h3>
<form action="films" method="post" enctype="multipart/form-data">
    <label for="title"><b>Наименование</b></label>
    <input id="title" type="text" name="title"><br/>
    <label for="year_of_release"><b>Год выпуска</b></label>
    <input id="year_of_release" type="number" name="year_of_release"><br/>
    <label for="restrictions"><b>Ограничения</b></label>
    <input id="restrictions" type="text" name="restrictions"><br/>
    <label for="description"><b>Описание</b></label>
    <input id="description" type="text" name="description"><br/>
    <label for="poster"><b>Постер</b></label>
    <input id="poster" type="file" name="poster"><br/>
    <button type="submit">Добавить</button>
</form>
<br><br>
<div>
    <table border="1">
        <tr>
            <th>Наименование</th>
            <th>Год выпуска</th>
            <th>Ограничения</th>
            <th>Описание</th>
            <th>Постер</th>
        </tr>
        <#list films as film>
            <tr>
                <td>${film.title}</td>
                <td>${film.yearOfRelease}</td>
                <td>${film.restrictions}</td>
                <td>${film.description}</td>
                <td>${film.poster}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>